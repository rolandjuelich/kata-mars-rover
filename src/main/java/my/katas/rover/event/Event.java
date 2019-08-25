package my.katas.rover.event;

public interface Event {

	static RoverMoved roverMoved(final Integer x, final Integer y) {
		return new RoverMoved(x, y);
	}

	static RoverTurned roverTurned(final String name) {
		return new RoverTurned(name);
	}

}
