package basicstructures;

/** A queue structure supporting basic enqueue/dequeue methods.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public class Queue<T> 
{
	private Sequence<T> list;
	
	/**
	 * Default constructor that builds the queue with a doubly linked list.
	 */
	public Queue() 
	{
		list = new DoublyLinkedList<T>();
	}
	
	/** Constructor that allows the user to decide their own sequence implementation (SinglyLinkedList, DoublyLinkedList, or ArrayList).
	 * @param sequence The type of sequence desired.
	 */
	public Queue(Sequence<T> sequence)
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
	
	/** Adds an object to the back of the queue.
	 * @param object The object being added to the back of the queue.
	 */
	public void enqueue(T object)
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		list.addStart(object);
	}
	
	/** Returns and remove the object at the front of the queue.
	 * @return The object at the front of the queue.
	 */
	public T dequeue()
	{
		return list.removeLast();
	}
	
	/** Return the object at the front of the queue — the object that will next be returned with dequeue.
	 * @return The object at the front of the queue.
	 */
	public T peekQueueFront()
	{
		return list.getLast();
	}
	
	/** Return the object at the back of the queue — the object that was recently added with enqueue.
	 * @return The object at the front of the queue.
	 */
	public T peekQueueBack()
	{
		return list.getFirst();
	}
	
	/** Returns the current size of the queue.
	 * @return The current size of the queue.
	 */
	public int size()
	{
		return list.size();
	}
	
	/** Return whether the queue is empty or not.
	 * @return True if the queue is empty. False if it is not empty.
	 */
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject)
	{
		if(anotherObject == null)
			return false;
		else if(anotherObject.getClass() != this.getClass())
			return false;
		else
		{
			Queue<T> anotherStack = (Queue<T>) anotherObject;
			return this.list.equals(anotherStack.list);
		}
	}
	
	public String toString()
	{
		return list.toString();
	}
}
