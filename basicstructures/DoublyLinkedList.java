package basicstructures;

/** A doubly linked list.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public class DoublyLinkedList<T> extends Sequence<T>{
	// Attributes
	private int size;
	private Node head;
	private Node tail;
	
	/**
	 * Default Constructor.
	 */
	public DoublyLinkedList(){
		size = 0;
		head = tail = null;
	}
	
	public void addToStart(T object) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		if(isEmpty())
			// Special case.
			head = tail = new Node(null, null, object);
		else {
			// Adding to start and changing head as needed.
			Node temp = new Node(null, head, object);
			head.prev = temp;
			head = temp;
		}
			
		size++;
	}
	
	public void addToEnd(T object) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		if(isEmpty())
			// Special case.
			head = tail = new Node(null, null, object);
		else {
			// Adding to the end and changing tail as needed.
			Node temp = new Node(tail, null, object);
			tail.next = temp;
			tail = temp;
		}
			
		size++;
	}

	public void addAtIndex(T object, int index) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		// Special cases for start and end.
		if(isEmpty() || index == 0)
			addToStart(object);
		else if (index == size)
			addToEnd(object);
		else {
			// Goes to correct index.
			Node position = head;
			for(int i = 0 ; i < index ;i++)
				position = position.next;
			
			// Adds the object and changes the previous and next nodes' connections.
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
		// Handling exceptional cases.
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		// Special cases for when it is empty, first index, or last.
		if(isEmpty())
			return null;
		if(index == 0)
			return getFirst();
		else if(index == size - 1)
			return getLast();
		else {
			// Go to position then return it.
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			return position.value;
		}
	}

	public T removeFirst() {
		if(this.isEmpty())
			return null;
		if(size == 1) {
			T temp = head.value;
			head = tail = null;
			size--;
			return temp;
		}	
		else {
			// Remove head and assign new head.
			T temp = head.value;
			head = head.next;
			head.prev = null;
			size--;
			return temp;
		}
	}

	public T removeLast() {
		if(this.isEmpty())
			return null;
		if(size == 1) {
			T temp = head.value;
			head = tail = null;
			size--;
			return temp;
		}	
		else {
			// Remove tail and assign new tail.
			T temp = tail.value;
			tail = tail.prev;
			tail.next = null;
			size--;
			return temp;
		}
	}

	public T removeAtIndex(int index) {
		// Handling exceptional cases.
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		// Special cases when empty, first index, or last.
		if(isEmpty())
			return null;
		if(index == 0)
			return removeFirst();
		else if(index == size - 1)
			return removeLast();
		else {
			// Going to the index.
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			
			// Changing connects so this position becomes stranded,
			position.next.prev = position.prev;
			position.prev.next = position.next;
			size--;
			return position.value;
		}
	}

	public T replaceAtIndex(T object, int index) {
		// Handling exceptional cases.
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
		
		// Special cases when empty, first index, or last index.
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
			// Going to the index.
			Node position = head;
			for(int i = 0 ; i < index ; i++)
				position = position.next;
			
			//Changing the value at that index and returning the old one.
			T temp = position.value;
			position.value = object;
			return temp;
		}
	}

	public int getIndexOf(T object) {
		// Handling special cases.
		if(object == null)
			throw new IllegalArgumentException("Null object passed.");
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

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject) {
		// Check to make sure it is not null.
		if(anotherObject == null) return false;
		// Check to make sure it is the right class.
		else if(anotherObject.getClass() != this.getClass()) return false;
		else {
			// Make sure the sizes match.
			DoublyLinkedList<T> anotherList = (DoublyLinkedList<T>) anotherObject;
			if(this.size != anotherList.size) return false;
			else {
				// Compare the sequences of nodes with each other.
				Node position1 = this.head;
				Node position2 = anotherList.head;
				while(position1 != null) {
					// Return false if the nodes don't match at any point.
					if(! position1.equals(position2)) return false;
					position1 = position1.next;
					position2 = position2.next;
				}
				// Return true if all nodes matched.
				return true;
			}
		}
	}
	
	public String toString() {
		if(isEmpty())
			return "";
		if(size == 1)
			return head.value.toString();
		
		// Go through the entire sequence and add the strings of the nodes.
		Node position = head;
		String output = "";
		while(position != null) {
			if(position == tail)
				output += position.toString();
			else
				output += position.toString() + " --> ";
			position = position.next;
		}
		return output;
	}
	
	/** Node class for containing data in the linked list.
	 * @author Sobhan Mehrpour
	 *
	 */
	private class Node {
		// Attributes
		private Node prev;
		private Node next;
		private T value;
		
		/** The constructor.
		 * @param aPrev The previous node.
		 * @param aNext The next node.
		 * @param aValue The value stored at this node.
		 */
		public Node(Node aPrev, Node aNext, T aValue){
			prev = aPrev;
			next = aNext;
			value = aValue;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object anotherObject) {
			// Check for null and the class.
			if(anotherObject == null) return false;
			else if (anotherObject.getClass() != this.getClass()) return false;
			else {
				// Equality is if the values are the same.
				Node anotherNode = (Node) anotherObject;
				return (anotherNode.value.equals(this.value));
			}
		}
		
		public String toString() {
			return this.value.toString();
		}
		
	}
}
