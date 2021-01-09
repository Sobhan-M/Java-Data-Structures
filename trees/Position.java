package trees;

/** Position interface that allows users to access the value at a given position, while still protecting data via encapsulation.
 * @author Sobhan Mehrpour
 * @param <T> The value stored internally.
 */
public interface Position<T>
{
	/** A method to return the value at a given position.
	 * @return Returns the value.
	 */
	T getValue();
}
