package util.impl;

import collections.Pair;
import util.RecSysLibException;

public final class Rate extends Pair<Pair<Integer, Integer>, Double> implements Comparable<Rate> {

	private Pair<Integer, Integer> uip;
	
	public Rate(Integer uid, Integer iid, double r){
		this(new Pair<>(uid, iid),r);		
	}	

	private Rate(Pair<Integer, Integer> key, Double value) {
		super(key, value);
		uip = key;
	}
	
	public int getUserId(){
		return uip.getKey();
	}
	
	public int getItemId(){
		return uip.getValue();
	}
	
	public double getRate(){
		return this.getValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)return true;
		if(!(obj instanceof Rate))return false;
		Rate r = (Rate)obj;
		return getUserId()==r.getUserId() && getItemId()==r.getItemId() && this.getRate() == r.getRate();
	}

	@Override
	public String toString() {
		return "User_"+this.getUserId()+" rates Item_"+this.getItemId()+" as "+this.getRate();
	}
	
	/**
	 * Compares this rate to another for order.
	 * If two rates are not related to same user or same item, throw <tt>RecSysLibException</tt>. 
	 * Returns a negative integer, zero, or a positive integer as this <code>rate</code> is 
	 * less than, equal to, or greater than the specified rate's <code>rate</code>.
	 */
	@Override
	public int compareTo(Rate r){
		if(getUserId() != r.getUserId() || getItemId() != r.getItemId())
			throw new RecSysLibException("Rates of same user or same item can be compared. ");
		return Double.compare(this.getRate(), r.getRate());
	}


}
