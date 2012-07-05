package matrix;

import core.RecSysLibException;

/**
 * This class provides some useful methods for vector operation.   
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Matrix
 * @see Vector
 */
public final class Matrics {
	
	private static enum  OperationType{
		ADD,
		SUB,
		MULT,
		TRANS,
		SCALAR_MULT,
		TRACE
	}
	
	private static void check(OperationType opType, Matrix... m){
		switch(opType){
		case ADD:
		case SUB:
			if(m[0].getRowNum()!=m[1].getRowNum() || m[0].getColumnNum()!=m[1].getColumnNum())
				throw new RecSysLibException("Matrix addition or subtraction requests " +
						"two matrics with same number of rows and columns. ");
			break;
		case MULT:
			if(m[0].getColumnNum() != m[1].getRowNum())
				throw new RecSysLibException("Matrix multiplication requests " +
						"the column number of first matrix equals the row number of second one. ");
			break;
		case TRACE:
			if(!isSquareMatrix(m[0]))
				throw new RecSysLibException("Can not compute trace for a non-square matrix. ");
			break;
		default:			
		}
	}
	
	/**
	 * Checks whether or not a matrix is a square matrix
	 */
	public static boolean isSquareMatrix(Matrix m){
		return m.getRowNum() == m.getColumnNum();
	}
	
	/**
	 * matrix trace
	 */
	public static double trace(Matrix m){
		check(OperationType.TRACE, m);
		int row = m.getRowNum();
		double trace = 0;
		for(int i = 0;i<row;i++){
			trace += m.getValue(i, i);
		}
		return trace;
	}
	
	/**
	 * Return the scalar product of the matrix m and the real number d
	 */
	public static Matrix scalarMult(double d, Matrix m){
		check(OperationType.SCALAR_MULT, m);
		int row = m.getRowNum();
		int column = m.getColumnNum();
		Matrix result = new SparseMatrix(row, column);
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				double value = d*m.getValue(row, column);
				result.setValue(row, column, value);
			}
		}
		return result;
	}
	
	/**
	 * matrix transposition
	 */
	public static Matrix trans(Matrix m){
		check(OperationType.TRANS, m);
		int row = m.getColumnNum();
		int column = m.getRowNum();
		Matrix result = new SparseMatrix(row, column);
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				double value = m.getValue(column, row);
				result.setValue(row, column, value);
			}
		}
		return result;
	}
	
	/**
	 * matrix multiplication
	 */
	public static Matrix mult(Matrix m1, Matrix m2){
		check(OperationType.MULT, m1, m2);
		int row = m1.getRowNum();
		int column = m2.getColumnNum();
		Matrix result = new SparseMatrix(row, column);
		int n = m1.getColumnNum();
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				Vector rv = m1.getRowVector(i);
				Vector cv = m2.getColumnVector(j);
				double value = 0;
				for(int k = 0;k<n;k++){
					value += rv.getValue(k)*cv.getValue(k);
				}
				result.setValue(i, j, value);
			}
		}
		return result;
	}
	
	/**
	 * matrix addition
	 */
	public static Matrix add(Matrix m1, Matrix m2){
		check(OperationType.ADD, m1, m2);
		int row = m1.getRowNum();
		int column = m1.getColumnNum();
		Matrix result = new SparseMatrix(row, column);
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				double v1 = m1.getValue(i, j);
				double v2 = m2.getValue(i, j);
				double v = v1 + v2;
				result.setValue(i, j, v);
			}
		}
		return result;
	}		
	
	/**
	 * matrix subtraction
	 */
	public static Matrix sub(Matrix m1, Matrix m2){
		check(OperationType.SUB, m1, m2);
		int row = m1.getRowNum();
		int column = m1.getColumnNum();
		Matrix result = new SparseMatrix(row, column);
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				double v1 = m1.getValue(i, j);
				double v2 = m2.getValue(i, j);
				double v = v1 - v2;
				result.setValue(i, j, v);
			}
		}
		return result;
	}
}
