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

	@Override
	public boolean equals(Object obj) {
		if(obj == this)return true;
		if(!(obj instanceof Rate))return false;
		Rate r = (Rate)obj;
		return this.getUser().equals(r.getUser()) && this.getItem().equals(r.getItem()) && this.getRate() == r.getRate();
	}

	@Override
	public String toString() {
		return this.getUser().toString()+"\t"+this.getItem().toString()+"\tRate:"+this.getRate();
	}

	/**
	 * Compares this rate to another for order.
	 * If two rates are related to same user, they are compared by their <code>rate</code> values. 
	 * Returns a negative integer, zero, or a positive integer as this <code>rate</code> is 
	 * less than, equal to, or greater than the specified rate's <code>rate</code>.
	 * Otherwise return the result that the user of this rate compares to the user of another rate. 
	 */
	@Override
	public int compareTo(Rate r) {
		if(this.getUser().equals(r.getUser()))
			return Double.compare(this.getRate(), r.getRate());
		else return this.getUser().compareTo(r.getUser());
	}

}
