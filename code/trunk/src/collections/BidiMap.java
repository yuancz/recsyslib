package collections;

import java.util.Map;
import java.util.Set;

/**
 * Defines a map that allows bidirectional lookup between key and values.
 * It actually saves the one-to-one correspondence between keys and values. 
 * It does not permit null as either key or value. 
 * It mainly consults org.apache.commons.collections.BidiMap, but is not identical.
 * @version 1.0 2012-4-27
 * @author Tan Chang
 * @since JDK 1.7
 * @see Map
 */
public interface BidiMap<K, V> extends Map<K, V> {

	/**
	 * Gets the value that is currently mapped to the specified key.
	 */
	public abstract V getValue(Object key);

	/**
	 * Gets the key that is currently mapped to the specified value.
	 */
	public abstract K getKey(Object value);

	/**
	 * Returns a Set view of the values contained in this map.
	 */
	public abstract Set<V> valueSet();

	/**
	 * Removes the key-value pair that is currently mapped to the specified key 
	 */
	public abstract V removeByKey(Object key);

	/**
	 * Removes the key-value pair that is currently mapped to the specified value 
	 */
	public abstract K removeByValue(Object value);

	/**
	 * Puts the key-value pair into the map, replacing any previous pair.
	 */	
	public abstract V put(K key, V value);
	
	/**
	 * Gets a view of this BidiMap where the keys and values are reversed. The reversed BidiMap is backed by this BidiMap, 
	 * so changes to the original are reflected in the reversed, and vice-versa. 
	 */
	public abstract BidiMap<V, K> getReversedBidiMap();

}