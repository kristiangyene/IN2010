class MyTest{

public static void main(String[] args) {
  BSTree tree = new BSTree();
  int[] tall = {8, 4, 2, 1, 12, 9, 15, 5};
  System.out.println(">>>Legger til noder fra array.");
  tree.addAll(tall);
  tree.printTree(tree.root, "");
  System.out.println(">>>Treets stoerrelse: " + tree.size());
  System.out.println(">>>Fjerner value 4.");
  tree.remove(4);
  tree.printTree(tree.root, "");
  System.out.println(">>>Treets stoerrelse: " + tree.size());
  System.out.print(">>>Printer inorder: ");
  tree.printInorder(tree.root);
  System.out.print("\n>>>Printer inorder fra array(de skal vaere like): ");
  for(int i: tree.sortedArray()){
    System.out.print(i + " ");
  }
  System.out.print("\n>>>Finner noder mellom 2-10: ");
  for(int i: tree.findInRange(2, 10)){
    System.out.print(i + " ");
  }
  System.out.println("\n>>>Finner naermeste mindre enn 9: " + tree.findNearestSmallerThan(9));
  System.out.println(">>>Finner naermeste mindre enn 3: " + tree.findNearestSmallerThan(3));
  System.out.println(">>>Sjekker om 12 finnes i treet: " + tree.existsInTree(12));
  System.out.println(">>>Sjekker om 10 finnes i treet: " + tree.existsInTree(10));
  System.out.println(">>>Fjerner value 8 og 2, og legger til 3.");
  tree.remove(8);
  tree.remove(2);
  tree.add(3);
  tree.printTree(tree.root, "");
  System.out.println(">>>Treets stoerrelse: " + tree.size());
  }
}
