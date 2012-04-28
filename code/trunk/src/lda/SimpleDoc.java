package lda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A simple implementation of the interface <tt>Document</tt>.
 * It assumes that a document includes two String objects, one is title, another is main body. 
 * And in the main body, words are separated by a specified char as the only separator. 
 * If the separator is not specified, the whitespace will be the default separator. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 */
public class SimpleDoc implements Document {
	
	public static final char DEFAULT_SEPARATOR = ' ';

	protected String title;
	
	protected String mainBody;
	
	protected List<String> wordList;
	
	protected char separator;
	
	/**
	 * Constructs a SimpleDoc by the title, mainBody and separator. 
	 */
	public SimpleDoc(String title, String mainBody, char separator){
		this.title = title;
		this.mainBody = mainBody;
		this.separator = separator;
		wordList = null;
	}
	
	/**
	 * Constructs a SimpleDoc by the title, mainBody, the separator will be the whitespace. 
	 */
	public SimpleDoc(String title, String mainBody){
		this(title, mainBody, DEFAULT_SEPARATOR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<String> iterator() {
		if(wordList == null){
			wordList = Arrays.asList(mainBody.split(String.valueOf(separator)));
		}
		return wordList.iterator();
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
