package lda;

import io.Inputer;

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
	
	 /**
	  * Creats a corpus from the given file path. 
	  * It assumes that the corpus is a text file, and each line is a document with two parts: a title and a main body. 
	  * The title and the main body of the document is separated by table character '\t', 
	  * and each word in the main body is separated by comma character ','. 
	  */
	public static Corpus createCorpus(String filePath) {
		Corpus corpus = new Corpus();
		Inputer in = new Inputer(filePath);
		String line = in.readLine();
		while(line != null){
			String[] strs = line.split("\t");
			Doc doc = new Doc(strs[0], strs[1], ',');
			corpus.addDoc(doc);
			line = in.readLine();
		}
		in.close();
		System.out.println(corpus.wordMap.wordSet().toString());
		return corpus;
	}
	
	private WordMap wordMap;
	
	private BidiMap<Integer, Doc> documents;
	
	private BidiMap<Integer, String> titles;
	
	/**
	 * Constructs a empty corpus. 
	 */
	public Corpus(){
		wordMap = new WordMap();
		documents = new HashBidiMap<>();
		titles = new HashBidiMap<>();;
	}

	/**
	 * Adds a new document doc into this corpus, all words in the document will be parsed and saved into this corpus.
	 * Returns false if the doc is null or already in this corpus.  
	 */
	public boolean addDoc(Doc doc){
		if(doc == null || documents.containsValue(doc))return false;
		int docId = documents.size();
		documents.put(docId, doc);
		titles.put(docId, doc.getTitle());
		parseDoc(doc);
		return true;
	}
	
	private void parseDoc(Doc doc) {
		Iterator<String> docIte = doc.iterator();
		while(docIte.hasNext()){
			String word = docIte.next();
			if(!wordMap.containsWord(word))addWord(word);
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
	public Doc getDoc(int docId){
		return documents.getValue(docId);
	}
	
	/**
	 * Returns the document id mapping the specified document, or -1 if the document is not existing in this corpus. 
	 */
	public int getDocId(Doc doc){
		Integer docId = documents.getKey(doc);
		if(docId == null)return -1;
		return docId.intValue();
	}
	
	/**
	 * Returns the document id mapping the specified title, or -1 if the document is not existing in this corpus. 
	 */
	public int getDocId(String title){
		Integer docId = titles.getKey(title);
		if(docId == null)return -1;
		return docId.intValue();
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
		System.out.println(docId+" "+n+" "+getDoc(docId).getWord(n));
		return getWordId(getDoc(docId).getWord(n));
	}

}
