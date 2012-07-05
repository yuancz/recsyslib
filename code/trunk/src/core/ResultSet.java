package core;

import io.Inputer;
import io.Outputer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import evaluation.Evaluator;


/**
 * The <tt>ResultSet</tt> presents recommendation results between a group of user 
 * and a group of item in Recommender Systems. 
 * For each user, the candidate item list is in descending order by the rating value. 
 * @version 1.0 2012-6-25
 * @author Tan Chang
 * @since JDK 1.7
 * @see Evaluator
 * @see ResultList
 */
public final class ResultSet {
	
	private HashMap<Integer, ResultList> map;
	
	/**
	 * Constructs an empty <tt>ResultSet</tt>.
	 */
	public ResultSet(){
		map = new HashMap<Integer, ResultList>();
	}
	
	/**
	 * Constructs an <tt>ResultSet</tt> from a specified file.
	 */
	public ResultSet(String filePath){
		this();
		Inputer in = new Inputer(filePath);
		String line = in.readLine();
		while(line != null){
			String[] strs = line.split("\t");
			int userId = Integer.valueOf(strs[0]);
			int itemId = Integer.valueOf(strs[1]);
			double rating = 1;
			if(strs.length == 3)rating = Double.valueOf(strs[2]);
			Rate rate = new Rate(userId, itemId, rating);
			if(!map.containsKey(userId))map.put(userId, new ResultList(userId));
			map.get(userId).addRate(rate);
			line = in.readLine();
		}
		in.close();
	}
	
	/**
	 * Saves this <tt>ResultSet</tt> to a specified file.
	 */
	public void saveToFile(String filePath){
		Outputer out = new Outputer(filePath);
		for(int user : getUserIds()){
			ResultList result = getResultList(user);
			for(int i = 0;i<result.size();i++){
				Rate rate = result.getRate(i);
				out.writeLine(rate.getUserId()+"\t"+rate.getItemId()+"\t"+rate.getRating());
			}
			out.flush();
		}
		out.close();
	}
	
	/**
	 * Add a <tt>ResultList</tt> into this result set. 
	 */
	public void addResultList(ResultList list){
		map.put(list.getUserId(), list);
	}
	
	/**
	 * Return the result list to which the specified user id is mapped, 
	 * or null if this result set contains no mapping for the user id.
	 */
	public ResultList getResultList(int userId){
		return map.get(userId);
	}
	
	/**
	 * Returns a <tt>Set</tt> view of the user ids contained in this result set.
	 */
	public Set<Integer> getUserIds(){
		return new HashSet<Integer>(map.keySet());
	}
}
