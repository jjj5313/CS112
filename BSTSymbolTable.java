interface SymbolTable {
	public void insert(String key, int val); // insert a new (key,value) pair in
												// the table

	public int find(String key) throws undefinedVariableException; // return
																	// value
																	// associated
																	// with key

	public boolean member(String key); // find out if a key is in the table

	public void delete(String key);

	public boolean isEmpty();

	public int size();

	public void init(); // reset the iterator to point to the minimum key

	public boolean hasNext(); // find out if there are any other nodes to
								// iterate through

	public Node next(); // save a pointer to the current node, set the next
						// pointer to the next
} // Node in order, and then return the pointer the current node

class BSTSymbolTable implements SymbolTable {

	private Node root;
	private int size;
	private Node head;
	private Node curNode;

	public BSTSymbolTable() {
		curNode = head = new Node(null, 0);
	}

	@Override
	public void insert(String key, int val) {
		if (key == null)
			return;
		root = insert(root, key, val, head);
		size++;
	}

	// recursively insert node into table
	private Node insert(Node node, String key, int val, Node prev) {
		Node newNode = null;
		if (node == null) {
			newNode = new Node(key, val);
			if (prev.getNext() == null) {
				prev.setNext(newNode);
			} else {
				Node temp = prev.getNext();
				prev.setNext(newNode);
				newNode.setNext(temp);
			}
			return newNode;
		}
		int result = key.compareTo(node.getKey());
		if (result < 0) {
			// insert node to left subtree
			newNode = insert(node.getLeft(), key, val, prev);
			node.setLeft(newNode);
		} else if (result > 0) {
			// insert node to right subtree
			newNode = insert(node.getRight(), key, val, node);
			node.setRight(newNode);
		} else {
			// replace old key and value if the key already exists
			node.setKey(key);
			node.setVal(val);
		}
		return node;
	}

	@Override
	public int find(String key) throws undefinedVariableException {
		return find(root, key);
	}

	// recursively find node with given key in tree
	private int find(Node node, String key) throws undefinedVariableException {
		if (node == null)
			throw new undefinedVariableException();
		int result = key.compareTo(node.getKey());
		if (result < 0) {
			// find node in the left subtree
			return find(node.getLeft(), key);
		} else if (result > 0) {
			// find node in the right subtree
			return find(node.getRight(), key);
		}
		return node.getVal();
	}

	@Override
	public boolean member(String key) {
		return member(root, key);
	}

	// recursively check whether node with given key exists in tree
	private boolean member(Node node, String key) {
		if (node == null)
			return false;
		int result = key.compareTo(node.getKey());
		if (result < 0) {
			return member(node.getLeft(), key);
		} else if (result > 0) {
			return member(node.getRight(), key);
		}
		return true;
	}

	@Override
	public void delete(String key) {
		root = delete(root, key, head);
	}

	// recursively delete node from tree with given key
	private Node delete(Node node, String key, Node prev) {
		if (node == null)
			return node;
		int result = key.compareTo(node.getKey());
		if (result < 0) {
			// find and delete node in the left subtree
			Node delNode = delete(node.getLeft(), key, prev);
			node.setLeft(delNode);
		} else if (result > 0) {
			// find and delete node in the right subtree
			Node delNode = delete(node.getRight(), key, node);
			node.setRight(delNode);
		} else {
			// node with given key wad found
			if (node.getLeft() != null && node.getRight() != null) {
				// find maximum node in the left subtree
				Node max = findMax(node.getLeft());
				node.setKey(max.getKey());
				node.setVal(max.getVal());
				node.setLeft(delete(node.getLeft(), max.getKey(), prev));
			} else {
				size--;
				Node replace = null;
				if (node.getLeft() == null) {
					replace = node.getRight();
				} else {
					replace = node.getLeft();
				}
				prev.setNext(node.getNext());
				return replace;
			}
		}
		return node;
	}

	// recursively find maximum node
	private Node findMax(Node node) {
		if (node.getRight() == null)
			return node;
		else
			return findMax(node.getRight());
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void init() {
		curNode = head;
	}

	@Override
	public boolean hasNext() {
		return (curNode.getNext() != null);
	}

	@Override
	public Node next() {
		if (hasNext()) {
			curNode = curNode.getNext();
		}
		return curNode;
	}
}

class undefinedVariableException extends Exception {
}

class Node {

	private String key;

	private int val;

	private Node left;

	private Node right;

	private Node next;

	public Node(String key, int val) {
		this.key = key;
		this.val = val;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
