public class BinaryTree{
  Node root;

  private class Node{
    Node right;
    Node left;
    int value;

    Node(int value){
      this.value = value;
    }
  }
  public Node insert(Node node, int value){
    if (node == null){
      return new Node(value);
    }
    if(value <= node.value){
      node.left = insert(node.left, value);
    }
    else{
      node.right = insert(node.right, value);
    }
    return node;
  }
  public void insert(int value){
    root = insert(root, value);
  }
  public Node checkValue(Node node, int value){
    if(node == null){
      return null;
    }
    if(value == node.value){
      return node;
    }
    else if(value < node.value){
      return checkValue(node.left, value);
    }
    else{
      return checkValue(node.right, value);
    }
  }
  public boolean checkValue(int value){
    if(checkValue(root, value) != null){
      return true;
    }
    return false;
  }
  public int findSmallest(Node node){
    if(node == null){
      return 0;
    }
    if(node.left == null){
      return node.value;
    }
    return findSmallest(node.left);
  }
  public Node remove(Node node, int value){
    if(node == null){
      return null;
    }
    if(value == node.value){
      if((node.left == null) && (node.right == null)){
        return null;
      }
      else if((node.left != null) && (node.right == null)){
        return node.right;
      }
      else if((node.left == null) && (node.right != null)){
        return node.left;
      }
      else{
        node.value = findSmallest(node.right);
        node.right = remove(node.right, value);
      }
    }
    else if(value < node.value){
      remove(node.left, value);
    }
    else{
      remove(node.right, value);
    }
    return node;
  }
}
