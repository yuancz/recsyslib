package matrix;

/**
 * The interface <tt>Vector</tt> presents a matrix mathematically, 
 * and accepts double values as vector elements. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public interface Vector {
	
	public static enum Type{
		RowVector,
		ColumnVector
	}
	
	public static final double ZERO = Matrix.ZERO;
	
	/**
	 * Returns the length of this vector
	 */
	public abstract int getLength();
	
	/**
	 * Returns the number of the non-zero elements in this vector
	 */
	public abstract int nonZeroCount();
	
	/**
	 * Returns the value of the element at the specified index of this vector
	 */
	public abstract double getValue(int index);
	
	/**
	 * Sets the value of the element at the specified index of this vector,
	 * returns the old value. The change of the number of non-zero elements should be considered. 
	 */
	public abstract double setValue(int index, double value);
	
	/**
	 * Returns the sub vector of this vector with the specified start index and length. 
	 */
	public abstract Vector subVector(int startIndex, int subLength);
	
	/**
	 * Converts this vector to a matrix. 
	 * If seeing this vector as a row vector, the matrix will be one-row matrix,
	 * else one-column matrix. 
	 */
	public abstract Matrix toMatrix(Type t);
	
	/**
	 * Converts this vector to an array
	 */
	public abstract double[] toArray();
}
