package jpl.ch01.ex15;

public interface MutableLookup extends Lookup {
	/**
	 * nameと関連付けられた値を追加する．
	 *
	 * @param name
	 *            名前
	 * @param value
	 *            値
	 */
	void add(String name, Object value);

	/**
	 * nameと関連付けられた値を削除し，削除成功かどうか返す．
	 *
	 * @param name
	 *            名前
	 * @return 削除に成功したか
	 */
	boolean remove(String name);
}
