package tools;

import java.util.HashMap;

public class Timer {
	
	public static final HashMap<String, Timer> map = new HashMap<>();
	
	public static Timer getTimer(String name){
		if(!map.containsKey(name))map.put(name, new Timer());
		return map.get(name);
	}
	
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
	
	public long getDuration(){
		return duration;
	}
	
	public State getState(){
		return state;
	}
	
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
	
	public long stop(){
		switch(state){
		case KEEP_TIME:
			state = State.STOP;
			duration = System.currentTimeMillis()-start;
			return duration;
		default:
			return 0;			
		}
	}
	
	public long pause(){
		switch(state){
		case KEEP_TIME:
			state = State.PAUSE;
			duration += System.currentTimeMillis()-start;
			return duration;
		default:
			return 0;			
		}
	}
	
	public void reset(){
		duration = 0;
		state = State.STOP;
	}
	
}
