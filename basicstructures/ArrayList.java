package basicstructures;

import java.util.Iterator;

/** A resizable array that can access each position in constant time.
 * @author Sobhan Mehrpour
 */
public class ArrayList<T> extends Sequence<T> {
	
	private boolean shouldDouble;
	private T[] array;
	private int size;
	
	/**
	 * Default constructor. Increments when resizing. Has a starting capacity of 10.
	 */
	public ArrayList() {
		// Calls full constructor.
		this(10, false);
	}
	
	/** Resizing is done based on user input. Starting capacity defaults to 10.
	 * @param shouldDouble Whether resizing is done incrementally or doubles. True means it doubles. 
	 * False means it increments.
	 */
	public ArrayList(boolean shouldDouble) {
		// Calls full constructor.
		this(10, shouldDouble);
	}
	
	/** Starting capacity is left up to the user. The sequence will increment when resizing by default.
	 * @param startCapacity The initial capacity of the array.
	 */
	public ArrayList(int startCapacity) {
		// Calls full constructor.
		this(startCapacity, false);
	}

	/** A full constructor that allows the user to decide both the start capacity and the means of resizing.
	 * @param startCapacity The initial capacity of the array.
	 * @param shouldDouble Whether resizing is done incrementally or doubles. True means it doubles. 
	 * False means it increments.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int startCapacity, boolean shouldDouble) {
		// Handling exceptional cases.
		if(startCapacity < 0)
			throw new IllegalArgumentException("The start capacity cannot be negative.");
		
		// Instantiating the attributes.
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
		// Checks to see if resizing is needed or not.
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
	
	public void addLast(T object) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Attempted to add null object to the end of the ArrayList.");
		
		// Resizing if needed, adding item, and increasing size.
		increaseCapacity();
		array[size++] = object;
	}
	
	public void addStart(T object) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Attempted to add null object to the start of the ArrayList.");
		
		// Resizing if needed and shifting all objects.
		increaseCapacity();
		for(int i = size-1 ; i >= 0 ; i--)
			array[i+1] = array[i];
		
		// Adding object to the start.
		array[0] = object;
		size++;
	}
	
	public void addAtIndex(T object, int index) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Attempted to add null object to the ArrayList.");
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		
		// Resizing if needed and shifting all objects.
		increaseCapacity();
		for(int i = size - 1 ; i >= index ; i--)
			array[i+1] = array[i];
		
		// Adding object to the index.
		array[index] = object;
		size++;
	}
	
	public T getFirst() {
		// Returns null if this is empty; otherwise returns the object at the first index.
		if(isEmpty())
			return null;
		else
			return array[0];
	}
	
	public T getLast() {
		// Returns null if this is empty; otherwise returns the object at the last index.
		if(isEmpty())
			return null;
		else
			return array[size - 1];
	}
	
	public T getAtIndex(int index) {
		// Handling exceptional cases.
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		if(isEmpty())
			return null;
		else
			// Returning the value at the index.
			return array[index];
	}

	public T removeFirst() {
		// Handling exceptional cases.
		if(isEmpty())
			return null;
		
		// Gets the first item and shifts everything appropriately.
		T temp = getFirst();
		for(int i = 1 ; i < size ; i++)
			array[i-1] = array[i];
		
		// Removing item and returning it.
		array[--size] = null;
		return temp;
	}
	
	public T removeLast() {
		// Handling exceptional cases.
		if(isEmpty())
			return null;
		
		// Removing last item and returning it.
		T temp = getLast();
		array[--size] = null;
		return temp;
	}
	
	public T removeAtIndex(int index) {
		// Handling exceptional cases.
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		if(isEmpty())
			return null;
		
		// Getting item and indices it as needed.
		T temp = getAtIndex(index);
		for(int i = index + 1 ; i < size ; i++)
			array[i-1] = array[i];
		
		// Removing item and returning it.
		array[--size] = null;
		return temp;
	}
	
	public T replaceAtIndex(T object, int index) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Attempted to use null object in the ArrayList.");
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of ArrayList bounds!");
		if(isEmpty())
			return null;
		
		// Replacing the object and returning the former object.
		T temp = getAtIndex(index);
		array[index] = object;
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object anotherObject) {
		// Checking for null.
		if(anotherObject == null) return false;
		// Checking if the object is the right class.
		else if (anotherObject.getClass() != this.getClass()) return false;
		else {
			// Casting and checking if sizes match.
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
		
		// Looping through and adding objects to string.
		for(int i = 0 ; i < size ; i++) {
			if(i != size - 1)
				output += array[i].toString() + ", ";
			else
				output += array[i].toString() + " ";
		}
		
		output += "]";
		return output;
	}
	
	public int size() {
		return size;
	}
	
	/** Returns the number of objects the sequence can contain before having to resize.
	 * @return The current capacity of the array.
	 */
	public int capacity() {
		return array.length;
	}
	
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
	 * Reduces the capacity to match size, thus saving space. Note that it is the user's responsibility to ensure 
	 * they do not call this method if the current size is zero and they are increasing capacity via doubling. 
	 * Doing so will prevent the capacity from increasing, and will lead to an error.
	 */
	@SuppressWarnings("unchecked")
	public void trimToSize() {
		// Do nothing if the size and capacity are equal.
		if(size == capacity())
			return;
		
		// Otherwise create a correctly sized array and transfer all objects.
		T[] temp = (T[]) (new Object[size]);
		for(int i = 0 ; i < size ; i++)
			temp[i] = array[i];
		
		// Assign new array.
		array = temp;
	}
	
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

	public boolean contains(T object) {
		// Handling exceptional cases.
		if(object == null)
			throw new IllegalArgumentException("Attempted to use null object in the ArrayList.");
		
		// Search for the index and check to see if not found.
		return getIndexOf(object) != -1;
	}
	
	public Iterator<T> iterator(){
		Iterator<T> temp = new Iterator<T>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < size;
			}

			@Override
			public T next() {
				return array[currentIndex++];
			}
			
			
		};
		return temp;
	}
}
