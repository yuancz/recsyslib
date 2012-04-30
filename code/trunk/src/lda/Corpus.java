package lda;

import java.util.Iterator;

import collections.BidiMap;
import collections.HashBidiMap;

/**
 * This <tt>Corpus</tt> class presents a corpus included some documents. 
 * It allows save and index a document and automatically assign an id to each document also for each word. 
 * @version 1.0 2012-4-29
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class Corpus {
	
	private WordMap wordMap;
	
	private BidiMap<Integer, Document> documents;
	
	/**
	 * Constructs a empty corpus. 
	 */
	public Corpus(){
		wordMap = new WordMap();
		documents = new HashBidiMap<>();
	}

	/**
	 * Adds a new document doc into this corpus, all words in the document will be parsed and saved into this corpus.
	 * Returns false if the doc is null or already in this corpus.  
	 */
	public boolean addDoc(Document doc){
		if(doc == null || documents.containsValue(doc))return false;
		int docId = documents.size();
		documents.put(docId, doc);
		parseDoc(doc);
		return true;
	}
	
	private void parseDoc(Document doc) {
		Iterator<String> docIte = doc.iterator();
		while(docIte.hasNext()){
			addWord(docIte.next());
		}
	}	
	
	/**
	 * @see {@link WordMap#addWord(String)}
	 */
	public int addWord(String word){
		return wordMap.addWord(word);
	}

	/**
	 * Returns the document mapping the specified document id, or null if the id is not existing in this corpus. 
	 */
	public Document getDoc(int docId){
		return documents.getValue(docId);
	}
	
	/**
	 * Returns the document id mapping the specified document, or -1 if the document is not existing in this corpus. 
	 */
	public int getDocId(Document doc){
		return documents.getKey(doc);
	}
	
	/**
	 * @see {@link WordMap#getWord(int)}
	 */
	public String getWord(int id){
		return wordMap.getWord(id);
	}
	
	/**
	 * @see {@link WordMap#getWordId(String)}
	 */
	public int getWordId(String word){
		return wordMap.getWordId(word);
	}

	/**
	 * Returns the document number in this corpus currently. 
	 */
	public int docCount() {
		return documents.size();
	}

	/**
	 * Returns the word number in this corpus currently. 
	 * @see {@link WordMap#size()}
	 */
	public int wordCount() {
		return wordMap.size();
	}

	/**
	 * Returns the word id of the n-th word in the document with specified docId
	 */
	public int getWordId(int docId, int n) {
		// TODO Auto-generated method stub
		return getWordId(getDoc(docId).getWord(n));
	}

}
