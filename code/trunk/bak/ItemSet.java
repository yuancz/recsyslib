package util;

import java.util.Set;


/**
 * This interface extends the <tt>Set</tt> interface to present a set of <tt>Item</tt> objects.
 * It allows to index a <tt>Item</tt> object through the <code>iid</code> using the method <code>getItem(int iid)</code>. 
 * It also does not permit the <tt>null</tt> element or duplicate elements.
 * More formally, a <tt>ItemSet</tt> object contains no pair of <tt>Item</tt>
 * objects <code>i1</code> and <code>i2</code> such that
 * <code>i1.equals(i2)</code>. 
 * @version 1.0 2012-4-19
 * @author tanchang
 * @since JDK 1.7
 */
public interface ItemSet extends Set<Item>{

	/**
	 * Returns the <tt>Item</tt> object with the specified <code>iid</code>, 
	 * or <code>null</code> if this <tt>ItemSet</tt> contains no mapping for the iid.
	 * @param iid Item id
	 * @return the <tt>Item</tt> object with the specified <code>iid</code>
	 */
	abstract public Item getItem(int iid);

}
