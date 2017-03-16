package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node root;//this will be the entry point to your linked list (the head)
  int size;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    root=new Node(0); //Note that the root's data is not a true part of your data set!
    size = 0;
  }


public boolean insert (Node n, int index){
	Node currentNode = root;
	if (index > size)
		return false;
	else if(currentNode != null){
		for(int i = 0; i < index && currentNode.getNext() != null; i++){
		currentNode = currentNode.getNext();
		}
	}
	n.next = currentNode.getNext();
	currentNode.next = n;
	size++;
	return true;
  }
  
  public boolean remove (int index){
	 Node currentNode = root;
	  if (index > size){
		  return false;
		 }
	  if (currentNode != null){
		  for(int i = 0; i < index; i++){
			  if (currentNode.getNext() == null){
				  return false;
			  }
		   currentNode = currentNode.getNext();
		  }
		  currentNode.next = currentNode.getNext().getNext();
		  size--;
	  }
	  return true;
  }
  
  public Node get(int index){
	 Node currentNode = null;
	  if (index < 0){
		  return null;
	  }
	  if (root != null){
		  currentNode = root.getNext();
		  for (int i = 0; i < index; i++){
			  if (currentNode.getNext() == null){
				  return null;
			  }
			  currentNode = currentNode.getNext();
		  }
	  }
	  return currentNode;
  }
  
  public int size(){
	  return size;
  }
  
  public boolean isEmpty(){
	  if (size == 0){
		  return true;
	  } else {
		  return false;
	  }
  }
  
  public void clear(){
	  for(int i = 1; i < size; i++){
		  this.remove(i);
	  }
	  size = 0;
  }
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return root;
  }
}

