package my.katas.rover;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.eventbus.EventBus;

import my.katas.command.CommandBus;
import my.katas.rover.initialize.InitializeRoverHandler;
import my.katas.rover.move.backward.MoveBackwardHandler;
import my.katas.rover.move.forward.MoveForwardHandler;
import my.katas.rover.terrain.Terrain;
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
	public CommandBus commandBus(final InitializeRoverHandler initialize,
			final MoveForwardHandler forward,
			final MoveBackwardHandler backward,
			final TurnRightHandler turnRight,
			final TurnLeftHandler turnLeft) {
		return spy(new CommandBus() //
				.register(initialize) //
				.register(forward) //
				.register(backward) //
				.register(turnRight) //
				.register(turnLeft));
	}

	@Bean
	public TerrainRepository terrains() {
		TerrainRepository terrains = Mockito.mock(TerrainRepository.class);
		given(terrains.findByName("Mars")).willReturn(new Terrain("Mars", 0, 99, 0, 99));
		return terrains;
	}

	@Bean
	public RoverRepository rovers() {
		return Mockito.spy(new RoverRepository());
	}

}
