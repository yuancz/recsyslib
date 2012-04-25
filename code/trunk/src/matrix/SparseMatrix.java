package matrix;

import java.util.HashMap;

public class SparseMatrix extends AbstractMatrix implements Matrix {
	
	protected HashMap<Integer, HashMap<Integer, Double>> rcMap;
	
	public SparseMatrix(int rowNum, int columnNum){
		super(rowNum, columnNum);
	}

	public SparseMatrix(double[][] arr) {
		super(arr);
	}

	public SparseMatrix(Matrix m) {
		super(m);
	}
	
	@Override
	protected void createInnerMatrix(int rowNum, int columnNum) {
		rcMap = new HashMap<>();		
	}

	@Override
	public double getValue(int row, int column) {
		check(row, column);
		if(rcMap.containsKey(row) && rcMap.get(row).containsKey(column))
			return rcMap.get(row).get(column);
		else return Matrix.ZERO;
	}
	
	@Override	
	protected void setNonZeroValue(int row, int column, double value){
		if(!rcMap.containsKey(row))rcMap.put(row, new HashMap<Integer, Double>());
		rcMap.get(row).put(column, value);//set nonzero value
	}
	
	@Override
	protected void setZeroValue(int row, int column, double value){
		if(rcMap.containsKey(row)){
			rcMap.get(row).remove(column);//set zero value
			if(rcMap.get(row).size() == 0)rcMap.remove(row);
		}
	}

}
