package collections;

import java.util.HashMap;
import java.util.Map;


public class HashBidiMap<K, V> extends AbstractBidiMap<K, V> implements BidiMap<K, V>{

	public HashBidiMap() {
		super();
	}
	
	public HashBidiMap(BidiMap<? extends K, ? extends V> bidiMap) {
		super(bidiMap);
	}

	public HashBidiMap(Map<? extends K, ? extends V> map) {
		super(map);
	}
	
	@Override
	protected void createInnerMaps() {
		kvMap = new HashMap<>();
		vkMap = new HashMap<>();		
	}

	public BidiMap<V, K> getReversed() {
		HashBidiMap<V, K> reversed = new HashBidiMap<>();
		reversed.kvMap = vkMap;
		reversed.vkMap = kvMap;
		return reversed;
	}

}
