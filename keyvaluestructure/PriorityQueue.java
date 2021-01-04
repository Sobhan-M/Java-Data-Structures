package keyvaluestructure;

public abstract class PriorityQueue <K extends Comparable<K>, V> 
{
	private boolean isMinPriority;
	
	public PriorityQueue(boolean isMinPriority)
	{
		this.isMinPriority = isMinPriority;
	}
	public boolean isMinPriority()
	{
		return isMinPriority;
	}
	
	abstract void insert(K key, V value);
	
	abstract void insert(Entry<K,V> entry);
	
	abstract Entry<K,V> getPriority();
	
	abstract Entry<K,V> removePriority();
	
	abstract int size();
	
	abstract boolean isEmpty();
}
