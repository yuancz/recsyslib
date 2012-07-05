package core.impl;

import java.util.HashMap;
import java.util.Set;

import core.Item;
import core.ItemSet;

/**
 * This class implements the <tt>ItemSet</tt> interface, 
 * backed by a <code>HashMap<Integer, Item></code> instance.
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 * @see Item
 * @see HashMap
 */
public class SimpleItemSet implements ItemSet {
	
	private HashMap<Integer, Item> map;
	
	public SimpleItemSet(){
		map = new HashMap<>();
	}
	
	@Override
	public boolean addItem(Item item){
		if(item == null)return false;
		int itemId = item.getItemId();
		if(!map.containsKey(itemId)){
			map.put(itemId, item);
			return true;
		}
		return false;
	}
	
	@Override
	public Item removeItem(int itemId){
		return map.remove(itemId);
	}
	
	@Override
	public int getItemCount() {
		return map.size();
	}

	@Override
	public Set<Integer> getItemIds() {
		return map.keySet();
	}

	@Override
	public Item getItem(int itemId) {
		return map.get(itemId);
	}

	@Override
	public boolean containsItem(int itemId) {
		return map.containsKey(itemId);
	}

}
