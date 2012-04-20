package matrix;

import java.util.HashMap;

public class SparseMatrix implements Matrix {
	
	private int rowNum;
	private int columnNum;
	
	private HashMap<Integer, HashMap<Integer, Double>> rcMap;
	
	public SparseMatrix(int rowNum, int columnNum){
		if(rowNum <= 0 || columnNum <= 0)
			throw new IllegalArgumentException("The number of row or column must be positive integer. ");
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		rcMap = new HashMap<>();
	}

	@Override
	public int getRowNum() {
		return this.rowNum;
	}

	@Override
	public int getColumnNum() {
		return this.columnNum;
	}
	
	private void check(int row, int column){
		if(row<0 || row>=rowNum)
			throw new IllegalArgumentException("The row value must be in [0, " + rowNum + "). ");
		if(column<0 || column>=columnNum)
			throw new IllegalArgumentException("The column value must be in [0, " + columnNum + "). ");
	}

	@Override
	public double getValue(int row, int column) {
		check(row, column);
		if(rcMap.containsKey(row) && rcMap.get(row).containsKey(column))
			return rcMap.get(row).get(column);
		else return Matrix.EMPTY_VALUE;
	}
	
	@Override
	public double removeValue(int row, int column) {
		check(row, column);
		if(rcMap.containsKey(row) && rcMap.get(row).containsKey(column)){
			double value = rcMap.get(row).remove(column);
			if(rcMap.get(row).size() == 0)rcMap.remove(row);
			return value;
		}
		else return Matrix.EMPTY_VALUE;
	}
	
	@Override
	public void setValue(int row, int column, double value) {
		check(row, column);
		if(value == Double.NaN)
			throw new IllegalArgumentException("Illegal argument: Double.NaN");
		if(!rcMap.containsKey(row))rcMap.put(row, new HashMap<Integer, Double>());
		rcMap.get(row).put(column, value);
	}

	@Override
	public Vector getRowVector(int row) {
		if(!rcMap.containsKey(row))return null;
		Vector vec = new SparseVector(columnNum);
		for(int column : rcMap.get(row).keySet()){
			vec.setValue(column, rcMap.get(row).get(column));
		}
		return vec;
	}

	@Override
	public Vector getColumnVector(int column) {
		Vector vec = new SparseVector(rowNum);
		for(int row : rcMap.keySet()){
			if(rcMap.get(row).containsKey(column))
				vec.setValue(row, rcMap.get(row).get(column));
		}
		if(vec.size() == 0)return null;
		return vec;
	}

}
