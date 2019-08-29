package my.katas.rover;

import my.katas.rover.move.RoverMoved;
import my.katas.rover.turn.RoverTurned;

public interface Events {

	static RoverMoved roverMoved(final Integer x, final Integer y) {
		return new RoverMoved(x, y);
	}

	static RoverTurned roverTurned(final String name) {
		return new RoverTurned(name);
	}

}
