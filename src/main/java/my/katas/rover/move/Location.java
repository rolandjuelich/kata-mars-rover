package my.katas.rover.move;

import java.util.function.Function;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Location {

	public static final Location ZERO = new Location(0, 0);
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
