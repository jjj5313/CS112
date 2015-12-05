
public class MyString  implements Stringy {
	
	private Node head = new Node(' ', null);
	
	private static class Node{
		public char data;
		public Node next;
		
		public Node(char d, Node n){
			data = d;
			next = n;
		}
		
	}
	
	public MyString () { // Constructor for a MyString; returns an empty MyString (list of chars is null)
		 
	}  
	
	public MyString (String _str){
		Node pre = head;
		for(int i=0;i<_str.length();i++){
			Node cur = new Node(_str.charAt(i), null);
			pre.next = cur;
			pre  = cur;
		}
	}
	
	@Override
	public char charAt(int index) {
		
		if(index<0 || index>=this.length()){
			return ' ';
		}else{
			Node cur = head.next;
			char res =' ';
			int i=0;
			while(cur!=null){
				if(i==index){
					res = cur.data;
					break;
				}else{
					i++;
					cur = cur.next;
				}
			}
			return res;
		}
	}

	@Override
	public int indexOf(char ch) {
		
		Node cur = head.next;
		int res = -1;
		int i=0;
		while(cur!=null){
			if(cur.data == ch){
				res = i;
				break;
			}else{
				i++;
				cur = cur.next;
			}
		}
		return res;
	}

	@Override
	public int length() {
		
		Node cur = head.next;
		int res = 0;
		while(cur!=null){
			cur = cur.next;
			res++;
		}
		return res;
	}

	@Override
	public int indexOf(MyString  str) {
		
		int res = -1;
		for(int i=0;i<this.length()-str.length();i++){
			MyString sub = this.substring(i, i+str.length());
			if(str.compareTo(sub)==0){
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public MyString replace(char oldChar, char newChar) {
		
		MyString res = new MyString(this.toString());
		Node cur = res.head.next;
		while(cur!=null){
			if(cur.data == oldChar){
				cur.data = newChar;
			}
			cur = cur.next;
		}
		return res;
	}

	@Override
	public int compareTo(MyString  anotherMyString) {
		
		for(int i=0;i<this.length() && i<anotherMyString.length();i++){
			if(this.charAt(i)>anotherMyString.charAt(i)){
				return 1;
			}else if(this.charAt(i)<anotherMyString.charAt(i)){
				return -1;
			}
		}
		if(this.length()>anotherMyString.length()){
			return 1;
		}else if(this.length()<anotherMyString.length()){
			return -1;
		}else{
			return 0;
		}
	}

	@Override
	public MyString  concat(MyString  str) {
		
		MyString res = new MyString(this.toString());
		Node cur = res.head;
		while(cur.next!=null){
			cur = cur.next;
		}
		Node pre = cur;
		for(int i=0;i<str.length();i++){
			cur = new Node(str.charAt(i),null);
			pre.next = cur;
			pre = cur;
		}
		return res;
	}

	@Override
	public MyString  substring(int beginIndex, int endIndex) {
		
		if(beginIndex<0 || endIndex>this.length() || (beginIndex>=endIndex)){
			return null;
		}else{
			char[] arr = new char[endIndex-beginIndex];
			for(int i=beginIndex;i<endIndex;i++){
				arr[i-beginIndex] = this.charAt(i);
			}
			return new MyString(new String(arr));
		}
	}

	@Override
	public int valueOf() {
		
		int v;
		int base = 1;
		v = 0;
		if(this.charAt(0)!=' '){
			if(this.charAt(0)=='-'){
				for(int i=this.length()-1;i>0;i--){
					v+=(this.charAt(i)-'0')*base;
					base*=10;
				}
				return -v;
			}else{
				for(int i=this.length()-1;i>=0;i--){
					if(this.charAt(i)>='0' && this.charAt(i)<='9'){
						v+=(this.charAt(i)-'0')*base;
						base*=10;
					}else{
						return Integer.MIN_VALUE;
					}
				}
			}
			return v;
		}
		return Integer.MIN_VALUE;
	}
	
	public MyString  clone(){
		
		MyString res = new MyString(this.toString());
		return res;
		
	}
	
	 public String toString() {
		
		 String str = "";
		 for(int i=0;i<this.length();i++){
			 str+= this.charAt(i);
		 }
		 return str;
	 }           

}
