package graphs;

import basicstructures.DoublyLinkedList;

public class EdgeListGraph<V,E> extends Graph<V,E>
{
	private DoublyLinkedList<Group> edgeList;
	private boolean isDirected;
	
	public EdgeListGraph()
	{
		edgeList = new DoublyLinkedList<Group>();
		isDirected = false;
	}
	
	public EdgeListGraph(boolean isDirectedGraph)
	{
		edgeList = new DoublyLinkedList<Group>();
		isDirected = isDirectedGraph;
	}

	@Override
	public DoublyLinkedList<E> incidentEdges(V vertex) 
	{
		if (vertex == null)
			throw new IllegalArgumentException("Cannot find incident edges of null.");
		
		DoublyLinkedList<E> incidentList = new DoublyLinkedList<E>();
		
		for (Group group: edgeList)
		{
			if (group.destination == vertex)
			{
				incidentList.addLast(group.edge);
			}
			// If it's an undirected graph, then the source can also be incident.
			else if (group.source == vertex && !isDirected)
			{
				incidentList.addLast(group.edge);
			}
		}
		
		return incidentList;
	}

	@Override
	public E getEdge(V vertex1, V vertex2)
	{
		if (vertex1 == null || vertex2 == null)
			throw new IllegalArgumentException("Cannot find incident edges of null.");
		
		E connectingEdge = null;
		
		for (Group group : edgeList)
		{
			if (group.source == vertex1 && group.destination == vertex2)
			{
				connectingEdge = group.edge;
			}
			else if (group.source == vertex2 && group.destination == vertex1 && !isDirected)
			{
				connectingEdge = group.edge;
			}
		}
		
		return connectingEdge;
	}

	@Override
	public void insertVertex(V vertex) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Vertices are bound to edges, so they can't be added to an EdgeList");	
	}

	@Override
	public void insertEdge(V vertex1, V vertex2, E edge) 
	{
		if (vertex1 == null || vertex2 == null || edge == null)
			throw new IllegalArgumentException("insertEdge() does not accept null arguments!");
		
		edgeList.addLast(new Group(edge, vertex1, vertex2));
		
	}

	@Override
	public V removeVertex(V vertex) 
	{
		if (vertex == null)
			throw new IllegalArgumentException("Cannot remove a null vertex.");
		
		int i = 0;
		for (Group group : edgeList)
		{
			if (group.source == vertex || group.destination == vertex)
			{
				edgeList.removeAtIndex(i);
			}
			i++;
		}

		return vertex;
	}

	@Override
	public E removeEdge(E edge) 
	{
		if (edge == null)
			throw new IllegalArgumentException("Cannot remove a null edge.");

		E removedEdge = null;
		int removedIndex = 0;
		
		for (Group group : edgeList)
		{
			if (group.edge == edge)
			{
				removedEdge = group.edge;
				break;
			}
			
			removedIndex++;
		}
		
		if (removedEdge != null)
		{
			edgeList.removeAtIndex(removedIndex);
		}
		
		return removedEdge;
	}

	@Override
	public V opposite(V vertex, E edge) 
	{
		if (vertex == null || edge == null)
			throw new IllegalArgumentException("Cannot find opposite of nulls.");
		
		V oppositeVertex = null;
		
		for (Group group : edgeList)
		{
			if (group.edge == edge)
			{
				if (group.source == vertex)
				{
					oppositeVertex = group.destination;
				}
				else if (group.destination == vertex)
				{
					oppositeVertex = group.source;
				}
				
				break;
			}
		}


		return oppositeVertex;
	}

	@Override
	public DoublyLinkedList<V> endVertices(E edge) 
	{
		if (edge == null)
			throw new IllegalArgumentException("Cannot find end vertices of null.");
		
		DoublyLinkedList<V> vertices = new DoublyLinkedList<V>();
		
		for (Group group : edgeList)
		{
			if (group.edge == edge)
			{
				vertices.addLast(group.source);
				vertices.addLast(group.destination);
				
				break;
			}
		}
		
		return vertices;
	}
	
	@Override
	public boolean isDirected() 
	{
		return isDirected;
	}
	
	private class Group
	{
		private E edge;
		private V source;
		private V destination;
		
		public Group(E anEdge, V aSource, V aDestination)
		{
			edge = anEdge;
			source = aSource;
			destination = aDestination;
		}
	}
}
