public class MyStack{
  private int maxSize;
  private int[] stackArray;
  private int top;

  public MyStack(int s){
    maxSize = s;
    stackArray = new int[maxSize];
    top = −1;
  }
  public void push(int j){
    stackArray[++ top] = j;
  }
  public long pop(){
    return stackArray[top−−];
  }
}

class BinTree{
  String value;
  BinTree left;
  BinTree right;
}
