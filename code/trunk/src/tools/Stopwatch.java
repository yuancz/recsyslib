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
	public static Stopwatch getTimer(String name){
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
		return duration;
	}
	
	/**
	 * Returns the current state of this stopwatch
	 */
	public State getState(){
		return state;
	}
	
	/**
	 * Starts keeping time, return true if the state before this operation is STOP or PAUSE. 
	 */
	public boolean start(){
		switch(state){
		case STOP:
		case PAUSE:
			state = State.KEEP_TIME;
			start = System.currentTimeMillis();
			return true;
		default:
			return false;			
		}
	}
	
	/**
	 * Stops keeping time.
	 * Return the duration of this stopwatch until this operation in milliseconds. 
	 */
	public long stop(){
		switch(state){
		case KEEP_TIME:
			state = State.STOP;
			duration = System.currentTimeMillis() - start;
			break;		
		}
		return duration;
	}
	
	/**
	 * Pauses keeping time. 
	 * Return the duration of this stopwatch until this operation in milliseconds. 
	 */
	public long pause(){
		switch(state){
		case KEEP_TIME:
			state = State.PAUSE;
			duration += System.currentTimeMillis() - start;
			break;		
		}
		return duration;
	}
	
	/**
	 * Resets this stopwatch, the duration will be zero and the state be STOP. 
	 * Return the duration of this stopwatch until this operation in milliseconds. 
	 */
	public long reset(){
		long oldDuration = duration;
		duration = 0;
		state = State.STOP;
		return oldDuration;
	}
	
}
