package util;

/**
 * The abstract class <tt>Rate</tt> is the superclass of all <tt>Rate</tt>
 * objects in Recommender Systems.
 * <p>
 * One <tt>Rate</tt> object should record three values, a user id, an item id and 
 * a double value <tt>rating</tt> as the user rating the item. 
 * <p>
 * This class implements the <tt>Comparable</tt> interface. 
 * @version 1.0 2012-4-19
 * @author Tan Chang
 * @since JDK 1.7
 */
public class Rate implements Comparable<Rate> {

	protected int userId;
	protected int itemId;
	protected double rating;

	/**
	 * Sole constructor. For invocation by subclass constructors. Constructs a
	 * <tt>Rate</tt> with the specified user id, item id and rating.
	 * @throws RecSysLibException
	 *             if the user id or item id is negative
	 */
	public Rate(int userId, int itemId, double rating) {
		if (userId < 0 || itemId < 0)
            throw new RecSysLibException("The user id and item id must be nonnegative. ");
		this.userId = userId;
		this.itemId = itemId;
		this.rating = rating;
	}

	/**
	 * Return the related user id of this <tt>Rate</tt> object. 
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Return the related item id of this <tt>Rate</tt> object. 
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Return the rating of the related user rating to the related item in this <tt>Rate</tt> object. 
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Return true if obj is a <tt>Rate</tt> object and
	 * <code>getUserId() == obj.getUserId() && getItemId() == obj.getItemId() 
	 * && getRating() == obj.getRating()</code>
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Rate))
			return false;
		Rate r = (Rate) obj;
		return getUserId() == r.getUserId() && getItemId() == r.getItemId()
				&& getRating() == r.getRating();
	}

	/**
	 * Converts this <tt>Rate</tt> object to a String of the form 
	 * <code>"User_" + userId + " rates Item_" + itemId + " as " + rating</code>. 
	 */	
	@Override
	public String toString() {
		return "User_" + this.getUserId() + " rates Item_" + this.getItemId()
				+ " as " + this.getRating();
	}

	/**
	 * Returns <code>userId ^ itemId ^ Double.valueOf(rating).hashCode()</code>. 
	 */	
	@Override
	public int hashCode() {
		return userId ^ itemId ^ Double.valueOf(rating).hashCode();
	}

	/**
	 * Compares this rate to another for order. If two rates are not related to
	 * same user or same item, throws a <tt>RecSysLibException</tt>. Returns a
	 * negative integer, zero, or a positive integer as this <code>rate</code>
	 * is less than, equal to, or greater than the specified rate's
	 * <code>rate</code>.
	 */
	@Override
	public int compareTo(Rate r) {
		if (getUserId() != r.getUserId() || getItemId() != r.getItemId())
			throw new RecSysLibException(
					"Only the rates related to same user or same item can be compared. ");
		return Double.compare(this.getRating(), r.getRating());
	}

}