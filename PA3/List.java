// Alston Huang
// 1471706
// CMPS101 PA1
// ADT functions for list
// List.java

class List{
	private class Node{
		//fields
		Object data;
		Node next;
		Node prev;

		//constructor
		public Node(Object data){
			this.data = data;
			next = null;
			prev = null;
		}

		public String toString(){
			return String.valueOf(data);
		}		

		public boolean equals(Object x){
			boolean eq = false;
			Node that;
			if(x instanceof Node){
				that = (Node) x;
				eq = (this.data == that.data);
			}
			return eq;
		}
	}

	//fields for list
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	private int index;

	//constructor for list
	List(){
		front = back = null;
		cursor = null;
		length = 0;
		index = -1;
	}

	//Access functions
	int length(){
		return length;
	}

	int index(){
		if(index > length-1){
			index = -1;
		}

		return index;
	}

	public Object front(){
		if(length>0)
			return front.data;
		else
			throw new RuntimeException("Cannot front() on empty list.");
	}

	public Object back(){
		if(length>0)
			return back.data;
		else 
			throw new RuntimeException("Cannot back() on empty list.");
	}

	public Object get(){
		if(index >= 0 && length >= 0)
			return cursor.data;
		else 
			throw new RuntimeException("Cannot get() on empty list.");
	}

	public boolean equals(List L){
		boolean eq = true;
		Node a = front;
		Node b = L.front;
		if(length == L.length()){
			while(eq && a!=null){
				eq = a.data == b.data;
				a = a.next;
				b = b.next;
			}
		}
		return eq;
	}

	//Manipulation procedures
	void clear(){
		front = back = null;
		index = -1;
		length = 0;
	}

	void moveFront(){
		if(length > 0){
			cursor = front;
			index = 0;
		}
	}

	void moveBack(){
		if(length > 0){
			cursor = back;
			index = length-1;
		}
	}

	void movePrev(){
		if( index <= length - 1 && index > 0){
			cursor = cursor.prev;
			index--;
		}
		else{
			index = -1;
			cursor = null;
		}
	}

	void moveNext(){
		if(cursor != null && cursor != back){
			cursor = cursor.next;
			index++;
		}
		else if(cursor == back){
			cursor = null;
			index = -1;
		}
		else{
			//do nothing
		}
	}

	void prepend(Object data){
		Node temp = new Node(data);
		if(length == 0){
			front = back = temp;
		}
		else{
			temp.next = front;
			front.prev = temp;
			front = temp;
			index++;
		}

		length++;
	}

	void append(Object data){
		Node temp = new Node(data);
		if(length == 0){
			front = back = temp;
		}
		else{
			back.next = temp;
			temp.prev = back;
			back = temp;
		}
		length++;
	}

	void insertBefore(Object data){
		Node temp = new Node(data);
		Node holdsThePrev;
		if(length<=0 || index<0)
			throw new RuntimeException("Cannot insertBefore() on empty list.");
		if(index == 0)
			prepend(data);
		else{
			temp.next = cursor;
			temp.prev = cursor.prev;
			holdsThePrev = cursor.prev;
			holdsThePrev.next = temp;
			cursor.prev = temp;
			index++;
			length++;
		}
	}

	void insertAfter(Object data){
		Node temp = new Node(data);
		Node holdTheNext;
		if(length<=0 || index<0)
			throw new RuntimeException("Cannot insertAfter() on empty list.");
		if(index == length - 1)
			append(data);
		else if(index >= length)
			throw new RuntimeException("insertAfter() called on end of list.");
		else{
			temp.prev = cursor;
			temp.next = cursor.next;
			holdTheNext = cursor.next;
			cursor.next = temp;
			holdTheNext.prev = temp;
			length++;
		}
	}

	void deleteFront(){
		if(length<=0)
			throw new RuntimeException("Cannot deleteFront() on empty list.");
		Node temp;
		temp = front.next;
		temp.prev = null;
		front = temp;
		length--;
	}

	void deleteBack(){
		if(length<=0)
			throw new RuntimeException("Cannot deleteBack() on empty list.");
		Node temp;
		temp = back.prev;
		temp.next = null;
		back = temp;
		length--;			
	}

	void delete(){
		if(length<=0 || index < 0)
			throw new RuntimeException("Cannot delete() on empty list.");
		if(index == 0)
			deleteFront();
		else if( index == length-1 )
			deleteBack();
		else{
			Node temp = cursor.prev;
			Node holdTheNext = cursor.next;
			temp.next = holdTheNext;
			holdTheNext.prev = temp;
			index = -1;
			cursor = null;
			length--;
		}
	}

	// List copy(){
	// 	List listTemp = new List();
	// 	Node nodeTemp = front;
	// 	while(nodeTemp != null){
	// 		listTemp.append(nodeTemp.data);
	// 		nodeTemp = nodeTemp.next;
	// 	}
	// 	return listTemp;
	// }

	public String toString(){
		String str = "";
		for(Node N=front; N!=null; N=N.next){
			str += N.toString() + " ";
		}
		return str;
	}
}