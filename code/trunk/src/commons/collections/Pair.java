package commons.collections;

/**
 * @author tanchang
 *
 * @param <K>
 * @param <V>
 * @see HashMap.Entry
 */
public class Pair<K, V> {
	protected K key;
	protected V value;
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}
	@Override
	public boolean equals(Object o) {
        if (!(o instanceof Pair))
            return false;
        Pair p = (Pair)o;
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
	@Override
	public int hashCode() {
        return (key==null   ? 0 : key.hashCode()) ^
                (value==null ? 0 : value.hashCode());
	}
	@Override
	public String toString() {
		return "<"+key.toString()+", "+value.toString()+">";
	}
	public K getKey() {
		return key;
	}
	public K setKey(K key) {
		K oldKey = this.key;
		this.key = key;
		return oldKey;
	}
	public V getValue() {
		return value;
	}
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}
	
}
