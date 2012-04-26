package matrix;

/**
 * This class implements the <tt>Matrix</tt> interface, backed by a <tt>double[][]</tt> instance. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public class ArrayMatrix extends AbstractMatrix implements Matrix {
	
	protected double[][] arr; 

	public ArrayMatrix(int rowNum, int columnNum) {
		super(rowNum, columnNum);		
	}

	public ArrayMatrix(Matrix m) {
		super(m);
	}

	public ArrayMatrix(double[][] arr) {
		super(arr);
	}	

	@Override
	protected void createInnerMatrix(int rowNum, int columnNum) {
		arr = new double[rowNum][columnNum];		
	}

	@Override
	public double getValue(int row, int column) {
		check(row, column);
		return arr[row][column];
	}

	@Override
	protected void setNonZeroValue(int row, int column, double value) {
		arr[row][column] = value;		
	}

	@Override
	protected void setZeroValue(int row, int column, double value) {
		arr[row][column] = value;			
	}

}
