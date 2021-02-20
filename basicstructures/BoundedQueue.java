package basicstructures;

public class BoundedQueue<T> extends Queue<T>
{
	private int capacity;
	private final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Default constructor. Sets capacity to default capacity.
	 */
	public BoundedQueue()
	{
		capacity = DEFAULT_CAPACITY;
	}
	
	/** Sequence constructor.
	 * @param sequence Creates the queue using the given sequence.
	 */
	public BoundedQueue(Sequence<T> sequence)
	{
		super(sequence);
		capacity = DEFAULT_CAPACITY;
	}
	
	/** Capacity constructor. Creates the capacity using the input.
	 * @param aCapacity The input capacity.
	 */
	public BoundedQueue(int aCapacity)
	{
		if (aCapacity <= 0)
		{
			throw new IllegalArgumentException("Bounded queue capacity must be a positive integer.");
		}
		
		capacity = aCapacity;
	}
	
	/** Full constructor.
	 * @param sequence The type of object under the hood.
	 * @param aCapacity The maximum capacity of the queue.
	 */
	public BoundedQueue(Sequence<T> sequence, int aCapacity)
	{
		super(sequence);
		if (aCapacity <= 0)
		{
			throw new IllegalArgumentException("Bounded queue capacity must be a positive integer.");
		}
		capacity = aCapacity;
	}
	
	/**
	 *@exception IllegalStateException Throws this exception if the queue is full.
	 */
	public void enqueue(T object) throws IllegalStateException
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		else if (isFull())
			throw new IllegalStateException("Cannot push unto a full queue.");
			
		super.enqueue(object);
	}
	
	/** Checks if the queue is full or not.
	 * @return True if the queue is full. False otherwise.
	 */
	public boolean isFull()
	{
		return size() == capacity;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject)
	{
		if (anotherObject == null)
		{
			return false;
		}
		else if (anotherObject.getClass() != this.getClass())
		{
			return false;
		}
		else
		{
			BoundedQueue<T> anotherBoundedQueue = (BoundedQueue<T>) anotherObject;
			return this.capacity == anotherBoundedQueue.capacity && isListEqual(anotherBoundedQueue);
		}
	}

}
