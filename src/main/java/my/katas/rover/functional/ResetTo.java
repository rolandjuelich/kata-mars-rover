package my.katas.rover.functional;

import java.util.function.Function;
import java.util.function.Predicate;

public class ResetTo implements Function<Integer, Integer> {

	private final Integer reset;

	private final Predicate<Integer> condition;

	private ResetTo(final Integer value, final Predicate<Integer> predicate) {
		this.reset = value;
		this.condition = predicate;
	}

	public static ResetTo resetTo(final Integer value, final Predicate<Integer> predicate) {
		return new ResetTo(value, predicate);
	}

	public static ResetTo resetTo(final Integer value) {
		return new ResetTo(value, t -> true);
	}

	public ResetTo when(final Predicate<Integer> predicate) {
		return new ResetTo(reset, predicate);
	}

	@Override
	public Integer apply(final Integer actual) {
		return condition.test(actual) ? reset : actual;
	}

}