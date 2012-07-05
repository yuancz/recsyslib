package matrix;

import core.RecSysLibException;

/**
 * This class provides a skeletal implementation of the <tt>Matrix</tt> interface 
 * to minimize the effort required to implement this interface.
 * To implement a matrix, the programmer needs only to extend this class and 
 * provide implementations for the createInnerMatrix, setNonZeroValue, setZeroValue and getValue methods. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Matrix
 * @see Vector
 */
public abstract class AbstractMatrix implements Matrix {

	protected int rowNum;
	
	protected int columnNum;
	
	protected int nonZeroCount;

	/**
	 * Constructs a matrix with the specified row and column number. 
	 * @throws RecSysLibException if <code>rowNum <= 0 || columnNum <= 0</code> 
	 */
	public AbstractMatrix(int rowNum, int columnNum) {
		if(rowNum <= 0 || columnNum <= 0)
			throw new RecSysLibException("The number of row or column must be positive integer. ");
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.nonZeroCount = 0;
		createInnerMatrix(rowNum, columnNum);
	}
	
	/**
	 * Constructs a matrix from the specified matrix. 
	 */
	public AbstractMatrix(Matrix m){
		this(m.getRowNum(), m.getColumnNum());
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<columnNum;j++){
				setValue(i,j,m.getValue(i, j));
			}
		}
	}
	
	/**
	 * Constructs a matrix from the specified array. 
	 */
	public AbstractMatrix(double[][] arr) {
		this(arr.length, getMaxLengthOfSubArrays(arr));
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<arr[i].length;j++){
				setValue(i,j,arr[i][j]);
			}//if(arr[i].length < colunmnNum), seeing the rest elements as zero
		}
	}
	
	protected static int getMaxLengthOfSubArrays(double[][] arr) {
		int max = 0;
		for(double[] sub : arr){
			if(sub.length>max)max=sub.length;
		}
		return max;
	}
	
	/**
	 * Creates a baking data structure which actually saves the values of this matrix
	 */
	protected abstract void createInnerMatrix(int rowNum, int columnNum);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowNum() {
		return this.rowNum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnNum() {
		return this.columnNum;
	}

	/**
	 * Makes sure the values of row and column are legal. 
	 */
	protected void check(int row, int column) {
		if(row<0 || row>=rowNum)
			throw new RecSysLibException("The row value must be in [0, " + rowNum + "). ");
		if(column<0 || column>=columnNum)
			throw new RecSysLibException("The column value must be in [0, " + columnNum + "). ");
	}
	
	/**
	 * Defines how to save non-zero value in baking data structure. 
	 */
	protected abstract void setNonZeroValue(int row, int column, double value);
	
	/**
	 * Defines how to save zero value in baking data structure. 
	 */
	protected abstract void setZeroValue(int row, int column, double value);
	
	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector getRowVector(int row) {
		Vector vec = new SparseVector(columnNum);
		for(int i = 0;i<columnNum;i++)vec.setValue(i, getValue(row, i));
		return vec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector getColumnVector(int column) {
		Vector vec = new SparseVector(rowNum);
		for(int i = 0;i<rowNum;i++)vec.setValue(i, getValue(i, column));
		return vec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int nonZeroCount() {
		return nonZeroCount;
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
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