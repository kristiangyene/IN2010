//Finn sum av verdiene til nodene.
static int sum(Node root){
  if (root == null)
    return 0;
  return (root.data + sum(root.left) + sum(root.right));
}

//Finn antall noder.
private int number(Node n){
  if(n == null){
    return 0;
  }
  return 1 + number(n.left) + number(n.right);
}

//Finn dybde til hver node.
public void updateDepth(Node node, int depth)
{
    if (node != null)
    {
        node.depth = depth;
        updateDepth(node.left, depth + 1); // left sub-tree
        updateDepth(node.right, depth + 1); // right sub-tree
    }
}

//Finn hoyde til hver node.
public int getNodeHeight(Node root, Node x, int height){
		if(root==null){
      return 0;
    }
		if(root==x){
      return height;
    }
		int level = getNodeHeight(root.left,x,height+1);
		if(level!=0){
      return level;
    }
    return getNodeHeight(root.right,x,height+1);

//Finn hoyde og dybde for hver node:
public void updateDepthHeight(Node node, int depth, int height){
  if(node != null){
    node.depth = depth;
    node.height = height;
    updateDepthHeight(node.left, depth + 1, height - 1);
    updateDepthHeight(node.right, depth + 1, height - 1);
  }
}

//Finn maks hoyde til treet.
public int findHeight(Node node) {
    if (node == null){
        return -1;
    }
    int leftHeight = findHeight(node.left);
    int rightHeight = findHeight(node.right);

    if (leftHeight > rightHeight) {
        return leftHeight + 1;
    } else {
        return rightHeight + 1;
    }
}
