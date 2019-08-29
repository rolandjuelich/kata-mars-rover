package my.katas.rover;

import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.turn.RoverTurned;

public interface Events {

	static RoverMoved movedTo(final Integer x, final Integer y) {
		return new RoverMoved(x, y);
	}

	static RoverTurned turnedTo(final String name) {
		return new RoverTurned(name);
	}

	static RoverInitialized initializedAt(final Integer x, final Integer y, final String name) {
		return new RoverInitialized(x, y, name);
	}

}
