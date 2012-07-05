package core.impl;

import core.Item;


/**
 * A simple implementation of the abstract class <tt>Item</tt>.
 * A <tt>SimpleItem</tt> object only contains the item id <code>iid</code>, without any other information about an item.  
 * @version 1.0 2012-4-10
 * @author Tan Chang
 * @since JDK 1.7
 * @see Item
 */
public class SimpleItem extends Item {	

/**
 * Constructs a <tt>SimpleItem</tt> object with item id <code>iid</code>. 	
 * @param iid item id 
 */
	public SimpleItem(int iid){
		super(iid);
	}

}
