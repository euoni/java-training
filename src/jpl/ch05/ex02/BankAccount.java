package jpl.ch05.ex02;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 銀行口座
 */
public class BankAccount {
	/**
	 * 保持する操作の最大数
	 */
	private final static int MAX_ACTIONS = 10;

	/**
	 * 口座番号
	 */
	private final long number;

	/**
	 * 現在の残高（単位はセント）
	 */
	private long balance;

	/**
	 * 直近{@link #MAX_ACTIONS}個の操作
	 */
	private final ArrayDeque<Action> actions = new ArrayDeque<>();

	public class Action {
		private final String act;
		private final long amount;

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return number + ": " + act + " " + amount;
		}
	}

	/**
	 * {@link BankAccount}に紐づく操作履歴
	 */
	public class History implements Iterator<Action> {
		private final Iterator<Action> iterator;

		History(Iterator<Action> iterator) {
			this.iterator = iterator;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Action next() {
			try {
				return iterator.next();
			} catch (final NoSuchElementException e) {
				return null;
			}
		}
	}

	/**
	 * @param number
	 *            口座番号
	 */
	public BankAccount(long number) {
		this.number = number;
	}

	/**
	 * @return 口座番号
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @return 残高（セント）
	 */
	public long getBalance() {
		return balance;
	}

	/**
	 * 入金する
	 *
	 * @param amount
	 *            入金額
	 */
	public void deposit(long amount) {
		balance += amount;
		add(new Action("deposit", amount));
	}

	/**
	 * 出金する
	 *
	 * @param amount
	 *            出金額
	 */
	public void withdraw(long amount) {
		balance -= amount;
		add(new Action("withdraw", amount));
	}

	/**
	 * @return 操作履歴
	 */
	public History history() {
		return new History(actions.iterator());
	}

	/**
	 * 新しい操作を記録する
	 *
	 * @param action
	 *            新しい操作
	 */
	private void add(Action action) {
		actions.add(action);
		if (actions.size() > MAX_ACTIONS) {
			actions.pollFirst();
		}
	}
}
