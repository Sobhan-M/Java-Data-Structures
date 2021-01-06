package keyvaluestructure;

import basicstructures.DoublyLinkedList;

/** A priority queue that contains the data in an unsorted list internally.
 * @author Sobhan Mehrpour
 *
 * @param <K> The comparable key of the entry.
 * @param <V> The value of the entry.
 */
public class UnsortedPriorityQueue<K extends Comparable<K>, V> extends PriorityQueue<K,V>
{

	/**
	 * The internal list. A doubly linked list.
	 */
	private DoublyLinkedList<Entry<K,V>> list;
	
	/**
	 * A default constructor. Creates min priority queue.
	 */
	public UnsortedPriorityQueue()
	{
		this(true);
	}
	
	/** A constructor that allows the user to decide whether they want a min priority queue or a max priority queue.
	 * @param isMinPriority True for min priority queue. False for max priority queue.
	 */
	public UnsortedPriorityQueue(boolean isMinPriority) 
	{
		super(isMinPriority);
		list = new DoublyLinkedList<Entry<K,V>>();
	}

	public void insert(K key, V value) 
	{
		// Exceptions.
		if (key == null || value == null)
			throw new IllegalArgumentException("Null value passed to priority queue.");
		
		// Calling overloaded method.
		insert(new Entry<K,V>(key,value));
	}

	public void insert(Entry<K, V> entry) 
	{
		// Exceptions
		if (entry == null || entry.getKey() == null || entry.getValue() == null)
			throw new IllegalArgumentException("Null value passed to priority queue.");
		
		// Adding to end to maintain order.
		list.addLast(entry);
	}

	public Entry<K, V> getPriority() 
	{
		// Special case.
		if (isEmpty())
			return null;
		
		// Creating local variables.
		Entry<K,V> priorityEntry = list.getFirst();
		int index = 0;
		int priorityIndex = 0;
		
		// Iterating over list to find priority entry and returning it.
		for (Entry<K,V> element : list)
		{
			if (hasPriority(element, priorityEntry))
			{
				priorityEntry = element;
				priorityIndex = index;
			}
			index++;
		}
		return list.getAtIndex(priorityIndex);
	}

	public Entry<K, V> removePriority() 
	{
		// Special case.
		if (isEmpty())
			return null;
		
		// Creating local variables.
		Entry<K,V> priorityEntry = list.getFirst();
		int index = 0;
		int priorityIndex = 0;
		
		// Iterating over list to find priority entry and removing it.
		for (Entry<K,V> element : list)
		{
			if (hasPriority(element, priorityEntry))
			{
				priorityEntry = element;
				priorityIndex = index;
			}
			index++;
		}
		return list.removeAtIndex(priorityIndex);
	}

	public int size() 
	{
		return list.size();
	}

	public boolean isEmpty() 
	{
		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject)
	{
		// Checking for null and class matches.
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
			// Comparing underlying lists.
			UnsortedPriorityQueue<K,V> anotherPriorityQueue = (UnsortedPriorityQueue<K,V>) anotherObject;
			return anotherPriorityQueue.list.equals(this.list);
		}
	}
	
	public String toString()
	{
		return list.toString();
	}
}
