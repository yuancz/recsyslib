package util;


/**
 * 
 * This class provides a skeletal implementation of the <code>User</code> interface, 
 * to minimize the effort required to implement this interface. 
 * <p>To implement a user, the programmer should generally provide a constructor with argument <code>uid</code>, 
 * as the recommendation in the <code>User</code> interface specification.
 * @author tanchang
 *
 */
public abstract class AbstractUser implements User {
	
	private int uid;
	
	protected AbstractUser(int uid){
		this.uid = uid;
	}
	
	/**
	 * Return the <code>uid</code> of this <code>User</code> object. 
	 */	
	public int getUid(){
		return uid;
	}

	/**
	 * Compares the specified object with this <code>User</code> for equality. 
	 * Returns true if and only if the specified object is also a <code>User</code> and has the same <code>uid</code> with this <code>User</code>.
	 * Thus, two <code>User</code> objects are equal if and only if the <code>getUid</code> method returns the same int value for both.
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
	 * Converts this <code>User</code> object to a String of the form "User"+uid. 
	 */	
	@Override
	public String toString() {
		return "User"+uid;
	}

}
