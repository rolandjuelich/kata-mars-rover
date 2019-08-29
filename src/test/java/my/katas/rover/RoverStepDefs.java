package my.katas.rover;

import static my.katas.rover.Commands.moveBackwardFrom;
import static my.katas.rover.Commands.moveForwardFrom;
import static my.katas.rover.Commands.turnLeftFrom;
import static my.katas.rover.Commands.turnRightFrom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.RoverTurned;

@ContextConfiguration(classes = { RoverConfiguration.class, MockedRepositoryConfiguration.class })
public class RoverStepDefs {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private Commands commands;

	@Autowired
	private TerrainRepository terrains;

	private EventStore store;

	private Integer actualX;
	private Integer actualY;
	private String actualHeading;
	private String terrain;

	@Subscribe
	public void listenFor(final RoverTurned event) {
		actualHeading = event.getHeading();
	}

	@Subscribe
	public void listenFor(final RoverMoved event) {
		actualX = event.getX();
		actualY = event.getY();
	}

	@Subscribe
	public void listenFor(final RoverInitialized event) {
		actualX = event.getX();
		actualY = event.getY();
		actualHeading = event.getHeading();
	}

	@Before
	public void beforeSceanrio() {
		store = new EventStore(eventBus);
		eventBus.register(this);
	}

	@Given("the terrain on {string} has following dimensions")
	public void the_terrain_on_has_following_dimensions(final String name, final DataTable table) {
		this.terrain = name;

		final List<Map<String, String>> data = table.asMaps();
		final int minX = Integer.valueOf(data.get(0).get("min"));
		final int maxX = Integer.valueOf(data.get(0).get("max"));
		final int minY = Integer.valueOf(data.get(1).get("min"));
		final int maxY = Integer.valueOf(data.get(1).get("max"));

		when(terrains.findByName(name)).thenReturn(new Terrain(name, minX, maxX, minY, maxY));

	}

	@Given("rover is heading {string}")
	public void rover_is_heading(final String heading) {
		commands.execute(new InitializeRover(0, 0, heading));
	}

	@Given("rover is heading {string} at {int}, {int}")
	public void rover_is_heading_at(final String heading, final Integer x, final Integer y) {
		commands.execute(new InitializeRover(x, y, heading));
	}

	@When("rover moves forward {int} times")
	public void rover_moves_forward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			commands.execute(moveForwardFrom(actualX, actualY, actualHeading, terrain));
		}
		assertThat(store.allOf(RoverMoved.class)).hasSize(times);
	}

	@When("rover moves backward {int} times")
	public void rover_moves_backward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			commands.execute(moveBackwardFrom(actualX, actualY, actualHeading, terrain));
		}
		assertThat(store.allOf(RoverMoved.class)).hasSize(times);
	}

	@When("rover turns right")
	public void rover_turns_right() {
		commands.execute(turnRightFrom(actualHeading));
	}

	@When("rover turns left")
	public void rover_turns_left() {
		commands.execute(turnLeftFrom(actualHeading));
	}

	@Then("rover should be heading {string}")
	public void rover_should_be_heading(final String expected) {
		assertThat(actualHeading).isEqualToIgnoringCase(expected);
	}

	@Then("rover should be heading {string} at {int}, {int}")
	public void rover_should_be_heading_at(final String expectedHeading, final Integer expectedX,
			final Integer expectedY) {
		assertThat(actualX).describedAs("x").isEqualTo(expectedX);
		assertThat(actualY).describedAs("y").isEqualTo(expectedY);
		assertThat(actualHeading).describedAs("heading").isEqualToIgnoringCase(expectedHeading);
	}

	private class EventStore {

		private final List<Object> events = new ArrayList<>();

		@Subscribe
		public void listenFor(final RoverTurned event) {
			events.add(event);
		}

		@Subscribe
		public void listenFor(final RoverMoved event) {
			events.add(event);
		}

		private EventStore(final EventBus bus) {
			bus.register(this);
		}

		public List<?> allOf(final Class<?> type) {
			return events.stream().filter(o -> o.getClass().isAssignableFrom(type)).collect(Collectors.toList());
		}

	}
}