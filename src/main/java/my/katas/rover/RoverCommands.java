package my.katas.rover;

import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.turn.Heading;
import my.katas.rover.turn.left.TurnLeft;
import my.katas.rover.turn.right.TurnRight;

public class RoverCommands {

	public static InitializeRover initialize(final String terrainId, final Integer x, final Integer y,
			final String heading) {
		return new InitializeRover(terrainId, x, y, heading);
	}

	public static InitializeRover initialize(final String terrainId, final String heading) {
		return new InitializeRover(terrainId, 0, 0, heading);
	}

	public static InitializeRover initialize(final String terrainId) {
		return new InitializeRover(terrainId, 0, 0, Heading.NORTH.name());
	}

	public static TurnLeft turnLeft(final String roverId) {
		return new TurnLeft(roverId);
	}

	public static TurnRight turnRight(final String roverId) {
		return new TurnRight(roverId);
	}

	public static MoveBackward moveBackward(final String roverId) {
		return new MoveBackward(roverId);
	}

	public static MoveForward moveForward(final String roverId) {
		return new MoveForward(roverId);
	}

}
