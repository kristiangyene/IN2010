class BinaryTree{
  BinNode root = null;
  int sum = root.sum();
  int number = root.number();

  class BinNode{
    int data;
    BinNode left;
    BinNode right;

    private int sum(){
      return data + left.sum() + right.sum();
    }


    private int number(){
      return 1 + left.sum() + right.sum();
    }
  }

  //Finn sum av verdiene til nodene.
  public int sum(BinNode root){
    if (root == null)
      return 0;
    return (root.data + sum(root.left) + sum(root.right));
  }

  //Finn antall noder.
  public int number(BinNode root){
    if(root == null){
      return 0;
    }
    return 1 + number(root.left) + number(root.right);
  }
}
