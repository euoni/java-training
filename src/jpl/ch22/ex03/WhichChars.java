package jpl.ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WhichChars {
	final HashMap<Byte, BitSet> used = new HashMap<>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			final char c = str.charAt(i);

			final byte msb = (byte) (c >> 8);
			BitSet set = used.get(msb);
			if (set == null)
				set = new BitSet();

			final byte lsb = (byte) c;
			set.set(lsb);
			used.put(msb, set);
		}
	}

	@Override
	public String toString() {
		String desc = "[";
		for (final Entry<Byte, BitSet> entry : new TreeMap<>(used).entrySet()) {
			final Byte msb = entry.getKey();
			final BitSet set = entry.getValue();
			for (int i = set.nextSetBit(0); i >= 0; i = set.nextSetBit(i + 1)) {
				desc += (char) (msb << 8 | i);
			}
		}
		return desc + "]";
	}
}
