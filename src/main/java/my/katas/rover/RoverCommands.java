package my.katas.rover;

import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.turn.Heading;
import my.katas.rover.turn.left.TurnLeft;
import my.katas.rover.turn.right.TurnRight;

public class RoverCommands {

	public static InitializeRover initialize(final String terrain, final Integer x, final Integer y) {
		return new InitializeRover(terrain, x, y, Heading.NORTH.name());
	}

	public static InitializeRover initialize(final String terrain, final Integer x, final Integer y,
			final String heading) {
		return new InitializeRover(terrain, x, y, heading);
	}

	public static TurnLeft turnLeft() {
		return new TurnLeft();
	}

	public static TurnRight turnRight() {
		return new TurnRight();
	}

	public static MoveBackward moveBackward() {
		return new MoveBackward();
	}

	public static MoveForward moveForward() {
		return new MoveForward();
	}

}
