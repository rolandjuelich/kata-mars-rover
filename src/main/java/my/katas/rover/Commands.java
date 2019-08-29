package my.katas.rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

import my.katas.rover.initialize.InitializeHandler;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.backward.MoveBackwardHandler;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.move.forward.MoveForwardHandler;
import my.katas.rover.turn.left.TurnLeft;
import my.katas.rover.turn.left.TurnLeftHandler;
import my.katas.rover.turn.right.TurnRight;
import my.katas.rover.turn.right.TurnRightHandler;

@Component
public class Commands {

	private final EventBus commandBus = new EventBus();

	@Autowired
	public Commands(final InitializeHandler initialize, final MoveForwardHandler forward,
			final MoveBackwardHandler backward,
			final TurnRightHandler turnRight,
			final TurnLeftHandler turnLeft) {
		commandBus.register(initialize);
		commandBus.register(forward);
		commandBus.register(backward);
		commandBus.register(turnRight);
		commandBus.register(turnLeft);
	}

	public void execute(final Object command) {
		this.commandBus.post(command);
	}

	public static TurnLeft turnLeftFrom(final String heading) {
		return new TurnLeft(heading);
	}

	public static TurnRight turnRightFrom(final String heading) {
		return new TurnRight(heading);
	}

	public static MoveBackward moveBackwardFrom(final Integer x, final Integer y, final String heading,
			final String terrain) {
		return new MoveBackward(terrain, x, y, heading);
	}

	public static MoveForward moveForwardFrom(final Integer x, final Integer y, final String heading,
			final String terrain) {
		return new MoveForward(terrain, x, y, heading);
	}

}
