package maps;

import basicstructures.DoublyLinkedList;
import priorityqueues.Entry;

public class UnsortedMap<K extends Comparable<K>, V> extends Map <K,V>
{
	private DoublyLinkedList<Entry<K,V>> list;
	
	public UnsortedMap()
	{
		list = new DoublyLinkedList<Entry<K,V>>();
	}

	public Entry<K, V> get(K key) {
		if (key == null)
			throw new IllegalArgumentException("The key cannot be null!");
		
		if(isEmpty())
			return null;
		
		for(Entry<K,V> entry : list)
		{
			if (entry.hasSameKey(key))
				return entry;
		}
		
		return null;
	}

	public void put(K key, V value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("The key and value must not be null");
		
		list.addStart(new Entry<K,V>(key,value));
		incrementSize();
	}

	public Entry<K, V> remove(K key) {
		if (key == null)
			throw new IllegalArgumentException("The key cannot be null!");
		
		if(isEmpty())
			return null;
		
		int index = 0;
		for(Entry<K,V> entry : list)
		{
			if (entry.hasSameKey(key))
			{
				decrementSize();
				return list.removeAtIndex(index);
			}
			index++;
		}
		
		return null;
	}
	
	public String toString()
	{
		return list.toString();
	}

}
