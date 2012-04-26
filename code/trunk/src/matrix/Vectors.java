package matrix;

import util.RecSysLibException;

/**
 * This class provides some useful methods for vector operation.   
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Matrix
 * @see Vector
 */
public final class Vectors {
	private static enum  OperationType{
		ADD,
		SUB,
		DOT_MULT,
		SCALAR_MULT
	}
	
	private static void check(OperationType opType, Vector... v){
		switch(opType){
		case ADD:
		case SUB:
		case DOT_MULT:
			if(v[0].getLength() != v[1].getLength())
				throw new RecSysLibException("Two vectors must have same length. ");
			break;
		default:			
		}
	}
	
	/**
	 * Converts the vector v to a matrix. 
	 * If seeing this vector as a row vector, the matrix will be one-row matrix,
	 * else one-column matrix. 
	 */
	public static Matrix toMatrix(Vector v, Vector.Type t){
		return v.toMatrix(t);
	}
	
	/**
	 * Return the dot product of these two vectors 
	 */
	public static double dotMult(Vector v1, Vector v2){
		check(OperationType.DOT_MULT, v1, v2);
		int length = v1.getLength();
		double result = 0;
		for(int i = 0;i<length;i++){
			result += v1.getValue(i) * v2.getValue(i);
		}
		return result;
	}
	
	/**
	 * Return the scalar product of the vector v and the real number d
	 */
	public static Vector scalarMult(double d, Vector v){
		check(OperationType.SCALAR_MULT, v);
		int length = v.getLength();
		Vector result = new SparseVector(length);
		for(int i = 0;i<length;i++){
			double value = d * v.getValue(i);
			result.setValue(i, value);
		}
		return result;
	}
	
	/**
	 * vector addition
	 */
	public static Vector add(Vector v1, Vector v2){
		check(OperationType.ADD, v1, v2);
		int length = v1.getLength();
		Vector result = new SparseVector(length);
		for(int i = 0;i<length;i++){
			double value = v1.getValue(i) + v2.getValue(i);
			result.setValue(i, value);
		}
		return result;
	}
	
	/**
	 * vector subtraction
	 */
	public static Vector sub(Vector v1, Vector v2){
		check(OperationType.SUB, v1, v2);
		int length = v1.getLength();
		Vector result = new SparseVector(length);
		for(int i = 0;i<length;i++){
			double value = v1.getValue(i) - v2.getValue(i);
			result.setValue(i, value);
		}
		return result;
	}
}
