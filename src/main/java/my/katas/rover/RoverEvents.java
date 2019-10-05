package my.katas.rover;

import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.turn.RoverTurned;

public class RoverEvents {

	public static RoverMoved movedTo(final Integer x, final Integer y) {
		return new RoverMoved(x, y);
	}

	public static RoverTurned turnedTo(final String heading) {
		return new RoverTurned(heading);
	}

	public static RoverInitialized initializedAt(final Integer x, final Integer y, final String heading) {
		return new RoverInitialized(x, y, heading, "willi");
	}

	public static RoverInitialized initialized(final String roverId, final Integer x, final Integer y,
			final String heading) {
		return new RoverInitialized(x, y, heading, roverId);
	}

}
