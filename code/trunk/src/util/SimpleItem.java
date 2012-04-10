package util;


/**
 * A simple implementation of the <code>Item</code> interface.
 * 
 * @author tanchang
 *
 */
public class SimpleItem extends AbstractItem implements Item{	
	
	public SimpleItem(int iid){
		super(iid);
	}
	
	/**
	 * Return the <code>iid</code> of this <code>Item</code> object. 
	 */	
	public int getIid(){
		return iid;
	}

}
