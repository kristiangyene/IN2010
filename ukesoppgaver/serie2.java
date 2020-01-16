/*Exercise 4 (Height of a binary tree)
1. Show that the maximum number of nodes in a binary tree of height h is 2h+1 − 1
2. Show that for a balanced tree with N nodes, the height is blog2(N)c
Solution:
1. This can be proved by induction.
Base step h = 0: 20+1 − 1 = 2 − 1 = 2
Induction step: Assume that a tree with height k has maximum 2k+1 − 1 nodes.
Prove that a tree with height k + 1 has maximum 2(k+1)+1 − 1 = 2k+2 − 1 nodes.
Proof: For a tree with height k + 1 we have:
• 1 root node
• a left subtree with maximum height k, having maximum 2k+1−1 nodes according
to the induction hypothesis
• a right subtree with maximum height k, having maximum 2k+1 − 1 nodes according
to the induction hypothesis
This gives a total of (2k+1 − 1) + (2k+1 − 1) + 1 = (2k+1 + 2k+1) − 1 = 2 ∗ 2
k+1 − 1 =
2
k+2 − 1 nodes.
2. Proved by manipulation of the result from the previous exercise: N = 2h+1 − 1 =⇒
2
h+1 = N + 1 =⇒ log2
(2h+1) = log2
(N + 1) =⇒ h + 1 = log2
(N + 1) =⇒ h =
log2
(N + 1) − 1 =⇒ h = blog2
(N)c*/
Node findSmallest(Node node){
  if node dont exists{
    return null;
  }
  if its a node smaller than node{
    return node to the left of node;
  }
  return findSmallest(root);
}
