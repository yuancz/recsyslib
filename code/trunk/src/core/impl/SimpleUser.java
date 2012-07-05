package core.impl;

import core.User;


/**
 * A simple implementation of the abstract class <tt>User</tt>.
 * A <tt>SimpleUser</tt> object only contains the user id <code>uid</code>, without any other information about a user. 
 * @version 1.0 2012-4-10
 * @author Tan Chang
 * @since JDK 1.7
 * @see User
 */
public class SimpleUser extends User {	
	
/**
 * Constructs a <tt>SimpleUser</tt> object with user id <code>uid</code>. 	
 * @param uid user id 
 */
	public SimpleUser(int uid){
		super(uid);
	}

}
