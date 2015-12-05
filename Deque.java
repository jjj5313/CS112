/** Yuanli Zhong
  * BU ID: U79526308
  **/
public class Deque implements Dequeable{
  public final int SIZE = 10; 
  public int [] A = new int[SIZE]; 
  public int size = 0;                      // number of elements stored in array, NOT size of array
  public int front = 0; 
  public int next = 0;  
  
  private void expand() {
    int [] tempA = new int[A.length * 2]; //create a new array with double length of A
    for (int i = 0; i < next; i++) { 
      tempA[i] = A[i];
    }
    A = tempA;   
  }  
  
  private void contract() {
    int [] tempA = new int[A.length / 2]; //create a new array with half length of A
    int i = 0; 
    for (int j = front; j < next; j++) { 
      tempA[i] = A[j];
      i++;
    }
    front = 0;
    next = size;
    A = tempA; 
  }  
  
  private void resize() {
    if (size == A.length) {
      expand();
    }
      if (size < 0.25 * A.length && !(0.5 * A.length < 10)) { 
        contract();
      }
  }
  
// gives next index in array which wraps around in a ring; moves clockwise through indices
  public int nextSlot(int k) {
    return ((k + 1) % A.length); 
  } 
  
  public int lastSlot(int j){
    if (j == 0) {
      return A.length - 1;
    }
    else{
      return ((j - 1) % A.length); 
    }
  }
  
  // enqueue in next slot clockwise around the ring
  public void enqueueFront(int n) { 
    if (front == next) {
      A[front] = n;
      next = nextSlot(next);
      size++;
      resize();
    }
    else {
      int [] temp = new int[A.length];
      int m = front;
      for (int i = 0; i < size; i++) {
        temp[i] = A[m];
        m = nextSlot(m);
      }
      m = front;
      size++;  
      resize(); 
      for (int i = 0; i< size; i++) {
        m = nextSlot(m);
        A[m] = temp[i];
      }        
      A[front] = n;
      next = nextSlot(next); 
    }
  }
  
  public void enqueueRear(int n) {  
    A[next] = n;
    size++;  
    resize();
    next = nextSlot(next); 
  } 
  
  // dequeue and move front pointer clockwise; can underflow without warning and produce error elements
  public int dequeueFront() { 
    int temp = A[front]; 
    front = nextSlot(front);  
    size--; 
    resize();
    return temp; 
  } 
  
  public int dequeueRear() { 
    next = lastSlot(next);  
    int temp = A[next];
    size--;
    resize();
    return temp; 
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
  // just a wrapper for private value size
  public int size() { 
    return size; 
  }  
  
  // standard utility for data structures
  public boolean isEmpty() { 
    return (size == 0); 
  } 
}