package priorityqueues;

import basicstructures.ArrayList;

public class Heap <K extends Comparable<K>, V> extends PriorityQueue<K,V>
{
	ArrayList<HeapEntry> list;
	
	public Heap()
	{
		this(true);
	}

	public Heap(boolean isMinPriority) 
	{
		super(isMinPriority);
		list = new ArrayList<HeapEntry>(1, true);
	}
	
	private boolean isOutOfBounds(HeapEntry entry)
	{
		return (entry.index >= list.size() || entry.index < 0 || list.getAtIndex(entry.index) == null);
	}
	
	private boolean isOutOfBounds(int index)
	{
		return (index >= list.size() || index < 0 || list.getAtIndex(index) == null);
	}
	
	private void swap(HeapEntry entry1, HeapEntry entry2)
	{
		if (isOutOfBounds(entry1) || isOutOfBounds(entry2))
			throw new IndexOutOfBoundsException("Cannot swap entries that are out of bound");
		
		// Swapping indices.
		int tempIndex = entry1.index;
		entry1.index = entry2.index;
		entry2.index = tempIndex;
		
		// Swapping entries.
		list.replaceAtIndex(entry1, entry1.index);
		list.replaceAtIndex(entry2, entry2.index);
		
	}
	
	private void upHeap(HeapEntry entry)
	{
			
		if (entry.hasParent() && hasPriority(entry, entry.parent()))
		{
			swap(entry, entry.parent());
			upHeap(entry);
		}
	}
	
	private void downHeap(HeapEntry entry)
	{
		if (entry == null)
			throw new IllegalArgumentException("Null value passed to downheap");
		
		// One child, left.
		if (entry.numOfChildren() == 1 && entry.hasLeft() && hasPriority(entry.left(), entry))
		{
			swap(entry.left(), entry);
			downHeap(entry);
		}
		// One child, right.
		else if (entry.numOfChildren() == 1 && entry.hasRight() && hasPriority(entry.right(), entry))
		{
			swap(entry.right(), entry);
			downHeap(entry);
		}
		// Two children, left.
		else if (entry.numOfChildren() == 2 && hasPriority(entry.left(), entry) 
				&& (hasPriority(entry.left(), entry.right()) || entry.left().hasSameKey(entry.right())))
		{
			swap(entry.left(), entry);
			downHeap(entry);
		}
		// Two children, right.
		else if (entry.numOfChildren() == 2 && hasPriority(entry.right(), entry) && hasPriority(entry.right(), entry.left()))
		{
			swap(entry.right(), entry);
			downHeap(entry);
		}
	}

	@Override
	public void insert(K key, V value) 
	{
		if (key == null || value == null)
			throw new IllegalArgumentException("Key or value cannot be null.");
		
		HeapEntry entry = new HeapEntry(key, value);
		list.addLast(entry);
		upHeap(entry);
	}

	@Override
	public void insert(Entry<K, V> entry) 
	{
		if (entry == null || entry.getKey() == null || entry.getValue() == null)
			throw new IllegalArgumentException("Entry or its attributes cannot be null.");
		
		HeapEntry actualEntry = new HeapEntry(entry);
		list.addLast(actualEntry);
		upHeap(actualEntry);
	}

	@Override
	public Entry<K, V> getPriority() 
	{
		return list.getFirst();
	}

	@Override
	public Entry<K, V> removePriority() 
	{
		if (isEmpty())
		{
			return null;
		}
		else if (size() == 1)
		{
			return list.removeLast();
		}
		
		swap(list.getFirst(), list.getLast());
		HeapEntry temp = list.removeLast();
		downHeap(list.getFirst());
		return temp;
	}

	public int size() 
	{
		return list.size();
	}

	public boolean isEmpty() 
	{
		return list.isEmpty();
	}
	
	private class HeapEntry extends Entry<K,V>
	{
		private int index;

		public HeapEntry(K key, V value)
		{
			this(key, value, size());
		}
		
		public HeapEntry(Entry<K,V> entry)
		{
			this(entry.getKey(), entry.getValue(), size());
		}
		
		public HeapEntry(K key, V value, int index) 
		{
			super(key, value);
			this.index = index;
		}
		
		public int parentIndex()
		{
			return (index - 1)/2;
		}
		
		public int leftIndex()
		{
			return index * 2 + 1;
		}
		
		public int rightIndex()
		{
			return index * 2 + 2;
		}
		
		public HeapEntry parent()
		{
			if (index == 0)
				return null;
			
			return list.getAtIndex(parentIndex());
		}
		
		public HeapEntry left()
		{
			return list.getAtIndex(leftIndex());
		}
		
		public HeapEntry right()
		{
			return list.getAtIndex(rightIndex());
		}
		

		public boolean hasParent()
		{
			return ! isOutOfBounds(parentIndex()) && index > 0;
		}
		
		public boolean hasLeft()
		{
			return ! isOutOfBounds(leftIndex());
		}
		
		public boolean hasRight()
		{
			return ! isOutOfBounds(rightIndex());
		}
		
		public int numOfChildren()
		{
			int num = 0;
			if (hasRight())
			{
				num++;
			}
			if (hasLeft())
			{
				num++;
			}
			return num;
		}	
	}
}
