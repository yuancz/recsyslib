package lda;

import java.util.Set;

import collections.BidiMap;
import collections.HashBidiMap;


public final class WordMap{
	
	private BidiMap<String, Integer> map;
	
	public WordMap(){
		map = new HashBidiMap<>();
	}
	
	public int addWord(String word){
		if(!map.containsValue(word)){
			int id = map.size();
			map.put(word, id);
			return id;
		}
		return -1;
	}
	
	public int size(){
		return map.size();
	}
	
	public Set<String> wordSet(){
		return map.keySet();
	}
	
	public String getWord(int id){
		return map.getKey(id);
	}
	
	public int getId(String word){
		return map.getValue(word);
	}
	
}
