package matrix;

/**
 * This class implements the <tt>Vector</tt> interface, backed by a <tt>double[]</tt> instance. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public class ArrayVector extends AbstractVector implements Vector {
	
	protected double[] arr;

	public ArrayVector(double[] arr) {
		super(arr);
	}

	public ArrayVector(int length) {
		super(length);
	}

	public ArrayVector(Vector v) {
		super(v);
	}
	
	@Override
	protected void createInnerVector(int length) {
		arr = new double[length];
	}

	@Override
	public double getValue(int index) {
		check(index);
		return arr[index];
	}

	@Override
	protected void setNonZeroValue(int index, double value) {
		arr[index] = value;
	}

	@Override
	protected void setZeroValue(int index, double value) {
		arr[index] = value;
	}

}
