package my.katas.rover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

import my.katas.rover.initialize.InitializeRoverHandler;
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
	public Commands(final InitializeRoverHandler initialize, final MoveForwardHandler forward,
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
