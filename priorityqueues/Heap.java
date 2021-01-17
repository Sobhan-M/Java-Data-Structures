package priorityqueues;

import basicstructures.ArrayList;

public class Heap <K extends Comparable<K>, V> extends PriorityQueue<K,V>
{
	ArrayList<Entry<K,V>> list;
	
	public Heap()
	{
		this(true);
	}

	public Heap(boolean isMinPriority) 
	{
		super(isMinPriority);
		list = new ArrayList<Entry<K,V>>(1, true);
	}
	
	private boolean isRoot(Entry<K,V> entry)
	{
		if (entry == null || entry.getKey() == null || entry.getValue() == null)
			throw new IllegalArgumentException("Entry cannot be null or have null components.");
		
		return entry == list.getFirst();
	}
	
	private boolean isOutOfBounds(int index)
	{
		return (index >= list.size() || index < 0 || list.getAtIndex(index) == null);
	}
	
	private int parent(int index)
	{
		return (index - 1)/2;
	}
	
	private int leftChild(int index)
	{
		return index * 2 + 1;
	}
	
	private int rightChild(int index)
	{
		return index * 2 + 2;
	}
	
	private void swap(int index1, int index2)
	{
		if (isOutOfBounds(index1) || isOutOfBounds(index2))
			throw new IndexOutOfBoundsException("Cannot swap indices that are out of bound");
		
		Entry<K,V> temp = list.getAtIndex(index1);
		list.replaceAtIndex(list.getAtIndex(index2), index1);
		list.replaceAtIndex(temp, index2);
	}
	
	private void upHeap(int index)
	{
		if (hasPriority(list.getAtIndex(index),list.getAtIndex(parent(index))))
		{
			swap(index, parent(index));
			upHeap(parent(index));
		}
	}
	
	private void downHeap(int index)
	{
		if (!isOutOfBounds(leftChild(index)) && hasPriority(list.getAtIndex(leftChild(index)), list.getAtIndex(index)))
		{
			swap(index, leftChild(index));
			downHeap(leftChild(index));
		}
		// TODO Complete downheap.
	}
	
	

	@Override
	public void insert(K key, V value) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Entry<K, V> entry) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entry<K, V> getPriority() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> removePriority() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int size() 
	{
		return list.size();
	}

	public boolean isEmpty() 
	{
		return list.isEmpty();
	}

}
