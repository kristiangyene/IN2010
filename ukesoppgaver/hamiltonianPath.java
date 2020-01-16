//Finner ut om grafen inneholder en hamiltonian path eller ikke. (En sti som ikke inneholder en cycle).

boolean hamiltonianPath(Node[] graph, Node current){
  if(current.tilstand == "running"){
    return false;
  }
  else if(current.tilstand == "unseen"){
    current.tilstand = "running";
    for(Node node: current.outEdges){
      hamiltonianPath(graph, node);
    }
    current.tilstand = "seen";
  }
  return true;
}

//Finner indegrees ved hjelp av adjacency list. (outedges)
public int[] calculateIndegrees(ListNode[] L){
  for(ListNode node: L){
    
  }
}
