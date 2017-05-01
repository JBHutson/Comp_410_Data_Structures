package DiGraph_A5;

public class Edge {

	protected long id;
	protected String source;
	protected String destination;
	protected long edgeWeight;
	protected String edgeLabel;
	
	Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel){
		id = idNum;
		source = sLabel;
		destination = dLabel;
		edgeWeight = weight;
		edgeLabel = eLabel;
	}
	
	public long getId(){
		return id;
	}
	
	public String getSource(){
		return source;
	}
	public String getDestination(){
		return destination;
	}
	public long getEdgeWeight(){
		return edgeWeight;
	}
	public String getEdgeLabel(){
		return edgeLabel;
	}
}
