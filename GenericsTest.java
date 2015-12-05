/** Yuanli Zhong
  * BU ID: U79526308
  * GenericsTest.java.
  **/
public class GenericsTest<Item extends Comparable<Item>> {  
  public static void main(String [] args) {         
    GenericsTest<Pair> t = new GenericsTest<Pair>();  
    Pair p = new Pair(2,3);         
    Pair q = new Pair(2,4);
    t.test(p,q); 
    t.test(q,p);
    t.test(p,p);
  }          
  
  public void test(Item m, Item n) {         
    if( m.compareTo(n) < 0)            
      System.out.println(m + " < " + n);         
    else if( m.compareTo(n) > 0)
      System.out.println(m + " > " + n); 
    else
      System.out.println(m + " equals " + n); 
  }      
}
