public class Tree{
  class BinNode{
    int data;
    BinNode left;
    BinNode right;

  }
  public int number(BinNode t){
    if(t == null){
      return 0;
    }
    else{
      return 1 + number(t.left) + number(t.right);
    }
  }
  public int sum(BinNode t){
    int totalt;
    int leftSum;
    int rightSum;
    if(t == null){
      totalt = 0;
      return totalt;
    }
    else{
      leftSum = sum(t.left);
      rightSum = sum(t.right);
    }
    totalt = t.data + leftSum + rightSum;
    return totalt;
  }
}
