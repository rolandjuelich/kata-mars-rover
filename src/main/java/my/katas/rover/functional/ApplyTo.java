package my.katas.rover.functional;

import java.util.function.Function;

public class ApplyTo {

	private final Integer value;
	private final Integer times;

	private ApplyTo(final Integer value) {
		this.value = value;
		this.times = 1;
	}

	public ApplyTo(final Integer value, final Integer times) {
		this.value = value;
		this.times = times;
	}

	public static ApplyTo applyTo(final Integer value) {
		return new ApplyTo(value);
	}

	public ApplyTo times(int times) {
		return new ApplyTo(value, times);
	}

	public Integer apply(final Function<Integer, Integer> function) {
		Integer result = value;
		for (int i = 0; i < times; i++) {
			result = function.apply(result);
		}
		return result;
	};
}