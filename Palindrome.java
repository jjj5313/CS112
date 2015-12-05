/*
 * Palindrome.java
 * 
 * Author: Cody Doucette <doucette@bu.edu>
 * Boston University CS 112, Summer I 2013
 * 
 * Purpose: This is an exercise in debugging by implementing a
 * palindrome-checker using a stack data structure.
 */

import java.util.Stack;               // this is the built-in Java generic Stack

public class Palindrome {
  private boolean debug = true;           // set this to true if you want to trace your execution
  
  private void db(String s) {            
    if(debug) 
      System.out.println("\t" + s);
  }
  /**
   * palindrome: returns a boolean representing whether @str
   * is a palindrome. str should contain only alphanumeric
   * characters.
   */
  private static boolean palindrome(String str) {
    
    Stack <char> s = new Stack<char>();
    
    /* Push first half of str onto stack. */
    for (int i = 0; i < str.length() / 2; i++) {
      s.push(str.charAt(i));
    }
    
    /* Pop second half of str from stack and compare. */
    for (int i = str.length() / 2; i < str.length(); i++) {
      char c = s.pop();
      if (c == str.charAt(i))
        return true;
    }
    
    return false;
  }   
  
  
  public static void main(String[] args) {
    
    System.out.println("Is redder a palindrome? Should be true:");
    System.out.println(palindrome("redder"));
    
    System.out.println("Is reddest a palindrome? Should be false:");
    System.out.println(palindrome("reddest"));
    
    //System.out.println("Is _______ a palindrome? Should be ____:");
    //System.out.println(palindrome(_______));
    
  }
  
}