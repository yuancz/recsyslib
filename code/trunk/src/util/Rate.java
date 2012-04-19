package util;

public class Rate implements Comparable<Rate> {
	
	private User user;
	private Item item;
	private double rate;
	
	public Rate(User u, Item i, double r){
		this.user = u;
		this.item = i;
		this.rate = r;
	}	
	
	public User getUser() {
		return user;
	}

	public Item getItem() {
		return item;
	}

	public double getRate() {
		return rate;
	}

	public int getUserId() {
		return user.getUserId();
	}

	public int getItemId() {
		return item.getItemId();
	}

	/**
	 * Compares this rate to another for order.
	 * Two rates are compared by their <code>rate</code> values.  
	 * Returns a negative integer, zero, or a positive integer as this <code>rate</code> is 
	 * less than, equal to, or greater than the specified rate's <code>rate</code>.
	 */
	@Override
	public int compareTo(Rate r) {
		return Double.compare(this.getRate(), r.getRate());
	}

}
