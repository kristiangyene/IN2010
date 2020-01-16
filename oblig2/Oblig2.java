import java.util.*;
import java.io.*;

//Project planner.
public class Oblig2{
  public final Map<Integer, Task> tasks;
  public List<Task> sorted;
  static int maxnr;
  static int maxTime;
  public List<Integer> cycle = null;
  public List<Integer> allTime;
  public File file;
  public FileWriter filewriter;
  public PrintWriter printwriter;

  public static void main(String[] args) {
    Oblig2 pp = new Oblig2(new File(args[0]));
    pp.printExecution();
    pp.printInfo();
  }

  public Oblig2(File file){
    this.file = file;
    try{
    filewriter = new FileWriter("output2.txt");
    printwriter = new PrintWriter(filewriter);
    }catch(IOException f){
    System.out.println("IO exception.");
    }
    tasks = fromFile(file);
    outEdges(tasks);
    sorted = topSort();
    cycle = checkForCycles(tasks.get(1));
    if(isCyclic()){
      printwriter.println("Listing : " + file);
      printwriter.println("\nCYCLE FOUND: " + cycle + "\n");
      System.out.println("CYCLE FOUND: " + cycle);
      printInfo();
      printwriter.close();
      System.exit(0);
    }
    findEsEf(findStartTasks(tasks));
    maxTime = maxTime();
    findLsLf(findEndTasks(tasks));
    setSlack();
    allTime = new ArrayList<Integer>();
    for(Task t: tasks.values()){
      allTime.add(t.earliestStart);
      allTime.add(t.earliestFinish);
    }
  }

  public static class Task{
    int id, time, staff;
    String name;
    int earliestStart, earliestFinish, critical;
    int latestStart, latestFinish;
    List<Task> outEdges;
    List<Integer> idOutedges;
    List<Integer> dependencies;
    int cntPredecessors = 0;
    Status status;
    int slack;
    boolean crit;
    public enum Status{UNVISITED, RUNNING, VISITED};

    public Task(int id, String name, int time, int staff){
      this.id = id;
      this.name = name;
      this.time = time;
      this.staff = staff;
      dependencies = new ArrayList<Integer>();
      outEdges = new ArrayList<Task>();
      idOutedges = new ArrayList<Integer>();
      status = Status.UNVISITED;
    }
  }

    public void outEdges(Map<Integer, Task> map){
      //Setter utkanter. Time O(n^2)
      for(Integer id: map.keySet()){
        for(Integer dep: map.get(id).dependencies){
          map.get(dep).idOutedges.add(id);
          map.get(dep).outEdges.add(map.get(id));
        }
      }
    }

    public int maxTime(){
      //Finner maks tid på utfoerelsen av prosjektet. Time O(n^2)
      int time = 0;
      for(Task task: tasks.values()){
        time = task.earliestFinish;
        for(Task t: task.outEdges){
          if(t.earliestFinish > time){
            time = t.earliestStart;
          }
        }
      }
      return time;
    }

    public Boolean isCyclic(){
      //Time O(n)
      if(cycle != null){
        return true;
      }
      return false;
    }

    public List<Integer> checkForCycles(Task task){
      //Dybde-foerst soek som sjekker om grafen har loekker.
      //Bruker enum for aa holde styr paa tilstander. Time O(|V|+|E|)
      if(task.status == Task.Status.RUNNING){
        //Funnet loekke.
        this.cycle = new ArrayList<Integer>();
        for(Task t: tasks.values()){
          if(t.status == Task.Status.RUNNING){
            cycle.add(t.id);
          }
        }
        cycle.add(task.id);
      }
      else if(task.status == Task.Status.UNVISITED){
        //Merker at de er sett.
        task.status = Task.Status.RUNNING;
        for(Task t: task.outEdges){
          checkForCycles(t);
        }
        task.status = Task.Status.VISITED;
      }
      return cycle;
    }

    public List<Task> topSort(){
      //Topologisk sortering av tasks for aa faa riktig rekkefoelge i printExecution.
      //Time O(|V|+|E|)
      Stack<Task> stack = new Stack<Task>();
      List<Task> sorted = new ArrayList<Task>();
      Task task;
      int counter = 0;
      for(Task t: tasks.values()){
        if(t.cntPredecessors == 0){
          stack.push(t);
          task = t;
          sorted.add(task);
        }
      }
      while(!stack.empty()){
        //fjerner og henter task fra stack.
        task = stack.pop();
        counter++;
        for(Task t: task.outEdges){
          t.cntPredecessors--;
          if(t.cntPredecessors == 0){
            stack.push(t);
            sorted.add(t);
          }
        }
      }
      if(counter < maxnr){
        return null;
      }
      return sorted;
    }

    public void findEsEf(List<Task> startTasks){
      //Starter med starttasks og setter standardverdier. Kaller på setEsEf()
      //paa de. Time O(n)
      for(Task task: startTasks){
        task.earliestStart = 0;
        task.earliestFinish = task.time;
        setEsEf(task);
      }
    }

