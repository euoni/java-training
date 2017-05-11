package jpl.ch21.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class WeakValueMapTest {
	@Test
	public void testSize() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.size(), is(0));

		map.put("key", new Object());
		assertThat(map.size(), is(1));

		map.remove("key");
		assertThat(map.size(), is(0));
	}

	@Test
	public void testIsEmpty() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.isEmpty(), is(true));

		map.put("key", new Object());
		assertThat(map.isEmpty(), is(false));

		map.remove("key");
		assertThat(map.isEmpty(), is(true));
	}

	@Test
	public void testContainsKey() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.containsKey("key"), is(false));

		map.put("key", new Object());
		assertThat(map.containsKey("key"), is(true));

		map.remove("key");
		assertThat(map.containsKey("key"), is(false));
	}

	@Test
	public void testContainsValue() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.containsValue(value), is(false));

		map.put("key", value);
		assertThat(map.containsValue(value), is(true));

		map.remove("key");
		assertThat(map.containsValue(value), is(false));
	}

	@Test
	public void testGet() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.get("key"), nullValue());

		map.put("key", value);
		assertThat(map.get("key"), is(value));

		map.remove("key");
		assertThat(map.get("key"), nullValue());
	}

	@Test
	public void testGetAfterGC() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", new Object());
		assertThat(map.get("key"), not(nullValue()));

		System.gc();
		assertThat(map.get("key"), nullValue());
	}

	@Test
	public void testPut() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();

		Object ret = map.put("key", value);
		assertThat(ret, nullValue());

		ret = map.put("key", new Object());
		assertThat(ret, is(value));
	}

	@Test
	public void testPutAfterGC() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", new Object());

		System.gc();
		final Object ret = map.put("key", new Object());
		assertThat(ret, nullValue());
	}

	@Test
	public void testRemove() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.remove("key"), nullValue());

		map.put("key", value);
		assertThat(map.remove("key"), is(value));
	}

	@Test
	public void testRemoveAfterGC() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", new Object());

		System.gc();
		assertThat(map.remove("key"), nullValue());
	}

	@Test
	public void testPutAll() {
		final HashMap<String, Object> m = new HashMap<>();
		m.put("key1", 1);
		m.put("key2", 2);

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.putAll(m);
		assertThat(map.get("key1"), is(1));
		assertThat(map.get("key2"), is(2));
	}

	@Test
	public void testClear() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", new Object());
		assertThat(map.isEmpty(), is(false));

		map.clear();
		assertThat(map.isEmpty(), is(true));
	}

	@Test
	public void testKeySet() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.keySet(), empty());

		map.put("key", new Object());
		assertThat(map.keySet(), contains("key"));
	}

	@Test
	public void testValues() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.values(), empty());

		map.put("key", value);
		assertThat(map.values(), contains(value));
	}

	@Test
	public void testValuesBeforeGC() {
		final WeakReference<Object> value = new WeakReference<>(new Object());

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", value.get());
		assertThat(map.get("key"), not(nullValue()));

		final Collection<Object> values = map.values();
		System.gc();
		assertThat(values, contains(value.get()));
	}

	@Test
	public void testValuesAfterGC() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", new Object());

		System.gc();
		final Collection<Object> values = map.values();
		assertThat(values, contains((Object) null));
	}

	@Test
	public void testEntrySet() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.entrySet(), empty());

		map.put("key", value);
		final Set<Entry<String, Object>> entrySet = map.entrySet();
		assertThat(entrySet.size(), is(1));

		final Entry<String, Object> entry = entrySet.iterator().next();
		assertThat(entry.getKey(), is("key"));
		assertThat(entry.getValue(), is(value));
	}

	@Test
	public void testEntrySetBeforeGC() {
		final WeakReference<Object> value = new WeakReference<>(new Object());

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.entrySet(), empty());

		map.put("key", value.get());
		assertThat(map.get("key"), not(nullValue()));

		final Set<Entry<String, Object>> entrySet = map.entrySet();
		System.gc();
		assertThat(entrySet.size(), is(1));

		final Entry<String, Object> entry = entrySet.iterator().next();
		assertThat(entry.getKey(), is("key"));
		assertThat(entry.getValue(), is(value.get()));
	}

	@Test
	public void testEntrySetAfterGC() {
		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		assertThat(map.entrySet(), empty());

		map.put("key", new Object());

		System.gc();
		final Set<Entry<String, Object>> entrySet = map.entrySet();
		assertThat(entrySet.size(), is(1));

		final Entry<String, Object> entry = entrySet.iterator().next();
		assertThat(entry.getKey(), is("key"));
		assertThat(entry.getValue(), nullValue());
	}

	@Test
	public void testEntrySetValue() {
		final Object value = new Object();

		final WeakValueMap<String, Object> map = new WeakValueMap<>();
		map.put("key", value);
		final Set<Entry<String, Object>> entrySet = map.entrySet();
		final Entry<String, Object> entry = entrySet.iterator().next();

		assertThat(map.get("key"), is(value));
		assertThat(entry.getValue(), is(value));

		final Object newValue = new Object();
		final Object oldValue = entry.setValue(newValue);
		assertThat(oldValue, is(value));

		assertThat(map.get("key"), is(newValue));
		assertThat(entry.getValue(), is(newValue));
	}
}
