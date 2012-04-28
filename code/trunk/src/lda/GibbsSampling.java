package lda;

/**
 * The implementation of the Gibbs sampling for Latent Dirichlet Allocation (LDA).
 * More about LDA and Gibbs sampling, see the followed two papers:
 * <br>Blei, D.M. and Ng, A.Y. and Jordan, M.I. "Latent Dirichlet allocation". Journal of Machine Learning Research, 2003. 
 * <br>Griffiths, T.L. and Steyvers, M. "Finding scientific topics". Proceedings of the National Academy of Sciences of the United States of America, 2004. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class GibbsSampling {
	
	// Input corpus
	private Corpus corpus;
	private int numD;// document number
	private int numW;// word number
	
	// Input parameters
	private int numT;// topic number
//	private int numI;// iteration number
	private double alpha;	// super-parameter alpha 
	private double beta;	// super-parameter beta
	
	// Estimated/Inferenced parameters
	private double [][] theta;	// document-topic distributions, size D*T
	private double [][] phi;	// topic-word distributions, size T*W

	// Temp variables while sampling
	private int[][] cntZ;// topic assignments for words, size D*document.count
	private int[][] cntTW;// number of instances of word i assigned to topic j, size T*W
	private int[][] cntDT;// number of words in document i assigned to topic j, size D*T
	private int[] cntT;// total number of words assigned to topic j, size T
	private int[] cntD;// total number of words in document i, size D
	
	public void sampling(){
//		for (int d = 0; d < numD; d++) {
//			for (int m = 0; m < corpus.getDocWordCount(d); m++) {
//				sample(d, m);
//			}
//		}
	}
	
}
