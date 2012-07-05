package core.util;

import java.util.LinkedList;
import java.util.List;

import core.recommender.Recommender;



/**
 * The class <tt>ResultList</tt> presents a candidate result list to a specified user in Recommender Systems.
 * The candidate list is in descending order by the rating value. 
 * It makes no guarantees as if there are no duplicated items in it. 
 * Programmer should check the duplicate items before invoking the <tt>addRate</tt> method. 
 * @version 1.0 2012-6-25
 * @author Tan Chang
 * @since JDK 1.7
 * @see Recommender
 * @see ResultSet
 */
public final class ResultList {
	
	private int userId;
	private List<Rate> rates;

	/**
	 * Sole constructor. Constructs a <tt>ResultList</tt> for the specified user id.
	 */
	public ResultList(int userId){
		this.userId = userId;
		this.rates = new LinkedList<Rate>();
	}
	
	/**
	 * Add a <tt>Rate</tt> into the result list with the specified item id and related rating. 
	 * It makes no guarantees as if there are no duplicated items in it. 
	 * Programmer should check the duplicate items before invoking the <tt>addRate</tt> method. 
	 * @throws RecSysLibException if the user id of the <tt>Rate</tt> is not same with this result list. 
	 */
	public void addRate(Rate rate){
		if(rate.getUserId() != userId)
			throw new RecSysLibException("The user id must be the same. ");
		int index = indexing(rate.getRating());
		rates.add(index, rate);
	}
	
	private int indexing(double rating){
		if(rates.size()==0)return 0;
		for(int i = 0;i<rates.size();i++){
			if(rating > rates.get(i).getRating())return i;
		}
		return rates.size();
	}
	
	/**
	 * Return the user id of this result list. 
	 */
	public int getUserId(){
		return userId;
	}
	
	/**
	 * Return the corresponding <tt>Rate</tt> for the specified index.
	 */
	public Rate getRate(int index){
		return rates.get(index);
	}
	
	/**
	 * Return the index for the specified item id. If not found in this result list, return -1. 
	 */
	public int getIndex(int itemId){
		for(int i = 0;i<rates.size();i++){
			if(itemId == rates.get(i).getItemId())return i;
		}
		return -1;
	}
	
	/**
	 * Return the size of this result list. 
	 */
	public int size(){
		return rates.size();
	}

}
