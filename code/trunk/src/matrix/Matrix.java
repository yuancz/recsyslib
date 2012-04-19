package matrix;

public interface Matrix {
	int getRowNum();
	int getColumnNum();
	double getValue(int row, int column);
	double remove(int row, int column);
	void setValue(int row, int column, double value);
}
