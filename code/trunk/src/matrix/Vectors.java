package matrix;

import util.RecSysLibException;

public class Vectors {
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
	
	public static Matrix toMatrix(Vector v, Vector.Type t){
		return v.toMatrix(t);
	}
	
	public static double dotMult(Vector v1, Vector v2){
		check(OperationType.DOT_MULT, v1, v2);
		int length = v1.getLength();
		double result = 0;
		for(int i = 0;i<length;i++){
			result += v1.getValue(i) * v2.getValue(i);
		}
		return result;
	}
	
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