    public void setEsEf(Task task){
      //Metoden traverserer rekursivt fra roots til leafs og setter de raskeste
      //tidene. Time O(|V|+|E|)
      int doneTime = task.earliestFinish;
      for(Task t: task.outEdges){
        if(doneTime >= t.earliestStart){
        t.earliestStart = doneTime;
        t.earliestFinish = doneTime + t.time;
        }
        setEsEf(t);
      }
    }

    public void findLsLf(List<Task> endTasks){
      //Gjør som findEsEf, men bare motsatt vei. Starter med sluttasks og
      //setter standardverdier. Kaller paa setLsLf() paa de. Time O(n)
      for(Task task: endTasks){
        task.latestFinish = maxTime;
        task.latestStart = task.latestFinish - task.time;
        setLsLf(task);
      }
    }

    public void setLsLf(Task task){
      //Gjør det motsatte av setEsEf(), og finner de seneste tidene. Time O(|V|+|E|)
      int doneTime = task.latestStart;
      for(int i: task.dependencies){
        Task t = tasks.get(i);
        t.latestFinish = doneTime;
        t.latestStart = t.latestFinish - t.time;
        setLsLf(t);
      }
      //Setter alle start- og sluttider for det samme.
      for(Task t: findStartTasks(tasks)){
        t.latestStart = t.earliestStart;
        t.latestFinish = t.earliestFinish;
      }
    }

    public void setSlack(){
      //Bruker utregningene til Ls og Es til aa finne slack. Time O(n)
      for(Task task: tasks.values()){
        task.slack = task.latestStart - task.earliestStart;
      }
    }

    public List<Task> criticalPath(List<Task> sorted){
      //Bruker utregningene av slack til å finne criticalPath. Time O(n)
      List<Task> critical = new ArrayList<Task>();
      for(Task task: sorted){
        if(task.slack == 0){
          task.crit = true;
          critical.add(task);
        }
      }
      //Returnerer liste med criticalPath(critical tasks).
      return critical;
    }

    public List<Task> findEndTasks(Map<Integer, Task> allTasks){
      //Finner tasks uten utkanter (Leafs). Time O(n^2)
      List<Task> remaining = new ArrayList<Task>();
      for(Task t: allTasks.values()){
        remaining.add(t);
      }
      for(Task t: allTasks.values()){
        for(int prev: t.dependencies){
          remaining.remove(allTasks.get(prev));
        }
      }
      return remaining;
    }

    public List<Task> findStartTasks(Map<Integer, Task> allTasks){
      //Finner tasks som ikke har dependencies (Roots). Time O(n^2)
      List<Task> remaining = new ArrayList<Task>();
      for(Task t: allTasks.values()){
        remaining.add(t);
      }
      for(Task t: allTasks.values()){
        for(Task next: t.outEdges){
          remaining.remove(next);
        }
      }
      return remaining;
    }

    public Map<Integer, Task> fromFile(File file){
      //Leser fra fil og setter alle verdier til hver task dersom fil eksisterer.
      //Time O(n)
      try{
      Map<Integer, Task> map = new HashMap<Integer, Task>();
      Scanner scan = new Scanner(file);
      maxnr = scan.nextInt();
      while(scan.hasNextLine()){
        String line = scan.nextLine();
        if(line.length() > 0){
          Scanner scanLine = new Scanner(line);
          Task task = new Task(scanLine.nextInt(), scanLine.next(), scanLine.nextInt(), scanLine.nextInt());
          while(scanLine.hasNextInt()){
            int dep = scanLine.nextInt();
            if(dep != 0){
              task.dependencies.add(dep);
              task.cntPredecessors+=1;
            }
          }
          map.put(task.id, task);
        }
      }
      return map;
      }
      catch(FileNotFoundException e){
        System.out.println("File doesn't exist.");
        return null;
      }
    }

    public void printExecution(){
      //Printer ut simulering av gjennomfoeringen av prosjektet til fil. Time O(n)
      int time = 0; //Bruker tid og loekker.
      int manpower = 0;
      printwriter.println("Listing : " + file);
      while(time <= maxTime){
      if(allTime.contains(time)){
        printwriter.println("Time: " + time);
      for(Task task: sorted){
        if(time == task.earliestStart){
          manpower += task.staff;
          printwriter.println("\t\t\t\tStarting: " + task.id);
        }
        if(time == task.earliestFinish){
          manpower -= task.staff;
          printwriter.println("\t\t\t\tFinished: " + task.id);
        }
      }
      printwriter.println("   Current staff: " + manpower + "\n");
      }
      time++;
        }
      printwriter.println("**** Shortest possible project execution is " + maxTime + " ****\n");
    }

    public void printInfo(){
      //Printer krav av informasjon om alle tasks til fil(output.txt). Time O(n)
      for(Task task: tasks.values()){
        printwriter.println("ID:" + task.id + " Name:" + task.name + "\nTime:" +
        task.time + "\nManpower:" + task.staff + "\nEs:" + task.earliestStart +
        " Ef:" + task.earliestFinish + "\nLs:" + task.latestStart + " Lf:" +
        task.latestFinish + "\nSlack:" + task.slack + "\nOut edges:" + task.idOutedges + "\n\n");
      }
        printwriter.close();
        System.out.println("Printed to file.");
    }
}
