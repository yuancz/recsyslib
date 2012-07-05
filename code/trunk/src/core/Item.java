package core;

/**
 * The abstract class <tt>Item</tt> is the superclass of all <tt>Item</tt> objects in Recommender Systems. 
 * <p>To distinguish from others, each <tt>Item</tt> object should have a unique nonnegative integer <code>itemId</code> (item id). 
 * The <code>itemId</code> should be given when this <tt>Item</tt> object be created and never changed. 
 * To implement a <tt>Item</tt> object, the programmer should provide a constructor with argument <code>itemId</code>. 
 * <p> This class implements the <tt>Comparable</tt> interface by the comparison of the <code>itemId</code> value. 
 * @version 1.0 2012-4-10
 * @author Tan Chang
 * @since JDK 1.7
 */
public abstract class Item implements Comparable<Item> {
	
	protected int itemId;
	
    /**
     * Sole constructor. For invocation by subclass constructors.
     * Constructs a <tt>Item</tt> with the specified item id.
     *
     * @param  itemId the item id 
     * @throws IllegalArgumentException if the item id is negative
     */
	protected Item(int itemId){
		if (itemId < 0)
            throw new RecSysLibException("Item id must be nonnegative. ");
		this.itemId = itemId;
	}

	/**
	 * Return the item id of this <tt>Item</tt> object. 
	 */	
	public int getItemId(){
		return itemId;
	}
	
	/**
	 * Compares the specified object with this <tt>Item</tt> for equality. 
	 * Returns true if and only if the specified object is also a <tt>Item</tt> and has the same <code>itemId</code> with this <tt>Item</tt>.
	 * Thus, two <tt>Item</tt> objects are equal if and only if the <code>getUid</code> method returns the same integer value for both.
	 */	
	@Override
	public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Item))
            return false;
        Item item = (Item)obj;
        return item.getItemId() == this.itemId;
	}

	/**
	 * Returns <code>itemId</code>
	 */	
	@Override
	public int hashCode() {
		return itemId;
	}
	
	/**
	 * Converts this <tt>Item</tt> object to a String of the form <code>"Item_"+itemId</code>. 
	 */	
	@Override
	public String toString() {
		return "Item_"+itemId;
	}	

	/**
	 * Compares this item to another for order.
	 * Two items are compared by their <code>itemId</code> values.  
	 * Returns a negative integer, zero, or a positive integer as this <code>itemId</code> is 
	 * less than, equal to, or greater than the specified item's itemId.
	 */
	@Override
	public int compareTo(Item item) {
		return this.itemId - item.getItemId();
	}
}
