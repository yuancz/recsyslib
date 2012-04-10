package util;


/**
 * 
 * This class provides a skeletal implementation of the <code>Item</code> interface, 
 * to minimize the effort required to implement this interface. 
 * <p>To implement a item, the programmer should generally provide a constructor with argument <code>iid</code>, 
 * as the recommendation in the <code>Item</code> interface specification.
 * @author tanchang
 *
 */
public abstract class AbstractItem implements Item {
	
	protected int iid;
	
	protected AbstractItem(int iid){
		this.iid = iid;
	}
	


	/**
	 * Compares the specified object with this <code>Item</code> for equality. 
	 * Returns true if and only if the specified object is also a <code>Item</code> and has the same <code>iid</code> with this <code>Item</code>.
	 * Thus, two <code>Item</code> objects are equal if and only if the <code>getIid</code> method returns the same integer value for both.
	 */	
	@Override
	public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Item))
            return false;
        Item user = (Item)obj;
        return user.getIid() == this.iid;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Converts this <code>Item</code> object to a String of the form "Item"+iid. 
	 */	
	@Override
	public String toString() {
		return "Item"+iid;
	}

}
