package sample;

import matrix.*;

public class MatrixSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[][] arr = {{1,2},{3,4}};
		Matrix m = new ArrayMatrix(arr);
		System.out.println(m.getColumnVector(1).getValue(1));

	}

}
