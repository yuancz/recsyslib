package lda;

/**
 * The interface <tt>CorpusFactory</tt> provides factory methods for create a corpus from different sources.
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see DataSet
 */
public interface CorpusFactory {
	
	/**
	 * Returns a <tt>Corpus</tt> object created by the arguments <code>args</code>. 
	 */
	public Corpus createCorpus(String... args);
}
