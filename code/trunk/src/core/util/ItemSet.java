package util;

import java.util.Set;

/**
 * The interface <tt>ItemSet</tt> presents a set of <tt>Item</tt> objects in Recommender Systems.
 * It allows to index a <tt>Item</tt> object through the <code>itemId</code> using the method <code>getItem(int itemId)</code>. 
 * It also does not permit the <tt>null</tt> element or duplicate elements.
 * <p>Noting that <tt>ItemSet</tt> does not support iterating each <tt>Item</tt> in it for efficiency, 
 * for example, the <tt>Item</tt> object points to a database entry. 
 * In most cases, recommendation algorithms just need the <code>itemId</code> to define an <tt>Item</tt> object, 
 * and the method <code>getItemIds()</code> provides the set of item ids for iterating. 
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Item
 */
public interface ItemSet {	
	
	/**
	 * Returns the number of items in this item set.
	 */
	public abstract int getItemCount();	

	/**
	 * Returns a <tt>Set</tt> view of the item ids contained in this item set.
	 */
	public abstract Set<Integer> getItemIds();	
	
	/**
	 * Return the item to which the specified item id is mapped, 
	 * or null if this item set contains no mapping for the item id
	 */	
	public abstract Item getItem(int itemId);
	
	/**
	 * Add a item into this item set. 
	 * If there is a item u in this item set meeting <code>u.equals(item)</code> , 
	 * or <code>item==null</code>, this item set will not be modified and return false¡£ 
	 */	
	public abstract boolean addItem(Item item);	
	
	/**
	 * Remove the item associated with the item id from this item set. 
	 * @return the removed item associated with the item id, 
	 * or null if this item set contains no mapping for the item id
	 */	
	public abstract Item removeItem(int itemId);	
	
	/**
	 * Returns true if this <tt>ItemSet</tt> contains an <tt>Item</tt> for the specified itemId.
	 */		
	public abstract boolean containsItem(int itemId);
}
