package util.impl;

import util.Rate;

/**
 * A simple implementation of the abstract class <tt>Rate</tt>.
 * A <tt>SimpleRate</tt> object only contains a user id, 
 * an item id and a rating value rated by the user to the item. 
 * @version 1.0 2012-4-10
 * @author Tan Chang
 * @since JDK 1.7
 * @see Rate
 */
public final class SimpleRate extends Rate {
	
	/**
	 * Constructs a <tt>SimpleRate</tt> object with userId, itemId and rating. 	
	 */
	public SimpleRate(int userId, int itemId, double rating){
		super(userId, itemId, rating);		
	}	

}
