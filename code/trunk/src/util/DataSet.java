package util;

import util.impl.Rate;

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
