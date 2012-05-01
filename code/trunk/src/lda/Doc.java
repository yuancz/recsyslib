package lda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The class <tt>Doc</tt> presents a document. 
 * It assumes that a document includes two String objects, one is title, another is main body. 
 * And in the main body, words are separated by a specified char as the only separator. 
 * If the separator is not specified, the whitespace will be the default separator. 
 * @version 1.0 2012-4-30
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class Doc {
	
	public static final char DEFAULT_SEPARATOR = ' ';

	protected String title;
	
	protected String mainBody;
	
	protected List<String> wordList;
	
	protected char separator;
	
	/**
	 * Constructs a SimpleDoc by the title, mainBody and separator. 
	 */
	public Doc(String title, String mainBody, char separator){
		this.title = title;
		this.mainBody = mainBody;
		this.separator = separator;
		wordList = Arrays.asList(mainBody.split(String.valueOf(separator)));
	}
	
	/**
	 * Constructs a SimpleDoc by the title, mainBody, the separator will be the whitespace. 
	 */
	public Doc(String title, String mainBody){
		this(title, mainBody, DEFAULT_SEPARATOR);
	}
	
	/**
	 * Returns an iterator which can iterate each word in the main body of this document. 
	 */
	public Iterator<String> iterator() {
		return wordList.iterator();
	}	

	/**
	 * Returns the word number of the main body of this document.
	 */
	public int wordCount() {
		return wordList.size();
	}	
	
	/**
	 * Return the n-th word in the main body of this document. 
	 */
	public String getWord(int n){
		return wordList.get(n);
	}

	/**
	 * Returns the title of this document. 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the main body of this document. 
	 */
	public String getMainBody() {
		return mainBody;
	}

	/**
	 * Returns the separator of this document. 
	 */
	public char getSeparator() {
		return separator;
	}

}
