package my.katas.rover;

import static my.katas.rover.Heading.valueOf;
import static my.katas.rover.Location.location;
import static my.katas.rover.Rover.landingOn;

public class Application {

	private RoverStateChangedListener listener;
	private Rover rover;

	public void initialize(int x, int y, String heading) {
		final Terrain terrain = new Terrain(0, 99, 0, 99);
		final Heading direction = valueOf(heading.toUpperCase());
		final Location initialPosition = location(x, y);

		rover = landingOn(terrain).heading(direction).startFrom(initialPosition);

		listener.notifyThat(roverHasChanged());
	}

	public void moveForward() {
		rover.moveForward();
		listener.notifyThat(roverHasChanged());
	}

	public void moveBackward() {
		rover.moveBackward();
		listener.notifyThat(roverHasChanged());
	}

	public void turnLeft() {
		rover.turnLeft();
		listener.notifyThat(roverHasChanged());
	}

	public void turnRight() {
		rover.turnRight();
		listener.notifyThat(roverHasChanged());
	}

	public void register(final RoverStateChangedListener handler) {
		this.listener = handler;
	}

	private RoverStateChanged roverHasChanged() {
		return new RoverStateChanged(rover.x(), rover.y(), rover.heading());
	}

}
