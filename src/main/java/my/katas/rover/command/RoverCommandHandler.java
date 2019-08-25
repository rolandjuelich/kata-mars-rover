package my.katas.rover.command;

import my.katas.hexagonal.command.CommandHandler;
import my.katas.hexagonal.event.EventBus;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.backward.MoveBackwardHandler;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.move.forward.MoveForwardHandler;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.left.TurnLeft;
import my.katas.rover.turn.left.TurnLeftHandler;
import my.katas.rover.turn.right.TurnRight;
import my.katas.rover.turn.right.TurnRightHandler;

public interface RoverCommandHandler<C extends RoverCommand> extends CommandHandler<C> {

	static CommandHandler<MoveForward> handleMoveForward(final TerrainRepository terrains, final EventBus eventBus) {
		return new MoveForwardHandler(terrains, eventBus);
	}

	static CommandHandler<MoveBackward> handleMoveBackward(final TerrainRepository terrains, final EventBus eventBus) {
		return new MoveBackwardHandler(terrains, eventBus);
	}

	static CommandHandler<TurnRight> handleTurnRight(final EventBus eventBus) {
		return new TurnRightHandler(eventBus);
	}

	static CommandHandler<TurnLeft> handleTurnLeft(final EventBus eventBus) {
		return new TurnLeftHandler(eventBus);
	}

}
