package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade;
  
  BST_Node(String data){ 
    this.data=data;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }
  public BST_Node getPar(){ return par; }
  public boolean getJustMade(){ return justMade; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  public BST_Node containsNode(String s, BST_Node t){ 
	  while (t != null){
		  if (s.compareTo(t.getData()) > 0 && t.right != null){
			  t = t.right;
		  } else if (s.compareTo(t.getData()) > 0 && t.right == null){
			  splay(t);
			  return t;
		  } else if (s.compareTo(t.getData()) < 0 && t.left != null){
			  t = t.left;
		  } else if (s.compareTo(t.getData()) < 0 && t.left == null){
			  splay(t);
			  return t;
		  } else if (s.equals(t.getData())){
			  break;
		  }
	  }
	  splay(t);
	  return t;
  }
  public BST_Node insertNode(String s, BST_Node t){ 
	BST_Node z = t;
	BST_Node p = null;
	while (z != null) {
		p = z;
		if (s.compareTo(p.getData()) < 0){
			z = z.left;
		} else if (s.compareTo(p.getData()) > 0){
			z = z.right;
		} else if (s == p.getData()){
			z.justMade = false;
			splay(z);
			return z;
		}
	}
		z = new BST_Node(s);
		z.justMade = true;
		z.par = p; 
	if (p == null){
		return z;
	} else if (s.compareTo(p.getData()) < 0){
		p.left = z;
	} else {
		p.right = z;
	}
	splay(z);
	return z;
  }
  public BST_Node removeNode(String s, BST_Node t){ 
	  BST_Node leftPar;
	  BST_Node rightPar;
	  if (t == null){
		  return t;
	  }
		  leftPar = t.getLeft();
		  rightPar = t.getRight();
		  t = null;
		  if(leftPar == null){
			  return rightPar;
		 } else {
		  leftPar = leftPar.findMax(leftPar);
		  leftPar.right = rightPar;
		  return leftPar;
		  }
  } 
  public BST_Node findMin(BST_Node t){ 
	  if (t == null){
		  return null;
	  } else {
		  while (t.getLeft() != null){
			  t = t.getLeft();
		  }
		  splay(t);
		  return t;
	  }
  }
  public BST_Node findMax(BST_Node t){ 
	  if (t == null){
		  return null;
	  } else {
		  while (t.getRight() != null){
			  t = t.getRight();
		  }
		  splay(t);
		  return t;
	  } 
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
		  return rightH + 1;
	  }
  }

  private BST_Node splay(BST_Node toSplay) { 
	  if (toSplay == null){
		  return null;
	  }
	  while (toSplay.par != null)
      {
          BST_Node parent = toSplay.getPar();
          BST_Node grandParent = parent.getPar();
          if (grandParent == null){
              if (toSplay == parent.left) {
                  rotateLeft(toSplay, parent);
              } else {
                  rotateRight(toSplay, parent);
              }
          } else {
              if (toSplay == parent.left) {
                  if (parent == grandParent.left) {
                      rotateLeft(parent, grandParent);
                      rotateLeft(toSplay, parent);
                  } else {
                      rotateLeft(toSplay, toSplay.par);
                      rotateRight(toSplay, toSplay.par);
                  }
              } else {
                  if (parent == grandParent.left) {
                      rotateRight(toSplay, toSplay.par);
                      rotateLeft(toSplay, toSplay.par);
                  } else {
                      rotateRight(parent, grandParent);
                      rotateRight(toSplay, parent);
                  }
              }
          }
      }
	 return toSplay;
  }
  
  private void rotateRight(BST_Node toSplay, BST_Node splayHelper){
	  if (splayHelper.par != null)
      {
          if (splayHelper == splayHelper.getPar().getLeft())
              splayHelper.par.left = toSplay;
          else
              splayHelper.par.right = toSplay;
      }
      if (toSplay.left != null)
          toSplay.left.par = splayHelper;
      toSplay.par = splayHelper.getPar();
      splayHelper.par = toSplay;
      splayHelper.right = toSplay.getLeft();
      toSplay.left = splayHelper;
  }
  
  private void rotateLeft(BST_Node toSplay, BST_Node splayHelper){
	  if (splayHelper.par != null)
      {
          if (splayHelper == splayHelper.par.left)
              splayHelper.par.left = toSplay;
          else 
              splayHelper.par.right = toSplay;
      }
      if (toSplay.right != null)
          toSplay.right.par = splayHelper;

      toSplay.par = splayHelper.getPar();
      splayHelper.par = toSplay;
      splayHelper.left = toSplay.getRight();
      toSplay.right = splayHelper;
  }
}
  // --- end example methods --------------------------------------

  
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
