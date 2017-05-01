package SPLT_A4;

public class SPLT implements SPLT_Interface{
	public BST_Node root;
	private int size;
	 
	public SPLT() {
		    this.size = 0;
		  } 
	  

	public void insert(String s){
		if (root == null){
			root = new BST_Node(s);
			size++;
			return;
		}
		root = root.insertNode(s, root);
		if (root.getJustMade() == true){
			size++;
			return;
		} else {
			return;
		}
	}
		  public void remove(String s){
			  if (root == null){
				  return;
			  }
			  if (root.getData() == s){
				  root = root.removeNode(s, root);
				  size--;
				  return;
			  }
			  BST_Node newRoot = root.containsNode(s, root);
			  root = newRoot;
			  if (newRoot.getData() != s){
				  return;
			  } else {
				  root = root.removeNode(s, root);
				  size--;
				  return;
			  }
		  }
		  public String findMin(){
			  if (size == 0){
				  return null;
			  } else {
				  root = root.findMin(root);
				  return root.getData();
			  }
		  }
		  public String findMax(){
			  if (size == 0){
				  return null;
			  } else {
				  root = root.findMax(root);
				  return root.getData();
			  }
		  }
		  public boolean empty(){
			  if (size == 0){
				  return true;
			  } else {
				  return false;
			  }
		  }
		  public boolean contains(String s){
			  if (size == 0){
				  return false;
			  }
			  root = root.containsNode(s, root);
			  if (root.getData() == s) {
				  return true;
			  } else {
				  return false;
			  }
		  }
		  public int size(){
			  return size;
		  }
		  public int height(){
			  return root.getHeight(root);
		  }
	  @Override
	  //used for testing, please leave as is
	  public BST_Node getRoot(){ return root; }

	}
