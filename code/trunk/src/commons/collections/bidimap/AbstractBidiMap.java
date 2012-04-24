package commons.collections.bidimap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import commons.collections.BidiMap;

import util.RecSysLibException;

public abstract class AbstractBidiMap<K, V> implements Map<K, V>, BidiMap<K, V> {
	
	protected transient Map<K,V> kvMap;
	protected transient Map<V,K> vkMap;
	protected transient BidiMap<V,K> reversed;
	protected transient boolean changed;
	
	protected AbstractBidiMap(){
		createMaps();
		reversed = null;
		changed = false;
	}
	
	protected AbstractBidiMap(BidiMap<? extends K, ? extends V> bidiMap){
		this();
		for(Entry<? extends K, ? extends V> e : bidiMap.entrySet()){
			put(e.getKey(), e.getValue());
		}
	}
				
	protected abstract void createMaps();

	public abstract BidiMap<V, K> getReversedBidiMap();
	
	private void changed(){
		changed = true;
	}
	
	@Override
	public void clear() {
		kvMap.clear();
		vkMap.clear();
		changed();
	}
	
	@Override
	public V removeByKey(Object key) {
		V value = kvMap.remove(key);		
		if(value != null){
			vkMap.remove(value);
			changed();
		}
		return value;
	}
	
	@Override
	public K removeByValue(Object value) {
		K key = vkMap.remove(value);
		if(key != null){
			kvMap.remove(key);
			changed();
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
		changed();
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
		return kvMap.values();
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

}
