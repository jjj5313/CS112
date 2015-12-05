/* Program: IntCollection.java
 * Author: Wayne Snyder
 * Date: 9/9/2014
 * Class: CS 112
 * Purpose: Solution for HW 01, Problem B.2. IntCollection is a simple container for integers; 
 * it allows duplicates, and if you delete an int, it will only remove the first instance it 
 * comes to. The interface Collection has been removed for the purposes of Lab 03. 
 * 
 * Modified by Yuanli Zhong
 */

public class IntCollectionLab {         // implements Collection {
  private boolean debug = true;           // set this to true if you want to trace your execution
  
  private void db(String s) {            
    if(debug) 
      System.out.println("\t" + s);
  }
  
  private final int MIN_SIZE = 10; 
  public int [] A = new int[MIN_SIZE]; 
  public int next = 0;  
  // To insert, first check if it is already there (no duplicates allowed!)
  // Scan from the right and move every element which is > over one slot to R; then insert
  // the new int when you find something <=.
  // Special case occurs if run off end of array without finding <= element.
  
  public void insert(int k) { 
    db("insert( " + k + " )");
    // Here's where to trace what number you are inserting 
    
    db(toString());
    // Here's where to print out the data structure using toString (problem 2)
    // to see what it looks like before the insertion
    
    if(member(k)) {            // if already present, do nothing! 
      
      db("\t" + k + " is already in the collection"); 
      // here's where to trace if k is already in the collection
      
      return; 
    }
    
    if(next == A.length) {       // check if need to expand array because is full
      
      db("\t" + "expanded");
      // Here's where to trace if array is being expanded and print out A.length
      
      expand();
    }
    
    // since k is not a member, we know we will move elements over to make
    // room, so start at end and move over under we get to the insertion point.
    
    for(int i = next-1; i >= -1; --i) {
      
      db("\t" + i);
      // Here's where to trace what i is each time through the loop
      
      if(i == -1) {            // Ran off the left side because inserting new smallest element
        db("\t" + "inserting new smallest element");   
        // Here's where to trace if inserting new smallest element
        A[0] = k;
      }
      else if(A[i] <= k) {        // found insertion point; keep moving later elements over to R 
        db("\t" + "found"); 
        // Here's where to trace whether you found the insertion point
        A[i+1] = k;
        break;
      }
      else {                          // else just keep moving larger elements to the right
        db("\t" + " moving elements"); 
        // Here's where to trace if you are moving elements over
        A[i+1] = A[i];
      }
    } 
    ++next;           // list has one more element
    
    db(toString());
    // Here's where to print out the data structure using toString (problem 2)
    // to see what effect the insert has had.
    db("insert( " + k + " )");
  } 
  
  
  // delete the first instance of a given int by scanning from left to right; if find the int, then
  // move every later element left one slot
  
  public void delete(int k) {   
    for(int i = 0; i < next; ++i) {
      db("\t" + i);
      if(A[i] == k) {      
        db("\t" + "found"); // found it; move later elements over 
        for(int j = i+1; j < next; ++j) {
          db("\t" + j);
          A[j-1] = A[j];
        }
        --next;           // list has one fewer element
        break;
      }                    
    } 
    // check if need to shrink the array because is it less than 1/4th full
    // but do not shrink if it already at minimum size
    if(A.length > MIN_SIZE && size() < A.length/4) {
      db("\t" + "shrink"); 
      shrink(); 
    }
  } 
  
  // does binary search on the array using the iterative algorithm from
  // the program BinarySearch.java provided on the CS 112 web site
  
  public boolean member(int k) {  
    
    boolean found = false;   
    int left = 0;
    int right = next - 1;              // search from 0 to end of useful elements (next - 1)
    
    while (left <= right && !found) {
      int middle = (left + right) / 2; 
      if(A[middle] == k)
        found = true;
      else if(k < A[middle])
        right = middle -1;
      else 
        left = middle + 1; 
    } 
    
    return found;
    
  }
  
  // for debugging....  shows the WHOLE array, and puts a "|" at the end of the elements actually
  //   stored
  
  public String toString() {
    String s = "[";
    for(int i = 0; i < A.length; ++i) {
      if(i == next)
        s += " | " + A[i];
      else if(i == 0)
        s += A[i];
      else 
        s += ", " + A[i];
    }
    return s;      
  }
  
  public int size() {
    return next;
  }
  
  public boolean isEmpty() {
    return (next == 0);
  }
  
