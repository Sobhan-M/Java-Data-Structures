package keyvaluestructure;

/** An abstract priority queue that supports the various priority queue implementations. 
 * These implementations include a sorted priority queue, unsorted priority queue, and a heap.
 * @author Sobhan Mehpour
 * @param <K> The comparable key class for the priority queue.
 * @param <V> The value held by the queue.
 */
public abstract class PriorityQueue <K extends Comparable<K>, V> 
{
	// True if it is a min priority queue. False if it is a max priority queue.
	private boolean isMinPriority;
	
	/** Constructor to decide whether it is a min priority queue or a max priority queue. 
	 * This property cannot be changed later.
	 * @param isMinPriority True if it is a min priority queue. False if it is a max priority queue.
	 */
	public PriorityQueue(boolean isMinPriority)
	{
		this.isMinPriority = isMinPriority;
	}
	
	/** Accessor for minPriorityQueue.
	 * @return True if it is a min priority queue. False if it is a max priority queue.
	 */
	public boolean isMinPriority()
	{
		return isMinPriority;
	}
	
	/** Method used to see which entry has priority.
	 * @param entry1 The entry being compared.
	 * @param entry2 The entry being compared to.
	 * @return True if entry1 has priority over entry2. False if entry entry1 does not have priority over entry2.
	 * Priority in this case is defined in terms of both max and min priority queues. 
	 * <ul>
	 * <li>In a <strong>min priority queue</strong>, the lesser value has priority, so <strong> entry1 < entry2 returns true </strong>. </li>
	 * <li> In a <strong>max priority queue</strong>, the greater value has priority, so <strong> entry1 > entry2 returns true </strong>. </li>
	 * </ul>
	 */
	protected boolean hasPriority(Entry<K,V> entry1, Entry<K,V> entry2)
	{
		if (isMinPriority())
			return entry1.compareTo(entry2) < 0;
		else
			return entry1.compareTo(entry2) > 0;
	}
	
	/** Allows the insertion of an entry via the key and value.
	 * @param key The entry's key.
	 * @param value The entry's value.
	 */
	abstract public void insert(K key, V value);
	
	/** Overloaded method for insert. Allows the direct insertion of an entry.
	 * @param entry The entry to be inserted.
	 */
	abstract public void insert(Entry<K,V> entry);
	
	/** Returns (but does not remove) the priority entry. This is the min entry in a 
	 * min priority queue, and the max entry in the max priority queue.
	 * @return The priority entry.
	 */
	abstract public Entry<K,V> getPriority();
	
	/** Removes and returns the priority queue.This is the min entry in a 
	 * min priority queue, and the max entry in the max priority queue.
	 * @return The priority entry.
	 */
	abstract public Entry<K,V> removePriority();
	
	/** Returns the current size of the priority queue.
	 * @return The current size of the priority queue.
	 */
	abstract public int size();
	
	/** Returns whether the priority queue is empty or not.
	 * @return True if it is empty. False if it is not empty.
	 */
	abstract public boolean isEmpty();
}
