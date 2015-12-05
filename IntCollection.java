/** Yuanli Zhong
  * BU ID: U79526308
  **/
class IntCollection implements Collection {  
// class IntCollection  {
  private final int SIZE = 10;
  private int [] A = new int[SIZE]; 
  private int next = 0;  
  
  
  private void SizeChange() {
    if (next == A.length) {   
      int [] tempA = new int[A.length * 2]; 
      for(int i = 0; i < next; i++) { // move data from A to tempA
        tempA[i] = A[i];
      }
      A = tempA;   
    }
    if (next< 0.25 * A.length && !(0.5 * A.length < 10)) { 
      int [] tempA = new int[A.length / 2]; 
      for (int i = 0; i < next; i++) { 
        tempA[i] = A[i];
      }
      A = tempA;   
    }
  }
  
  // insert a new int at the end of the list -- may run off the end of the array if it is full!
  
  public void insert(int k) { 
    if (isEmpty()) { 
      A[next] = k; 
      next++;
    }
    else{
      for (int i = 0; i < next; i++) {
        if (A[i] == k) {
          return;
        } 
      } // Do nothing if key is already there
      
      //if it is not inside
      int i = 0; 
      while (i < next && k > A[i]) {
        i++; 
      }
      next++;   
      SizeChange(); 
      int [] temp = new int[A.length]; 
      for (int j = i; j < next; j++) {  
        temp[j] = A[j];
      }
      for (int j = i; j < next; j++){
        A[j + 1] = temp[j]; 
      } 
      A[i] = k;
    }
  }
  
  // delete the first instance of a given int by scanning from left; if find the int, then
  //    move every later element left one slot
  
  public void delete(int k) {  
    if (!member(k)) { 
      return;
    }
    int i = 0; 
    while (k != A[i]) { 
      i++; 
    }
    int[] temp = new int[A.length+1];
    
    for (int j = i; j < next; j++) { 
      temp[j] = A[j]; 
    }
    for (int j = i; j < next; j++) {
      A[j] = temp[j+1];
    }
    next--;
    SizeChange(); 
  }
  
  // Use binary search!
  
  public boolean member(int k) { 
    boolean findm = false;    
    int left = 0; 
    int right = next; 
    
    while (left <= right && !findm) {
      int middle = (left + right) / 2;  
      if (A[middle] == k) {
        findm = true; 
      }
      else if (k < A[middle]) {
        right = middle -1;
      }
      else {
        left = middle + 1; 
      }
    }
    if (findm) {
      return true;
    }
    else {
      return false;
  }
  }
  
  
  
// for debugging....  shows the WHOLE array, and puts a "|" at the end of the elements actually
//   stored
  
  public void list() {
    System.out.print("[");
    for(int i = 0; i < A.length; ++i) {
      if(i == next)
        System.out.print(" | " + A[i]);
      else if(i == 0)
        System.out.print(A[i] );
      else 
        System.out.print(", " + A[i] );
    }
    System.out.println("]"); 
  
  }
  
// typical utility methods for showing status of the collection
  
  public int size() {
    return next;  
  }
  
  public boolean isEmpty() {
    return (next == 0);  
  }
} 
