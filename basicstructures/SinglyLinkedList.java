package basicstructures;

import java.util.Iterator;

public class SinglyLinkedList<T> extends Sequence<T>{
	
	private int size;
	private Node head;
	
	public SinglyLinkedList() {
		size = 0;
		head = null;
	}

	public Iterator<T> iterator() {
		Iterator<T> temp = new Iterator<T>() {
		
			private Node position = head;
			
			@Override
			public boolean hasNext() {
				return position != null;
			}
	
			@Override
			public T next() {
				T temp = position.value;
				position = position.next;
				return temp;
			}
		};
	
		return temp;
	}

	public void addStart(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		
		head = new Node(head, object);
		size++;
	}

	public void addLast(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		
		if(isEmpty())
			addStart(object);
		else 
		{
			Node position = head;
			for(int i = 1 ; i < size ; i++)
				position = position.next;
			
			position.next = new Node(null, object);
			size++;
		}
	}

	public void addAtIndex(T object, int index) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if(this.isEmpty() || index == 0)
			addStart(object);
		else if (index == size)
			addLast(object);
		else
		{
			Node position = head;
			for(int i = 1 ; i < index ; i++)
				position = position.next;
			
			position.next = new Node(position.next, object);
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
		{
			Node position = head;
			for(int i = 1 ; i < size ; i++)
				position = position.next;
			
			return position.value;
		}
	}

	public T getAtIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		if(isEmpty())
			return null;
		else if (index == 0)
			return getFirst();
		else if (index == size - 1)
			return getLast();
		else
		{
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			
			return position.value;
		}
	}

	public T removeFirst() {
		if(isEmpty())
			return null;
		else
		{
			T temp = head.value;
			head = head.next;
			size--;
			return temp;
		}
	}

	public T removeLast() {
		if(isEmpty())
			return null;
		else if (size == 1)
			return removeFirst();
		else
		{
			Node position = head;
			for(int i = 0 ; i < size - 2 ; i ++)
				position = position.next;
			T temp = position.next.value;
			position.next = null;
			size--;
			return temp;
		}
	}

	public T removeAtIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		if(isEmpty())
			return null;
		else if(index == 0)
			return removeFirst();
		else if(index == size - 1)
			return removeLast();
		else
		{
			Node position = head;
			for(int i = 1 ; i < index ; i++)
				position = position.next;
			T temp = position.next.value;
			position.next = position.next.next;
			size--;
			return temp;
		}
	}

	public T replaceAtIndex(T object, int index) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		Node position = head;
		for(int i = 0 ; i < index ; i++)
			position = position.next;
		T temp = position.value;
		position.value = object;
		return temp;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getIndexOf(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		if(isEmpty())
			return -1;
		
		// Go through all nodes.
		Node position = head;
		int index = 0;
		while(position != null) {
			// If one node matches, return its index.
			if(position.value.equals(object))
				return index;
			// Otherwise keep searching.
			position = position.next;
			index++;
		}
		
		// Return -1 if no matches were found.
		return -1;
	}

	public boolean contains(T object) {
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
		
		// Uses getIndexOf to search and see if it is contained.
		return (getIndexOf(object) != -1);
	}
	
	public String toString() 
	{
		if(isEmpty())
			return "";
		if(size == 1)
			return head.value.toString();
		
		// Go through the entire sequence and add the strings of the nodes.
		Node position = head;
		String output = "";
		while(position != null) 
		{
			output += position.toString();
			position = position.next;
			if(position != null)
				output += " --> ";
		}
		return output;
	}
	
	private class Node {
		private Node next;
		private T value;
		
		public Node(Node next, T value) {
			this.next = next;
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object anotherObject) {
			if(anotherObject == null) return false;
			if(anotherObject.getClass() != this.getClass()) return false;
			else {
				Node anotherNode = (Node) anotherObject;
				return anotherNode.value.equals(this.value);
			}
		}
		
		public String toString() {
			return value.toString();
		}
	}
}
