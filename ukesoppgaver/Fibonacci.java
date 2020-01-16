class Fibonacci{
  public Fibonacci(){}
  public static void main(String[] args) {
    Fibonacci fibo = new Fibonacci();
    fibo.fib(10);
  }
  public int fib(int n){
    if(n <= 1){
      return 1;
    }
    return fib(n-1) + fib(n-2);
  }
}
