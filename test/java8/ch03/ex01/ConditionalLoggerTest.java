package java8.ch03.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class ConditionalLoggerTest {
	private Logger base;
	private TestHandler handler;
	private ConditionalLogger logger;

	@Before
	public void setUp() throws Exception {
		base = Logger.getLogger("test");
		base.setLevel(Level.ALL);
		handler = new TestHandler();
		base.addHandler(handler);
		logger = new ConditionalLogger(base);
	}

	@Test
	public void testLogIfConditionTrue() {
		logger.logIf(Level.CONFIG, () -> "config", () -> true);
		handler.assertRecord(Level.CONFIG, "config");
	}

	@Test
	public void testLogIfConditionFalse() {
		logger.logIf(Level.CONFIG, () -> "config", () -> false);
		handler.assertNoRecord();
	}

	@Test
	public void testLogIfConditionCalled() {

		final boolean[] called = { false };
		logger.logIf(Level.CONFIG, () -> "config", () -> {
			called[0] = true;
			return true;
		});

		assertThat(called[0], is(true));
	}

	@Test
	public void testLogIfConditionNotCalledWhenLevelNotMatched() {
		base.setLevel(Level.INFO);

		final boolean[] called = { false };
		logger.logIf(Level.CONFIG, () -> "config", () -> {
			called[0] = true;
			return true;
		});

		assertThat(called[0], is(false));
	}

	@Test
	public void testLogIfMsgNotCalledWhenConditionNotMatched() {
		final boolean[] called = { false };
		logger.logIf(Level.CONFIG, () -> {
			called[0] = true;
			return "config";
		}, () -> false);

		assertThat(called[0], is(false));
	}

	@Test
	public void testLogIfMsgNotCalledWhenLevelNotMatched() {
		base.setLevel(Level.INFO);

		final boolean[] called = { false };
		logger.logIf(Level.CONFIG, () -> {
			called[0] = true;
			return "config";
		}, () -> true);

		assertThat(called[0], is(false));
	}

	private static class TestHandler extends Handler {
		private LogRecord record;

		@Override
		public void publish(LogRecord record) {
			this.record = record;
		}

		@Override
		public void flush() {
		}

		@Override
		public void close() throws SecurityException {
		}

		public void assertNoRecord() {
			assertThat(record, nullValue());

			this.record = null;
		}

		public void assertRecord(Level level, String msg) {
			assertThat(record, not(nullValue()));
			assertThat(record.getLevel(), is(level));
			assertThat(record.getMessage(), is(msg));

			this.record = null;
		}
	}
}
