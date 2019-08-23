package my.katas.rover.commands;

import com.google.common.eventbus.EventBus;

import my.katas.rover.commands.move.backward.MoveBackward;
import my.katas.rover.commands.move.backward.MoveBackwardHandler;
import my.katas.rover.commands.move.forward.MoveForward;
import my.katas.rover.commands.move.forward.MoveForwardHandler;
import my.katas.rover.commands.turn.left.TurnLeft;
import my.katas.rover.commands.turn.left.TurnLeftHandler;
import my.katas.rover.commands.turn.right.TurnRight;
import my.katas.rover.commands.turn.right.TurnRightHandler;
import my.katas.rover.model.terrain.TerrainRepository;

public interface CommandHandler<C> {

	void handle(final C command);

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
