package tools;

import java.util.HashMap;

/**
 * This class <tt>Timer</tt> provides stop-watch function.   
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public class Timer {
	
	private static final HashMap<String, Timer> map = new HashMap<>();
	
	/**
	 * Gets a <tt>Timer</tt> object with specified name. 
	 * If existing a timer with the name, return the timer,
	 * else return a new timer. 
	 */
	public static Timer getTimer(String name){
		if(!map.containsKey(name))map.put(name, new Timer());
		return map.get(name);
	}
	
	/**
	 * The timer state
	 */
	public static enum State{
		KEEP_TIME,
		PAUSE,
		STOP
	}
	
	private State state;
	
	private long duration;
	
	private long start;
	
	private Timer(){
		duration = 0;
		state = State.STOP;
	}		
	
	/**
	 * Returns the duration of this timer in keep-time state in milliseconds. 
	 */
	public long getDuration(){
		return duration;
	}
	
	/**
	 * Returns the current state of this timer
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
	 * Stops keeping time, return the duration of this timer until this operation in milliseconds. 
	 */
	public long stop(){
		switch(state){
		case KEEP_TIME:
			state = State.STOP;
			duration = System.currentTimeMillis()-start;
			return getDuration();
		default:
			return 0;			
		}
	}
	
	/**
	 * Pauses keeping time, return the duration of this timer until this operation in milliseconds. 
	 */
	public long pause(){
		switch(state){
		case KEEP_TIME:
			state = State.PAUSE;
			duration += System.currentTimeMillis()-start;
			return getDuration();
		default:
			return 0;			
		}
	}
	
	/**
	 * Resets this timer, the duration will be zero and the state be STOP. 
	 */
	public void reset(){
		duration = 0;
		state = State.STOP;
	}
	
}
