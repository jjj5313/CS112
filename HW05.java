/* Yuanli Zhong
 * BUID:U79526308
 */

public class HW05 {
  private static class Node { // Node class for LLs
    int item;
    Node next;
    
    private Node() {
      item = 0;
      next = null;
    };
    
    private Node(int n) {
      item = n;
      next = null;
    };
    
    private Node(int n, Node p) {
      item = n;
      next = p;
    }
    
    private static Node split(Node p) {
      int len = length(p);
      return splitHelper(p, len / 2 - 1);
    }
    
    private static int length(Node p) {
      if (p == null)
        return 0;
      else
        return 1 + length(p.next);
    }
    
    private static Node splitHelper(Node p, int i) {
      if (i == 0) {
        Node temp = p.next;
        p.next = null;
        return temp;
      } else
        return splitHelper(p.next, i - 1);
    }
    
    private static Node append(Node p, Node q) {
      if (p == null)
        return q;
      else {
        p.next = append(p.next, q);
        return p;
      }
    }
    
    private static Node insertInOrder(int k, Node p) {
      if (p == null || p.item >= k)
        return new Node(k, p);
      else {
        p.next = insertInOrder(k, p.next);
        return p;
      }
    }
    
    private static Node copy(Node p) {
      if (p == null)
        return null;
      else
        return new Node(p.item, copy(p.next));
    }
  }
  
  private static void printList(Node p) {
    if (p == null)
      System.out.println("null");
    else {
      System.out.print((char) p.item);
      if (p.next != null) {
        printList(p.next);
      } else
        System.out.println();
    }
  }
  
  /**
   * problem 2
   */
  
  private static Node spliceAfter(Node p, int k, Node q) {
    if (p == null || q == null) {
      return p;
    } else {
      return spliceAfterHelper(p, k, q);
    }
  }
  
  private static Node spliceAfterHelper(Node p, int k, Node q) {
    Node head;
    if (p != null && q != null)
      if (p.item == k) {
      head = p.next;
      p.next = Node.append(q, head);
      return p;
    } else {
      spliceAfterHelper(p.next, k, q);
      return p;
    }
    return p;
  }
  
  /**
   * problem3
   */
  
  private static Node replace(Node p, Node old, Node neuw) {
    if (p == null)
      return p;
    if (neuw == null) {
      return removePrefix(p, Node.length(old));
    }
    return replaceHelper(p, old, neuw);
  }
  
  private static Node replaceHelper(Node p, Node old, Node neuw) {
    if (prefix(old, p)) {
      p = Node.append(neuw, removePrefix(p, Node.length(old)));
      return p;
    } else {
      p.next = replace(p.next, old, neuw);
      return p;
    }
    
  }
  
  private static boolean prefix(Node p, Node q) {
    if (p == null)
      return true;
    if (p.item == q.item) {
      return prefix(p.next, q.next);
    }
    return false;
  }
  
  private static Node removePrefix(Node p, int k) {
    if (k == 0)
      return p;
    else
      p = removePrefix(p.next, k - 1);
    return p;
  }
  
  /**
   * problem 4
   */
  
  private static boolean subsequence(Node p, Node q) {
    if (p == null)
      return true;
    if (q == null)
      return false;
    if (p.item == q.item)
      return subsequence(p.next, q.next);
    if (p.item != q.item)
      return subsequence(p, q.next);
    return false;
  }
  
  /**
   * problem 5
   */
  
  private static Node insertionSort(Node p) {
    if (p == null)
      return p;
    else
      return insertionSortHelper(p);
  }
  
  private static Node insertionSortHelper(Node p) {
    if (p.next == null) {// the end
      return Node.insertInOrder(p.item, p.next);
    } else {
      p.next = insertionSortHelper(p.next);
    }
    return Node.insertInOrder(p.item, p.next);
  }
  
  /**
   * problem 6
   */
  
  private static Node selectionSort(Node p) {
    if (p == null)
      return p;
    else
      return selectionSortHelper(p);
  }
  
  private static Node selectionSortHelper(Node p) {
    if (p == null)
      return p;// the end
    Node temp = bringMinToHead(p);
    p.item = temp.item;
    p.next = selectionSort(p.next);
    return p;
    
  }
  
  private static Node bringMinToHead(Node p) {
    if (p.next != null) {
      p.next = bringMinToHead(p.next);
    } else {
      return p;
    }
    if (p.next.item < p.item) { // swap
      int temp = p.next.item;
      p.next.item = p.item;
      p.item = temp;
    }
    return p;
  }
  
  /**
   * problem 7
   */
  
