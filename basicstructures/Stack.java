package basicstructures;

public class Stack<T> 
{
	private Sequence<T> list;
	
	public Stack() 
	{
		list = new DoublyLinkedList<T>();
	}
	
	@SuppressWarnings("unchecked")
	public Stack(Sequence<T> sequence) throws InstantiationException, IllegalAccessException
	{
		list = (Sequence<T>)sequence.getClass().newInstance();
	}
	
	public void push(T object)
	{
		if(object == null)
			throw new IllegalArgumentException("Null value passed.");
		list.addStart(object);
	}
	
	public T pop()
	{
		return list.removeFirst();
	}
	
	public T peek()
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
			Stack<T> anotherStack = (Stack<T>) anotherObject;
			return this.list.equals(anotherStack.list);
		}
	}
	
	public String toString()
	{
		return list.toString();
	}
}
