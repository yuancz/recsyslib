package matrix;

import java.util.HashMap;

import util.RecSysLibException;

public class SparseMatrix implements Matrix {
	
	protected int rowNum;
	protected int columnNum;
	protected int nonZeroCount;
	
	protected HashMap<Integer, HashMap<Integer, Double>> rcMap;
	
	public SparseMatrix(int rowNum, int columnNum){
		if(rowNum <= 0 || columnNum <= 0)
			throw new RecSysLibException("The number of row or column must be positive integer. ");
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.nonZeroCount = 0;
		rcMap = new HashMap<>();
	}
	
	public SparseMatrix(Matrix m){
		this(m.getRowNum(), m.getColumnNum());
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<columnNum;j++){
				setValue(i,j,m.getValue(i, j));
			}
		}
	}

	@Override
	public int getRowNum() {
		return this.rowNum;
	}

	@Override
	public int getColumnNum() {
		return this.columnNum;
	}
	
	protected void check(int row, int column){
		if(row<0 || row>=rowNum)
			throw new RecSysLibException("The row value must be in [0, " + rowNum + "). ");
		if(column<0 || column>=columnNum)
			throw new RecSysLibException("The column value must be in [0, " + columnNum + "). ");
	}

	@Override
	public double getValue(int row, int column) {
		check(row, column);
		if(rcMap.containsKey(row) && rcMap.get(row).containsKey(column))
			return rcMap.get(row).get(column);
		else return Matrix.ZERO;
	}
	
	@Override
	public double setValue(int row, int column, double value) {
		check(row, column);
		if(value == Double.NaN)
			throw new RecSysLibException("Illegal matrix value: Double.NaN");		
		double oldValue = getValue(row, column);		
		if(oldValue != value){
			if(oldValue == SparseMatrix.ZERO){
				if(!rcMap.containsKey(row))rcMap.put(row, new HashMap<Integer, Double>());
				rcMap.get(row).put(column, value);//set nonzero value
				nonZeroCount++;
			}
			if(value == Matrix.ZERO){				
				if(rcMap.containsKey(row)){
					rcMap.get(row).remove(column);//set zero value
					if(rcMap.get(row).size() == 0)rcMap.remove(row);
				}
				nonZeroCount--;
			}
		}
		return oldValue;
	}

	@Override
	public Vector getRowVector(int row) {
		Vector vec = new SparseVector(columnNum);
		if(rcMap.containsKey(row)){
			for(int column : rcMap.get(row).keySet()){
				vec.setValue(column, rcMap.get(row).get(column));
			}
		}
		return vec;
	}

	@Override
	public Vector getColumnVector(int column) {
		Vector vec = new SparseVector(rowNum);
		for(int row : rcMap.keySet()){
			vec.setValue(row, rcMap.get(row).get(column));
		}
		return vec;
	}

	@Override
	public int nonZeroCount() {
		return nonZeroCount;
	}

}
