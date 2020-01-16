import java.util.Random;
class TestKorrekthet{
  public static void main(String[] args) {
    Sort sort = new Sort();
    Random r = new Random();
    int[] random = r.ints(10, 0, 9).toArray();
    int[] sorted = new int[10];
    for(int i = 0; i < sorted.length; i++){
      sorted[i] = i;
    }
    int[] reversed = new int[10];
    for(int i = 0; i < reversed.length; i++){
      reversed[i] = i;
    }
    for (int i = 0; i < reversed.length / 2; i++) {
      int temp = reversed[i];
      reversed[i] = reversed[reversed.length - i - 1];
      reversed[reversed.length - i - 1] = temp;
    }
    System.out.println("TEST MED 10 TALL");
    System.out.println("*** Random list ***");
    sort.testPrint(sort, random);
    System.out.println("\n*** Sorted list ***");
    sort.testPrint(sort, sorted);
    System.out.println("\n*** Reversed list ***");
    sort.testPrint(sort, reversed);



    /*System.out.println("\nTEST MED 10 TALL");
    random = r.ints(10, 0, 10).toArray();
    sorted = new int[10];
    for(int i = 0; i < sorted.length; i++){
      sorted[i] = i;
    }
    reversed = new int[10];
    for(int i = 0; i < reversed.length; i++){
      reversed[i] = i;
    }
    for (int i = 0; i < reversed.length / 2; i++) {
      int temp = reversed[i];
      reversed[i] = reversed[reversed.length - i - 1];
      reversed[reversed.length - i - 1] = temp;
    }
    System.out.println("*** Random list ***");
    sort.testTime(sort, random);
    System.out.println("*** Sorted list ***");
    sort.testTime(sort, sorted);
    System.out.println("*** Reversed list ***");
    sort.testTime(sort, reversed);*/



  }
}
