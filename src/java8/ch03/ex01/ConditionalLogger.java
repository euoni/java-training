package java8.ch03.ex01;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConditionalLogger {
	private final Logger base;

	public ConditionalLogger(Logger base) {
		this.base = base;
	}

	public void logIf(Level level, Supplier<String> msgSupplier, BooleanSupplier condition) {
		if (!base.isLoggable(level) || !condition.getAsBoolean())
			return;

		base.log(level, msgSupplier);
	}
}
