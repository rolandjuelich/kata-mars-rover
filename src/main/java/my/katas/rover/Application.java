package my.katas.rover;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import my.katas.rover.commands.backward.MoveBackward;
import my.katas.rover.commands.backward.MoveBackwardHandler;
import my.katas.rover.commands.forward.MoveForward;
import my.katas.rover.commands.forward.MoveForwardHandler;
import my.katas.rover.commands.turnleft.TurnLeft;
import my.katas.rover.commands.turnleft.TurnLeftHandler;
import my.katas.rover.commands.turnright.TurnRight;
import my.katas.rover.commands.turnright.TurnRightHandler;
import my.katas.rover.model.terrain.TerrainRepository;

@AllArgsConstructor
public class Application {

	private final TerrainRepository terrains;
	private final EventBus eventBus;

	public void handle(final MoveForward command) {
		new MoveForwardHandler(terrains, eventBus).handle(command);
	}

	public void handle(final MoveBackward command) {
		new MoveBackwardHandler(terrains, eventBus).handle(command);
	}

	public void handle(final TurnLeft command) {
		new TurnLeftHandler(eventBus).handle(command);
	}

	public void handle(final TurnRight command) {
		new TurnRightHandler(eventBus).handle(command);
	}

}
