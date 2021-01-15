package maps;

import basicstructures.ArrayList;
import priorityqueues.Entry;

public class SortedMap <K extends Comparable<K>, V> extends Map<K,V>
{
	ArrayList<Entry<K,V>> list;
	
	public SortedMap()
	{
		list = new ArrayList<Entry<K,V>>();
	}
	
	private int binarySearch(K key, int startIndex, int endIndex)
	{
		if (key == null || startIndex < 0 || endIndex < 0)
			throw new IllegalArgumentException();
		
		// Terminating condition.
		if (startIndex > endIndex)
		{
			return -1;
		}

		// Local variables.
		int middle = (startIndex + endIndex)/2;
		K entryKey = list.getAtIndex(middle).getKey();
		
		// Case 1: Correct key.
		if (key.equals(entryKey))
		{
			return middle;
		}
		// Case 2: Key is smaller.
		else if (key.compareTo(entryKey) < 0)
		{
			return binarySearch (key, startIndex, middle);
		}
		// Case 3: Key is larger.
		else
		{
			return binarySearch (key, middle + 1, startIndex);
		}
	}

	@Override
	public Entry<K, V> get(K key) 
	{
		// Exceptions.
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null.");
		
		// Special case.
		if (list.isEmpty())
			return null;
		
		// Index.
		int index = binarySearch(key, 0, list.size());
		
		// Not found.
		if (index == -1)
		{
			return null;
		}
		// If found.
		else
		{
			return list.getAtIndex(index);
		}
	}

	public void put(K key, V value) 
	{
		// Exception handling.
		if (key == null || value == null)
			throw new IllegalArgumentException("Cannot pass null values to map.");
		
		// Local variables.
		boolean hasInserted = false;
		Entry<K,V> entry = new Entry<>(key, value);
		
		// Checking for the right spot.
		for (int i = 0 ; i < list.size() ; i++)
		{
			if (entry.compareTo(list.getAtIndex(i)) < 0)
			{
				list.addAtIndex(entry, i);
				hasInserted = true;
				break;
			}
		}
		
		// Adds to the end of the list if it has the largest key. This will also be used if the list is empty.
		if (!hasInserted)
		{
			list.addLast(entry);
		}
		
		incrementSize();
	}

	public Entry<K, V> remove(K key) 
	{
		// Exceptions.
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null.");
		
		// Special case.
		if (list.isEmpty())
			return null;
		
		// Index.
		int index = binarySearch(key, 0, list.size());
		
		// Not found.
		if (index == -1)
		{
			return null;
		}
		// If found.
		else
		{
			decrementSize();
			return list.removeAtIndex(index);
		}
	}
}
