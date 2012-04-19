package util;

import java.util.AbstractSet;
import java.util.Set;

/**
 * This abstract class provides a skeletal implementation of the
 * <tt>ItemSet</tt> interface to minimize the effort required to implement this
 * interface. <p>
 * The process of implementing a <tt>ItemSet</tt> by extending this class is
 * identical to that of implementing a Set by extending
 * AbstractSet, except that all of the methods and constructors in
 * subclasses of this class must obey the additional constraints imposed by the
 * <tt>ItemSet</tt> interface (for instance, the add method must not permit addition
 * of multiple instances of an object or a <tt>null</tt> object to a <tt>ItemSet</tt>).
 * 
 * @version 1.0 2012-4-19
 * @author tanchang
 * @since JDK 1.7
 * @see Set
 * @see ItemSet
 */
public abstract class AbstractItemSet extends AbstractSet<Item> implements
		ItemSet, Set<Item>, Cloneable, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668455093939094026L;
	
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractItemSet() {
    }
    


}
