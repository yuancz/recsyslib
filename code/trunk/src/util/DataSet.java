package util;

/**
 * The interface <tt>DataSet</tt> presents a data set which includes users, items and rates in Recommender Systems.
 * In order to consistency, the <tt>addRate(Rate)</tt> method are redefined. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface DataSet extends UserSet, ItemSet, RateSet {
	/**
	 * Add a rate into this data set. If there is a rate r in this data set
	 * meeting
	 * <code>r.getUserId() == rate.getUserId() && r.getItemId == rate.getItemId()</code>
	 * , r will be replaced by rate. If <code>rate==null</code> or the user or
	 * item id does not exist in this data set, this data set will not be
	 * modified and return false¡£ So that if there are some rates related to new
	 * user or item needed to add into this data set, the user or item should be
	 * added by <code>addUser(User user)</code> or
	 * <code>addItem(Item item)</code> at first.
	 */
	public abstract boolean addRate(Rate rate);
}
