package trees;

/** A binary tree implemented with nodes.
 * @author Sobhan Mehrpour
 * @param <T>
 */
public class LinkedBinaryTree<T> extends BinaryTree<T>
{
	private int size;
	private Node root;

	/**
	 * Default constructor. Sets size to zero and has no root.
	 */
	public LinkedBinaryTree()
	{
		root = null;
		size = 0;
	}
	
	/** A constructor that begins the tree with a root.
	 * @param rootValue The value of the root.
	 */
	public LinkedBinaryTree(T rootValue)
	{
		root = new Node(rootValue, null);
		size = 1;
	}
	
	/** Private method that safely converts a position into a node.
	 * @param position The position.
	 * @return The position converted into a node.
	 */
	private Node positionToNode(Position<T> position)
	{
		if (position.getClass() != (new Node(null, null).getClass()))
			throw new IllegalArgumentException("Position is not of the right type!");
		
		return (Node) position;
	}
	
	public Position<T> addRoot(T object)
	{
		// Exception handling.
		if (!isEmpty())
			throw new IllegalStateException("Cannot add root to a non-empty tree!");
		if (object == null)
			throw new IllegalArgumentException("Cannot pass a null value.");
		
		// Creating new node.
		root = new Node(object, null);
		size++;
		return root;
	}

	public Position<T> addLeft(Position<T> position, T object) 
	{
		// Exception handling.
		if (position == null || object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		// Conversion to node.
		Node pos = positionToNode(position);
		if (pos.hasLeft())
			throw new IllegalStateException("Cannot add a left child to a node that already has a left child.");
		
		// Inserting item.
		pos.leftChild = new Node(object, pos);
		size++;
		
		return pos.leftChild;
	}

	public Position<T> addRight(Position<T> position, T object) 
	{
		// Exception handling.
		if (position == null || object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		// Converting to node.
		Node pos = positionToNode(position);
		if (pos.hasRight())
			throw new IllegalStateException("Cannot add a left child to a node that already has a left child.");
		
		// Inserting node.
		pos.rightChild = new Node(object, pos);
		size++;
		
		return pos.rightChild;
	}

	public T replace(Position<T> position, T object) 
	{
		// Exception handling.
		if (position == null || object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		// Replacement.
		Node pos = positionToNode(position);
		T temp = pos.value;
		pos.value = object;
		return temp;
	}

	public T remove(Position<T> position) 
	{
		// Exception handling.
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		if (isEmpty())
			throw new IllegalStateException("Cannot remove from an empty binary tree.");
		
		// Converting to node.
		Node node = positionToNode(position);
		T temp = node.value;
		
		// Doesn't work if it has two children.
		if (numChildren(node) == 2)
		{
			throw new IllegalStateException("Cannot remove a node with two children.");
		}
		else if (node.hasLeft())
		{
			// The case when the current node is not the root.
			if (node != root)
			{
				if (node.parent.leftChild == node)
				{
					node.parent.leftChild = node.leftChild;
					node.leftChild.parent = node.parent;
				}
				else
				{
					node.parent.rightChild = node.leftChild;
					node.leftChild.parent = node.parent;
				}
			}
			// When it is the root.
			else
			{
				root = node.leftChild;
				root.parent = null;
			}
		}
		else if (node.hasRight())
		{
			// The case when the current node is not the root.
			if (node != root)
			{
				if (node.parent.leftChild == node)
				{
					node.parent.leftChild = node.rightChild;
					node.rightChild.parent = node.parent;
				}
				else
				{
					node.parent.rightChild = node.rightChild;
					node.rightChild.parent = node.parent;
				}
			}
			// When it is the root.
			else
			{
				root = node.rightChild;
				root.parent = null;
			}
		}
		// The case when it has no children.
		else
		{
			// When it is not the root.
			if (node != root)
			{
				if (node.parent.leftChild == node)
				{
					node.parent.leftChild = null;
				}
				else
				{
					node.parent.rightChild = null;
				}
			}
			// When it is the root.
			else
			{
				root = null;
			}
			
		}
		
		size--;
		return temp;
	}

	public Position<T> root() 
	{
		return root;
	}

	public Position<T> parent(Position<T> position)
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);	
		return pos.parent;
	}

	public Position<T> leftChild(Position<T> position)
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);	
		return pos.leftChild;
	}

	public Position<T> rightChild(Position<T> position) 
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);	
		return pos.rightChild;
	}

	public int numChildren(Position<T> position)
	{
		// Exception handling.
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);
		int numOfChildren = 0;
		
		// Counts each child.
		if (pos.hasLeft())
		{
			numOfChildren++;
		}
		if (pos.hasRight())
		{
			numOfChildren++;
		}
		
		return numOfChildren;
	}

	public int size() 
	{
		return size;
	}

	public int height() 
	{
		if (isEmpty())
			return 0;
		
		return height(root, 0);
	}
	
	/** Private method for finding the height of the tree recursively.
	 * @param root The root of the tree.
	 * @param height The current height of the tree thus far.
	 * @return The total height of the tree.
	 */
	private int height(Node root, int height)
	{
		// If no children, add one to height and return.
		if (root.isExternal())
		{
			return height++;
		}
		// If only has a left child, follow that subtree.
		else if (numChildren(root) == 1 && root.hasLeft())
		{
			return height(root.leftChild, height++);
		}
		// If only has a right child, follow that subtree.
		else if (numChildren(root) == 1 && root.hasRight())
		{
			return height(root.rightChild, height++);
		}
		// If it has two children, then follow both subtrees and return the biggest one.
		else
		{
			return Math.max(height(root.leftChild, height++), height(root.rightChild, height++));
		}
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public boolean isInternal(Position<T> position) 
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		return positionToNode(position).isInternal();
	}

	public boolean isExternal(Position<T> position) 
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		return positionToNode(position).isExternal();
	}

	public boolean isRoot(Position<T> position) 
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		return positionToNode(position).isRoot();
	}

	public boolean hasLeftChild(Position<T> position) 
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		return positionToNode(position).hasLeft();
	}

	public boolean hasRightChild(Position<T> position) 
	{
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		return positionToNode(position).hasRight();
	}
	
	/** Private node class
	 * @author Sobhan Mehrpour
	 */
	private class Node implements Position<T>
	{
		private T value;
		private Node parent;
		private Node leftChild;
		private Node rightChild;

		/** Node constructor.
		 * @param value The value stored at the node.
		 * @param parent The node's parent.
		 */
		public Node(T value, Node parent)
		{
			this.value = value;
			this.parent = parent;
			this.leftChild = null;
			this.rightChild = null;
		}
		
		/**
		 * Returns the value.
		 */
		public T getValue() 
		{
			return value;
		}
		
		private boolean hasLeft()
		{
			return leftChild != null;
		}
		
		private boolean hasRight()
		{
			return rightChild != null;
		}
		
		private boolean isRoot()
		{
			return parent == null;
		}
		
		private boolean isInternal()
		{
			return leftChild != null || rightChild != null;
		}
		
		private boolean isExternal()
		{
			return leftChild == null && rightChild == null;
		}
		
		public String toString()
		{
			return value.toString();
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object anotherObject)
		{
			if (anotherObject == null)
			{
				return false;
			}
			else if (anotherObject.getClass() != this.getClass())
			{
				return false;
			}
			else
			{
				Node anotherNode = (Node) anotherObject;
				return this.value.equals(anotherNode.value);
			}
		}
		
	}
}
