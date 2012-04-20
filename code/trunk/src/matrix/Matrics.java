package matrix;

public class Matrics {
	
	private static void check(Matrix m1, Matrix m2){
		if(m1.getRowNum()!=m2.getRowNum() || m1.getColumnNum()!=m2.getColumnNum())
			throw new ArithmeticException("Matrix addition or subtraction requests two matrics with same number of rows and columns. ");
	}
	
	public static Matrix addition(Matrix m1, Matrix m2){
		check(m1,m2);
		int row = m1.getRowNum();
		int column = m1.getColumnNum();
		Matrix m = new SparseMatrix(row, column);
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				double v1 = m1.getValue(i, j);
				double v2 = m2.getValue(i, j);
				if(v1 == Matrix.EMPTY_VALUE)v1 = 0;
				if(v2 == Matrix.EMPTY_VALUE)v2 = 0;
				double v = v1 + v2;
				if(v != 0)m.setValue(i, j, v);
			}
		}
		return m;
	}
	
	
	
	public static Matrix subtraction(Matrix m1, Matrix m2){
		check(m1,m2);
		int row = m1.getRowNum();
		int column = m1.getColumnNum();
		Matrix m = new SparseMatrix(row, column);
		for(int i = 0;i<row;i++){
			for(int j = 0;j<column;j++){
				double v1 = m1.getValue(i, j);
				double v2 = m2.getValue(i, j);
				if(v1 == Matrix.EMPTY_VALUE)v1 = 0;
				if(v2 == Matrix.EMPTY_VALUE)v2 = 0;
				double v = v1 - v2;
				if(v != 0)m.setValue(i, j, v);
			}
		}
		return m;
	}
}
