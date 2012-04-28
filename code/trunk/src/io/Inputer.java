package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads text from a text file. 
 * This class warps a BufferedReader(FileReader(file) object for easy to use. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see BufferedReader
 * @see FileReader
 */
public class Inputer {
    
    private BufferedReader br;
    
    /**
     * Solo constructor. Creates a new Inputer, given the name of the file to read from.
     */
    public Inputer(String file){
    	try {
    		br = new BufferedReader(new FileReader(file));
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Reads a line of text.
     * @see {@link BufferedReader#readLine()}
     */
    public String readLine(){
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
        	e.printStackTrace();
        } 
        return line;
    }
    
    /**
     * Closes the stream and releases any system resources associated with it.
     * @see {@link BufferedReader#close()}
     */
    public void close(){
        try {
            br.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
}
