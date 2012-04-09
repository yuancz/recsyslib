package util;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = new SimpleUser(123);
		System.out.println(u.toString());
		User u2 = new SimpleUser(123);
		System.out.println(u.equals(u2));
		System.out.println(u.equals("123"));
	}

}
