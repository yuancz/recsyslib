package matrix;

import util.RecSysLibException;

/**
 * This class provides a skeletal implementation of the <tt>Vector</tt> interface 
 * to minimize the effort required to implement this interface.
 * To implement a vector, the programmer needs only to extend this class and 
 * provide implementations for the createInnerVector, setNonZeroValue, setZeroValue and getValue methods. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Matrix
 * @see Vector
 */
public abstract class AbstractVector implements Vector {

	protected int length;
	
	protected int nonZeroCount;

	/**
	 * Constructs a vector with the specified length. 
	 * @throws RecSysLibException if <code>length <= 0</code> 
	 */
	public AbstractVector(int length) {
		if(length <= 0)
			throw new RecSysLibException("The length must be positive integer. ");
		this.length = length;
		this.nonZeroCount = 0;
		createInnerVector(length);
	}
	
	/**
	 * Constructs a vector from the specified vector. 
	 */
	public AbstractVector(Vector v) {
		this(v.getLength());
		for(int i = 0;i<length;i++)setValue(i, v.getValue(i));
	}
	
	/**
	 * Constructs a vector from the specified array. 
	 */
	public AbstractVector(double[] arr) {
		this(arr.length);
		for(int i = 0;i<length;i++)setValue(i, arr[i]);
	}
	
	/**
	 * Creates a baking data structure which actually saves the values of this vector
	 */
	protected abstract void createInnerVector(int length);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLength() {
		return this.length;
	}

	/**
	 * Makes sure the index value is legal. 
	 */
	protected void check(int index) {
		if(index<0 || index>=length)
			throw new RecSysLibException("The index value must be in [0, "+length+"). ");
	}
	
	/**
	 * Defines how to save non-zero value in baking data structure. 
	 */
	protected abstract void setNonZeroValue(int index, double value);
	
	/**
	 * Defines how to save zero value in baking data structure. 
	 */
	protected abstract void setZeroValue(int index, double value);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double setValue(int index, double value) {
		check(index);
		if(value == Double.NaN)
			throw new RecSysLibException("Illegal vector value: Double.NaN");
		double oldValue = getValue(index);
		if(value != oldValue){
			if(value != Vector.ZERO){
				nonZeroCount++;
				setNonZeroValue(index, value);
			}
			else {
				nonZeroCount--;
				setZeroValue(index, value);
			}
		}
		return oldValue;
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
	public Vector subVector(int startIndex, int subLength){
		check(startIndex);
		check(startIndex+subLength);
		Vector sub = new SparseVector(subLength);
		for(int i = 0;i<subLength;i++){
			sub.setValue(i, getValue(startIndex+i));
		}
		return sub;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix toMatrix(Type t) {
		Matrix m;
		switch(t){
		case RowVector:
			m = new SparseMatrix(1, getLength());
			for(int i = 0;i<getLength();i++)m.setValue(0, i, getValue(i));
			return m;
		case ColumnVector:
			m = new SparseMatrix(getLength(), 1);
			for(int i = 0;i<getLength();i++)m.setValue(i, 0, getValue(i));
			return m;
		default:
			return null;
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	public double[] toArray(){
		double[] arr = new double[length];
		for(int i = 0;i<length;i++)arr[i] = getValue(i);
		return arr;
	}

}