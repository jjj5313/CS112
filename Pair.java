/* Yuanli Zhong
 * BU ID:U79526308
 * Pair.java
 */
public class Pair implements Comparable<Pair>{
  private int x;
  private int y;
  
  public Pair(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public String toString() {
    return "(" + x + " ," + y + ") "; 
  }
  
  public int compareTo(Pair q) {
    if(x < q.x)
      return -1;
    else if (x > q.x)
      return 1;
    else {
      if(y < q.y)
        return -1;
      else if (y > q.y)
        return 1;
      else {   
        return 0;
      }
    }
  }
}
  
