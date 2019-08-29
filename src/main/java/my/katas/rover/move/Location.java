package my.katas.rover.move;

import java.util.function.Function;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Location {

	private final int x;
	private final int y;

	public static Location location(final Integer x, final Integer y) {
		return new Location(x, y);
	}

	public Location y(final Function<Integer, Integer> function) {
		return new Location(x, function.apply(y));
	}

	public Location x(final Function<Integer, Integer> function) {
		return new Location(function.apply(x), y);
	}

}
