package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    size = 0;
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
  public void insert(EntryPair entry){              
	 int hole = ++size;
	 for (array[ 0 ] = entry; entry.getPriority() < array[ hole / 2].getPriority(); hole /= 2){
		 array[ hole ] = array[ hole / 2];
	 }
	 array[hole] = entry;
	 }
  public void delMin(){
	  if (array[1] == null){
		  return;
	  } else {
		  array[1] = array[size--];
		  percolateDown(1);
	  }
  }
  public EntryPair getMin(){
	  if (array[1] == null){
		  return null;
	  } else {
	  return array[1];
	  }
  }
  public int size(){
	  int returnedSize = size;
	  return returnedSize;
  }
  public void build(EntryPair [] entries){                      
	 size = entries.length;
	  int i = 1;
	  for (EntryPair entry : entries){
		  array [i++] = entry;
	  }
	  for (int j = this.size()/2; j > 0; j-- ){
		  percolateDown(j);
	  }
  }
  private void percolateDown( int hole ){                 
  int child;
  EntryPair tmp = array[ hole ];
 
  for( ; hole * 2 <= this.size(); hole = child ) { 
  child = hole * 2;
  if( child != this.size() && array[ child + 1 ].getPriority() < ( array[ child ].getPriority())){
  child++;
  } if( array[ child ].getPriority() < tmp.getPriority()){
  array[ hole ] = array[ child ];
  } else {
  break;
  	}
  }
  array[ hole ] = tmp;
}
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }
}
