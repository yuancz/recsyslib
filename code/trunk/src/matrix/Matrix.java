package matrix;

public interface Matrix {
	public static final double EMPTY_VALUE = Double.NaN;
	int getRowNum();
	int getColumnNum();
	double getValue(int row, int column);
	double removeValue(int row, int column);
	void setValue(int row, int column, double value);
	Vector getRowVector(int row);
	Vector getColumnVector(int column);
}
