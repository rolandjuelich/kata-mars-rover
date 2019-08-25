package my.katas.rover.event;

import my.katas.hexagonal.event.Event;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.turn.RoverTurned;

public interface RoverEvent extends Event {

	static RoverMoved roverMoved(final Integer x, final Integer y) {
		return new RoverMoved(x, y);
	}

	static RoverTurned roverTurned(final String name) {
		return new RoverTurned(name);
	}

}
