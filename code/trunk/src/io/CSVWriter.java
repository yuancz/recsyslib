package io;

/**
 * Writes a CSV file.
 * It assumes each element is a string and separated by a comma. 
 * It is backed by a Outputer object. 
 * @version 1.0 2012-4-29
 * @author Tan Chang
 * @since JDK 1.7
 * @see Outputer
 */
public final class CSVWriter {
	
	public static final char COMMA = ',';
	
	private Outputer out;
	
    /**
     * Solo constructor. Creates a new CSVWriter, given the name of the file to write to.
     * Noting that this operation will overwrite the older file of the same name. 
     */
	public CSVWriter(String filePath){
		out = new Outputer(filePath);
	}
	
    /**
     * Writes an element.
     */
	public void writeElement(String element){
		out.write(element+COMMA);
	}
	
    /**
     * Starts a new line. 
     * @see {@link Outputer#newLine()}
     */
	public void newLine(){
		out.newLine();
	}
	
    /**
     * Flushes this writer.
     * @see {@link Outputer#flush()}
     */
	public void flush(){
		out.flush();
	}
	
    /**
     * Closes this writer, flushing it first.
     * @see {@link Outputer#close()}
     */
	public void close(){
		out.close();
	}
}
