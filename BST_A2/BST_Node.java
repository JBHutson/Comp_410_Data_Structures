package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  public boolean containsNode(String s, BST_Node t){ 
	  if (t == null){
		  return false;
	  }
	  int compare = s.compareTo(t.getData());
	  if (compare < 0){
		  return containsNode(s, t.getLeft());
	  } else if (compare > 0){
		  return containsNode(s, t.getRight());
	  } else {
		  return true;
	  }
  }
  public BST_Node insertNode(String s, BST_Node t){ 
	  if (t == null){
		   return new BST_Node(s);
	  } 
	  int compare = s.compareTo(t.getData());
	  if (compare < 0){
		 t.left = insertNode(s, t.getLeft());
	  } else if (compare > 0){
		 t.right = insertNode(s, t.getRight());
	  } else {
	  }
	  return t;
  }
  public BST_Node removeNode(String s, BST_Node t){ 
	  if (t == null){
		  return t;
	  }
	  int compare = s.compareTo(t.getData());
	  if (compare < 0){
		 t.left = removeNode(s, t.getLeft());
	  } else if (compare > 0){
		 t.right = removeNode(s, t.getRight());
	  } else if (t.getLeft() != null && t.getRight() != null){
		 t.data = findMin(t.getRight()).getData();
		 t.right = removeNode(t.data, t.getRight());
	  } else {
		  t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
	  } 
	  return t;
  } 
  public BST_Node findMin(BST_Node t){ 
	  if (t == null){
		  return null;
	  } else if (t.getLeft() == null){
		  return t;
	  }
	  return findMin(t.getLeft());
  }
  public BST_Node findMax(BST_Node t){ 
	  if (t == null){
		  return null;
	  } else if (t.getRight() == null){
		  return t;
	  } 
	  return findMax (t.getRight());
  }
  public int getHeight(BST_Node t){ 
	  if (t == null){
		  return -1;
	  } 
	  int leftH = getHeight(t.getLeft());
	  int rightH = getHeight(t.getRight());
	  if (leftH > rightH){
		  return leftH + 1;
	  } else {
		  return rightH +1;
	  }
  }

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}