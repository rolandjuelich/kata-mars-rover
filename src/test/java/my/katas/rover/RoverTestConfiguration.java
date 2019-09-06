package my.katas.rover;

import static org.mockito.Mockito.spy;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.eventbus.EventBus;

import my.katas.rover.initialize.InitializeRoverHandler;
import my.katas.rover.move.backward.MoveBackwardHandler;
import my.katas.rover.move.forward.MoveForwardHandler;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.left.TurnLeftHandler;
import my.katas.rover.turn.right.TurnRightHandler;

@Configuration
@Profile("test")
@ComponentScan(basePackages = { "my.katas.*" })
public class RoverTestConfiguration {

	@Bean
	public EventBus events() {
		return spy(new EventBus());
	}

	@Bean
	public Commands commands(final InitializeRoverHandler initialize,
			final MoveForwardHandler forward,
			final MoveBackwardHandler backward,
			final TurnRightHandler turnRight,
			final TurnLeftHandler turnLeft) {
		return spy(new Commands(
				initialize,
				forward,
				backward,
				turnRight,
				turnLeft));
	}

	@Bean
	public TerrainRepository terrains() {
		return Mockito.mock(TerrainRepository.class);
	}

	@Bean
	public RoverRepository rovers() {
		return Mockito.spy(new RoverRepository());
	}

}
