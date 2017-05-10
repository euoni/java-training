package jpl.ch21.ex03;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WeakValueMap<K, V> implements Map<K, V> {
	private final HashMap<K, WeakReference<V>> data = new HashMap<>();

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return data.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values().contains(value);
	}

	@Override
	public V get(Object key) {
		final WeakReference<V> value = data.get(key);
		return value == null ? null : value.get();
	}

	@Override
	public V put(K key, V value) {
		final WeakReference<V> oldValue = data.put(key, new WeakReference<>(value));
		return oldValue == null ? null : oldValue.get();
	}

	@Override
	public V remove(Object key) {
		final WeakReference<V> oldValue = data.remove(key);
		return oldValue == null ? null : oldValue.get();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		m.entrySet().stream().forEach(e -> put(e.getKey(), e.getValue()));
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public Set<K> keySet() {
		return data.keySet();
	}

	/**
	 * 呼び出された時点の値の強い参照を持つリストを返す。
	 * すでにクリアされている場合、値は<code>null</code>になる。
	 */
	@Override
	public Collection<V> values() {
		return data.entrySet().stream().map(e -> e.getValue().get()).collect(Collectors.toList());
	}

	/**
	 * 呼び出された時点のキーと値の強い参照のセットを返す。
	 * すでにクリアされている場合、値は<code>null</code>になる。
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return keySet().stream().map(k -> new Entry(k)).collect(Collectors.toSet());
	}

	final class Entry implements Map.Entry<K, V> {
		private final K key;
		private V value;

		public Entry(K key) {
			this.key = key;
			this.value = get(key);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return put(key, value);
		}
	}
}
