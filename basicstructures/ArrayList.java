package basicstructures;

/** A resizable array that can access each position in constant time.
 * @author Sobhan Mehrpour
 */
public class ArrayList <T> {
	
	private boolean shouldDouble;
	private T[] array;
	private int size;
	
	/**
	 * Default constructor.
	 */
	public ArrayList() {
		this(10, false);
	}
	
	/** Constructor.
	 * @param shouldDouble Whether resizing is done incrementally or doubles.
	 */
	public ArrayList(boolean shouldDouble) {
		this(10, shouldDouble);
	}
	
	/** Constructor
	 * @param startCapacity The initial capacity of the array.
	 */
	public ArrayList(int startCapacity) {
		this(startCapacity, false);
	}

	/** Full constructor.
	 * @param startCapacity The initial capacity of the array.
	 * @param shouldDouble Whether resizing is done incrementally or doubles.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int startCapacity, boolean shouldDouble) {
		if(startCapacity <= 0)
			startCapacity = 10;
		size = 0;
		this.shouldDouble = shouldDouble;
		array = (T[])(new Object[startCapacity]);
	}
	
	/**
	 * Private method for resizing the array when needed. <br>
	 * <strong> If shouldDouble = true </strong> then the capacity will double to achieve better amortized time. <br>
	 * <strong> If shouldDouble = false</strong>  then the capacity will increment to save space. <br>
	 */
	@SuppressWarnings("unchecked")
	private void increaseCapacity() {
		if(size < capacity())
			return;
		
		// Resizing.
		T[] temp;
		if(shouldDouble)
			temp = (T[])(new Object[array.length * 2]);
		else
			temp = (T[])(new Object[array.length + 1]);
		
		// Copying all items.
		for(int i = 0 ; i < size ; i++)
			temp[i] = array[i];
		
		// Replacement.
		array = temp;
	}
	
	/** Adds the parameter to the end of the ArrayList.
	 * @param object The object being passed.
	 */
	public void addToEnd(T object) {
		if(object == null)
			throw new IllegalArgumentException("Attempted to add null object to the end of the ArrayList.");
		
		increaseCapacity();
		array[size++] = object;
	}
	
	/** Adds the parameter to the start of the ArrayList.
	 * @param object The object being passed.
	 */
	public void addToStart(T object) {
		if(object == null)
			throw new IllegalArgumentException("Attempted to add null object to the start of the ArrayList.");
		
		increaseCapacity();
		for(int i = size-1 ; i >= 0 ; i--)
			array[i+1] = array[i];
		
		array[0] = object;
		size++;
	}
	
	/** Adds an object to the given index (shifts the previous contents of the index forward).
	 * @param object The object being passed.
	 * @param index The index the object is inserted at.
	 */
	public void addAtIndex(T object, int index) {
		if(object == null)
			throw new IllegalArgumentException("Attempted to add null object to the ArrayList.");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		
		increaseCapacity();
		for(int i = size - 1 ; i >= index ; i--)
			array[i+1] = array[i];
		
		array[index] = object;
		size++;
	}
	
	/**
	 * @return The contents of the first index of the ArrayList.
	 */
	public T getFirst() {
		if(isEmpty())
			return null;
		else
			return array[0];
	}
	
	/**
	 * @return The contents of the last index of the ArrayList.
	 */
	public T getLast() {
		if(isEmpty())
			return null;
		else
			return array[size - 1];
	}
	
