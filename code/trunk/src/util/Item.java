package util;

/**
 * The root interface of all <code>Item</code> objects in Recommender Systems.
 * Each <code>Item</code> object should have a unique <code>iid</code> to distinguish from others. 
 * The <code>iid</code> should be given when this <code>Item</code> object be created and never changed. 
 * @author tanchang
 */
public interface Item {
	/**
	 * Return the <code>iid</code> of this <code>Item</code> object. 
	 */	
	abstract public int getIid();
}