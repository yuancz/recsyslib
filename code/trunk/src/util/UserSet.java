package util;

import java.util.Set;

public interface UserSet {
	public abstract int getUserCount();
	public abstract Set<Integer> getUserIds();
	public abstract User getUser(int userId);
	public abstract boolean addUser(User user);
	public abstract User removeUser(int userId);
}
