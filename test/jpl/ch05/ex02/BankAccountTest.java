package jpl.ch05.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch05.ex02.BankAccount.History;

public class BankAccountTest {
	@Test
	public void testBankAccount() {
		final BankAccount account = new BankAccount(123);

		assertThat(account.getNumber(), is(123L));
		assertThat(account.getBalance(), is(0L));
	}

	@Test
	public void testDeposit() {
		final BankAccount account = new BankAccount(123);
		account.deposit(100);

		assertThat(account.getBalance(), is(100L));
	}

	@Test
	public void testWithdraw() {
		final BankAccount account = new BankAccount(123);
		account.withdraw(100);

		assertThat(account.getBalance(), is(-100L));
	}

	@Test
	public void testHistory() {
		// add 11 actions
		final BankAccount account = new BankAccount(123);
		for (int i = 0; i < 11; i++) {
			account.deposit(i);
		}
		final History history = account.history();

		// assert 10 actions in history
		for (int i = 1; i < 11; i++) {
			assertThat(history.hasNext(), is(true));
			assertThat(history.next().toString(), is("123: deposit " + i));
		}
		assertThat(history.hasNext(), is(false));
		assertThat(history.next(), nullValue());
	}
}
