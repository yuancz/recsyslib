package matrix;

/**
 * The interface <tt>Matrix</tt> presents a matrix mathematically, 
 * and accepts double values as matrix elements. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Matrix {
	
	public static final double ZERO = 0;
	
	/**
	 * Returns the row number of this matrix
	 */
	public abstract int getRowNum();
	
	/**
	 * Returns the column number of this matrix
	 */
	public abstract int getColumnNum();
	
	/**
	 * Returns the number of the non-zero elements in this matrix
	 */
	public abstract int nonZeroCount();
	
	/**
	 * Returns the value of the element at the specified location of this matrix
	 */
	public abstract double getValue(int row, int column);
	
	/**
	 * Sets the value of the element at the specified location of this matrix,
	 * returns the old value. The change of the number of non-zero elements should be considered. 
	 */
	public abstract double setValue(int row, int column, double value);
	
	/**
	 * Returns the specified row vector of this matrix
	 */
	public abstract Vector getRowVector(int row);
	
	/**
	 * Returns the specified column vector of this matrix
	 */
	public abstract Vector getColumnVector(int column);
	
	/**
	 * Returns the sub matrix of this matrix with the specified start location and dimension. 
	 */
	public abstract Matrix getSubMatrix(int startRow, int startColumn, int rowNum, int columnNum);
	
	/**
	 * Converts this matrix to an array
	 */
	public abstract double[][] toArray();
}
