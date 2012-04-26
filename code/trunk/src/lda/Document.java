package lda;

import java.util.Set;

import collections.Bag;
import collections.HashBag;

public final class Document {
	
	private String title;
	
	private Bag<Integer> bag;//Integer: wordId
	
	public Document(String title){
		this.title = title;
		bag = new HashBag<>();
	}
	
	public void addWord(int id){
		bag.add(id);
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getWordCount(int id){
		return bag.getCount(id);
	}
	
	public Set<Integer> getWordIdSet(){
		return bag.uniqueSet();
	}
	
	public int getTotalWordCount(){
		return bag.size();
	}
}
