package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes text to a text file. 
 * This class warps a BufferedWriter(FileWriter(file) object for easy to use. 
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see BufferedWriter
 * @see FileWriter
 */
public class Outputer {
    
    private BufferedWriter bw;
    
    /**
     * Solo constructor. Creates a new Outputer, given the name of the file to write to.
     * Noting that this operation will overwrite the older file of the same name. 
     */
    public Outputer(String file){
        try {
            bw = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Writes a String.
     */
    public void write(String string){
        try {
            bw.write(string);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Writes a String line and starts a new line.
     */
    public void writeLine(String line){
        try {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Writes a line separator. 
     * @see {@link BufferedWriter#newLine()}
     */
    public void newLine(){
        try {
            bw.newLine();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Flushes this Inputer.
     * @see {@link BufferedWriter#flush()}
     */
    public void flush(){
        try {
            bw.flush();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Closes this Outputer, flushing it first.
     * @see {@link BufferedWriter#close()}
     */
    public void close(){
        try {
        	bw.flush();
            bw.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
