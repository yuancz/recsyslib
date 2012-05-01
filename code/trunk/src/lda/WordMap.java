package lda;

import java.util.HashSet;
import java.util.Set;

import collections.BidiMap;
import collections.HashBidiMap;

/**
 * This <tt>WordMap</tt> class allows save and index a word and automatically assign an id to each word. 
 * It is backed by a <code>HashBidiMap<String, Integer></code>. 
 * Noting that the <tt>WordMap</tt> permits null as a word. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see BidiMap
 */
public final class WordMap{
	
	private BidiMap<String, Integer> map;
	
	/**
	 * Solo constructor, Constructor an empty word map. 
	 */
	public WordMap(){
		map = new HashBidiMap<>();
	}
	
	/**
	 * Adds a word into this word map, returns the mapping word id. 
	 * If the word is already in this word map, returns existed word id. 
	 */
	public int addWord(String word){
		if(!map.containsKey(word)){
			int id = map.size();
			map.put(word, id);
			return id;
		}
		return map.get(word);
	}
	
	/**
	 * Returns the total number of unique words in this word map. 
	 */
	public int size(){
		return map.size();
	}
	
	/**
	 * Returns a shallow copy of all words in this word map. 
	 */
	public Set<String> wordSet(){
		return new HashSet<String>(map.keySet());
	}
	
	/**
	 * Returns the word mapping the specified id, 
	 * noting that returns null if the mapping word is null or the id is not existing in this word map. 
	 */
	public String getWord(int id){
		return map.getKey(id);
	}
	
	/**
	 * Returns the word id mapping the specified word, or -1 if the word is not existing in this word map. 
	 */
	public int getWordId(String word){
		Integer id = map.getValue(word);
		if(id == null)id = -1;
		return id;
	}
	
	/**
	 * Returns true if existing the specified word in this word map. 
	 */
	public boolean containsWord(String word){
		return map.containsValue(word);
	}
	
}
