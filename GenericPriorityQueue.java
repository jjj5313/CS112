/* Yuanli Zhong
 * BU ID:U79526308
 * GenericPriorityQueue.java
 **/
@SuppressWarnings("unchecked")

  public class GenericPriorityQueue<Item> {
  public static void main(String[] Arg) {
    GenericPriorityQueue<String> s = new GenericPriorityQueue<String>();
    s.insert("benjamin");
    s.insert("able");
    s.insert("cs112");
    s.getMax();   // should return "cs112"
    s.getMax();   // should return "benjamin"
    s.insert("aaron");
    s.insert("ben");
    s.getMax();   // should return "ben"
    s.getMax();   // should return "able"
    s.getMax();   // should return "aaron"
  }
  
  public final int SIZE = 10;
  public Item [] A = (Item []) new Object[SIZE];
  public int size = 0;                      // number of elements stored in array
  public int front = 0; 
  public int next = 0;  
  
  
  public int nextSlot(int k) { 
    return ((k + 1) % A.length); 
  } 
  
  public int preSlot(int m){
    if (m == 0){
      return A.length - 1;
    }
    else{
      return ((m - 1) % A.length); 
    }
  }
  
  public void list() {
    System.out.print("[");
    for (int i = 0; i < A.length; ++i) {
      if (i == next && i == front) {
        System.out.print(" <  > " + A[i] ); 
      }
      else if (i == next && i != front) {
        System.out.print(" > " + A[i] ); 
      }
      else if (i != next && i == front) {
        System.out.print(" < " + A[i] ); 
      }
      else if (i == 0) {
        System.out.print(A[i] );
      }
      else {
        System.out.print(", " + A[i] );
      }
    }
    System.out.println("]");
  }
  
  
  public void insert(Item k) { 
    A[next] = k; 
    size++; 
    resize();
    
    for (int i = next; i > front; i--) {
      Comparable c1 = (Comparable) A[i];  
      Comparable c2 = (Comparable) A[preSlot(i)];  
      if(c2.compareTo(c1) < 0){
        Item temp = A[i];
        A[i] = A[preSlot(i)];
        A[preSlot(i)] = temp;
      }
    }
    next = nextSlot(next); 
  }
  
  // dequeue and move front pointer clockwise;
  public Item getMax() { 
    Item temp = A[front]; 
    front = nextSlot(front);  
    size--;  
    return temp;
  } 
  
  // double Array size
  public void expandA() {
    Item [] tempA = (Item[]) new Object[A.length * 2]; //create a new array with double length of A
    for(int i = 0; i < next; i++){ // move data from A to temparray
      tempA[i] = A[i];
    }
    A = tempA;   //let A equal to the temp array 
  }  
  
  
  // shrink 1/2 Array size
  public void shrinkA() {
    Item [] tempA = (Item[])new Object[A.length / 2];
    int k = 0; 
    for(int i = front; i < next; i++){ // move data from A to temparray
      tempA[k] = A[i];
      k = k + 1;
    }
    front = 0;
    next = size;
    A = tempA;   //let A equal to the temp array 
  }  
  
  
  // resize
  public void resize() {
    if(size == A.length)   
      expandA();   
    if(!(0.5*A.length < 10) && size < 0.25 * A.length){ 
      shrinkA();
    }
  }
  // just a wrapper for public value size
  public int size() { 
    return size; 
  }  
  
  // standard utility for data structures
  public boolean isEmpty() { 
    return (size == 0); 
  } 
  
}
