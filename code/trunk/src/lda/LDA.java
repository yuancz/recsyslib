package lda;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import core.RecSysLibException;

import io.CSVReader;
import io.CSVWriter;
import io.Outputer;

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
	
	// Input parameters
	private String filePath;// save the result file		
	
	// Inner parameters
	private GibbsSampler sampler;
	
	private Corpus corpus;
	
	private LDAConfig config;
	
	private int numT;// topic number
	
	private int numI;// iteration number
	
	private int saveI;// save intermediate result per saveI iterations	
	
	private int top;// show top word in phi
	
	private int curI;// iteration number currently
	
	/**
	 * Constructs the LDA model from the folder pointed by the given file path, the result also will be saved in this folder. 
	 * Two files must already be in this folder, one is corpus.txt, and another is config.txt. 
	 * @see Corpus#createCorpus(String)
	 * @see LDAConfig
	 */
	public LDA(String filePath){
		System.out.println("Initializing LDA...");
		File folder = new File(filePath);
		if(!folder.isDirectory())
			throw new RecSysLibException("The sepcified file path does not exist. ");
		this.filePath = filePath;
		config = new LDAConfig(filePath+LDAConfig.CONFIG);
		System.out.println("Loading corpus...");
		corpus = Corpus.createCorpus(filePath+LDAConfig.CORPUS);
		// check curI
		numI = config.numI;		
		curI = config.curI;		
		if(curI == 0) sampler = new GibbsSampler(corpus, config);
		else if(curI<numI && curI>0)sampler = new GibbsSampler(corpus, config, loadCntZ());
		else throw new RecSysLibException("The curI value must be in [0, numI). ");		
		saveI = config.saveI;
		numT = config.numT;		
		// check top
		top = config.top;
		if(top == 0)top = corpus.wordCount();	
		System.out.println("Initialization complete. ");
	}

	public void estimating(){
		for(int i = curI+1;i<=numI;i++){
			System.out.println("Estimating..."+i);
			sampler.sampling();
			if(saveI > 0 && i%saveI == 0){
				curI = i;
				save();
			}			
		}
		if(curI < numI){
			curI = numI;
			save();
		}
	}
	
	private int[][] loadCntZ(){
		CSVReader reader = new CSVReader(filePath+curI+LDAConfig.Z);
		int[][] cntZ = new int[corpus.docCount()][];
		int docId = -1;;
		while(reader.hasNext()){
			String element = reader.next();
			if(reader.isNewLine()){
				docId = corpus.getDocId(element);
				cntZ[docId] = new int[corpus.getDoc(docId).wordCount()];
			}
			else {
				cntZ[docId][reader.getColumn()-1] = Integer.valueOf(element);
			}
		}
		reader.close();
		return cntZ;
	}
	
	public void save(){
		saveConfig();
		savePhi();
		saveTheta();
		saveCntZ();
	}
	
	private void saveConfig() {
		config.curI = curI;
		Outputer out = new Outputer(filePath+LDAConfig.CONFIG);
		for(String param : LDAConfig.Params){
			String line = "";
			switch(param){
			case "numI":
				line = param+"="+config.numI;
				break;
			case "saveI":
				line = param+"="+config.saveI;
				break;
			case "curI":
				line = param+"="+config.curI;
				break;
			case "numT":
				line = param+"="+config.numT;
				break;
			case "top":
				line = param+"="+config.top;
				break;
			case "alpha":
				line = param+"="+config.alpha;
				break;
			case "beta":
				line = param+"="+config.beta;
				break;
			}
			out.writeLine(line);
		}
		out.flush();
		out.close();
	}

	private void saveCntZ() {
		int[][] cntZ = sampler.getCntZ();
		CSVWriter writer = new CSVWriter(filePath+curI+LDAConfig.Z);
		for(int docId = 0;docId<corpus.docCount();docId++){
			Doc doc = corpus.getDoc(docId);
			String title = doc.getTitle();
			writer.writeElement(title);
			for(int w = 0;w<cntZ[docId].length;w++){
				writer.writeElement(String.valueOf(cntZ[docId][w]));
			}
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}

	private void saveTheta() {
		double[][] theta = sampler.getTheta();
		CSVWriter writer = new CSVWriter(filePath+curI+LDAConfig.THETA);
		writer.writeElement("Theta");
		for(int t = 0;t<numT;t++){
			writer.writeElement("Topic"+t);
		}
		writer.newLine();
		for(int docId = 0;docId<corpus.docCount();docId++){
			Doc doc = corpus.getDoc(docId);
			String title = doc.getTitle();
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

	private void savePhi() {
		double[][] phi = sampler.getPhi();
		int numW = corpus.wordCount();
		String[][] ordered = new String[numT*2][top];
		for(int t = 0;t<numT;t++){
			LinkedList<Pair<String, Double>> pairs = new LinkedList<>();
			for(int w = 0;w<numW;w++){
				String word = corpus.getWord(w);
				pairs.add(new Pair<String, Double>(word, phi[t][w]));
			}
			System.out.println(pairs.size()+" "+numW);
			Collections.sort(pairs, new ValueComparator());
			System.out.println(pairs.size()+" "+numW);
			int i = 0;
			while(i < top){
				Pair<String, Double> pair = pairs.get(numW-i-1);
				ordered[t*2][i] = pair.getKey();
				ordered[t*2+1][i] = pair.getValue().toString();
				i++;
			}
		}
		CSVWriter writer = new CSVWriter(filePath+curI+LDAConfig.PHI);
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
