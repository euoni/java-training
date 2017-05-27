package jpl.ch22.ex15;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Calculator {
	static Map<String, Double> consts = new HashMap<>();
	static Map<String, UnaryOperator<Double>> unaryOps = new HashMap<>();
	static Map<String, BinaryOperator<Double>> binaryOps = new HashMap<>();

	static {
		consts.put("E", Math.E);
		consts.put("PI", Math.PI);
		binaryOps.put("+", (x, y) -> x + y);
		binaryOps.put("-", (x, y) -> x - y);
		binaryOps.put("*", (x, y) -> x * y);
		binaryOps.put("/", (x, y) -> x / y);
		binaryOps.put("%", (x, y) -> x % y);
		unaryOps.put("sin", Math::sin);
		unaryOps.put("cos", Math::cos);
		unaryOps.put("tan", Math::tan);
		unaryOps.put("asin", Math::asin);
		unaryOps.put("acos", Math::acos);
		unaryOps.put("atan", Math::atan);
		binaryOps.put("atan2", Math::atan2);
		unaryOps.put("toRadians", Math::toRadians);
		unaryOps.put("toDegrees", Math::toDegrees);
		unaryOps.put("exp", Math::exp);
		unaryOps.put("sinh", Math::sinh);
		unaryOps.put("cosh", Math::cosh);
		unaryOps.put("tanh", Math::tanh);
		binaryOps.put("pow", Math::pow);
		unaryOps.put("log", Math::log);
		unaryOps.put("log10", Math::log10);
		unaryOps.put("sqrt", Math::sqrt);
		unaryOps.put("cbrt", Math::cbrt);
		unaryOps.put("signum", Math::signum);
		unaryOps.put("ceil", Math::ceil);
		unaryOps.put("floor", Math::floor);
		unaryOps.put("rint", Math::rint);
		unaryOps.put("round", x -> (double) Math.round(x));
		unaryOps.put("abs", Math::abs);
		binaryOps.put("max", Math::max);
		binaryOps.put("min", Math::min);
		binaryOps.put("hypot", Math::hypot);
	}

	public static double calc(String str) {
		final Deque<Double> stack = new LinkedList<>();
		for (final String token : str.split("\\s+")) {
			try {
				stack.push(Double.valueOf(token));
			} catch (final NumberFormatException e) {
				if (consts.containsKey(token)) {
					stack.push(consts.get(token));
				} else if (unaryOps.containsKey(token)) {
					final Double x = stack.pop();
					final Double ret = unaryOps.get(token).apply(x);
					stack.push(ret);
				} else if (binaryOps.containsKey(token)) {
					final Double y = stack.pop();
					final Double x = stack.pop();
					final Double ret = binaryOps.get(token).apply(x, y);
					stack.push(ret);
				} else {
					throw new IllegalStateException();
				}
			}
		}

		if (stack.size() != 1)
			throw new IllegalStateException();

		return stack.pop();
	}
}
