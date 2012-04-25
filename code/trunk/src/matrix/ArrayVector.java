package matrix;

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
