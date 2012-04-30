package lda;

import java.util.Arrays;

/**
 * The implementation of the Gibbs sampling for Latent Dirichlet Allocation
 * (LDA). More about Gibbs sampling, see the paper: <br>
 * Griffiths, T.L. and Steyvers, M. "Finding scientific topics". Proceedings of
 * the National Academy of Sciences of the United States of America, 2004.
 * 
 * @version 1.0 2012-4-29
 * @author Tan Chang
 * @since JDK 1.7
 * @see LDA
 */
public final class GibbsSampler {
	
	// Input parameters
	private Corpus corpus;

	private int numD;// document number
	private int numW;// word number
	private int numT;// topic number

	private double alpha; // super-parameter alpha
	private double beta; // super-parameter beta

	// Temp variables while sampling
	private double alphaSum;
	private double betaSum;
	private double[] p;
	private int[][] cntZ;// topic assignments for words, size
							// D*document.wordCount()
	private int[][] cntTW;// number of instances of word i assigned to topic j,
							// size T*W
	private int[][] cntDT;// number of words in document i assigned to topic j,
							// size D*T
	private int[] cntT;// total number of words assigned to topic j, size T
	private int[] cntD;// total number of words in document i, size D

	/**
	 * Rebuilds a GibbsSampler object based on the given parameters.
	 */
	public GibbsSampler(Corpus corpus, LDAConfig config, int[][] cntZ) {
		this.corpus = corpus;
		this.numD = corpus.docCount();
		this.numW = corpus.wordCount();
		this.numT = config.numT;
		this.alpha = config.alpha;
		this.beta = config.beta;
		this.cntZ = cntZ;
		initTempVariables();
	}
	
	/**
	 * Constructs a new GibbsSampler object based on the given parameters.
	 */
	public GibbsSampler(Corpus corpus, LDAConfig config) {
		this.corpus = corpus;
		this.numD = corpus.docCount();
		this.numW = corpus.wordCount();
		this.numT = config.numT;
		this.alpha = config.alpha;
		this.beta = config.beta;
		int i = 0;
		while(i < corpus.wordCount()){
			System.out.println(i+" "+corpus.getWord(i));
			i++;
		}
		initCntZ();
		initTempVariables();
	}

	// Initializing temp variables cntZ
	private void initCntZ() {
		cntZ = new int[numD][];
		for (int docId = 0; docId < numD; docId++) {
			int cntW = corpus.getDoc(docId).wordCount();// words number in document docId
			cntZ[docId] = new int[cntW];
			// initialize for z
			for (int n = 0; n < cntW; n++) {
				int topic = (int) Math.floor(Math.random() * numT);
				cntZ[docId][n] = topic;
			}
		}
	}

	// Initializing temp variables while sampling
	private void initTempVariables() {
		alphaSum = alpha * numT;
		betaSum = beta * numW;
		p = new double[numT];

		cntTW = new int[numT][numW];
		cntDT = new int[numD][numT];
		cntT = new int[numT];
		cntD = new int[numD];
		for (int i = 0; i < numT; i++) {
			Arrays.fill(cntTW[i], 0);
		}
		for (int i = 0; i < numD; i++) {
			Arrays.fill(cntDT[i], 0);
		}
		Arrays.fill(cntT, 0);
		
		// temp variables related to cntZ
		for (int docId = 0; docId < numD; docId++) {
			int cntW = corpus.getDoc(docId).wordCount();// words number in document docId
			cntD[docId] = cntW;
			for (int n = 0; n < cntW; n++) {
				int topic = cntZ[docId][n];
				cntTW[topic][corpus.getWordId(docId, n)]++;
				cntT[topic]++;
				// number of words in document i assigned to topic j
				cntDT[docId][topic]++;
			}
		}
	}

	/**
	 * Estimated result theta, document-topic distributions, size D*T
	 */
	public double[][] getTheta() {
		double[][] theta = new double[numD][numT];
		for (int d = 0; d < numD; d++) {
			for (int t = 0; t < numT; t++) {
				theta[d][t] = (cntDT[d][t] + alpha) / (cntD[d] + alphaSum);
			}
		}
		return theta;
	}

	/**
	 * Estimated result phi, topic-word distributions, size T*W
	 */
	public double[][] getPhi() {
		double[][] phi = new double[numT][numW];
		for (int t = 0; t < numT; t++) {
			for (int w = 0; w < numW; w++) {
				phi[t][w] = (cntTW[t][w] + beta) / (cntT[t] + betaSum);
			}
		}
		return phi;
	}

	/**
	 * Used by LDA for saving the cntZ matrix.
	 */
	public int[][] getCntZ() {
		return cntZ;
	}

	/**
	 * Sampling once for all documents in the specified corpus.
	 */
	public void sampling() {
		for (int docId = 0; docId < numD; docId++) {
			for (int n = 0; n < corpus.getDoc(docId).wordCount(); n++) {
				sample(docId, n);
			}
		}
	}

	// Sampling the n-th word in document docId
	private void sample(int docId, int n) {
		int z = cntZ[docId][n];
		int w = corpus.getWordId(docId, n);

		// remove z_i from the count variable
		cntDT[docId][z] -= 1;
		cntTW[z][w] -= 1;
		cntT[z] -= 1;
		cntD[docId] -= 1;

		// do multi-nominal sampling via cumulative method
		for (int t = 0; t < numT; t++) {
			p[t] = (cntTW[t][w] + beta) / (cntT[t] + betaSum)
					* (cntDT[docId][t] + alpha) / (cntD[docId] + alphaSum);
		}

		// cumulate multinomial parameters
		// scaled sample because of unnormalized p[]
		for (int t = 1; t < numT; t++) {
			p[t] += p[t - 1];
		}

		// sample topic w.r.t distribution p
		double randP = Math.random() * p[numT - 1];
		int topic = 0;
		while (randP >= p[topic]) {
			topic++;
		}

		// add newly estimated z_i to count variables
		cntZ[docId][n] = topic;
		cntDT[docId][topic] += 1;
		cntTW[topic][w] += 1;
		cntT[topic] += 1;
		cntD[docId] += 1;
	}

}
