package my.katas.rover.events;

public class Events {

	public static RoverMoved roverMoved(final Integer x, final Integer y) {
		return new RoverMoved(x, y);
	}

	public static RoverTurned roverTurned(final String name) {
		return new RoverTurned(name);
	}

}
