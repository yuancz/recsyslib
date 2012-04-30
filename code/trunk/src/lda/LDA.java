package lda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import io.CSVWriter;

import collections.Pair;

/**
 * The implementation of Latent Dirichlet Allocation (LDA).
 * More about LDA, see the paper:
 * <br>Blei, D.M. and Ng, A.Y. and Jordan, M.I. "Latent Dirichlet allocation". Journal of Machine Learning Research, 2003. 
 * @version 1.0 2012-4-29
 * @author Tan Chang
 * @since JDK 1.7
 * @see GibbsSampler
 */
public final class LDA {
	
	private static final String PHI = "_phi.csv";
	
	private static final String THETA = "_theta.csv";
	
	private static final String DT = "_cntDT.csv";
	
	private static final String TW = "_cntTW.csv";
	
	// Input
	private String filePath;// save the result file
		
	private Corpus corpus;
	
	private int numT;// topic number
	
	private int numI;// iteration number
	
	private int saveI;// save intermediate result per saveI iterations	
	
	private int top;// show top word in phi
	
	// Inner
	private GibbsSampler sampler;
	
	private int curI;// iteration number currently
	
	public LDA(){
		//TODO
		// how about save and load cntZ, corpus rebuild, etc
	}

	public void estimating(){
		for(int i = 1;i<=numI;i++){
			sampler.sampling();
			if(i%saveI == 0){
				curI = i;
				save();
			}			
		}
		if(curI < numI){
			curI = numI;
			save();
		}
	}
	
	public void save(){
		savePhi(sampler.getPhi());
		saveTheta(sampler.getTheta());
		saveCntDT(sampler.cntDT());
		saveCntTW(sampler.cntTW());
	}
	
	private void saveCntTW(int[][] cntTW) {
		CSVWriter writer = new CSVWriter(filePath+curI+TW);
		for(int i = 0;i<cntTW.length;i++){
			for(int j = 0;j<cntTW[i].length;j++){
				writer.writeElement(String.valueOf(cntTW[i][j]));
			}
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	private void saveCntDT(int[][] cntDT) {
		CSVWriter writer = new CSVWriter(filePath+curI+DT);
		for(int i = 0;i<cntDT.length;i++){
			for(int j = 0;j<cntDT[i].length;j++){
				writer.writeElement(String.valueOf(cntDT[i][j]));
			}
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	private void saveTheta(double[][] theta) {
		CSVWriter writer = new CSVWriter(filePath+curI+THETA);
		writer.writeElement("Theta");
		for(int t = 0;t<numT;t++){
			writer.writeElement("Topic"+t);
		}
		writer.newLine();
		for(int docId = 0;docId<corpus.docCount();docId++){
			Document doc = corpus.getDoc(docId);
			String title;
			if(doc instanceof SimpleDoc)title = ((SimpleDoc)doc).getTitle();
			else title = "Doc_"+docId;
			writer.writeElement(title);
			for(int t = 0;t<numT;t++){
				writer.writeElement(String.valueOf(theta[docId][t]));
			}
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}
	
	private class ValueComparator implements Comparator<Pair<String,Double>>{
		@Override
		public int compare(Pair<String,Double> p1, Pair<String,Double> p2) {
			return Double.compare(p1.getValue(), p2.getValue());
		}
	}

	private void savePhi(double[][] phi) {
		int numW = corpus.wordCount();
		String[][] ordered = new String[numT*2][top];
		for(int t = 0;t<numT;t++){
			TreeSet<Pair<String, Double>> pairs = new TreeSet<>(new ValueComparator());
			for(int w = 0;w<numW;w++){
				String word = corpus.getWord(w);
				pairs.add(new Pair<String, Double>(word, phi[t][w]));
			}
			Iterator<Pair<String, Double>> descIte = pairs.descendingIterator();
			int i = 0;
			while(descIte.hasNext() && i < top){
				Pair<String, Double> pair = descIte.next();
				ordered[t*2][i] = pair.getKey();
				ordered[t*2+1][i] = pair.getValue().toString();
				i++;
			}
		}
		CSVWriter writer = new CSVWriter(filePath+curI+PHI);
		for(int t = 0;t<numT;t++){
			writer.writeElement("TOPIC_"+t);
			writer.writeElement("");
		}
		writer.newLine();
		for(int i = 0;i<top;i++){
			for(int t = 0;t<numT;t++){
				writer.writeElement(ordered[t*2][i]);
				writer.writeElement(ordered[t*2+1][i]);
			}
			writer.newLine();
		}
		writer.flush();
		writer.close();		
	}

}
