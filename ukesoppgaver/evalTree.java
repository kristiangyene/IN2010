 //stack er initalized et annet sted.
public void evalTree(BinTree node){
  int numb1, numb2;
  if(node == null){
    return;
  }
  evalTree(node.left);
  evalTree(node.right);

  if(node.value.equals("+")){
    numb1 = stack.pop();
    numb2 = stack.pop();
    int result = numb1 + numb2;
  }
  else if(node.value.equals("/")){
    numb1 = stack.pop();
    numb2 = stack.pop();
    int result = numb1 / numb2;
  }
  else if(node.value.equals("*")){
    numb1 = stack.pop();
    numb2 = stack.pop();
    int result = numb1 * numb2;
  }
  else if(node.value.equals("-")){
    numb1 = stack.pop();
    numb2 = stack.pop();
    int result = numb1 - numb2;
  }
  else{
    result =Integer.parseInt(node.value);
  }
  stack.push(result);
}
