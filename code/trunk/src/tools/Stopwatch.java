package tools;

import java.util.HashMap;

/**
 * This class <tt>Stopwatch</tt> provides stopwatch function.   
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public class Stopwatch {
	
	private static final HashMap<String, Stopwatch> map = new HashMap<>();
	
	/**
	 * Gets a <tt>Stopwatch</tt> object with specified name. 
	 * If existing a stopwatch with the name, return the stopwatch,
	 * else return a new stopwatch. 
	 */
	public static Stopwatch getInstance(String name){
		if(!map.containsKey(name))map.put(name, new Stopwatch());
		return map.get(name);
	}
	
	/**
	 * The stopwatch state
	 */
	public static enum State{
		KEEP_TIME,
		PAUSE,
		STOP
	}
	
	private State state;
	
	private long duration;
	
	private long start;
	
	private Stopwatch(){
		duration = 0;
		state = State.STOP;
	}		
	
	/**
	 * Returns the duration of this stopwatch in keep-time state in milliseconds. 
	 */
	public long getDuration(){
		switch(state){
		case STOP:
		case PAUSE:
			return duration;
		case KEEP_TIME:
		default:
			return System.currentTimeMillis() - start;	
		}
		
	}
	
	/**
	 * Returns the current state of this stopwatch
	 */
	public State getState(){
		return state;
	}
	
	/**
	 * Starts keeping time, return true if the state before this operation is STOP or PAUSE. 
	 * If the before state is STOP, the duration will be 0, 
	 * else if PAUSE, the duration will continue to increase.  
	 */
	public boolean start(){
		switch(state){
		case STOP:
			state = State.KEEP_TIME;
			start = System.currentTimeMillis();
			return true;
		case PAUSE:
			state = State.KEEP_TIME;
			return true;
		default:
			return false;			
		}
	}
	
	/**
	 * Stops keeping time, return true if the state before this operation is KEEP_TIME. 
	 */
	public boolean stop(){
		switch(state){
		case KEEP_TIME:
			state = State.STOP;
			duration = System.currentTimeMillis() - start;
			return true;
		case STOP:
		case PAUSE:
		default:
			return false;
		}
	}
	
	/**
	 * Pauses keeping time, return true if the state before this operation is KEEP_TIME. 
	 */
	public boolean pause(){
		switch(state){
		case KEEP_TIME:
			state = State.PAUSE;
			duration = System.currentTimeMillis() - start;
			return true;	
		case STOP:
		case PAUSE:
		default:
			return false;
		}
	}
	
}