  // resizing methods: should be private!
  
  private void expand() {
    int[] B = new int[2*A.length];
    for(int i = 0; i < A.length; ++i) {
      B[i] = A[i];
    }
    A = B;
  }
  
  private void shrink() {
    int[] B = new int[A.length/2];
    for(int i = 0; i < next; ++i) {
      B[i] = A[i];
    }
    A = B;
  }
  
  // Here's where the unit test goes!
  public static void main(String [] args) { 
    IntCollectionLab C = new IntCollectionLab();
    
    System.out.println("\nTesting size and isEmpty...");
    System.out.println("At beginning should be:\n0  true");
    System.out.println(C.size()+ "  " + C.isEmpty()); 
    System.out.println("\nNow insert two elements and test again; should be:\n2  false");
    C.insert(7);
    C.insert(13);
    System.out.println(C.size()+ "  " + C.isEmpty()); 
    System.out.println("\nNow delete the two elements and test again; should be:\n0  true");
    C.delete(7);
    C.delete(13);
    System.out.println(C.size()+ "  " + C.isEmpty());
    
    System.out.println("\nTesting basic insertions and deletions...");
    System.out.println("\nInserting 8 elements; should be:\n[2, 3, 7, 13, 34, 64, 111, 234 | 0, 0]"); 
    C.insert(64);
    C.insert(2);
    C.insert(7);
    C.insert(34);
    C.insert(3); 
    C.insert(111);
    C.insert(13);
    C.insert(234); 
    System.out.println(C);                         
    System.out.println("\nDeleting 2 from front; should be:\n[3, 7, 13, 34, 64, 111, 234 | 234, 0, 0]"); 
    C.delete(2);
    System.out.println(C); 
    System.out.println("\nDeleting 234 from end; should be:\n[3, 7, 13, 34, 64, 111 | 234, 234, 0, 0]"); 
    C.delete(234);
    System.out.println(C);
    System.out.println("\nDeleting 13 and 34 from middle; should be:\n[3, 7, 64, 111 | 111, 111, 234, 234, 0, 0]"); 
    C.delete(34);
    C.delete(13);
    System.out.println(C);
    System.out.println("\nTesting that can not insert duplicates...");
    System.out.println("\nInserting 64 twice more; should be:\n[3, 7, 64, 111 | 111, 111, 234, 234, 0, 0]"); 
    C.insert(64); 
    C.insert(64);   
    System.out.println(C); 
    System.out.println("\nTesting that deleting non-members (-3, 5, 234) should have no effect; Should be:");
    System.out.println("\n[3, 7, 64, 111 | 111, 111, 234, 234, 0, 0]");
    C.delete(-3);
    C.delete(5);
    C.delete(234);
    System.out.println(C);
    
    System.out.println("\nTesting member......");
    System.out.println("\nChecking member(7); should be:\ntrue");
    System.out.println(C.member(7)); 
    System.out.println("\nChecking member(3); should be:\ntrue");
    System.out.println(C.member(3)); 
    System.out.println("\nChecking member(111); should be:\ntrue");
    System.out.println(C.member(111)); 
    System.out.println("\nChecking member(13); should be:\nfalse");
    System.out.println(C.member(13)); 
    System.out.println("\nChecking member(234); should be:\nfalse");
    System.out.println(C.member(234)); 
    
    System.out.println("\nTesting that array will expand.... should be:\n[3, 7, 64, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 111 | 0, 0, 0, 0, 0, 0]");
    for(int i = 100; i < 110; ++i)
      C.insert(i);
    System.out.println(C); 
    System.out.println("\nTesting that array will NOT shrink until < 1/4th full.... should be:\n[3, 7, 64, 109, 111 | 111, 111, 111, 111, 111, 111, 111, 111, 111, 0, 0, 0, 0, 0, 0]");
    for(int i = 100; i < 109; ++i)
      C.delete(i);
    System.out.println(C); 
    System.out.println("\nTesting that array WILL shrink when < 1/4th full.... should be:\n[3, 64, 109, 111 | 0, 0, 0, 0, 0, 0]");
    C.delete(7);
    System.out.println(C);  
    System.out.println("\nTesting that array will NOT shrink if minimum size of 10 reached; should be: \n[64 | 109, 111, 111, 0, 0, 0, 0, 0, 0]");
    C.delete(3);
    C.delete(111);
    C.delete(109);
    System.out.println(C);      
  }  
} 



