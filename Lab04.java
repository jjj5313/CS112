/* File: Lab04.java
 * Author: Wayne Snyder
 * Date: 10/2/14
 * Purpose: This is the starter code for Lab 04 on basic linked list
 *          processing
 * Modefied By Yuanli Zhong
 * BUID:U79526308
 */

public class Lab04 {
  
  /************  Basic definitions for this lab ***************/
  
  // basic definition of the Node class; this is an "inner class" inside class Lab04
  
  private static class Node {
    
    public int item;
    public Node next;
    
    Node() {                 // this would be the default if we did not provide the next two
      item = 0; 
      next = null;      
    } 
    
    Node(int n) {
      item = n;
      next = null;
    }
    
    Node(int n, Node p) {
      item = n;
      next = p;
    }
    
    // Problem Two: create a toString() method to print out whole list
    public String toString() {
      // Your code here!
      return (item + " -> " + next);             // just to get it to compile 
    }
  }; 
  
  // Assume a global variable head which points to first node in list
  // Note that pointers are set to null by default, as if "Node head = null"
  
  private static Node head; 
  
  // Basic algorithms provided for this lab
  
  
  private static void printList(Node p) {
    for( ; p != null; p = p.next)
      System.out.print(p.item + " -> ");
    System.out.println("null"); 
  }
  
  private static int length(Node p) {
    int len = 0; 
    for( ; p != null; p = p.next)
      ++len;
    return len; 
  }
  
  
  /************  Code for Problems for This Lab ***************/
  
  // Problem One: creating linked lists
  
  private static Node arrayToList(int[] A) {
    // Your code here
    head = null;
    for ( int i = A.length-1; i >= 0;  i--) {
      head = new Node(A[i], head); 
    }
    return head;             // just to get it to compile 
  }
  
  
  // Problem Three: inserting into an unordered list
  
  private static void insertBeginning(int k) {
    // Your code here
    head = new Node(k, head);
  }
  
  private static void insertEnd(int k) {
    // Your code here
    Node p = head;
    for(; p != null; p = p.next ) {
      if (p.next == null) {
        p.next = new Node(k);
        p = p.next;
      }
    }
    p = new Node(k, p);
  }
  
  private static void insertMiddle(int k) {
    // Your code here
    int mid = 0;
    Node p = head;
    mid = ((length(p) - 1)/2);
    for (int i = 0; i < mid; i++) {
      p = p.next;
    }
    p.next = new Node(k,p.next);   
  }
  
  
// Problem Four: inserting into an ordered list
  
  public static void insertInOrder(int k) {
    // Your code here
    if(head == null)              // case 1: list is empty
      head = new Node(k,head);  
    else if(head.item >= k)     // case 2: n should be before the first node 
      head = new Node(k, head);        //    push on front of list
    else {                        // case 3: find the node before where n should be using the inchworm technique
      Node p = head.next;                                  
      Node q = head;                      
      while( p != null ) {
        if(p.item >= k) {               // found insertion point, between p and q
          q.next = new Node(k, p);
          return;
        }
        q = p;                     // chain along!
        p = p.next;
      }
      q.next = new Node(k, null);   // case 4: Node must be added at end of list
    }
  }
  
  private static void printInOrder(int[] A) {
    // Your code here   
    for (int i = 0; i < A.length; i++) {
      insertInOrder(A[i]);
    }
    printList(head); 
  }
  
  
  
  
  public static void main(String[] args) {
    
    head = new Node(5);
    head = new Node(7, head);
    System.out.println("Example:"); 
    printList(head); 
    
    // Problem One: Warmup....
    System.out.println("\n1.A: Should get\n3 -> 1 -> 4 -> 1 -> 5 -> 9 -> null"); 
    head = null;
    // Your solution to Problem 1 here
    head = new Node(9);
    head = new Node(5, head); 
    head = new Node(1, head); 
    head = new Node(4, head); 
    head = new Node(1, head); 
    head = new Node(3, head); 
    
    printList(head); 
    
    int[] pi = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3 };
    head = arrayToList(pi); 
    System.out.println("\n1.B: Should get\n3 -> 1 -> 4 -> 1 -> 5 -> 9 -> 2 -> 6 -> 5 -> 3 -> null"); 
    printList(head); 
    
    // Problem Two: Printing a list using a toString() method
    
    System.out.println("\n2: Should get\n3 -> 1 -> 4 -> 1 -> 5 -> 9 -> 2 -> 6 -> 5 -> 3 -> null"); 
    System.out.println(head); 
    
    // Problem Three: inserting into an unordered list
    
    // 3.A
    
    int[] cake = { 1, 4, 1, 5, 9, 2, 6, 5, 3 };
    head = arrayToList(cake); 
    System.out.println("\n3.A: Should get\n3 -> 1 -> 4 -> 1 -> 5 -> 9 -> 2 -> 6 -> 5 -> 3 -> null"); 
    insertBeginning(3); 
    System.out.println(head);      
    
    // 3.B
    
    System.out.println("\n3.B: Should get\n3 -> 1 -> 4 -> 1 -> 5 -> 9 -> 2 -> 6 -> 5 -> 3 -> 5 -> null"); 
    insertEnd(5); 
    System.out.println(head); 
    
    // 3.C
    
    System.out.println("\n3.C: Should get\n3 -> 1 -> 4 -> 1 -> 5 -> 7 -> 9 -> 2 -> 6 -> 5 -> 3 -> 5 -> null   OR"); 
    System.out.println("3 -> 1 -> 4 -> 1 -> 5 -> 9 -> 7 -> 2 -> 6 -> 5 -> 3 -> 5 -> null"); 
    insertMiddle(7); 
    System.out.println(head); 
    
    // Problem Four: inserting into an ordered list
    
    System.out.println("\n4: Should get\n1 -> 1 -> 2 -> 3 -> 3 -> 4 -> 5 -> 5 -> 5 -> 6 -> 9 -> null"); 
    head = null; 
    insertInOrder(3); 
    insertInOrder(1); 
    insertInOrder(4); 
    insertInOrder(1); 
    insertInOrder(5); 
    insertInOrder(9); 
    insertInOrder(2); 
    insertInOrder(6); 
    insertInOrder(5); 
    insertInOrder(3); 
    insertInOrder(5); 
    System.out.println(head); 
    
    // Problem Five: Insertion sort using linked lists
    
    System.out.println("\n5: Should get\n1 -> 1 -> 2 -> 3 -> 3 -> 4 -> 5 -> 5 -> 6 -> 9 -> null"); 
    head = null; 
    printInOrder(pi); 
    
    
  }
}