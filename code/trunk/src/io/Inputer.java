package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Tan Chang
 */
public class Inputer {
    
    private BufferedReader br;
    
    public Inputer(String file){
            try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    }
    
    public String readLine(){
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
        	e.printStackTrace();
        } 
        return line;
    }
    
    public void close(){
        try {
            br.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
}
