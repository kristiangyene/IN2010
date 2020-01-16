public void DFS(Node n){
  n.visited = true;
  for(Node node: n.outEdges){
    if(!node.visited){
      DFS(node);
    }
  }
}

public void BFS(Node n){
  Queue<Node> kø = new Queue<Node>();
  kø.add(n);
  while(!kø.isEmpty()){
    Node x = kø.remove();
    x.visited = true;
    for(Node node; x.outEdges){
      if(!node.visited){
        kø.add(node);
      }
    }
  }
}

public int antallNoder(Node n){
  if(n == null){
    return 0;
  }
  return 1 + antallNoder(n.left) + antallNoder(n.right);
}

public int summenAvInnhold(Node n){
  if(n = null){
    return 0;
  }
  return n.value + summenAvInnhold(n.left) + summenAvInnhold(n.right);
}

public int gjennomsnitt(Node n){
  return summenAvInnhold(n)/antallNoder(n);
}
