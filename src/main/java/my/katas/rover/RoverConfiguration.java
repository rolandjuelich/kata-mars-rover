package my.katas.rover;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import my.katas.hexagonal.command.CommandBus;
import my.katas.hexagonal.event.EventBus;
import my.katas.rover.command.RoverCommandHandler;
import my.katas.rover.terrain.TerrainRepository;

@Configuration
@ComponentScan(basePackages = { "my.katas.*" })
public class RoverConfiguration {


	@Bean
	public CommandBus commandBus(final EventBus eventBus, final TerrainRepository terrains) {
		final CommandBus commandBus = new CommandBus();
		commandBus.register(RoverCommandHandler.handleMoveForward(terrains, eventBus));
		commandBus.register(RoverCommandHandler.handleMoveBackward(terrains, eventBus));
		commandBus.register(RoverCommandHandler.handleTurnRight(eventBus));
		commandBus.register(RoverCommandHandler.handleTurnLeft(eventBus));
		return commandBus;
	}

}
