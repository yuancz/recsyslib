package cf.similarity;

/**
 * The interface <tt>Similarity</tt> presents the similarity of two objects with same type in Recommender Systems.
 * @version 1.0 2012-5-1
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Similarity {
	
	/**
	 * Returns the similarity of two objects with the specified id1 and id2
	 */
	public abstract double getSimilarity(int id1, int id2);
}
