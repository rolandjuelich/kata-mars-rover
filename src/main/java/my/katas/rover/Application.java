package my.katas.rover;

public class Application {

	private RoverStateChangedListener listener;
	private Rover rover;

	public void initialize(int x, int y, char heading) {
		rover = new Rover(x, y, heading);
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
		char heading = rover.heading().toUpperCase().charAt(0);
		return new RoverStateChanged(rover.x(), rover.y(), heading);
	}

}
