package trees;

abstract public class BinaryTree <T>
{
	abstract public Position<T> addRoot(T object);
	abstract public Position<T> addLeft(Position<T> position, T object);
	abstract public Position<T> addRight(Position<T> position, T object);
	abstract public T replace(Position<T> position, T object);
	abstract public T remove(Position<T> position);
	abstract public Position<T> root();
	abstract public Position<T> parent(Position<T> position);
	abstract public Position<T> leftChild(Position<T> position);
	abstract public Position<T> rightChild(Position<T> position);
	abstract public int numChildren(Position<T> position);
	abstract public int size();
	abstract public int height();
	abstract public boolean isEmpty();
	abstract public boolean isInternal(Position<T> position);
	abstract public boolean isExternal(Position<T> position);
	abstract public boolean isRoot(Position<T> position);
	abstract public boolean hasLeftChild(Position<T> position);
	abstract public boolean hasRightChild(Position<T> position);	
}
