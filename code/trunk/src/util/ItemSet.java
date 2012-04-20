package util;

import java.util.Set;

public interface ItemSet {	
	public abstract int getItemCount();
	public abstract Set<Integer> getItemIds();	
	public abstract Item getItem(int itemId);	
	public abstract boolean addItem(Item item);	
	public abstract Item removeItem(int itemId);
}
