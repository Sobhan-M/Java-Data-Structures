package keyvaluestructure;

public class Entry <K extends Comparable<K>, V> implements Comparable<Entry<K,V>>
{
	private K key;
	private V value;
	
	public Entry(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject)
	{
		if (anotherObject == null)
			return false;
		else if (anotherObject.getClass() != this.getClass())
			return false;
		else
		{
			Entry <K, V> anotherEntry = (Entry <K, V>) anotherObject;
			return this.key.equals(anotherEntry.key) && this.value.equals(anotherEntry.value);
		}
	}

	@Override
	public int compareTo(Entry<K, V> entry) 
	{
		if (entry == null)
			throw new NullPointerException("Null value passed to entry.");
		
		return this.key.compareTo(entry.key);
	}
	
	public String toString()
	{
		return String.format("(%s, %s)" , key.toString(), value.toString());
	}
	
	public void setValue(V value)
	{
		if (value == null)
			throw new NullPointerException("Null value passed to value.");
		
		this.value = value;
	}
	
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}

}
