package matrix;

public interface Vector {
	public static enum Type{
		RowVector,
		ColumnVector
	}
	public static final double ZERO = Matrix.ZERO;
	public abstract int getLength();
	public abstract int nonZeroCount();
	public abstract double getValue(int index);
	public abstract double setValue(int index, double value);
	public abstract Matrix toMatrix(Type t);
	public abstract Vector subVector(int startIndex, int subLength);
	public abstract double[] toArray();
}
