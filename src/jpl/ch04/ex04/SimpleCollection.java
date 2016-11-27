package jpl.ch04.ex04;

public interface SimpleCollection<E> extends Iterable<E> {
	/**
	 * 要素を1つ追加する。
	 *
	 * @param elem
	 *            追加する要素
	 */
	void add(E elem);

	/**
	 * 要素を1つ削除する。
	 *
	 * @param elem
	 *            削除する要素
	 * @return 削除に成功した場合はtrue
	 */
	boolean remove(E elem);

	/**
	 * 要素が含まれている場合trueを返す。
	 *
	 * @param elem
	 *            調べる要素
	 * @return 指定された要素がある場合はtrue
	 */
	boolean contains(E elem);
}
