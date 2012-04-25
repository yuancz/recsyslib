package matrix;

public interface Matrix {
	public static final double ZERO = 0;
	public abstract int getRowNum();
	public abstract int getColumnNum();
	public abstract int nonZeroCount();
	public abstract double getValue(int row, int column);
	public abstract double setValue(int row, int column, double value);
	public abstract Vector getRowVector(int row);
	public abstract Vector getColumnVector(int column);
}
