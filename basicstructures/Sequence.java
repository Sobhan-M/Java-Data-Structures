package basicstructures;

public abstract class Sequence<T> {
	
	abstract public void addToStart(T object);
	abstract public void addToEnd(T object);
	abstract public void addAtIndex(T object, int index);
	abstract public T getFirst();
	abstract public T getLast();
	abstract public T getAtIndex(int index);
	abstract public T removeFirst();
	abstract public T removeLast();
	abstract public T removeAtIndex(int index);
	abstract public T replaceAtIndex(T object, int index);
	abstract public int size();
	abstract public boolean isEmpty();
	abstract public int getIndexOf(T object);
	abstract public boolean contains(T object);
}
