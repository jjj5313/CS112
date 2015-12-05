/* File: PriorityQueueGradingClient.java
 * Date: 9/19/14
 * Author:  Wayne Snyder (snyder@bu.edu)
 * Class: CS 112, Fall, 2014
 * Purpose: Grading client for problem B.2 in HW 02
 */

public class PriorityQueueGradingClient {
   
   public static void main(String [] args) {
      
      System.out.println("Testing using Character...");
      GenericPriorityQueue<Character> P = new GenericPriorityQueue<Character>();
      
      P.insert('B');
      P.insert('A');
      P.insert('C');
      System.out.println("\nShould print out:\nC B");
      System.out.print(P.getMax() + " ");   
      System.out.println(P.getMax());  
      P.insert('E');
      P.insert('A');
      System.out.println("\nShould print out:\nE A A");
      while(!P.isEmpty()) {
         System.out.print(P.getMax() + " ");   
      }
      System.out.println();
      
      System.out.println("\nTesting using OneInt...");
      GenericPriorityQueue<OneInt> R = new GenericPriorityQueue<OneInt>();   
      
      R.insert(new OneInt(4)); 
      R.insert(new OneInt(5));
      R.insert(new OneInt(2));
      System.out.println("\nShould print out:\n(5)");
      System.out.println(R.getMax());
      R.insert(new OneInt(3));
      
      System.out.println("\nShould print out:\n(4)");
      System.out.println(R.getMax());
      
      R.insert(new OneInt(1));
      R.insert(new OneInt(6));
      
      System.out.println("\nShould print out:\n(6) (3) (2) (1)");
      while(!R.isEmpty()) {
         System.out.print(R.getMax() + " ");
      }
      System.out.println(); 
      
      System.out.println("\nTesting using Pair...");
      GenericPriorityQueue<Pair> S = new GenericPriorityQueue<Pair>();   
      
      S.insert(new Pair(2,2)); 
      S.insert(new Pair(5,1));
      S.insert(new Pair(2,4));
      System.out.println("\nShould print out:\n(5,1)");
      System.out.println(S.getMax());
      S.insert(new Pair(2,2));
      S.insert(new Pair(2,1));
      System.out.println("\nShould print out:\n(2,4)");
      System.out.println(S.getMax());
      
      
      S.insert(new Pair(1,9));
      S.insert(new Pair(6,1));
      
      System.out.println("\nShould print out:\n(6,1) (2,2) (2,2) (2,1) (1,9) ");
      while(!S.isEmpty()) {
         System.out.print(S.getMax() + " ");   
      }
      System.out.println();      
      
   } 
   
}

