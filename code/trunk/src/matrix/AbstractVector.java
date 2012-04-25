package matrix;

import util.RecSysLibException;

public abstract class AbstractVector implements Vector {

	protected int length;
	
	protected int nonZeroCount;

	public AbstractVector(int length) {
		this.length = length;
		this.nonZeroCount = 0;
		createInnerVector(length);
	}
	
	public AbstractVector(Vector v) {
		this(v.getLength());
		for(int i = 0;i<length;i++)setValue(i, v.getValue(i));
	}
	
	public AbstractVector(double[] arr) {
		this(arr.length);
		for(int i = 0;i<length;i++)setValue(i, arr[i]);
	}
	
	protected abstract void createInnerVector(int length);

	@Override
	public int getLength() {
		return this.length;
	}

	protected void check(int index) {
		if(index<0 || index>=length)
			throw new RecSysLibException("The index value must be in [0, "+length+"). ");
	}
	
	protected abstract void setNonZeroValue(int index, double value);
	
	protected abstract void setZeroValue(int index, double value);
	
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
	
	@Override
	public int nonZeroCount() {
		return nonZeroCount;
	}

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
	
	public double[] toArray(){
		double[] arr = new double[length];
		for(int i = 0;i<length;i++)arr[i] = getValue(i);
		return arr;
	}

}