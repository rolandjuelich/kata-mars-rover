package my.katas.rover.functional;

import java.util.function.Function;

public class Functions {

	public static Function<Integer, Integer> increase() {
		return value -> value + 1;
	}

	public static Function<Integer, Integer> decrease() {
		return t -> t - 1;
	}

	public static ResetTo resetTo(final Integer value) {
		return ResetTo.resetTo(value);
	}

}