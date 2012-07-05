package core;

/**
 * The abstract class <tt>User</tt> is the superclass of all <tt>User</tt> objects in Recommender Systems. 
 * <p>To distinguish from others, each <tt>User</tt> object should have a unique nonnegative integer <code>userId</code> (user id). 
 * The <code>userId</code> should be given when this <tt>User</tt> object be created and never changed. 
 * To implement a <tt>User</tt> object, the programmer should provide a constructor with argument <code>userId</code>. 
 * <p> This class implements the <tt>Comparable</tt> interface by the comparison of the <code>userId</code> value. 
 * @version 1.0 2012-4-19
 * @author Tan Chang
 * @since JDK 1.7
 */
public abstract class User implements Comparable<User> {
	
	protected int userId;
	
    /**
     * Sole constructor. For invocation by subclass constructors.
     * Constructs a <tt>User</tt> with the specified user id.
     *
     * @param  userId the user id 
     * @throws IllegalArgumentException if the user id is negative
     */
	protected User(int userId){
		if (userId < 0)
            throw new RecSysLibException("User id must be nonnegative. ");
		this.userId = userId;
	}

	/**
	 * Return the user id of this <tt>User</tt> object. 
	 */	
	public int getUserId(){
		return userId;
	}
	
	/**
	 * Compares the specified object with this <tt>User</tt> for equality. 
	 * Returns true if and only if the specified object is also a <tt>User</tt> and has the same <code>userId</code> with this <tt>User</tt>.
	 * Thus, two <tt>User</tt> objects are equal if and only if the <code>getUserId</code> method returns the same integer value for both.
	 */	
	@Override
	public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User)obj;
        return user.getUserId() == this.userId;
	}

	/**
	 * Returns <code>userId</code>
	 */	
	@Override
	public int hashCode() {
		return userId;
	}
	
	/**
	 * Converts this <tt>User</tt> object to a String of the form <code>"User_"+userId</code>. 
	 */	
	@Override
	public String toString() {
		return "User_"+userId;
	}	

	/**
	 * Compares this user to another for order.
	 * Two users are compared by their <code>userId</code> values.  
	 * Returns a negative integer, zero, or a positive integer as this <code>userId</code> is 
	 * less than, equal to, or greater than the specified user's userId.
	 */
	@Override
	public int compareTo(User user) {
		return this.userId - user.getUserId();
	}
}
