package util;

/**
 * The root interface of all <code>User</code> objects in Recommender Systems.
 * Each <code>User</code> object should have a unique <code>uid</code> to distinguish from others. 
 * The <code>uid</code> should be given when this <code>User</code> object be created and never changed. 
 * @author tanchang
 */
public interface User {
	
	/**
	 * Return the <code>uid</code> of this <code>User</code> object. 
	 */	
	abstract public int getUid();
}
