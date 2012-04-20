package sample;

import util.*;
import util.impl.*;

public class DataSetSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u0= new SimpleUser(0);
		User u1= new SimpleUser(1);
		Item i0 = new SimpleItem(0);
		Item i1 = new SimpleItem(1);
		Rate r00 = new Rate(u0,i0,1);
		Rate r01 = new Rate(u0,i1,3);
		Rate r11 = new Rate(u1,i1,5);		
		DataSet dataset = new SimpleDataSet();
		dataset.addRate(r00);
		dataset.addRate(r01);
		dataset.addRate(r11);
		System.out.println(r11);
		System.out.println(dataset.getRateCount());
	}

}
