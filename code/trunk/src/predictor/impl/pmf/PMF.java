package predictor.impl.pmf;

import io.CSVWriter;

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
 * @version 1.0 2012-7-5
 * @author Tan Chang
 * @since JDK 1.7
 */
public class PMF {
	
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
	
	public PMF(DataSet dataSet){
		this.dataSet = dataSet;
		numU = dataSet.getUserCount();
		numV = dataSet.getItemCount();
		uidList = new ArrayList<Integer>(dataSet.getUserIds());
		iidList = new ArrayList<Integer>(dataSet.getItemIds());
		uf = new ArrayMatrix(numD,numU);
		vf = new ArrayMatrix(numD,numV);
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numU;j++){
				uf.setValue(i, j, Math.random());
			}
		}
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numV;j++){
				vf.setValue(i, j, Math.random());
			}
		}
		ufTemp = new ArrayMatrix(numD,numU);
		vfTemp = new ArrayMatrix(numD,numV);
	}	
	
	public void save(String filePath){
		String ufFile = "uf.txt";
		String vfFile = "vf.txt";
		File file = new File(filePath);
		if(!file.isDirectory())
			throw new RecSysLibException("The sepcified file path does not exist. ");
		CSVWriter out;
		//save uf
		out = new CSVWriter(filePath+ufFile);
		for(int i = 0;i<numU;i++){
			out.writeElement(String.valueOf(uidList.get(i)));
			for(int j = 0;j<numD;j++){
				out.writeElement(String.valueOf(uf.getValue(j, i)));
			}
			out.newLine();
		}
		out.flush();
		out.close();
		//save vf
		out = new CSVWriter(filePath+vfFile);
		for(int i = 0;i<numV;i++){
			out.writeElement(String.valueOf(iidList.get(i)));
			for(int j = 0;j<numD;j++){
				out.writeElement(String.valueOf(vf.getValue(j, i)));
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
	
	public boolean update(){
		double[][] upO = new double[numD][numU];
		double[][] upD = new double[numD][numV];
		for(int i = 0;i<uidList.size();i++){
			int userId = uidList.get(i);
			for(int j = 0;j<iidList.size();j++){
				int itemId = iidList.get(j);
				Rate rate = dataSet.getRate(userId, itemId);
					double rating = Vectors.dotMult(uf.getColumnVector(i), vf.getColumnVector(j));
					double diff = rate.getRating() - rating;
					for(int k = 0;k<numD;k++){
						upO[k][i] += vf.getValue(k, j) * diff;
						upD[k][j] += uf.getValue(k, i) * diff;
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
	
	public double likelihood(){
		return likelihood(uf, vf);
	}
	
	private double likelihood(Matrix uf, Matrix vf){
		double squareDiff = 0;
		for(int i = 0;i<uidList.size();i++){
			int userId = uidList.get(i);
			for(int j = 0;j<iidList.size();j++){
				int itemId = iidList.get(j);
				Rate rate = dataSet.getRate(userId, itemId);
				if(rate != null){					
					double rating = Vectors.dotMult(uf.getColumnVector(i), vf.getColumnVector(j));
					squareDiff += (rate.getRating()-rating)*(rate.getRating()-rating);
				}
			}
		}
		double l2Norm = 0;
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numU;j++){
				l2Norm += uf.getValue(i, j)*uf.getValue(i, j);
			}
		}
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numV;j++){
				l2Norm += vf.getValue(i, j)*vf.getValue(i, j);
			}
		}
		return -squareDiff-rs*l2Norm;		
	}
	
	private void tryUpdate(double[][] upO, double[][] upD) {
		double alpha = lr;
		double beta = -rs;
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numU;j++){
				double old = uf.getValue(i, j);
				double temp = old + alpha * (beta * old + upO[i][j]);
				ufTemp.setValue(i, j, temp);
			}
		}
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numV;j++){
				double old = vf.getValue(i, j);
				double temp = old + alpha * (beta * old + upD[i][j]);
				vfTemp.setValue(i, j, temp);
			}
		}		
	}
	
	private void applyUpdate() {
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numU;j++){
				uf.setValue(i, j, ufTemp.getValue(i, j));
			}
		}
		for(int i = 0;i<numD;i++){
			for(int j = 0;j<numV;j++){
				vf.setValue(i, j, vfTemp.getValue(i, j));
			}
		}			
	}

}
