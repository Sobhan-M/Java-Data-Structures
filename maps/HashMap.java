package maps;

import basicstructures.DoublyLinkedList;
import priorityqueues.Entry;

public class HashMap <K extends Comparable<K>, V> extends Map<K,V>
{
	DoublyLinkedList<Entry<K,V>>[] list;
	int[] arrat= new int[10];
	
	@SuppressWarnings("unchecked")
	public HashMap(int tableSize)
	{
		list = (DoublyLinkedList<Entry<K,V>>[]) new Object[tableSize];
		for (int i = 0 ; i < list.length ; i++)
		{
			list[i] = new DoublyLinkedList<Entry<K,V>>();
		}
	}
	
	private int hash(K key)
	{
		return key.hashCode() % list.length;
	}

	public Entry<K, V> get(K key) {
		if (key == null)
			throw new IllegalArgumentException("Null values cannot be passed.");
		
		int hashIndex = hash(key);
		if (list[hashIndex].isEmpty())
		{
			return null;
		}
		else
		{
			for (Entry<K,V> entry : list[hashIndex])
			{
				if (entry.hasSameKey(key))
				{
					return entry;
				}
			}
			return null;
		}
	}

	public void put(K key, V value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("Null values cannot be passed.");
		
		Entry<K,V> entry = new Entry<>(key,value);
		list[hash(key)].addStart(entry);
		incrementSize();
	}

	public Entry<K, V> remove(K key) {
		if (key == null)
			throw new IllegalArgumentException("Null values cannot be passed.");
		
		int hashIndex = hash(key);
		if (list[hashIndex].isEmpty())
		{
			return null;
		}
		else
		{
			int index = 0;
			for (Entry<K,V> entry : list[hashIndex])
			{
				if (entry.hasSameKey(key))
				{
					Entry<K,V> temp = list[hashIndex].removeAtIndex(index);
					decrementSize();
					return temp;
				}
				index++;
			}
			return null;
		}
	}
	
	public int tableSize()
	{
		return list.length;
	}

}
