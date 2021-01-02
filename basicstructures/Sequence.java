package basicstructures;

/** An abstract sequence that can be implemented using an ArrayList, Singly Linked List, or Doubly Linked List.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public abstract class Sequence<T>  implements Iterable<T>{
	
	/** Adds the object to the beginning of the sequence.
	 * @param object The object being passed.
	 */
	abstract public void addToStart(T object);
	
	/** Adds the object to the end of the sequence.
	 * @param object The object being passed.
	 */
	abstract public void addToEnd(T object);
	
	/** Pushes anything at or after index forward and inserts the object at the index.
	 * @param object The object being passed.
	 * @param index The index the object is inserted at.
	 */
	abstract public void addAtIndex(T object, int index);
	
	/** Returns a reference to the first object in the sequence.
	 * @return The first object in the sequence. Returns null if empty.
	 */
	abstract public T getFirst();
	
	/** Returns a reference to the last object in the sequence.
	 * @return The first object in the sequence. Returns null if empty.
	 */
	abstract public T getLast();
	
	/** Returns a reference to the object at that index.
	 * @param index The index the object is retrieved from.
	 * @return The object at the index.
	 */
	abstract public T getAtIndex(int index);
	
	/** Removes and returns the first object in the sequence.
	 * @return The first object in the sequence. Returns null if the sequence is empty.
	 */
	abstract public T removeFirst();
	
	/** Removes and returns the last object in the sequence.
	 * @return The last object in the sequence. Returns null if the sequence is empty.
	 */
	abstract public T removeLast();
	
	/** Removes and returns an object at the given index.
	 * @param index The position from which the object is removed.
	 * @return The object at the index specified.
	 */
	abstract public T removeAtIndex(int index);
	
	/** Replaces the object at the index and returns the previous object at that position.
	 * @param object The replacement object.
	 * @param index The index that is being replaced.
	 * @return The object previously at the index.
	 */
	abstract public T replaceAtIndex(T object, int index);
	
	/** Returns the current size of the sequence.
	 * @return The size of the sequence.
	 */
	abstract public int size();
	
	/** States whether the sequence is empty or not.
	 * @return Returns true if the sequence is empty. Returns false if it not empty.
	 */
	abstract public boolean isEmpty();
	
	/** Returns the index of the object.
	 * @param object The object being searched for.
	 * @return Returns the index of the object being searched for. Returns -1 if it is not found.
	 */
	abstract public int getIndexOf(T object);
	
	/** States whether the object currently resides in the sequence or not.
	 * @param object The object being searched for.
	 * @return True if the object is found in the sequence. False if the object is not found.
	 */
	abstract public boolean contains(T object);
}
