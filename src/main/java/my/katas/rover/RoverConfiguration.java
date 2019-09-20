package my.katas.rover;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.eventbus.EventBus;

import my.katas.command.CommandBus;
import my.katas.rover.initialize.InitializeRoverHandler;
import my.katas.rover.move.backward.MoveBackwardHandler;
import my.katas.rover.move.forward.MoveForwardHandler;
import my.katas.rover.turn.left.TurnLeftHandler;
import my.katas.rover.turn.right.TurnRightHandler;

@Configuration
@Profile("production")
@ComponentScan(basePackages = { "my.katas.*" })
public class RoverConfiguration {

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}

	@Bean
	public CommandBus commandBus(final InitializeRoverHandler initialize,
			final MoveForwardHandler forward,
			final MoveBackwardHandler backward,
			final TurnRightHandler turnRight,
			final TurnLeftHandler turnLeft) {
		return new CommandBus() //
				.register(initialize) //
				.register(forward) //
				.register(backward) //
				.register(turnRight) //
				.register(turnLeft);
	}

}
