package my.katas.rover;

import my.katas.rover.state.RoverEast;
import my.katas.rover.state.RoverNorth;
import my.katas.rover.state.RoverSouth;
import my.katas.rover.state.RoverWest;

public interface Locatable {

	int x();

	int y();

	String heading();

	public static Locatable of(final Integer x, final Integer y, final Character heading) {
		switch (Character.toUpperCase(heading)) {
		case 'N':
			return new RoverNorth(x, y);
		case 'E':
			return new RoverEast(x, y);
		case 'S':
			return new RoverSouth(x, y);
		case 'W':
			return new RoverWest(x, y);
		default:
			return null;
		}

	}

}