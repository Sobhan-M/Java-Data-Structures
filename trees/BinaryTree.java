package trees;

/** Abstract binary tree class that includes methods to be implemented by its children.
 * @author Sobhan Mehrpour
 * @param <T> The data type stored within the tree.
 */
abstract public class BinaryTree <T>
{
	/** Adds a root to the binary tree. This only works when the tree is empty. It otherwise throws an IllegalStateException.
	 * @param object The value of the root node.
	 * @return The position of the root node.
	 */
	abstract public Position<T> addRoot(T object);
	
	/** Inserts an object as the left child of the current position. Throws an exception if it already has a left child.
	 * @param position The position which has a left child added to it.
	 * @param object The value at the left child.
	 * @return The position of the left child.
	 */
	abstract public Position<T> addLeft(Position<T> position, T object);
	
	/** Inserts an object as the right child of the current position. Throws an exception if it already has a right child.
	 * @param position The position which has a right child added to it.
	 * @param object The value at the right child.
	 * @return The position of the right child.
	 */
	abstract public Position<T> addRight(Position<T> position, T object);
	
	/** Replaces the value at the current position and returns the old value.
	 * @param position The position which will be replaced.
	 * @param object The new value of the position.
	 * @return The old value of the position.
	 */
	abstract public T replace(Position<T> position, T object);
	
	/** Removes a position from the binary tree and replaces it with its child (if it has one child) or throws an exception (if it has two children).
	 * @param position The position to be removed.
	 * @return The value stored at the position.
	 */
	abstract public T remove(Position<T> position);
	
	/** Returns the root position. To be used for other methods.
	 * @return The root position.
	 */
	abstract public Position<T> root();
	
	/** Returns the parent position of the argument.
	 * @param position The current position.
	 * @return The parent of the current position.
	 */
	abstract public Position<T> parent(Position<T> position);
	
	/** Returns the left child of the argument.
	 * @param position The current position.
	 * @return The left child of the current position.
	 */
	abstract public Position<T> leftChild(Position<T> position);
	
	/** Returns the right child of the argument.
	 * @param position The current position.
	 * @return The right child of the current position.
	 */
	abstract public Position<T> rightChild(Position<T> position);
	
	/** Returns the number of children of the argument.
	 * @param position The current position.
	 * @return The number of children. (0, 1, or 2).
	 */
	abstract public int numChildren(Position<T> position);
	
	/** Returns the current size of the binary tree.
	 * @return The current size of the binary tree.
	 */
	abstract public int size();
	
	/** Returns the height of the binary tree, which is defined as the maximum number of ancestors any node in the tree has.
	 * @return The height of the binary tree.
	 */
	abstract public int height();
	
	/** Returns whether the binary tree is empty or not.
	 * @return True if the binary tree is empty. False if the binary tree is not empty.
	 */
	abstract public boolean isEmpty();
	
	/** Returns whether a position is an internal node (it has children) or not.
	 * @param position The position.
	 * @return True if the position has at least one child. False if it has no children.
	 */
	abstract public boolean isInternal(Position<T> position);
	
	/** Returns whether a position is external (it has no children) or not.
	 * @param position The current position.
	 * @return True if the position has no children. False otherwise.
	 */
	abstract public boolean isExternal(Position<T> position);
	
	/** Returns a boolean whether the current position is the root of the tree or not.
	 * @param position The current position.
	 * @return True if the current position references the root. False otherwise.
	 */
	abstract public boolean isRoot(Position<T> position);
	
	/** Returns a boolean regarding whether the current node has a left child or not.
	 * @param position The current node.
	 * @return True if it has a left child. False if it does not.
	 */
	abstract public boolean hasLeftChild(Position<T> position);
	
	/** Returns a boolean regarding whether the current node has a right child or not.
	 * @param position The current node.
	 * @return True if it has a right child. False if it does not.
	 */
	abstract public boolean hasRightChild(Position<T> position);	
}
