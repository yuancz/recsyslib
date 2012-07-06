package predictor.impl.pmf;

import io.Outputer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import matrix.ArrayMatrix;
import matrix.Matrix;
import matrix.Vectors;

import core.DataSet;
import core.Rate;
import core.RecSysLibException;

/**
 * The implementation of Probabilistic Matrix Factorization (PMF).
 * More about PMF, see the paper:
 * <br>Salakhutdinov, R. and Mnih, A. "Probabilistic matrix factorization". 
 * Advances in neural information processing systems, 2008. 
 * This implementation refers to "Netflix algorithm: Prize Tribute Recommendation Algorithm in Python"
 * (http://blog.smellthedata.com/2009/06/netflix-prize-tribute-recommendation.html). 
 * It can be used as follows, the trained PMF model will be saved in the specified file path:
 * <pre>
 * PMF pmf = new PMF(dataSet);
 * pmf.estimating();
 * pmf.save(filePath);
 * </pre>
 * @version 1.0 2012-7-5
 * @author Tan Chang
 * @since JDK 1.7
 */
public class PMF {
	
	protected static final String ufFile = "uf.txt";
	protected static final String vfFile = "vf.txt";
	
	private DataSet dataSet;
	
	private List<Integer> uidList;
	private List<Integer> iidList;

	private Matrix uf;//latent user feature matrix, D*U
	private Matrix vf;//latent item feature matrix, D*I	
	
	private Matrix ufTemp;//latent user feature matrix, D*U
	private Matrix vfTemp;//latent item feature matrix, D*I	
	
	private int numU;//number of users
	private int numV;//number of items
	
	private int numD = 5;//number of latent features
	private double lr = 0.0001;//learning rate
	private double rs = 0.1;//regularization strength
	private boolean converged = false;
	
	public PMF(DataSet dataSet, int numD){
		this.dataSet = dataSet;
		this.numD = numD;
		numU = dataSet.getUserCount();
		numV = dataSet.getItemCount();
		uidList = new ArrayList<Integer>(dataSet.getUserIds());
		iidList = new ArrayList<Integer>(dataSet.getItemIds());
		uf = new ArrayMatrix(numU,numD);
		vf = new ArrayMatrix(numV,numD);
		for(int i = 0;i<numU;i++){
			for(int j = 0;j<numD;j++){
				uf.setValue(i, j, Math.random());
			}
		}
		for(int i = 0;i<numV;i++){
			for(int j = 0;j<numD;j++){
				vf.setValue(i, j, Math.random());
			}
		}
		ufTemp = new ArrayMatrix(numU,numD);
		vfTemp = new ArrayMatrix(numV,numD);
	}	
	
	public void save(String filePath){
		File file = new File(filePath);
		if(!file.isDirectory())
			throw new RecSysLibException("The sepcified file path does not exist. ");
		Outputer out;
		//save uf
		out = new Outputer(filePath+ufFile);
		out.writeLine(numU+"\t"+numD);
		for(int i = 0;i<numU;i++){
			out.write(uidList.get(i)+"\t");
			for(int j = 0;j<numD;j++){
				out.write(uf.getValue(i,j)+"\t");
			}
			out.newLine();
		}
		out.flush();
		out.close();
		//save vf
		out = new Outputer(filePath+vfFile);
		out.writeLine(numV+"\t"+numD);
		for(int i = 0;i<numV;i++){
			out.write(iidList.get(i)+"\t");
			for(int j = 0;j<numD;j++){
				out.write(vf.getValue(i,j)+"\t");
			}
			out.newLine();
		}
		out.flush();
		out.close();
	}
	
	public void estimating(){
		while (update()){
			System.out.println(likelihood());
		}
	}
	
	private boolean update(){
		double[][] upO = new double[numU][numD];
		double[][] upD = new double[numV][numD];
		for(int i = 0;i<numU;i++){
			int userId = uidList.get(i);
			for(int j = 0;j<numV;j++){
				int itemId = iidList.get(j);
				Rate rate = dataSet.getRate(userId, itemId);
				double rating = Vectors.dotMult(uf.getRowVector(i), vf.getRowVector(j));
				double diff = rate.getRating() - rating;
				for(int k = 0;k<numD;k++){
					upO[i][k] += vf.getValue(j,k) * diff;
					upD[j][k] += uf.getValue(i,k) * diff;
				}
			}
		}
		while(!converged){
			double like = likelihood(uf,vf);
			tryUpdate(upO,upD);
			double likeNew = likelihood(ufTemp, vfTemp);
			if(likeNew > like){
				applyUpdate();
				lr *= 1.25;
				if(likeNew - like < 0.1){
					converged = true;
					break;
				}
			}
			else{
				lr *= 0.5;
			}
			if(lr < 1e-10){
				converged = true;
			}
		}
		return !converged;
	}
	
	private double likelihood(){
		return likelihood(uf, vf);
	}
	
	private double likelihood(Matrix uf, Matrix vf){
		double squareDiff = 0;
		for(int i = 0;i<numU;i++){
			int userId = uidList.get(i);
			for(int j = 0;j<numV;j++){
				int itemId = iidList.get(j);
				Rate rate = dataSet.getRate(userId, itemId);
				if(rate != null){					
					double rating = Vectors.dotMult(uf.getRowVector(i), vf.getRowVector(j));
					squareDiff += (rate.getRating()-rating)*(rate.getRating()-rating);
				}
			}
		}
		double l2Norm = 0;
		for(int i = 0;i<numU;i++){
			for(int j = 0;j<numD;j++){
				l2Norm += uf.getValue(i, j)*uf.getValue(i, j);
			}
		}
		for(int i = 0;i<numV;i++){
			for(int j = 0;j<numD;j++){
				l2Norm += vf.getValue(i, j)*vf.getValue(i, j);
			}
		}
		return -squareDiff-rs*l2Norm;		
	}
	
	private void tryUpdate(double[][] upO, double[][] upD) {
		double alpha = lr;
		double beta = -rs;
		for(int i = 0;i<numU;i++){
			for(int j = 0;j<numD;j++){
				double old = uf.getValue(i, j);
				double temp = old + alpha * (beta * old + upO[i][j]);
				ufTemp.setValue(i, j, temp);
			}
		}
		for(int i = 0;i<numV;i++){
			for(int j = 0;j<numD;j++){
				double old = vf.getValue(i, j);
				double temp = old + alpha * (beta * old + upD[i][j]);
				vfTemp.setValue(i, j, temp);
			}
		}		
	}
	
	private void applyUpdate() {
		for(int i = 0;i<numU;i++){
			for(int j = 0;j<numD;j++){
				uf.setValue(i, j, ufTemp.getValue(i, j));
			}
		}
		for(int i = 0;i<numV;i++){
			for(int j = 0;j<numD;j++){
				vf.setValue(i, j, vfTemp.getValue(i, j));
			}
		}			
	}

}
