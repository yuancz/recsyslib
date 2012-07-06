package core.impl;

import core.Rate;

/**
 * The <tt>WeightedRate</tt> class extends <tt>Rate</tt> by adding a weight value.
 * The default value of the weight is 1. 
 * @version 1.0 2012-7-5
 * @author Tan Chang
 * @since JDK 1.7
 * @see Rate
 */
public class WeightedRate extends Rate {
	
	protected double weight;
	
	/**
	 * Constructs a <tt>WeightedRate</tt> object with userId, itemId, rating and weight. 	
	 */
	public WeightedRate(int userId, int itemId, double rating, double weight){
		super(userId, itemId, rating);	
		this.weight = weight;
	}	
	
	/**
	 * Constructs a <tt>WeightedRate</tt> object with userId, itemId, rating and the default value of the weight. 	
	 */
	public WeightedRate(int userId, int itemId, double rating){
		this(userId, itemId, rating, 1);		
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}	

}
