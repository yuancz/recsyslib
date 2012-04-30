package io;

/**
 * Read a CSV file. This class uses a cursor iterating each element in the CSV file. 
 * It assumes each element is a string and separated by a comma. 
 * It is backed by a Inputer object. 
 * @version 1.0 2012-4-29
 * @author Tan Chang
 * @since JDK 1.7
 * @see Inputer
 */
public final class CSVReader {
	
	public static final char COMMA = CSVWriter.COMMA;
	
	private Inputer in;
	
	private int row;
	
	private int column;
	
	private String[] curLine;
	
	private String nextLine;
	
	private boolean isNewLine;
	
	/**
	 * Constructs a CSVReader to read a CSV file with the specified file path. 
	 */
	public CSVReader(String filePath){
		in = new Inputer(filePath);
		row = -1;
		column = -1;
		curLine = new String[0];
		nextLine = in.readLine();
		isNewLine = false;
	}
	
	/**
	 * Returns true if the cursor is at the beginning of one line. 
	 */
	public boolean isNewLine(){
		return isNewLine;
	}
	
	/**
	 * Returns true if existing next element, or false if reaching the file end. 
	 */
	public boolean hasNext(){
		if(column < curLine.length || nextLine != null){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the next element in this CSV file. 
	 */
	public String next(){
		if(column < curLine.length){
			column++;		
			isNewLine = false;
		} else{
			curLine = nextLine.split(String.valueOf(COMMA));
			nextLine = in.readLine();
			row++;
			column = 0;		
			isNewLine = true;
		}
		return curLine[column];
	}
	
	/**
	 * Closes this reader and releases any system resources associated with it.
	 * @see {@link Inputer#close()}
	 */
	public void close(){
		in.close();
	}

	/**
	 * Returns the row number of the cursor located currently, starting from zero. 
	 */			
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column number of the cursor located currently, starting from zero. 
	 */		
	public int getColumn() {
		return column;
	}
	
}
