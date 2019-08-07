package my.katas.rover;

import my.katas.rover.command.Initialize;
import my.katas.rover.command.MoveBackward;
import my.katas.rover.command.MoveForward;
import my.katas.rover.command.TurnLeft;
import my.katas.rover.command.TurnRight;

public class Application {

	private RoverStateChangedListener listener;
	private Rover rover;

	public void execute(final Initialize command) {
		rover = new Rover(command.getX(), command.getY(), command.getHeading());
		listener.notifyThat(roverHasChanged());
	}

	public void execute(final MoveForward command) {
		rover.moveForward();
		listener.notifyThat(roverHasChanged());
	}

	public void execute(final MoveBackward command) {
		rover.moveBackward();
		listener.notifyThat(roverHasChanged());
	}

	public void execute(final TurnLeft command) {
		rover.turnLeft();
		listener.notifyThat(roverHasChanged());
	}

	public void execute(final TurnRight command) {
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
