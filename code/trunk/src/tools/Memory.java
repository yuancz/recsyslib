package tools;

/**
 * This class <tt>Memory</tt> provides the function for check memory amount of JVM.   
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public class Memory {
	
	/**
	 * Returns the amount of total memory in the Java Virtual Machine measured in bytes. 
	 */
	public static long totalMemory(){
		return Runtime.getRuntime().totalMemory();
	}
	
	/**
	 * Returns the amount of free memory in the Java Virtual Machine measured in bytes. 
	 */
	public static long freeMemory(){
		return Runtime.getRuntime().freeMemory();
	}
	
	/**
	 * Returns the amount of used memory in the Java Virtual Machine measured in bytes. 
	 */
	public static long usedMemory(){
		return Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	}
}
