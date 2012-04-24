package util.impl;

import java.util.HashMap;
import java.util.Set;

import util.Item;
import util.ItemSet;

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
