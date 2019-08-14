package my.katas.rover;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Location {

	private int x;
	private int y;

	public static Location location(final Integer x, final Integer y) {
		return new Location(x, y);
	}

}
