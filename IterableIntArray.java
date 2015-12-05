/* File: IterableIntArray.java
 * Author: Wayne Snyder
 * Date: 10/16/14
 * Purpose: This is the starter code for Lab 06 on iterators. 
 */

public class IterableIntArray implements MyIterable<Integer> {
  
  /************  Basic definitions for this lab ***************/
  
  
  private Integer[] A = {2, 1, 6, 4, 7, 3, 9, 8, -3, 4}; 
  
  public void init() {
    // your code here
    for (int i = 0; i < A.length; i++) {
      int n = A[i]; 
    }
  }
    
    public boolean hasNext() {
      // your code here
      return (n < A.length); 
    }
    
    public Integer next() {
      // your code here
      return 0;                     // just to get it to compile
    }
    
    
    
    
    public static void main(String[] args) {
      
      
      IterableIntArray it = new IterableIntArray(); 
      
      
      it.init(); 
      System.out.println("Testing iterator: should print out\n2 1 6 4 7 3 9 8 -3 4"); 
      while(it.hasNext()) {
        System.out.print(it.next() + " ");   
      }
      System.out.println(); 
      
      it.init(); 
      System.out.println("\nTesting iterator: should print out\n2 1 6"); 
      for(int i = 0; i < 3; ++i) {
        System.out.print(it.next() + " ");   
      }
      System.out.println();
      
      System.out.println("\nDoing some other stuff before continuing the enumeration....."); 
      
      System.out.println("\nTesting iterator again: should print out\n4 7 3 9"); 
      for(int i = 0; i < 4; ++i) {
        System.out.print(it.next() + " ");   
      }
      System.out.println(); 
      
      System.out.println("\nDoing some other stuff before continuing the enumeration.....");  
      
      System.out.println("\nTesting iterator: should print out\n8 -3 4"); 
      while(it.hasNext()) {
        System.out.print(it.next() + " ");   
      }
      System.out.println();   
      
    }
  }
  
  interface MyIterable<Item> {          // since there is a Java interface called "Iterable" we will use "My...." to distinguish
    
    public void init();                     // Initialize the enumeration
    
    public boolean hasNext();               // Is there a next element in the enumeration?
    
    public Item next();                     // Provide the next element in the enumeration
    
  }