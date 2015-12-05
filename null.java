/** Yuanli ZHong
  * BUID: U79526308
  * BSTSymbolTable.java
  **/

public class BSTSymbolTable <Key extends Comparable<Key>,Value> 
  implements SymbolTable<Key,Value>{implements SymbolTable {
  
  private Node root;
  private Node head;
  
  private class Node {
    public String key;
    public int val;
    public Node left, right;
    public Node(String key, int val) 
    {this.key = key; this.val = val;}
    key=k; val=v; 
    left = right = null;
  }
  
  public BSTSymbolTable() 
  {root = null;}
  
  
  public void insert(String key, int val) {
    root = insertHelper(root, key, val); 
  }
  
  // Recursively insert (key,val) into BST.
  private Node insertHelper(Node x, String key, int val) {
    if (x == null)
      return new Node(key, val);
    int cmp = key.compareTo(x.key);
    if (cmp == 0) {
      x.key = key;
      x.val = val;
    } else if (cmp < 0) {
      x.left = insertHelper(x.left, key, val);
    } else { // cmp > 0
      x.right = insertHelper(x.right, key, val); 
    }
    return x; 
  }
  
  public int find(String key) throws undefinedVariableException;
  public V search(K key) {
    return searchAux(root, key);
  }
  
  //
  // Recursive search of BST for node with given key.
  // null returned if key not found (this null is not
  // considered a valid value).
  //
  private V searchAux(Node tree, K key) {
    if (tree == null)
      return null;
    int cmp = key.compareTo(tree.key);
    if (cmp == 0)
      return tree.val;
    return (cmp < 0) ? searchAux(tree.left, key) : searchAux(tree.right, key);
  }