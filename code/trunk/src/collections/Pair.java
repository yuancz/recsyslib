package collections;

/**
 * This class presents a composition of two objects. It permits null value.  
 * @version 1.0 2012-4-26
 * @author Tan Chang
 * @since JDK 1.7
 */
public final class Pair<K, V> {
	
	private K key;
	
	private V value;
	
    /**
     * Constructs a <tt>Pair</tt> with the specified key and value.
     */
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}
	
    /**
     * Constructs a empty <tt>Pair</tt>, the key and value will be set as <code>null</code>.
     */
	public Pair(){
		this(null, null);
	}
	
	/**
	 * Returns true if and only if the specified object is also a <tt>Pair</tt> and 
	 * has both the same key and the same value.
	 */	
	@Override
	public boolean equals(Object o) {
        if (!(o instanceof Pair))
            return false;
        Pair<?, ?> p = (Pair<?, ?>)o;
        Object k1 = getKey();
        Object k2 = p.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = p.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2)))
                return true;
        }
        return false;
	}
	
	/**
	 * Returns a hash code of this <tt>Pair</tt>. 
	 */	
	@Override
	public int hashCode() {
        return (key==null   ? 0 : key.hashCode()) ^
                (value==null ? 0 : value.hashCode());
	}
	
	/**
	 * Converts this <tt>Pair</tt> object to a String of the form 
	 * <code>"<"+key.toString()+", "+value.toString()+">"</code>. 
	 */	
	@Override
	public String toString() {
		return "<"+key.toString()+", "+value.toString()+">";
	}
	
	/**
	 * Returns the key object of this pair. 
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * Sets the key as the new one, returns the old one. 
	 */
	public K setKey(K key) {
		K oldKey = this.key;
		this.key = key;
		return oldKey;
	}
	
	/**
	 * Returns the value object of this pair. 
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * Sets the value as the new one, returns the old one. 
	 */
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}
	
}
