package DiGraph_A5;

import java.util.*;

public class DiGraph implements DiGraph_Interface {
	private long nodeNum = 0;
	private long edgeNum = 0;
	HashMap<String, Vertex> Graph = new HashMap<>();
	HashSet<Long> NodeIds = new HashSet<>();
	HashSet<Long> EdgeIds = new HashSet<>();
	HashSet<String> NodeNames = new HashSet<>();

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }
  
  public boolean addNode(long idNum, String label){
	  if (idNum < 0 || NodeIds.contains(idNum) == true){
		  return false;
	  } else if (label == null || Graph.containsKey(label)){
		  return false;
	  } else {
		  Vertex newVertex = new Vertex(idNum, label);
		  NodeIds.add(idNum);
		  NodeNames.add(label);
		  Graph.put(label, newVertex);
		  nodeNum++;
		  return true;
	  }
  }
  public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel){
	  if (idNum < 0 || EdgeIds.contains(idNum)){
		  return false;
	  } else if (Graph.containsKey(sLabel) != true || Graph.containsKey(dLabel) != true) {
		  return false;
	  } else if (Graph.get(sLabel).getAdjacentList().containsKey(dLabel)) {
		return false;
	  } else {
		  Edge newEdge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		  EdgeIds.add(idNum);
		  Graph.get(dLabel).setInDegrees(sLabel);
		  Graph.get(sLabel).setAdjacentList(dLabel, newEdge);
		  edgeNum++;
		  return true;
		  }
	  }
  public boolean delNode(String label){
	  if(Graph.containsKey(label) != true){
		  return false;
	  } else {
		  Iterator<String> iterator1 = Graph.get(label).getInDegrees().iterator();
			while (iterator1.hasNext()){
				String sLabel = iterator1.next();
				EdgeIds.remove(Graph.get(sLabel).getAdjacentList().get(label).getId());
				Graph.get(sLabel).removeAdjacentList(label);
				edgeNum--;
		  }
		  edgeNum = edgeNum - Graph.get(label).getAdjacentList().size();
		  Iterator<Long> iterator2 = Graph.get(label).getVertexEdgeIds().iterator();
		  while (iterator2.hasNext()){
			  long id = iterator2.next();
			  EdgeIds.remove(id);
		  }
		  Graph.get(label).getAdjacentList().clear();
		  NodeNames.remove(label);
		  Graph.remove(label);
		  nodeNum--;
		  return true;
	  }
  }
  public boolean delEdge(String sLabel, String dLabel){
	  if (Graph.containsKey(sLabel) != true){
		  return false;
	  } else if(Graph.get(sLabel).getAdjacentList().containsKey(dLabel) != true){
		  return false;
	  } else {
		  EdgeIds.remove(Graph.get(sLabel).getAdjacentList().get(dLabel).getId());
		  Graph.get(sLabel).removeAdjacentList(dLabel);
		  Graph.get(dLabel).removeInDegrees(sLabel);
		  edgeNum--;
		  return true;
	  }
  }
  public long numNodes(){
	  return nodeNum;
  }
  public long numEdges(){
	  return edgeNum;
  }
  public String[] topoSort(){
	  String[] topoNames;
	  topoNames = new String[Graph.size()];
	  Queue<Vertex> q = new LinkedList<Vertex>();
	  int counter = 0;
	  
	  Iterator<String> iterator3 = NodeNames.iterator();
	  while (iterator3.hasNext()){
		  String node = iterator3.next();
		  if (Graph.get(node).getInDegreeNum() == 0){
			  q.add(Graph.get(node));
		  }
	  }
	  while (!q.isEmpty()){
		  Vertex v = q.remove();
		  topoNames[counter] = v.getNodeLabel();
		  counter++;
		  Iterator<String> iterator4 = v.getOutDegrees().iterator();
		  while (iterator4.hasNext()){
			  String node = iterator4.next();
			  Vertex w = Graph.get(node);
			  w.inDegreeNum--;
			  if(w.getInDegreeNum() == 0){
				  q.add(w);
			  }
		  }
	  }
	  if(counter != nodeNum){
		  return null;
	  } else {
		  return topoNames;
	  }
  }
}
