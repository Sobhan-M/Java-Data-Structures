package trees;

public class LinkedBinaryTree<T> extends BinaryTree<T>
{
	private int size;
	private Node root;

	public LinkedBinaryTree()
	{
		root = null;
		size = 0;
	}
	
	public LinkedBinaryTree(T rootValue)
	{
		root = new Node(rootValue, null);
		size = 1;
	}
	
	private Node positionToNode(Position<T> position)
	{
		if (position.getClass() != (new Node(null, null).getClass()))
			throw new IllegalArgumentException("Position is not of the right type!");
		
		return (Node) position;
	}
	
	public Position<T> addRoot(T object)
	{
		if (!isEmpty())
			throw new IllegalStateException("Cannot add root to a non-empty tree!");
		
		root = new Node(object, null);
		size++;
		return root;
	}

	public Position<T> addLeft(Position<T> position, T object) 
	{
		if (position == null || object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);
		if (pos.hasLeft())
			throw new IllegalStateException("Cannot add a left child to a node that already has a left child.");
		
		pos.leftChild = new Node(object, pos);
		size++;
		
		return pos.leftChild;
	}

	public Position<T> addRight(Position<T> position, T object) 
	{
		if (position == null || object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);
		if (pos.hasRight())
			throw new IllegalStateException("Cannot add a left child to a node that already has a left child.");
		
		pos.rightChild = new Node(object, pos);
		size++;
		
		return pos.rightChild;
	}

	public T replace(Position<T> position, T object) 
	{
		if (position == null || object == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);
		T temp = pos.value;
		pos.value = object;
		return temp;
	}

	@Override
	public T remove(Position<T> position) 
	{
		// TODO Auto-generated method stub
		return null;
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
		if (position == null)
			throw new IllegalArgumentException("Null value passed.");
		
		Node pos = positionToNode(position);
		int numOfChildren = 0;
		
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

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
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
	
	private class Node implements Position<T>
	{
		private T value;
		private Node parent;
		private Node leftChild;
		private Node rightChild;

		public Node(T value, Node parent)
		{
			this.value = value;
			this.parent = parent;
			this.leftChild = null;
			this.rightChild = null;
		}
		
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
