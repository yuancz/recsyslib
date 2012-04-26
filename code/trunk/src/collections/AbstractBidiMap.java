package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import util.RecSysLibException;

public abstract class AbstractBidiMap<K, V> implements Map<K, V>, BidiMap<K, V> {
	
	protected transient Map<K,V> kvMap;
	protected transient Map<V,K> vkMap;
	protected transient BidiMap<V,K> reversed;
	
	protected AbstractBidiMap(){
		createInnerMaps();
		reversed = null;
	}
	
	protected AbstractBidiMap(BidiMap<? extends K, ? extends V> bidiMap){
		this();
		for(Entry<? extends K, ? extends V> e : bidiMap.entrySet()){
			put(e.getKey(), e.getValue());
		}
	}
	
	protected AbstractBidiMap(Map<? extends K, ? extends V> map){
		this();
		for(Entry<? extends K, ? extends V> e : map.entrySet()){
			put(e.getKey(), e.getValue());
		}
	}			
				
	protected abstract void createInnerMaps();
	
	protected abstract BidiMap<V, K> getReversed();
	
	@Override
	public BidiMap<V, K> getReversedBidiMap() {
		if(reversed == null){
			reversed = getReversed();
		}
		return reversed;
	}
	
	@Override
	public void clear() {
		kvMap.clear();
		vkMap.clear();
	}
	
	@Override
	public V removeByKey(Object key) {
		V value = kvMap.remove(key);		
		if(value != null){
			vkMap.remove(value);
		}
		return value;
	}
	
	@Override
	public K removeByValue(Object value) {
		K key = vkMap.remove(value);
		if(key != null){
			kvMap.remove(key);
		}
		return key;
	}

	@Override
	public V remove(Object key) {
		return removeByKey(key);
	}
	
	@Override
	public V put(K key, V value) {	
		if(key==null || value==null)
			throw new RecSysLibException("Illegal value: null");
		vkMap.put(value, key);
		return kvMap.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for(Entry<? extends K, ? extends V> e : map.entrySet()){
			put(e.getKey(), e.getValue());
		}		
	}

	@Override
	public Set<K> keySet() {
		return kvMap.keySet();
	}
	
	@Override
	public Set<V> valueSet() {
		return vkMap.keySet();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return kvMap.entrySet();
	}	
	
	@Override
	public Collection<V> values() {
		return vkMap.keySet();
	}

	@Override
	public int size() {
		return kvMap.size();
	}
	
	@Override
	public boolean containsKey(Object key) {
		return kvMap.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value) {
		return vkMap.containsKey(value);
	}
	
	@Override
	public boolean isEmpty() {
		return kvMap.isEmpty() && vkMap.isEmpty();
	}
	
	@Override
	public V getValue(Object key) {
		return kvMap.get(key);
	}
	
	@Override
	public K getKey(Object value) {
		return vkMap.get(value);
	}
	
	@Override
	public V get(Object key) {
		return getValue(key);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(!(obj instanceof BidiMap))
			return false;
		BidiMap<?,?> bm = (BidiMap<?,?>)obj;
		if(bm.size() != size())return false;
		for(K key : keySet()){
			if(!(bm.containsKey(key) && getValue(key).equals(bm.getValue(key))))return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
        int h = 0;
        Iterator<Entry<K,V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
	}

	@Override
	public String toString() {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        while(i.hasNext()) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key == this ? "(this)" : key);
            sb.append('=');
            sb.append(value == this ? "(this)" : value);
            sb.append(',').append(' ');
        }
        return sb.append('}').toString();
	}

}
