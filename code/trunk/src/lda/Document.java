package lda;

import collections.Bag;
import collections.HashBag;

public final class Document {
	
	private Bag<Integer> bag;//Integer: wordId
	
	public Document(){
		bag = new HashBag<>();
	}
}
