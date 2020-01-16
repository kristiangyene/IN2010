public class BSTree implements BSTOper{
  public static Node root;
  public int size;
  //Index til array.
  public int index = 0;

  public BSTree(){
    root = null;
  }

 private class Node{
  Node left, right;
  Node parent = null;
  int value;

  public Node(){
    left = null;
    right = null;
  }

  public Node(int v){
    value = v;
    left = null;
    right = null;
    }
  }

  //Hjelpemetode som legger til node paa ledig plass.
  private Node addHelper(int value, Node n){
    if(n == null){
      this.size++;
      //legger til node nederst.
      return new Node(value);
    }
    //Gaar venstre:
    if(value <= n.value){
      n.left = addHelper(value, n.left);
      n.left.parent = n;
    }
    //Gaar hoyre:
    else{
      n.right = addHelper(value, n.right);
      n.right.parent = n;
    }
    return n;
  }

  //Legger til node fra root.
  public void add(int value){
    root = addHelper(value, root);
  }

  //Legger til alt fra array.
  public void addAll(int[] integers){
    for(int i = 0; i < integers.length; i++){
      add(integers[i]);
    }
  }

  //Hjelpemetode for aa finne node.
  private Node findHelper(int value, Node n){
    if(n == null){
      return null;
    }
    if(n.value == value){
      return n;
    }
    else if(n.value > value){
      return findHelper(value, n.left);
    }
    else{
      return findHelper(value, n.right);
    }
  }

  //Finner node.
  private Node find(int value){
    return findHelper(value, root);
  }

  //Sjekker om noden finnes i treet.
  public boolean existsInTree(int value){
    if(find(value) != null){
      return true;
    }
    else{
      return false;
    }
  }

  //Hjelpemetode for aa finne stoerrelse paa treet.
  private int counter(Node n){
    if(n == null){
      return 0;
    }
    //Legger til 1 for hver node.
    return counter(n.left) + counter(n.right) + 1;
  }

  //Finner stoerrelse fra root.
  public int size(){
    return counter(root);
  }

  /*Hjelpemetode som finner node som skal slettes og justerer treet dersom den
   har to barn.*/
  private Node removeHelper(int value, Node n){
    //Traverserer for aa finne node som skal slettes.
    if(n == null){
      return null;
    }
    if(value < n.value){
      n.left = removeHelper(value, n.left);
    }
    else if(value > n.value){
      n.right = removeHelper(value, n.right);
    }
    else{
      //Funnet node som skal slettes. Oppstaar flere muligheter.
      //Har ingen barn:
      if(n.left == null && n.right == null){
        this.size--;
        return null;
      }
      //Har ett barn:
      if(n.left != null && n.right == null){
        this.size--;
        return n.left;
      }
      if(n.left == null && n.right != null){
        this.size--;
        return n.right;
      }
      //har to barn:
      else{
        int smallestValue = smallestValue(n.right);
        n.value = smallestValue;
        n.right = removeHelper(smallestValue, n.right);
        this.size--;
        return n;
      }
    }
    return n;
  }

  //Fjerner node fra root om node finnes i treet.
  public boolean remove(int value){
    if(existsInTree(value)){
      removeHelper(value, root);
      return true;
    }
    else{
      return false;
    }
  }

  //Finner minste verdi i treet.
  private int smallestValue(Node n){
    //Gaar helt til venstre i treet.
    if(n.left == null){
      return n.value;
    }
    return smallestValue(n.left);
  }

  //Returnerer noden sin parent.
  private Node findParent(Node n){
    return n.parent;
  }

  //returnerer noden sin parent sin parent.
  private Node findGrandparent(Node n){
   return n.parent.parent;
  }

  //Print inorder for aa sjekke om arrayet er riktig.
  public void printInorder(Node n){
    if (n == null){
        return;
    }
    printInorder(n.left);
    System.out.print(n.value + " ");
    printInorder(n.right);
  }

  //Hjelpemetode for aa lage sortert array rekursivt.
  private void makeArray(Node n, int[] array){
    if(n.left != null){
      makeArray(n.left, array);
    }
    array[this.index++] = n.value;
    if(n.right != null){
      makeArray(n.right, array);
    }
  }

  //Lager sortert array fra root.
  public int[] sortedArray(){
    int[] array = new int[size()];
    this.index = 0;
    makeArray(root, array);
    return array;
  }

  //Hjelpemetode for findInRange(low, high) for aa bestemme plass i array.
  private int countRange(int low, int high){
    int counter = 0;
    int[] sorted = sortedArray();
    for(int i: sorted){
      if(i >= low && i <= high){
        counter++;
      }
    }
    return counter;
  }

  //Gaar gjennom sortert array og legger til det som er likt og mellom low/high.
  public int[] findInRange(int low, int high){
    int counter = 0;
    int[] range = new int[countRange(low, high)];
    int[] sorted = sortedArray();
    for(int i: sorted){
      if(i >= low && i <= high){
        range[counter++] = i;
      }
    }
    return range;
  }

  //Gaar igjennon alle noder i treet og finner det absolutt naermeste.
  public int findNearestSmallerThan(int value){
    Node temp = root;
    Node target = null;
    /*Alle whilelooper trengs for aa ikke faa nullpointerexception + for aa
    kunne gaa igjennom alle noder.*/
    while(temp != null){
      while(temp != null && temp.value >= value){
        temp = temp.left;
      }
      while(temp != null && temp.value < value){
        target = temp;
        temp = temp.right;
      }
    }
    return target.value;
  }

  //Printer treet paa en oversiktlig maate.
  public void printTree(Node n, String s){
        if(n == null){
            return;
        }
        printTree(n.right, s + "   ");
        System.out.println(s + n.value);
        printTree(n.left, s + "   ");
    }

}

interface BSTOper{
  public void add(int value);
  public boolean remove(int value);
  public int size();
  public boolean existsInTree(int value);
  public int findNearestSmallerThan(int value);
  public void addAll(int[] integers);
  public int[] sortedArray(); // inorder
  public int[] findInRange(int low, int high);
}
