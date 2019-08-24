package my.katas.rover;

import static my.katas.rover.commands.CommandHandler.handleMoveBackward;
import static my.katas.rover.commands.CommandHandler.handleMoveForward;
import static my.katas.rover.commands.CommandHandler.handleTurnLeft;
import static my.katas.rover.commands.CommandHandler.handleTurnRight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

import my.katas.rover.commands.CommandBus;
import my.katas.rover.model.terrain.TerrainRepository;

@Configuration
@ComponentScan(basePackages = { "my.katas.rover.*" })
public class ApplicationConfiguration {

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}

	@Bean
	public CommandBus commandBus(final EventBus eventBus, final TerrainRepository terrains) {
		final CommandBus commandBus = new CommandBus();
		commandBus.register(handleMoveForward(terrains, eventBus));
		commandBus.register(handleMoveBackward(terrains, eventBus));
		commandBus.register(handleTurnRight(eventBus));
		commandBus.register(handleTurnLeft(eventBus));
		return commandBus;
	}

}
