package lda;

import java.util.Iterator;

import matrix.Matrix;
import matrix.SparseMatrix;

import collections.BidiMap;
import collections.HashBag;
import collections.HashBidiMap;

/**
 * This <tt>Corpus</tt> class presents a corpus included some documents. 
 * It allows save and index a document and automatically assign an id to each document also for each word. 
 * The toDocWordMatrix method will return a document-word statistics matrix 
 * including all documents and word in this corpus currently.
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class Corpus {
	
	private WordMap wordMap;
	
	private BidiMap<Integer, Document> documents;
	
	private Matrix docWordMatrix;
	
	private int modCount;
	
	private int modCountUntilToMatrix;
	
	/**
	 * Constructs a empty corpus. 
	 */
	public Corpus(){
		wordMap = new WordMap();
		documents = new HashBidiMap<>();
		docWordMatrix = null;
		modCount = 0;
	}

	/**
	 * Adds a new document doc into this corpus, returns false if the doc is null or already in this corpus.  
	 */
	public boolean addDocument(Document doc){
		if(doc == null || documents.containsValue(doc))return false;
		int docId = documents.size();
		documents.put(docId, doc);
		modCount++;
		return true;
	}
	
	/**
	 * Returns a document-word matrix, each row presents a document and each column presents a word, 
	 * each element is the word count in the document. 
	 * The id of document or word is the row or column number. 
	 */
	public Matrix toDocWordMatrix(){
		if(docWordMatrix == null || modCountUntilToMatrix < modCount){
			modCountUntilToMatrix = modCount;
			docWordMatrix = new SparseMatrix(documents.size(), wordMap.size());
			for(Document doc : documents.valueSet()){
				parseDoc(doc);
			}
		}
		return docWordMatrix;
	}
	
	private void parseDoc(Document doc) {
		Iterator<String> docIte = doc.iterator();
		HashBag<String> wordCnt = new HashBag<>();
		while(docIte.hasNext()){
			wordCnt.add(docIte.next());
		}
		for(String word : wordCnt.uniqueSet()){
			docWordMatrix.setValue(getDocId(doc), getWordId(word), wordCnt.getCount(word));
		}
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
	 * @see {@link WordMap#addWord(String)}
	 */
	public int addWord(String word){
		if(!wordMap.containsWord(word))modCount++;
		return wordMap.addWord(word);
	}
}
