package maps;
import priorityqueues.Entry;

/** Abstract map class to be implemented has an unsorted map, search table, or hash table.
 * @author Sobhan Mehrpour
 * @param <K> The key type.
 * @param <V> The value type.
 */
abstract public class Map <K extends Comparable<K>, V>
{
	/**
	 * The current size of the map.
	 */
	private int size;
	
	/**
	 * Map constructor. Sets size to 0.
	 */
	public Map()
	{
		size = 0;
	}
	
	/** Returns current size.
	 * @return Current size.
	 */
	public int size()
	{
		return size;
	}
	
	/** Returns whether the map is empty or not.
	 * @return True if the map is empty. False otherwise.
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	/**
	 * Increments the size.
	 */
	protected void incrementSize()
	{
		size++;
	}
	
	/**
	 * Decrements the size.
	 */
	protected void decrementSize()
	{
		size--;
	}
	
	/** Returns the first entry with the given key value.
	 * @param key The key value.
	 * @return The entry with the given key.
	 */
	abstract public Entry<K,V> get(K key);
	
	/** Creates a new entry and inserts it into the map.
	 * @param key The key of the entry.
	 * @param value The value of the entry.
	 */
	abstract public void put(K key, V value);
	
	/** Removes and returns the first entry with the given key.
	 * @param key The key.
	 * @return The entry with the key.
	 */
	abstract public Entry<K,V> remove(K key);
	
	

}
