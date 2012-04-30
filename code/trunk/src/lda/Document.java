package lda;

import java.util.Iterator;

/**
 * The interface <tt>Document</tt> presents a document. 
 * A document at least includes a main body which consists of words. 
 * @version 1.0 2012-4-29
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Document extends Iterable<String>{
	
	/**
	 * Returns an iterator which can iterate each word in the main body of this document. 
	 */
	public Iterator<String> iterator();
	
	/**
	 * Returns the word number of the main body of this document.
	 */
	public int wordCount();
	
	/**
	 * Return the n-th word in the main body of this document. 
	 */
	public String getWord(int n);
}
