package util;

/**
 * The interface <tt>DataSetFactory</tt> provides factory methods for create a dataset from different sources.
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see DataSet
 */
public interface DataSetFactory {
	
	/**
	 * Returns a <tt>DataSet</tt> object created by the arguments <code>args</code>. 
	 */
	public DataSet createDataSet(String... args);
}
