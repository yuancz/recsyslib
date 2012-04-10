package util;


/**
 * A simple implementation of the abstract class <tt>Item</tt>.
 * A <tt>SimpleItem</tt> object only contains the item id <code>iid</code>, without any other information about an item.  
 * @version 1.0 2012-4-10
 * @author tanchang
 * @since JDK 1.7
 * @see Item
 */
public class SimpleItem extends Item {	

	private static final long serialVersionUID = 7901179443249110616L;

/**
 * Constructs a <tt>SimpleItem</tt> object with item id <code>iid</code>. 	
 * @param iid item id 
 */
	public SimpleItem(int iid){
		super(iid);
	}

}
