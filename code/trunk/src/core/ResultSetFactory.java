package core;


/**
 * The interface <tt>ResultSetFactory</tt> provides factory methods for create a result set from different sources.
 * @version 1.0 2012-6-15
 * @author Tan Chang
 * @since JDK 1.7
 * @see ResultSet
 */
public interface ResultSetFactory {
	
	/**
	 * Returns a <tt>ResultSet</tt> object created by the arguments <code>args</code>. 
	 */
	public ResultSet createResultSet(String... args);
}
