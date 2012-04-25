package matrix;

import java.util.HashMap;

public class SparseVector extends AbstractVector implements Vector {
	
	private HashMap<Integer, Double> map;

	public SparseVector(int length){
		super(length);		
	}	

	public SparseVector(double[] arr) {
		super(arr);
	}

	public SparseVector(Vector v) {
		super(v);
	}

	@Override
	protected void createInnerVector(int length) {
		map = new HashMap<>();		
	}
	
	@Override
	public double getValue(int index) {
		check(index);
		if(map.containsKey(index))return map.get(index);
		return Vector.ZERO;
	}
	
	@Override
	protected void setNonZeroValue(int index, double value) {
		map.put(index, value);		
	}

	@Override
	protected void setZeroValue(int index, double value) {
		map.remove(index);		
	}

}
