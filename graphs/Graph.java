package graphs;
import basicstructures.DoublyLinkedList;

abstract public class Graph<V,E>
{
    /** Returns a sequence of incident edges on the vertex.
     * @param vertex The vertex whose incident edges you seek.
     * @return A list of incident edges on the paramter.
     */
    abstract public DoublyLinkedList<E> incidentEdges(V vertex);

    /** Returns the edge connecting the two vertices in the parameter.
     * @param vertex1 One side of the edge.
     * @param vertex2 The other side of the edge.
     * @return An edge connecting the parameters.
     */
    abstract public E getEdge(V vertex1, V vertex2);

    /** Inserts a vertex into the graph.
     * @param vertex The vertex being inserted into the graph.
     */
    abstract public void insertVertex(V vertex);

    /** Inserts an edge in between two vertices.
     * @param vertex1 One of the two vertices.
     * @param vertex2 Another one of the two vertices.
     * @param edge The edge connecting the two vertices.
     */
    abstract public void insertEdge(V vertex1, V vertex2, E edge);

    /** Removes and returns the vertex. It also removes any edges connected to the vertex.
     * @param vertex The vertex you seek to remove.
     * @return The vertex returned to you.
     */
    abstract public V removeVertex(V vertex);

    /** Removes the edge and returns it.
     * @param edge The edge you seek to remove.
     * @return The edge returned to you.
     */
    abstract public E removeEdge(E edge);

    /** Finds and returns the vertex connecting to a known vertex through a known edge.
     * @param vertex One side of the edge.
     * @param edge The edge connecting the two vertices.
     * @return The unknown vertex connected to the parameter via the edge in the parameter.
     */
    abstract public V opposite(V vertex, E edge);

    /** Finds and returns the two vertices connected by the parameter.
     * @param edge The edge connecting the two vertices.
     * @return A list of the two vertices.
     */
    abstract public DoublyLinkedList<V> endVertices(E edge);

    abstract public DoublyLinkedList<V> DepthFirstSearch(Graph<V,E> graph, V vertex);

    abstract public DoublyLinkedList<V> BreadthFirstSearch(Graph<V,E> graph, V vertex);
}