package matrix;

public interface Vector {
	public abstract int getLength();
	public abstract int size();
	public abstract double getValue(int index);
	public abstract void setValue(int index, double value);
	public abstract double removeValue(int index);
}
