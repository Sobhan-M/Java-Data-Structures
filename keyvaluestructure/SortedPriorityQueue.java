package keyvaluestructure;

import basicstructures.DoublyLinkedList;

/** A sorted priority queue that stored information in a sorted list internally.
 * @author Sobhan Mehrpour
 * @param <K> The comparable key.
 * @param <V> The value.
 */
public class SortedPriorityQueue<K extends Comparable<K>, V>  extends PriorityQueue<K, V>  
{
	// The internal list.
	private DoublyLinkedList<Entry<K,V>> list;
	
	
	/**
	 * Default constructor. Creates a min priority queue.
	 */
	public SortedPriorityQueue()
	{
		this(true);
	}
	
	/** Priority queue constructor.
	 * @param isMinPriority True for min priority queue. False for max priority queue.
	 */
	public SortedPriorityQueue(boolean isMinPriority) 
	{
		super(isMinPriority);
		list = new DoublyLinkedList<Entry<K,V>>();
	}

	public void insert(K key, V value) 
	{
		if (key == null || value == null)
			throw new IllegalArgumentException("Null value passed to priority queue.");
		
		insert(new Entry<K,V>(key,value));
	}

	public void insert(Entry<K, V> entry) 
	{
		// Exceptions
		if (entry == null || entry.getKey() == null || entry.getValue() == null)
			throw new IllegalArgumentException("Null value passed to priority queue.");
		
		// Special case for when empty,
		if (isEmpty())
		{
			list.addStart(entry);
		}
		// If has more priority than the current highest priority.
		else if (hasPriority(entry, list.getFirst()))
		{
			list.addStart(entry);
		}
		// If has less or equal priority than the least priority entry.
		else if (! hasPriority(entry, list.getLast()))
		{
			list.addLast(entry);
		}
		else
		{
			// Otherwise go through the list and enter the value wherever appropriate.
			int i = 0;
			for(Entry<K,V> element : list)
			{
				if (hasPriority(entry, element))
				{
					list.addAtIndex(entry, i);
					break;
				}
				i++;
			}
		}
		
	}

	public Entry<K, V> getPriority() 
	{
		if (isEmpty())
			return null;
		else
			return list.getFirst();
	}

	public Entry<K, V> removePriority() 
	{
		if (isEmpty())
			return null;
		else
			return list.removeFirst();
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
			SortedPriorityQueue<K,V> anotherPriorityQueue = (SortedPriorityQueue<K,V>) anotherObject;
			return anotherPriorityQueue.list.equals(this.list);
		}
	}
	
	public String toString()
	{
		return list.toString();
	}
}
