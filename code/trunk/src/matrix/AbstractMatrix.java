package matrix;

import util.RecSysLibException;

public abstract class AbstractMatrix implements Matrix {

	protected int rowNum;
	protected int columnNum;
	protected int nonZeroCount;

	public AbstractMatrix(int rowNum, int columnNum) {
		if(rowNum <= 0 || columnNum <= 0)
			throw new RecSysLibException("The number of row or column must be positive integer. ");
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.nonZeroCount = 0;
		createInnerMatrix(rowNum, columnNum);
	}
	
	public AbstractMatrix(Matrix m){
		this(m.getRowNum(), m.getColumnNum());
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<columnNum;j++){
				setValue(i,j,m.getValue(i, j));
			}
		}
	}
	
	public AbstractMatrix(double[][] arr) {
		this(arr.length, arr[0].length);
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<columnNum;j++){
				setValue(i,j,arr[i][j]);
			}
		}
	}
	
	protected abstract void createInnerMatrix(int rowNum, int columnNum);

	@Override
	public int getRowNum() {
		return this.rowNum;
	}

	@Override
	public int getColumnNum() {
		return this.columnNum;
	}

	protected void check(int row, int column) {
		if(row<0 || row>=rowNum)
			throw new RecSysLibException("The row value must be in [0, " + rowNum + "). ");
		if(column<0 || column>=columnNum)
			throw new RecSysLibException("The column value must be in [0, " + columnNum + "). ");
	}
	
	protected abstract void setNonZeroValue(int row, int column, double value);
	
	protected abstract void setZeroValue(int row, int column, double value);
	
	public double setValue(int row, int column, double value) {
		check(row, column);
		if(value == Double.NaN)
			throw new RecSysLibException("Illegal matrix value: Double.NaN");	
		//check zero and set value
		double oldValue = getValue(row, column);		
		if(oldValue != value){
			if(oldValue == Matrix.ZERO){
				nonZeroCount++;
				setNonZeroValue(row, column, value);
			}
			if(value == Matrix.ZERO){				
				setZeroValue(row, column, value);
				nonZeroCount--;
			}
		}
		return oldValue;
	}

	@Override
	public Vector getRowVector(int row) {
		Vector vec = new SparseVector(columnNum);
		for(int i = 0;i<columnNum;i++)vec.setValue(i, getValue(row, i));
		return vec;
	}

	@Override
	public Vector getColumnVector(int column) {
		Vector vec = new SparseVector(rowNum);
		for(int i = 0;i<rowNum;i++)vec.setValue(i, getValue(i, column));
		return vec;
	}

	@Override
	public int nonZeroCount() {
		return nonZeroCount;
	}
	
	@Override
	public Matrix getSubMatrix(int startRow, int startColumn, int rowNum, int columnNum) {
		check(startRow, startColumn);
		check(startRow+rowNum, startColumn+columnNum);
		Matrix sub = new SparseMatrix(rowNum, columnNum);
		for(int i = 0;i<rowNum;i++){
			for(int j = 0;j<columnNum;j++){
				sub.setValue(i, j, getValue(startRow+i, startColumn+j));
			}
		}
		return sub;
	}
	
	@Override
	public double[][] toArray(){
		double[][] arr = new double[rowNum][columnNum];
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<columnNum;j++){
				arr[i][j] = getValue(i,j);
			}
		}
		return arr;
	}
}