package lda;

import java.util.HashMap;

public class Corpus {
	
	private WordMap wordMap;
	
	private HashMap<String, Document> documents;
	
	public Document getCocument(String title){
		return documents.get(title);
	}
	
	public String getWord(int id){
		return wordMap.getWord(id);
	}
}
