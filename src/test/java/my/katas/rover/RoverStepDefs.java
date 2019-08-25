package my.katas.rover;

import static my.katas.rover.command.Command.moveBackward;
import static my.katas.rover.command.Command.moveForward;
import static my.katas.rover.command.Command.turnLeft;
import static my.katas.rover.command.Command.turnRight;
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
import my.katas.rover.command.CommandBus;
import my.katas.rover.configuration.MockedRepositoryConfiguration;
import my.katas.rover.event.RoverMoved;
import my.katas.rover.event.RoverTurned;
import my.katas.rover.model.terrain.Terrain;
import my.katas.rover.model.terrain.TerrainRepository;

@ContextConfiguration(classes = { ApplicationConfiguration.class, MockedRepositoryConfiguration.class })
public class RoverStepDefs {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private CommandBus commandBus;

	@Autowired
	private TerrainRepository terrains;

	private EventStore store;

	private Integer actualX;
	private Integer actualY;
	private String actualHeading;
	private String terrain;

	@Before
	public void beforeSceanrio() {
		store = new EventStore(eventBus);
		eventBus.register(this);
	}

	@Subscribe
	public void handle(final RoverTurned event) {
		this.actualHeading = event.getHeading();
	}

	@Subscribe
	public void handle(final RoverMoved event) {
		this.actualX = event.getX();
		this.actualY = event.getY();
	}

	@Given("the terrain on {string} has following dimensions")
	public void the_terrain_on_has_following_dimensions(final String name, final DataTable table) {
		this.terrain = name;

		List<Map<String, String>> data = table.asMaps();
		int minX = Integer.valueOf(data.get(0).get("min"));
		int maxX = Integer.valueOf(data.get(0).get("max"));
		int minY = Integer.valueOf(data.get(1).get("min"));
		int maxY = Integer.valueOf(data.get(1).get("max"));

		when(terrains.findByName(name)).thenReturn(new Terrain(name, minX, maxX, minY, maxY));

	}

	@Given("rover is heading {string}")
	public void rover_is_heading(final String heading) {
		this.actualX = 0;
		this.actualY = 0;
		this.actualHeading = heading;
	}

	@Given("rover is heading {string} at {int}, {int}")
	public void rover_is_heading_at(final String heading, final Integer x, final Integer y) {
		this.actualX = x;
		this.actualY = y;
		this.actualHeading = heading;

	}

	@When("rover moves forward {int} times")
	public void rover_moves_forward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			commandBus.post(moveForward(terrain, actualX, actualY, actualHeading));
		}
		assertThat(store.allOf(RoverMoved.class)).hasSize(times);
	}

	@When("rover moves backward {int} times")
	public void rover_moves_backward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			commandBus.post(moveBackward(terrain, actualX, actualY, actualHeading));
		}
		assertThat(store.allOf(RoverMoved.class)).hasSize(times);
	}

	@When("rover turns right")
	public void rover_turns_right() {
		commandBus.post(turnRight(actualHeading));
	}

	@When("rover turns left")
	public void rover_turns_left() {
		commandBus.post(turnLeft(actualHeading));
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

		private EventStore(final EventBus bus) {
			bus.register(this);
		}

		@Subscribe
		public void handle(final RoverTurned event) {
			this.events.add(event);
		}

		@Subscribe
		public void handle(final RoverMoved event) {
			this.events.add(event);
		}

		public List<?> allOf(final Class<?> type) {
			return events.stream().filter(o -> o.getClass().isAssignableFrom(type)).collect(Collectors.toList());
		}

	}
}