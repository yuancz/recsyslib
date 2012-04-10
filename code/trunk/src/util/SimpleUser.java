package util;


/**
 * A simple implementation of the <code>User</code> interface.
 * 
 * @author tanchang
 *
 */
public class SimpleUser extends AbstractUser implements User{	
	
	public SimpleUser(int uid){
		super(uid);
	}
	
	/**
	 * Return the <code>uid</code> of this <code>User</code> object. 
	 */	
	public int getUid(){
		return uid;
	}

}
