package util;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Set;

/**
 * This abstract class provides a skeletal implementation of the
 * <tt>UserSet</tt> interface to minimize the effort required to implement this
 * interface. <p>
 * The process of implementing a <tt>UserSet</tt> by extending this class is
 * identical to that of implementing a Set by extending
 * AbstractSet, except that all of the methods and constructors in
 * subclasses of this class must obey the additional constraints imposed by the
 * <tt>UserSet</tt> interface (for instance, the add method must not permit addition
 * of multiple instances of an object or a <tt>null</tt> object to a <tt>UserSet</tt>).
 * 
 * @version 1.0 2012-4-18
 * @author tanchang
 * @since JDK 1.7
 * @see Set
 * @see UserSet
 */
public abstract class AbstractUserSet extends AbstractSet<User> implements
		UserSet, Set<User>, Cloneable, java.io.Serializable {

	private static final long serialVersionUID = -394176332765457158L;
	
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractUserSet() {
    }
    


}
