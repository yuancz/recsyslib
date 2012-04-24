package commons.collections.bidimap;

import java.util.HashMap;

import commons.collections.BidiMap;

public class HashBidiMap<K, V> extends AbstractBidiMap<K, V> {

	public HashBidiMap() {
		super();
	}
	
	@Override
	protected void createMaps() {
		kvMap = new HashMap<>();
		vkMap = new HashMap<>();		
	}

	@Override
	public BidiMap<V, K> getReversedBidiMap() {
		if(changed){
			reversed = new HashBidiMap<>();
			for(Entry<K, V> e : entrySet()){
				reversed.put(e.getValue(), e.getKey());
			}
		}
		return reversed;
	}

}
