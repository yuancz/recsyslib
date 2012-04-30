package sample;

import lda.LDA;

public class LDASample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LDA lda = new LDA("./data/ldaData/");
		lda.estimating();
	}

}
