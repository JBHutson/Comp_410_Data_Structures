package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }

	  public boolean insert(String s){
		  if (root == null){
			  root = new BST_Node(s);
			  size++;
			  return true;
		  }
		  root.insertNode(s, root);
		  if (root.containsNode(s, root)){
			  size++;
			  return true;
		  } else {
			  return false;
		  }
	  }
	  public boolean remove(String s){
		  if (root == null){
			  return false;
		  }
		  if (root.getData() == s && size == 1){
			  root = null;
			  size--;
			  return true;
		  }
		  if (root.removeNode(s, root) == null){
			  return false;
		  } else {
			  size--;
			  return true;
		  }
	  }
	  public String findMin(){
		  if (size == 0){
			  return null;
		  } else {
			  return root.findMin(root).getData();
		  }
	  }
	  public String findMax(){
		  if (size == 0){
			  return null;
		  } else {
			  return root.findMax(root).getData();
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
		  return root.containsNode(s, root);
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
