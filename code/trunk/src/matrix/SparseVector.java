package matrix;

import java.util.HashMap;

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
			throw new IllegalArgumentException("The row value must be in [0, "+length+"). ");
	}

	@Override
	public double getValue(int index) {
		check(index);
		if(map.containsKey(index))return map.get(index);
		return Matrix.EMPTY_VALUE;
	}

	@Override
	public void setValue(int index, double value) {
		check(index);
		if(value == Double.NaN)
			throw new IllegalArgumentException("Illegal argument: Double.NaN");
		map.put(index, value);
	}

	@Override
	public double removeValue(int index) {
		check(index);
		return map.get(index);
	}

	@Override
	public int size() {
		return map.size();
	}

}
