package my.katas.rover.functional;

import java.util.function.Predicate;

public class Predicates {

	public static Predicate<Integer> greaterThan(int max) {
		return t -> t > max;
	}

	public static Predicate<Integer> smallerThan(int min) {
		return t -> t < min;
	}

}
