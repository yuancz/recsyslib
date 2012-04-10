package util;

import java.io.Serializable;

/**
 * The abstract class <tt>Item</tt> is the superclass of all <tt>Item</tt> objects in Recommender Systems. 
 * <p>To distinguish from others, each <tt>Item</tt> object should have a unique nonnegative integer <code>iid</code> (item id). 
 * The <code>iid</code> should be given when this <tt>Item</tt> object be created and never changed. 
 * To implement a <tt>Item</tt> object, the programmer should provide a constructor with argument <code>iid</code>. 
 * <p> This class implements the <tt>Comparable</tt> interface by the comparison of the <code>iid</code> value. 
 * @version 1.0 2012-4-10
 * @author tanchang
 * @since JDK 1.7
 */
public abstract class Item implements Comparable<Item>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1707920089838343245L;
	
	protected int iid;
	
    /**
     * Sole constructor. For invocation by subclass constructors.
     * Constructs a <tt>Item</tt> with the specified item id.
     *
     * @param  iid the item id 
     * @throws IllegalArgumentException if the item id is negative
     */
	protected Item(int iid){
		if (iid < 0)
            throw new IllegalArgumentException("Illegal item id: " + iid);
		this.iid = iid;
	}

	/**
	 * Return the item id of this <tt>Item</tt> object. 
	 */	
	public int getUid(){
		return iid;
	}
	
	/**
	 * Compares the specified object with this <tt>Item</tt> for equality. 
	 * Returns true if and only if the specified object is also a <tt>Item</tt> and has the same <code>iid</code> with this <tt>Item</tt>.
	 * Thus, two <tt>Item</tt> objects are equal if and only if the <code>getUid</code> method returns the same integer value for both.
	 */	
	@Override
	public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Item))
            return false;
        Item item = (Item)obj;
        return item.getUid() == this.iid;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Converts this <tt>Item</tt> object to a String of the form <code>"Item"+iid</code>. 
	 */	
	@Override
	public String toString() {
		return "Item"+iid;
	}	

	/**
	 * Compares this item to another for order.
	 * Two items are compared by their <code>iid</code> values.  
	 * Returns a negative integer, zero, or a positive integer as this <code>iid</code> is 
	 * less than, equal to, or greater than the specified item's iid.
	 */
	@Override
	public int compareTo(Item item) {
		return this.iid - item.getUid();
	}
}
