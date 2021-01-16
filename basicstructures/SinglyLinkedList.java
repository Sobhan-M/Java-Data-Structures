package basicstructures;

import java.util.Iterator;

/** A singly linked list.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public class SinglyLinkedList<T> extends Sequence<T>
{
	// Attributes.
	private int size;
	private Node head;
	
	/**
	 * Default constructor.
	 */
	public SinglyLinkedList() 
	{
		size = 0;
		head = null;
	}

	public Iterator<T> iterator() 
	{
		// Anonymous iterator class.
		Iterator<T> temp = new Iterator<T>() 
		{
		
			private Node position = head;
			
			public boolean hasNext() 
			{
				return position != null;
			}
	
			// Return current value before going to next.
			public T next() 
			{
				if (!hasNext())
					throw new IndexOutOfBoundsException("Iterator has no next!");
				
				T temp = position.value;
				position = position.next;
				return temp;
			}
		};
	
		return temp;
	}

	public void addStart(T object) 
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		
		// Add to start and create new head.
		head = new Node(head, object);
		size++;
	}

	public void addLast(T object) 
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		
		if(isEmpty())
			addStart(object);
		else 
		{
			// Go to the end.
			Node position = head;
			for(int i = 1 ; i < size ; i++)
				position = position.next;
			
			// Add new node.
			position.next = new Node(null, object);
			size++;
		}
	}

	public void addAtIndex(T object, int index) 
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		// Special cases.
		if(this.isEmpty() || index == 0)
			addStart(object);
		else if (index == size)
			addLast(object);
		else
		{
			// Go to the node.
			Node position = head;
			for(int i = 1 ; i < index ; i++)
				position = position.next;
			
			// Add node.
			position.next = new Node(position.next, object);
			size++;
		}
	}

	public T getFirst() 
	{
		if(isEmpty())
			return null;
		else
			return head.value;
	}

	public T getLast() 
	{
		if(isEmpty())
			return null;
		else
		{
			// Go to the end.
			Node position = head;
			for(int i = 1 ; i < size ; i++)
				position = position.next;
			
			// Return value.
			return position.value;
		}
	}

	public T getAtIndex(int index) 
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		// Special cases.
		if(isEmpty())
			return null;
		else if (index == 0)
			return getFirst();
		else if (index == size - 1)
			return getLast();
		else
		{
			// Go to the index.
			Node position = head;
			for (int i = 0 ; i < index ; i++)
				position = position.next;
			
			// Return the value.
			return position.value;
		}
	}

	public T removeFirst() 
	{
		if(isEmpty())
			return null;
		else
		{
			// Remove head, assign new head, and return the old head.
			T temp = head.value;
			head = head.next;
			size--;
			return temp;
		}
	}

	public T removeLast() 
	{
		// Special cases.
		if(isEmpty())
			return null;
		else if (size == 1)
			return removeFirst();
		else
		{
			// Go to 1 less than the last.
			Node position = head;
			for(int i = 0 ; i < size - 2 ; i ++)
				position = position.next;
			
			// Remove the node and return it.
			T temp = position.next.value;
			position.next = null;
			size--;
			return temp;
		}
	}

	public T removeAtIndex(int index) 
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		// Special cases.
		if(isEmpty())
			return null;
		else if(index == 0)
			return removeFirst();
		else if(index == size - 1)
			return removeLast();
		else
		{
			// Go to 1 less than the index.
			Node position = head;
			for(int i = 1 ; i < index ; i++)
				position = position.next;
			
			// Remove and return the node.
			T temp = position.next.value;
			position.next = position.next.next;
			size--;
			return temp;
		}
	}

	public T replaceAtIndex(T object, int index) 
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		// Go to the index.
		Node position = head;
		for(int i = 0 ; i < index ; i++)
			position = position.next;
		
		// Replace the value and return the old one.
		T temp = position.value;
		position.value = object;
		return temp;
	}

	public int size() 
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public int indexOf(T object) 
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed!");
		if(isEmpty())
			return -1;
		
		// Go through all nodes.
		Node position = head;
		int index = 0;
		while(position != null) 
		{
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

	public boolean contains(T object) 
	{
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
		
		// Uses getIndexOf to search and see if it is contained.
		return (indexOf(object) != -1);
	}
	
	public String toString() 
	{
		if(isEmpty())
			return "";
		if(size == 1)
			return head.value.toString();
		
		// Go through the entire sequence and add the strings of the nodes.
		Node position = head;
		StringBuilder output = new StringBuilder();
		while(position != null) 
		{
			output.append(position.toString());
			position = position.next;
			if(position != null)
				output.append(" --> ");
		}
		return output.toString();
	}
	
	/** Internal node for containing data in the list.
	 * @author Sobhan Mehrpour
	 */
	private class Node {
		private Node next;
		private T value;
		
		/** Default constructor.
		 * @param next The next node in the list.
		 * @param value The value contained in this node.
		 */
		public Node(Node next, T value) 
		{
			this.next = next;
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object anotherObject) 
		{
			// Check for null and for the right class.
			if(anotherObject == null) 
				return false;
			if(anotherObject.getClass() != this.getClass()) 
				return false;
			else 
			{
				// Check equality of the value.
				Node anotherNode = (Node) anotherObject;
				return anotherNode.value.equals(this.value);
			}
		}
		
		public String toString() 
		{
			return value.toString();
		}
	}
}
