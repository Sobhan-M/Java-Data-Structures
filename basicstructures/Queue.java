package basicstructures;

public class Queue<T> {
	private Sequence<T> list;
	
	public Queue() 
	{
		list = new DoublyLinkedList<T>();
	}
	
	@SuppressWarnings("unchecked")
	public Queue(Sequence<T> sequence) throws InstantiationException, IllegalAccessException
	{
		list = (Sequence<T>)sequence.getClass().newInstance();
	}
	
	public void enqueue(T object)
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		list.addStart(object);
	}
	
	public T dequeue()
	{
		return list.removeLast();
	}
	
	public T peekQueueEnd()
	{
		return list.getLast();
	}
	
	public T peekQueueStart()
	{
		return list.getFirst();
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
