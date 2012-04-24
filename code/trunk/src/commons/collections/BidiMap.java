package commons.collections;

import java.util.Map;
import java.util.Set;

/**
 * Defines a map that allows bidirectional lookup between key and values.
 * It actually saves the one-to-one correspondence between keys and values. 
 * It does not permit null as either key or value. 
 * @author tanchang
 */
public interface BidiMap<K, V> extends Map<K, V> {

	public abstract V getValue(Object key);

	public abstract K getKey(Object value);

	public abstract Set<V> valueSet();

	public abstract V removeByKey(Object key);

	public abstract K removeByValue(Object value);

	/**
	 * Puts the key-value pair into the map, replacing any previous pair.
	 */	
	public abstract V put(K key, V value);
	
	public abstract BidiMap<V, K> getReversedBidiMap();

}