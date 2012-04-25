package tools;

public class Memory {
	public static long totalMemory(){
		return Runtime.getRuntime().totalMemory();
	}
	public static long freeMemory(){
		return Runtime.getRuntime().freeMemory();
	}
	public static long usedMemory(){
		return Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	}
}