	/** Returns the contents of a given index.
	 * @param index The index we want to return.
	 * @return The contents of index.
	 */
	public T getAtIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		if(isEmpty())
			return null;
		else
			return array[index];
	}
	
	/** Removes the first item (index 0) and returns it.
	 * @return The first index of the ArrayList.
	 */
	public T removeFirst() {
		if(isEmpty())
			return null;
		
		T temp = getFirst();
		for(int i = 1 ; i < size ; i++)
			array[i-1] = array[i];
		
		size--;
		return temp;
	}
	
	/** Removes the last item of the ArrayList and returns it.
	 * @return The content of the last index of the ArrayList.
	 */
	public T removeLast() {
		if(isEmpty())
			return null;
		
		T temp = getLast();
		size--;
		return temp;
	}
	
	/** Removes and returns the item at a given index.
	 * @param index The index the object is removed from.
	 * @return The contents of the index.
	 */
	public T removeAtIndex(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		if(isEmpty())
			return null;
		
		T temp = getAtIndex(index);
		for(int i = index + 1 ; i < size ; i++)
			array[i-1] = array[i];
		
		size--;
		return temp;
	}
	
	/** Replaces the contents of a given index with the object provided.
	 * @param object The object placed inside the index.
	 * @param index The index whose content is being replaced.
	 * @return The original content of the index.
	 */
	public T replaceAtIndex(T object, int index) {
		if(object == null)
			throw new IllegalArgumentException("Attempted to use null object in the ArrayList.");
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		if(isEmpty())
			return null;
		
		T temp = getAtIndex(index);
		array[index] = object;
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject) {
		if(anotherObject == null) return false;
		else if (anotherObject.getClass() != this.getClass()) return false;
		else {
			ArrayList<T> anotherArrayList = (ArrayList<T>) anotherObject;
			if(this.size != anotherArrayList.size)
				return false;
			
			// Check each index to make sure they are in the same order.
			for(int i = 0 ; i < size ; i++)
			{
				if(! this.array[i].equals(anotherArrayList.array[i]))
					return false;
			}	
			
			// Return true if the entire array was searched successfully.
			return true;
			
		}
	}
	
	public String toString() {
		String output = "[ ";
		
		for(int i = 0 ; i < size ; i++) {
			if(i != size - 1)
				output += array[i].toString() + ", ";
			else
				output += array[i].toString() + " ";
		}
		
		output += "]";
		return output;
	}
	
	
	/**
	 * @return The current size of the array (the number of objects it houses).
	 */
	public int size() {
		return size;
	}
	
	/**
	 * @return The current capacity of the array (the number of object it can house without resizing).
	 */
	public int capacity() {
		return array.length;
	}
	
	/**
	 * @return Returns true if size = 0. Returns false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * @return Returns the current state of whether it doubles or not.
	 */
	public boolean shouldDouble() {
		return shouldDouble;
	}
	
	/**
	 * @param shouldDouble The new value for shouldDouble. True indicates the ArrayList will 
	 * double whenever it reaches the capacity. False indicates that the ArrayList will increment 
	 * whenever it reaches the capacity.
	 */
	public void setDouble(boolean shouldDouble) {
		this.shouldDouble = shouldDouble;
	}
	
	/**
	 * Reduces the capacity to match size, thus saving space.
	 */
	@SuppressWarnings("unchecked")
	public void trimToSize() {
		if(size == capacity())
			return;
		
		T[] temp = (T[]) (new Object[size]);
		for(int i = 0 ; i < size ; i++)
			temp[i] = array[i];
		
		array = temp;
	}
	
	/** Scans the entire ArrayList and returns the index of the object if it's found. Returns -1 
	 * if the object is not found.
	 * @param object The object being searched for.
	 * @return The index of object.
	 */
	public int getIndexOf(T object) {
		// Throw exception if the object is null.
		if(object == null)
			throw new IllegalArgumentException("Attempted to use null object in the ArrayList.");
		
		// Return the index if a match is found.
		for(int i = 0 ; i < size ; i++) {
			if(object.equals(array[i]))
				return i;
		}
		
		// Return -1 if not found.
		return -1;
	}
	
	/**
	 * @param object The object being searched for.
	 * @return Returns true if the object is found. Returns false if it is null or has not been found.
	 */
	public boolean contains(T object) {
		if(object == null)
			throw new IllegalArgumentException("Attempted to use null object in the ArrayList.");
		
		return getIndexOf(object) != -1;
	}
	
}
