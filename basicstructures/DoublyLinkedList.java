package basicstructures;

import java.util.Iterator;

public class DoublyLinkedList<T> extends Sequence<T> implements Iterable<DoublyLinkedList<T>.Node> {
	
	private int size;
	private Node head;
	private Node tail;
	
	public DoublyLinkedList(){
		size = 0;
		head = tail = null;
	}
	
	public void addToStart(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		if(isEmpty()) 
			head = tail = new Node(null, null, object);
		else
			head = new Node(null, head, object);
		size++;
	}
	
	public void addToEnd(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		if(isEmpty()) 
			head = tail = new Node(null, null, object);
		else
			tail = new Node(tail, null, object);
		size++;
	}

	public void addAtIndex(T object, int index) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if(isEmpty() || index == 0)
			addToStart(object);
		else if (index == size)
			addToEnd(object);
		else {
			Node position = head;
			for(int i = 0 ; i < index ;i++)
				position = position.next;
			Node temp = new Node(position.prev, position, object);
			position.prev.next = temp;
			position.prev = temp;
			size++;
		}
	}

	public T getFirst() {
		if(isEmpty())
			return null;
		else
			return head.value;
	}

	public T getLast() {
		if(isEmpty())
			return null;
		else
			return tail.value;
	}

	public T getAtIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		if(isEmpty())
			return null;
		if(index == 0)
			return getFirst();
		else if(index == size - 1)
			return getLast();
		else {
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			return position.value;
		}
	}

	public T removeFirst() {
		if(this.isEmpty())
			return null;
		T temp = head.value;
		head = head.next;
		head.prev = null;
		size--;
		return temp;
	}

	public T removeLast() {
		if(this.isEmpty())
			return null;
		T temp = tail.value;
		tail = tail.prev;
		tail.next = null;
		size--;
		return temp;
	}

	public T removeAtIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		if(isEmpty())
			return null;
		if(index == 0)
			return removeFirst();
		else if(index == size - 1)
			return removeLast();
		else {
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			position.next.prev = position.prev;
			position.prev.next = position.next;
			size--;
			return position.value;
		}
	}

	public T replaceAtIndex(T object, int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
		
		if(isEmpty())
			return null;
		if(index == 0){
			T temp = removeFirst();
			addToStart(object);
			return temp;
		}
		else if(index == size - 1) {
			T temp = removeLast();
			addToEnd(object);
			return temp;
		}
		else {
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			T temp = position.value;
			position.value = object;
			return temp;
		}
	}

	public int getIndexOf(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
		
		if(isEmpty())
			return -1;
		
		Node position = head;
		int index = 0;
		while(position.hasNext()) {
			if(position.value.equals(object))
				return index;
			position = position.next;
			index++;
		}
		return -1;
		
	}

	public boolean contains(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
		
		return (getIndexOf(object) != -1);
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<DoublyLinkedList<T>.Node> iterator() {
		return head;
	}
	
	public class Node implements Iterator<DoublyLinkedList<T>.Node>{
		private Node prev;
		private Node next;
		private T value;
		
		public Node(Node aPrev, Node aNext, T aValue){
			prev = aPrev;
			next = aNext;
			value = aValue;
		}

		public boolean hasNext() {
			return(this.next != null);
		}

		public DoublyLinkedList<T>.Node next() {
			return this.next;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object anotherObject) {
			if(anotherObject == null) return false;
			else if (anotherObject.getClass() != this.getClass()) return false;
			else {
				Node anotherNode = (Node) anotherObject;
				return (anotherNode.value.equals(this.value));
			}
		}
		
		public String toString() {
			return this.value.toString();
		}
		
		public T getValue() {
			return this.value;
		}
		
	}

	
}
