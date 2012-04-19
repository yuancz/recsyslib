package matrix;

import java.util.HashMap;

public class SparseMatrix implements Matrix {
	
	public class Triple {
	    int row;
	    int column;
	    double value;	
	    Triple(int row, int column, double value){
	    	this.row = row;
	    	this.column = column;
	    	this.value = value;
	    }
	}
	
	private int rowNum;
	private int columnNum;
	
	private HashMap<Integer, HashMap<Integer, Triple>> rcMap;
//	private HashMap<Integer, HashMap<Integer, Triple>> crMap;
	
	public SparseMatrix(int rowNum, int columnNum){
		if(rowNum <= 0 || columnNum <= 0)
			throw new IllegalArgumentException("The number of row or column must be positive integer. ");
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		rcMap = new HashMap<>();
//		crMap = new HashMap<>();
	}

	@Override
	public int getRowNum() {
		return this.rowNum;
	}

	@Override
	public int getColumnNum() {
		return this.columnNum;
	}

	@Override
	public double getValue(int row, int column) {
		if(rcMap.containsKey(row) && rcMap.get(row).containsKey(column))
			return rcMap.get(row).get(column).value;
		else return Double.NaN;
	}
	
	@Override
	public double remove(int row, int column) {
		if(row<0 || row>=rowNum)
			throw new IllegalArgumentException("The row value must be in [0, rowNum). ");
		if(column<0 || column>=columnNum)
			throw new IllegalArgumentException("The column value must be in [0, columnNum). ");
		if(rcMap.containsKey(row) && rcMap.get(row).containsKey(column)){
			double value = rcMap.get(row).remove(column).value;
			if(rcMap.get(row).size() == 0)rcMap.remove(row);
			return value;
		}
		else return Double.NaN;
	}
	
	@Override
	public void setValue(int row, int column, double value) {
		if(row<0 || row>=rowNum)
			throw new IllegalArgumentException("The row value must be in [0, rowNum). ");
		if(column<0 || column>=columnNum)
			throw new IllegalArgumentException("The column value must be in [0, columnNum). ");
		if(value == Double.NaN)
			throw new IllegalArgumentException("Double.NaN is not an illegal value. ");
		if(!rcMap.containsKey(row))rcMap.put(row, new HashMap<Integer, Triple>());
		rcMap.get(row).put(column, new Triple(row, column, value));
	}

}
