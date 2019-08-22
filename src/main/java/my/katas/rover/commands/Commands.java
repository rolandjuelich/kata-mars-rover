package my.katas.rover.commands;

import my.katas.rover.commands.backward.MoveBackward;
import my.katas.rover.commands.forward.MoveForward;
import my.katas.rover.commands.turnleft.TurnLeft;
import my.katas.rover.commands.turnright.TurnRight;

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
