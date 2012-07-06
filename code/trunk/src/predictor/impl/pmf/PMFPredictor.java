package predictor.impl.pmf;

import io.Inputer;

import java.util.ArrayList;
import java.util.List;

import matrix.ArrayMatrix;
import matrix.Matrix;
import matrix.Vectors;
import core.DataSet;
import core.Rate;
import predictor.AbstractPredictor;
import predictor.Predictor;

/**
 * The <tt>PMFPredictor</tt> uses a PMF model to predict the ratings. 
 * The PMF model can be trained by a <tt>PMF</tt> object, and be saved and loaded from the specified file path.
 * @version 1.0 2012-7-6
 * @author Tan Chang
 * @since JDK 1.7
 * @see PMF
 */
public final class PMFPredictor extends AbstractPredictor implements Predictor {
	
	private List<Integer> uidList;
	private List<Integer> iidList;

	private Matrix uf;//latent user feature matrix, D*U
	private Matrix vf;//latent item feature matrix, D*I	
	
	/**
	 * Constructs a <tt>PMFPredictor</tt> with given data set and file path. 
	 * This data set should be same one with which is used for training the PMF model. 
	 */
	public PMFPredictor(DataSet dataSet, String filePath) {
		super(dataSet);
		load(filePath);
	}
	
	private void load(String filePath) {
		Inputer in;
		String line;
		String[] strs;
		int numD, numU, numV;
		//uidList and uf
		in = new Inputer(filePath + PMF.ufFile);
		line = in.readLine();
		strs = line.split("\t");
		numU = Integer.valueOf(strs[0]);
		numD = Integer.valueOf(strs[1]);
		uidList = new ArrayList<Integer>();
		uf = new ArrayMatrix(numU,numD);
		line = in.readLine();
		while(line != null){
			strs = line.split("\t");
			int j = uidList.size();
			int uid = Integer.valueOf(strs[0]);
			uidList.add(uid);
			for(int i = 0;i<numD;i++){
				uf.setValue(i, j, Double.valueOf(strs[i+1]));
			}
			line = in.readLine();
		}
		in.close();
		//iidList and vf
		in = new Inputer(filePath + PMF.vfFile);
		line = in.readLine();
		strs = line.split("\t");
		numV = Integer.valueOf(strs[0]);
		numD = Integer.valueOf(strs[1]);
		iidList = new ArrayList<Integer>();
		vf = new ArrayMatrix(numV,numD);
		line = in.readLine();
		while(line != null){
			strs = line.split("\t");
			int j = iidList.size();
			int iid = Integer.valueOf(strs[0]);
			iidList.add(iid);
			for(int i = 0;i<numD;i++){
				vf.setValue(i, j, Double.valueOf(strs[i+1]));
			}
			line = in.readLine();
		}
		in.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rate getRate(int userId, int itemId) {
		checkUserId(userId);
		checkItemId(itemId);
		int i = uidList.indexOf(userId);
		int j = iidList.indexOf(itemId);
		double rating = Vectors.dotMult(uf.getRowVector(i), vf.getRowVector(j));
		return new Rate(userId, itemId, rating);
	}

}
