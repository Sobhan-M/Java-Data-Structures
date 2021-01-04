package basicstructures;

/** A stack that features the basic push/pop methods.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public class Stack<T> 
{
	private Sequence<T> list;
	
	/**
	 * Default constructor. Uses a doubly linked list internally.
	 */
	public Stack() 
	{
		list = new DoublyLinkedList<T>();
	}
	
	/** A constructor to allow the user to create the stack with their desired sequence 
	 * (ArrayList, SinglyLinkedList, or DoublyLinkedList). 
	 * @param sequence The type of sequence desired.
	 */
	public Stack(Sequence<T> sequence)
	{
		// Check for DoublyLinkedList.
		if (sequence.getClass() == (new DoublyLinkedList<T>()).getClass())
		{
			list = new DoublyLinkedList<T>();
		}
		// Check for SinglyLinkedList.
		else if (sequence.getClass() == (new SinglyLinkedList<T>()).getClass())
		{
			list = new SinglyLinkedList<T>();
		}
		// Check for ArrayList.
		else if (sequence.getClass() == (new ArrayList<T>()).getClass())
		{
			list = new ArrayList<T>();
		}
		else
			throw new IllegalArgumentException("The argument did not match any known sequence implementations.");
	}
	
	/** Add an object to the top of the stack.
	 * @param object The object being added to the stack.
	 */
	public void push(T object)
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		list.addStart(object);
	}
	
	/** Remove and return the object at the top of the stack.
	 * @return The object at the top of the stack.
	 */
	public T pop()
	{
		return list.removeFirst();
	}
	
	/** Return the object at the top of the stack. This is the object that will be removed with pop().
	 * @return The object at the top of the stack.
	 */
	public T peek()
	{
		return list.getFirst();
	}
	
	/** Return the current size of the stack.
	 * @return The size of the stack.
	 */
	public int size()
	{
		return list.size();
	}
	
	/** Return whether the stack is empty or not.
	 * @return True if empty. False if not empty.
	 */
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject)
	{
		// Check for null and class.
		if(anotherObject == null)
			return false;
		else if(anotherObject.getClass() != this.getClass())
			return false;
		else
		{
			// Compare the lists underneath.
			Stack<T> anotherStack = (Stack<T>) anotherObject;
			return this.list.equals(anotherStack.list);
		}
	}
	
	public String toString()
	{
		return list.toString();
	}
}
