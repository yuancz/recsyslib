package util;

import java.util.Set;

public interface ItemSet {	
	public abstract int getItemCount();	
	public abstract Set<Integer> getItemIds();	
	/**
	 * @return the item to which the specified item id is mapped, 
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
	public abstract boolean containsItem(int itemId);
}
