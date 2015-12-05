/* File: IntStack.java
 * Classes: IntStack, Stack (interface)
 * Date: 10/3/13
 * Class: Boston University CS 112, Fall 2013
 * Author: Wayne Snyder (snyder@bu.edu)
 * Purpose: Example of simple array-based stack for Lab 04
 * 
 * Modified by Yuanli Zhong
 * BUID:U79526308
 */

public class IntStack implements Stack {
  
  private int [] A = new int[5]; 
  
  private int next = 0;                        // location of next available unused slot  
  
  // interface methods
  
  public void push(int key) throws StackOverflowException { 
    if (size() >= 5)
      throw new StackOverflowException("Stack Overflow! Can not push ", key);         // push the key onto the top of the stack 
    else 
      A[next++] = key; 
  }
  
  public int pop() throws StackUnderflowException {            // remove the top integer and return it -- will cause error if empty! 
    if (isEmpty())
      throw new StackUnderflowException("Stack Underflow!");
    else 
      return A[--next];   
  }
  
  public boolean isEmpty() {
    return (next == 0); 
  }
  
  public int size() {                 // how many integers in the stack 
    return next; 
  }
  
  // unit test
  
  public static void main(String [] args) {
    try {
      Stack S = new IntStack();        
      
      
      System.out.println("Pushing 5, 9, 9, -3, 31 then popping and printing it out:"); 
      S.push(5); S.push(9); S.push(9); S.push(-3); S.push(31);
      
      while(!S.isEmpty()) {
        System.out.println(S.pop()); 
      }
      
      
      // this one will cause an error!
      // System.out.println(S.pop());
      
      
      // So will this one!
      System.out.println("Pushing 5, 9, 9, -3, 31, and 99 then popping and printing it out:"); 
      S.push(5); S.push(9); S.push(9); S.push(-3); S.push(31); S.push(99);
      
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    finally {
      System.out.println("Done");
    }
  }
}

class StackUnderflowException extends Exception {
  public String msg;
  public StackUnderflowException(String msg) {
    super(msg);
  }
}
class StackOverflowException extends Exception {
  public int num;
  public StackOverflowException(String msg, int n) {
    super(msg + n);
    num = n;
  }
}

// Stack Interface

interface Stack { 
  public void push(int key) throws StackOverflowException;         // push the key onto the top of the stack 
  public int pop() throws StackUnderflowException;                   // remove the top integer and return it 
  public boolean isEmpty(); 
  public int size();                  // how many integers in the stack 
}
