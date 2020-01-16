import java.util.*;

public void shortestPathFrom(Node start){
  PriorityQueue<Node> kø; new PriorityQueue<Node>();
  kø.add(start);
  while(!kø.isEmpty()){
    Node node = kø.get(0);
    for(Node n: node.adjacencyList){
      int weigth = n.weigth;
      int distance = n.distance + weigth;
      if(distance < n.minDistance){
        kø.remove(n);
        n.minDistance = distance;
        n.previous = node;
        kø.add(n)
      }
    }
  }
}
