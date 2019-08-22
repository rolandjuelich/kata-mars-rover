package my.katas.rover.commands;

import my.katas.rover.commands.move.backward.MoveBackward;
import my.katas.rover.commands.move.forward.MoveForward;
import my.katas.rover.commands.turn.left.TurnLeft;
import my.katas.rover.commands.turn.right.TurnRight;

public class Commands {

	public static MoveForward moveForward(final String terrain, final Integer x, final Integer y,
			final String heading) {
		return new MoveForward(terrain, x, y, heading);
	}

	public static MoveBackward moveBackward(final String terrain, final Integer x, final Integer y,
			final String heading) {
		return new MoveBackward(terrain, x, y, heading);
	}

	public static TurnRight turnRight(final String heading) {
		return new TurnRight(heading);
	}

	public static TurnLeft turnLeft(final String heading) {
		return new TurnLeft(heading);
	}

}
