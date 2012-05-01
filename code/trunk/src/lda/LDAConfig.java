package lda;

import java.util.Arrays;
import java.util.HashSet;

import io.Inputer;

/**
 * This class saves config information for a <tt>LDA</tt> object. 
 * @version 1.0 2012-4-30
 * @author Tan Chang
 * @since JDK 1.7
 * @see LDA
 */
final class LDAConfig {
	
	protected static final String PHI = "_phi.csv";
	
	protected static final String THETA = "_theta.csv";
	
	protected static final String Z = "_cntZ.csv";
	
	protected static final String CORPUS = "corpus.txt";
	
	protected static final String CONFIG = "config.txt";
	
	protected static final String[] Params = {"numI", "saveI", "curI", "top", "numT", "alpha", "beta"};
	
	private static final int ITE = 1000;
	private static final int SAVE = 100;
	private static final int CUR_ITE = 0;
	private static final int TOPIC = 50;
	private static final int TOP = 30;
	private static double ALPHA = 0.5;
	private static final double BETA = 0.1;	
	
	protected int numI;// iteration number
	
	protected int saveI;// save intermediate result per saveI iterations	
	
	protected int curI;// iteration number currently
	
	protected int numT;// topic number
	
	protected int top;// show top words in phi result file
	
	protected double alpha;// super-parameter alpha 
	
	protected double beta;// super-parameter beta
	
	protected LDAConfig(String filePath){
		HashSet<String> params = new HashSet<>(Arrays.asList(Params));
		Inputer in = new Inputer(filePath);
		String line = in.readLine();
		while(line != null){
			int comments = line.indexOf("#");
			String str;
			if(comments != -1)str = line.substring(0, comments).trim();
			else str = line.trim();
			if(str.length()>0){
				String[] strs = str.split("=");
				switch(strs[0]){
				case "numI":
					numI = Integer.valueOf(strs[1]);
					break;
				case "saveI":
					saveI = Integer.valueOf(strs[1]);
					break;
				case "curI":
					curI = Integer.valueOf(strs[1]);
					break;
				case "numT":
					numT = Integer.valueOf(strs[1]);
					break;
				case "top":
					top = Integer.valueOf(strs[1]);
					break;
				case "alpha":
					alpha = Double.valueOf(strs[1]);
					break;
				case "beta":
					beta = Double.valueOf(strs[1]);
					break;
				}
				params.remove(strs[0]);
			}
			line = in.readLine();
		}
		in.close();
		if(params.size()>0){
			for(String param : params){
				switch(param){
				case "numI":
					numI = ITE;
					break;
				case "saveI":
					saveI = SAVE;
					break;
				case "curI":
					curI = CUR_ITE;
					break;
				case "numT":
					numT = TOPIC;
					break;
				case "top":
					top = TOP;
					break;
				case "alpha":
					alpha = ALPHA;
					break;
				case "beta":
					beta = BETA;
					break;
				}
			}
		}
	}
	
}
