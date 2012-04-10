package util;

import java.io.Serializable;

/**
 * The abstract class <tt>User</tt> is the superclass of all <tt>User</tt> objects in Recommender Systems. 
 * <p>To distinguish from others, each <tt>User</tt> object should have a unique nonnegative integer <code>uid</code> (user id). 
 * The <code>uid</code> should be given when this <tt>User</tt> object be created and never changed. 
 * To implement a <tt>User</tt> object, the programmer should provide a constructor with argument <code>uid</code>. 
 * <p> This class implements the <tt>Comparable</tt> interface by the comparison of the <code>uid</code> value. 
 * @version 1.0 2012-4-10
 * @author tanchang
 * @since JDK 1.7
 */
public abstract class User implements Comparable<User>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1707920089838343245L;
	
	protected int uid;
	
    /**
     * Sole constructor. For invocation by subclass constructors.
     * Constructs a <tt>User</tt> with the specified user id.
     *
     * @param  uid the user id 
     * @throws IllegalArgumentException if the user id is negative
     */
	protected User(int uid){
		if (uid < 0)
            throw new IllegalArgumentException("Illegal user id: " + uid);
		this.uid = uid;
	}

	/**
	 * Return the user id of this <tt>User</tt> object. 
	 */	
	public int getUid(){
		return uid;
	}
	
	/**
	 * Compares the specified object with this <tt>User</tt> for equality. 
	 * Returns true if and only if the specified object is also a <tt>User</tt> and has the same <code>uid</code> with this <tt>User</tt>.
	 * Thus, two <tt>User</tt> objects are equal if and only if the <code>getUid</code> method returns the same integer value for both.
	 */	
	@Override
	public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User)obj;
        return user.getUid() == this.uid;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Converts this <tt>User</tt> object to a String of the form <code>"User"+uid</code>. 
	 */	
	@Override
	public String toString() {
		return "User"+uid;
	}	

	/**
	 * Compares this user to another for order.
	 * Two users are compared by their <code>uid</code> values.  
	 * Returns a negative integer, zero, or a positive integer as this <code>uid</code> is 
	 * less than, equal to, or greater than the specified user's uid.
	 */
	@Override
	public int compareTo(User user) {
		return this.uid - user.getUid();
	}
}
