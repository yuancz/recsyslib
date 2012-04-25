package matrix;

import java.util.HashMap;

import util.RecSysLibException;

public class SparseVector implements Vector {
	
	private int length;
	private HashMap<Integer, Double> map;

	public SparseVector(int length){
		this.length = length;
		map = new HashMap<>();
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	private void check(int index){
		if(index<0 || index>=length)
			throw new RecSysLibException("The row value must be in [0, "+length+"). ");
	}

	@Override
	public double getValue(int index) {
		check(index);
		if(map.containsKey(index))return map.get(index);
		return Matrix.ZERO;
	}

	@Override
	public double setValue(int index, double value) {
		check(index);
		if(value == Double.NaN)
			throw new RecSysLibException("Illegal vector value: Double.NaN");
		double oldValue = getValue(index);
		if(value != oldValue){
			if(value != Vector.ZERO)map.put(index, value);
			else map.remove(index);
		}
		return oldValue;
	}

	@Override
	public int nonZeroCount() {
		return map.size();
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

}
