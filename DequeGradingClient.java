/* File: DequeGradingClient.java
 * Date: 1/28/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Spring 2014
 * Purpose: Grading client for Problem B.3 in HW 01
 */

public class DequeGradingClient { 
   
   public static void main(String[] args) {
      
      Dequeable D = new Deque(); 
      
      
      
      
      
      System.out.println("\nFirst test size and isEmpty\nShould print out:\n0  true:"); 
      System.out.println(D.size() + "  " + D.isEmpty()); 
      D.enqueueFront(1); 
      D.enqueueFront(2);
      D.enqueueFront(3); 
      D.enqueueFront(4); 
      System.out.println("\nShould print out:\n4  false:"); 
      System.out.println(D.size() + "  " + D.isEmpty()); 
      D.dequeueFront(); 
      D.dequeueFront(); 
      D.dequeueFront(); 
      D.dequeueFront(); 
      System.out.println("\nShould print out:\n0  true:"); 
      System.out.println(D.size() + "  " + D.isEmpty()); 
      
      System.out.println("\nNow test enqueueFront and dequeueFront\nShould print out:\n4  3  2  1");            
      D.enqueueFront(1); 
      D.enqueueFront(2);
      D.enqueueFront(3); 
      D.enqueueFront(4); 
      while( !D.isEmpty() ) {
         System.out.print( D.dequeueFront() + "  "); 
      }
      System.out.println(); 
      
      
      System.out.println("\nNow test enqueueRear and dequeueRear\nShould print out:\n14  13  12  11");      
      D.enqueueRear(11); 
      D.enqueueRear(12);
      D.enqueueRear(13);
      D.enqueueRear(14);
      
      
      while( !D.isEmpty() ) {
         System.out.print( D.dequeueRear() + "  "); 
      }
      System.out.println(); 
      
      System.out.println("\nNow test all four together\nShould print out:\n1 2 3 4 5 6");      
      D.enqueueRear(6); 
      D.enqueueFront(4);
      D.enqueueRear(1);
      D.enqueueFront(2);
      System.out.print(D.dequeueRear() + " "); 
      System.out.print(D.dequeueFront() + " "); 
      D.enqueueRear(5);
      D.enqueueFront(3);
      System.out.print(D.dequeueFront() + " "); 
      System.out.print(D.dequeueFront() + " "); 
      System.out.print(D.dequeueRear() + " "); 
      System.out.println(D.dequeueFront() + " ");
      
      
      Deque E = new Deque(); 
      System.out.println("\nNow test resizing, inserting 0.. 99\nShould print out size:\n100"); 
      for(int i = 0; i < 100; ++i) {
         E.enqueueRear(i);
      }
      System.out.println(E.size()); 
      System.out.println("\nNow should print out:\n0 99 1 98 2 97 3 96 4 95");     
      for(int i = 0; i < 5; ++i) {
         System.out.print(E.dequeueFront() + " ");
         System.out.print(E.dequeueRear() + " ");
      }
      System.out.println();
      
      System.out.println("\nShould list the queue and somehow show that it is has 160 slots:");
      E.list(); 
      
      System.out.println("\nTesting shrinkage; now should print out:\n40"); 
      for(int i = 0; i < 50; ++i) {
         E.dequeueFront();
      }
      System.out.println(E.size()); 
      
      System.out.println("\nShould list somehow and show has not resized:"); 
      E.list(); 
      
      System.out.println("\nNow should print out:\n39"); 
      
      E.dequeueFront();
      
      System.out.println(E.size()); 
      
      System.out.println("\nShould list somehow and show HAS resized to 80 slots:"); 
      E.list(); 
      
      System.out.println("\nNow should print out:\n1"); 
      for(int i = 0; i < 38; ++i) {
         E.dequeueFront();
      }
      
      System.out.println(E.size()); 
      
      System.out.println("\nShould list somehow and show has resized down to 10 slots:"); 
      E.list(); 
      
   } 
   
}

