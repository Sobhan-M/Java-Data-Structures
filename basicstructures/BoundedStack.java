package basicstructures;

/** A bounded stack that can only hold a limited number of items.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public class BoundedStack <T> extends Stack<T>
{
	/**
	 * The maximum capacity.
	 */
	private int capacity;
	
	/**
	 * The default maximum capacity.
	 */
	private final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Default constructor. Sets capacity to default capacity.
	 */
	public BoundedStack()
	{
		capacity = DEFAULT_CAPACITY;
	}
	
	/** Sequence constructor.
	 * @param sequence Creates the stack using the given sequence.
	 */
	public BoundedStack(Sequence<T> sequence)
	{
		super(sequence);
		capacity = DEFAULT_CAPACITY;
	}
	
	/** Capacity constructor. Creates the capacity using the input.
	 * @param aCapacity The input capacity.
	 */
	public BoundedStack(int aCapacity)
	{
		if (aCapacity <= 0)
		{
			throw new IllegalArgumentException("Bounded stack capacity must be a positive integer.");
		}
		
		capacity = aCapacity;
	}
	
	/** Full constructor.
	 * @param sequence The type of object under the hood.
	 * @param aCapacity The maximum capacity of the stack.
	 */
	public BoundedStack(Sequence<T> sequence, int aCapacity)
	{
		super(sequence);
		capacity = aCapacity;
	}
	
	/**
	 *@exception IllegalStateException Throws this exception if the stack is full.
	 */
	public void push(T object) throws IllegalStateException
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		else if (isFull())
			throw new IllegalStateException("Cannot push unto a full stack.");
			
		super.push(object);
	}
	
	/** Checks if the stack is full or not.
	 * @return True if the stack is full. False otherwise.
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
			BoundedStack<T> anotherBoundedStack = (BoundedStack<T>) anotherObject;
			return this.capacity == anotherBoundedStack.capacity && isListEqual(anotherBoundedStack);
		}
	}

}
