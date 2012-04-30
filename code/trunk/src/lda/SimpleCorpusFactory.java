package lda;

import io.Inputer;

/**
 * The class <tt>SimpleCorpusFactory</tt> implements the interface <tt>CorpusFactory</tt>. 
 * It assumes that the corpus is a text file, and each line is a document with two parts: a title and a main body. 
 * The title and the main body of the document is separated by table character '\t', 
 * and each word in the main body is separated by comma character ','. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see Corpus
 * @see SimpleDoc
 */
public class SimpleCorpusFactory implements CorpusFactory {
	
	/**
	 * {@inheritDoc}
	 * The first value of the <code>args</code> will be processed as the specified file path of the corpus. 
	 */
	public Corpus createCorpus(String... args) {
		if(args.length == 0)return null;
		Corpus corpus = new Corpus();
		Inputer in = new Inputer(args[0]);
		String line = in.readLine();
		while(line != null){
			String[] strs = line.split("\t");
			Document doc = new SimpleDoc(strs[0], strs[1], ',');
			corpus.addDoc(doc);
			line = in.readLine();
		}
		in.close();
		return corpus;
	}

}
