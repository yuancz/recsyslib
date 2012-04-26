package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tan Chang
 */
public class Outputer {
    
    private BufferedWriter bw;
    
    public Outputer(String file){
        try {
            bw = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    public void write(String string){
        try {
            bw.write(string);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void writeLine(String line){
        try {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void flush(){
        try {
            bw.flush();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void close(){
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
