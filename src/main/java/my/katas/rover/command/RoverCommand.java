package my.katas.rover.command;

import my.katas.hexagonal.command.Command;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.turn.left.TurnLeft;
import my.katas.rover.turn.right.TurnRight;

public interface RoverCommand extends Command {

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
