package predictor.impl.pmf;

import core.DataSet;
import core.Rate;
import predictor.AbstractPredictor;
import predictor.Predictor;

/**
 * The implementation of Probabilistic Matrix Factorization (PMF).
 * More about PMF, see the paper:
 * <br>Salakhutdinov, R. and Mnih, A. "Probabilistic matrix factorization". 
 * Advances in neural information processing systems, 2008. 
 * @version 1.0 2012-7-5
 * @author Tan Chang
 * @since JDK 1.7
 * @see PMF
 */
public final class PMFPredictor extends AbstractPredictor implements Predictor {
	
	/**
	 * 
	 * @param dataSet
	 * @param filePath
	 */
	public PMFPredictor(DataSet dataSet, String filePath) {
		super(dataSet);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public Rate getRate(int userId, int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
