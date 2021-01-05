package keyvaluestructure;

/** A wrapper class for key-value pairs. To be used in various key-value structures like priority queues and maps.
 * @author Sobhan Mehrpour
 * @param <K> A comparable key.
 * @param <V> A value.
 */
public class Entry <K extends Comparable<K>, V> implements Comparable<Entry<K,V>>
{
	private K key;
	private V value;
	
	/** Constructor to create an entry.
	 * @param key The entry's key.
	 * @param value The entry's value.
	 */
	public Entry(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject)
	{
		// Check for null and the right class.
		if (anotherObject == null)
			return false;
		else if (anotherObject.getClass() != this.getClass())
			return false;
		else
		{
			// Compare the keys and values of the two objects.
			Entry <K, V> anotherEntry = (Entry <K, V>) anotherObject;
			return this.key.equals(anotherEntry.key) && this.value.equals(anotherEntry.value);
		}
	}

	public int compareTo(Entry<K, V> entry) 
	{
		if (entry == null)
			throw new NullPointerException("Null value passed to entry.");
		
		// Only compare the key.
		return this.key.compareTo(entry.key);
	}
	
	public String toString()
	{
		// Return string as an ordered pair.
		return String.format("(%s, %s)" , key.toString(), value.toString());
	}
	
	/** Allows you to change the value of an entry.
	 * @param value The new value of the entry.
	 */
	public void setValue(V value)
	{
		if (value == null)
			throw new NullPointerException("Null value passed to value.");
		
		this.value = value;
	}
	
	/** Accessor for the key.
	 * @return They key.
	 */
	public K getKey()
	{
		return key;
	}
	
	/** Accessor for the value.
	 * @return The value;
	 */
	public V getValue()
	{
		return value;
	}

}
