package jpl.ch01.ex15;

public interface Lookup {
	/**
	 * nameと関連付けられた値を返す．
	 * そのような値がなければnullを返す．
	 *
	 * @param name
	 *            名前
	 * @return 値
	 */
	Object find(String name);
}
