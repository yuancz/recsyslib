package sample;

import java.util.Arrays;
import java.util.List;

import collections.Bag;
import collections.HashBag;

public class BagSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strs = {"a", "a", "a", "b", "b", "c"};
		List<String> list = Arrays.asList(strs);
		Bag<String> bag = new HashBag<String>(list);
		System.out.println(bag.size());
		System.out.println(bag.retainAll(list.subList(0, 3)));
	}

}