  private static Node mergeSort(Node p) {
    if (p.next == null)
      return p;
    Node top = p;
    Node after = Node.split(top);
    
    Node p1 = mergeSort(top);
    Node p2 = mergeSort(after);
    
    return mergeSortHelper(p1, p2);
  }
  
  private static Node mergeSortHelper(Node p, Node q) {
    if (p == null)
      return q;
    else if (q == null)
      return p;
    else if (p.item < q.item) {
      p.next = mergeSortHelper(p.next, q);
      return p;
    } else {
      q.next = mergeSortHelper(p, q.next);
      return q;
    }
  }
  
  /**
   * unit test main
   */
  public static void main(String[] args) {
    Node p = new Node('a', new Node('b', new Node('b', new Node('a'))));
    Node p2 = new Node('b', new Node('b', new Node('a')));
    Node p3 = new Node('b', new Node('a', new Node('b', new Node('a'))));
    Node p4 = new Node('a', new Node('X', new Node('b', new Node('b',
                                                                 new Node('Y', new Node('c', new Node('c', new Node('Z',
                                                                                                                    new Node('c')))))))));
    Node p5 = new Node('X', new Node('a', new Node('c', new Node('b',
                                                                 new Node('Z', new Node('x', new Node('x', new Node('Y'))))))));
    Node q = new Node('X', new Node('Y', new Node('Z')));
    Node r = new Node('a', new Node('b'));
    Node s = new Node('b', new Node('a'));
    
    // problem2
    System.out.println("\nspliceAfter(null, 'a', q)...   Should print out:\nnull"); 
    printList(spliceAfter(null, 'a', Node.copy(q)));
    System.out.println("\nspliceAfter(p, 'a', null)...   Should print out:\nabba"); 
    printList(spliceAfter(Node.copy(p), 'a', null)); 
    System.out.println("\nspliceAfter(p, 'c', q)...   Should print out:\nabba"); 
    printList(spliceAfter(Node.copy(p), 'c', Node.copy(q)));
    System.out.println("\nspliceAfter(p2, 'a', q)...   Should print out:\nbbaXYZ"); 
    printList(spliceAfter(Node.copy(p2), 'a', Node.copy(q)));
    System.out.println("\nspliceAfter(p3, 'a', q)...   Should print out:\nbaXYZba"); 
    printList(spliceAfter(Node.copy(p3), 'a', Node.copy(q))); 
    
    // problem3
    System.out.println("\nreplace(null, r, q)...   Should print out:\nnull"); 
    printList(replace(null, Node.copy(r), Node.copy(q)));
    System.out.println("\nreplace(p, r, null)...   Should print out:\nba"); 
    printList(replace(Node.copy(p), Node.copy(r), null)); 
    System.out.println("\nreplace(p, r, q)...   Should print out:\nXYZba"); 
    printList(replace(Node.copy(p), Node.copy(r), Node.copy(q))); 
    System.out.println("\nreplace(p2, s, q)...   Should print out:\nbXYZ"); 
    printList(replace(Node.copy(p2), Node.copy(s), Node.copy(q)));
    System.out.println("\nreplace(p3, r, q)...   Should print out:\nbXYZa"); 
    printList(replace(Node.copy(p3), Node.copy(r), Node.copy(q)));
    
    // problem4
    System.out.println("\nsubsequence(null, p)...   Should print out:\ntrue"); 
    System.out.println(subsequence(null, Node.copy(p)));
    System.out.println("\nsubsequence(p, null)...   Should print out\nfalse"); 
    System.out.println(subsequence(Node.copy(p), null));
    System.out.println("\nsubsequence(p, q)...   Should print out:\nfalse"); 
    System.out.println(subsequence(Node.copy(p), Node.copy(q)));
    System.out.println("\nsubsequence(p2, p)...   Should print out:\ntrue"); 
    System.out.println(subsequence(Node.copy(p2), Node.copy(p)));
    System.out.println("\nsubsequence(q, p4)...   Should print out:\ntrue"); 
    System.out.println(subsequence(Node.copy(q), Node.copy(p4))); 
    System.out.println("\nsubsequence(q, p5)...   Should print out:\nfalse"); 
    System.out.println(subsequence(Node.copy(q), Node.copy(p5)));
    
    // problem5
    System.out.println("\ninsertionSort(p5)...   Should print out:\nXYZabcxx"); 
    printList(insertionSort(Node.copy(p5)));
    
    // problem6
    System.out.println("\nselectionSort(p5)...   Should print out:\nXYZabcxx"); 
    printList(selectionSort(Node.copy(p5)));
    
    // problem7
    System.out.println("\nmergeSort(p5)...   Should print out:\nXYZabcxx"); 
    printList(mergeSort(Node.copy(p5)));
  }
}