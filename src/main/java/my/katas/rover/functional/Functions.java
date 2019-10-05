package my.katas.rover.functional;

import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;

import java.util.function.Function;

import my.katas.rover.terrain.Dimension;

public class Functions {

	public static Function<Integer, Integer> increaseByOne() {
		return value -> ++value;
	}

	public static Function<Integer, Integer> decreaseByOne() {
		return value -> --value;
	}

	public static ResetTo resetTo(final Integer value) {
		return ResetTo.resetTo(value);
	}

	public static Function<Integer, Integer> increaseByOne(final Dimension axis) {
		return increaseByOne().andThen(resetTo(axis.getMin()).when(greaterThan(axis.getMax())));
	}

	public static Function<Integer, Integer> decreaseByOne(final Dimension axis) {
		return decreaseByOne().andThen(resetTo(axis.getMax()).when(smallerThan(axis.getMin())));
	}

}