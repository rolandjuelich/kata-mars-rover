package my.katas.rover.command;

import my.katas.rover.command.move.backward.MoveBackward;
import my.katas.rover.command.move.forward.MoveForward;
import my.katas.rover.command.turn.left.TurnLeft;
import my.katas.rover.command.turn.right.TurnRight;

public interface Command {

	static MoveForward moveForward(final String terrain, final Integer x, final Integer y,
			final String heading) {
		return new MoveForward(terrain, x, y, heading);
	}

	static MoveBackward moveBackward(final String terrain, final Integer x, final Integer y,
			final String heading) {
		return new MoveBackward(terrain, x, y, heading);
	}

	static TurnRight turnRight(final String heading) {
		return new TurnRight(heading);
	}

	static TurnLeft turnLeft(final String heading) {
		return new TurnLeft(heading);
	}

}
