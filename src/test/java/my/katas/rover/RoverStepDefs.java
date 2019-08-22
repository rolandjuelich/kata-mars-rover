package my.katas.rover;

import static my.katas.rover.commands.Commands.moveBackward;
import static my.katas.rover.commands.Commands.moveForward;
import static my.katas.rover.commands.Commands.turnLeft;
import static my.katas.rover.commands.Commands.turnRight;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.google.common.eventbus.Subscribe;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.katas.rover.events.EventBus;
import my.katas.rover.events.RoverMoved;
import my.katas.rover.events.RoverTurned;
import my.katas.rover.model.terrain.Terrain;
import my.katas.rover.model.terrain.TerrainRepository;

public class RoverStepDefs {

	private List<Object> events = new ArrayList<>();

	private EventBus eventBus;
	private com.google.common.eventbus.EventBus guava;
	private TerrainRepository terrains;
	private Application application;

	private int actualX;
	private int actualY;
	private String actualHeading;
	private String terrain;

	@Before
	public void beforeSceanrio() {
		
		terrains = mock(TerrainRepository.class);

		eventBus = new EventBus();
		eventBus.forEvery(RoverMoved.class)
				.notify(event -> {
					handle(event);
				});

		eventBus.forEvery(RoverTurned.class)
				.notify(event -> {
					handle(event);
				});

		guava = new com.google.common.eventbus.EventBus();
		guava.register(this);

		application = new Application(eventBus, terrains, guava);

	}

	@Subscribe
	public void handle(final RoverTurned event) {
		this.actualHeading = event.getHeading();
		this.events.add(event);
	}

	@Subscribe
	public void handle(final RoverMoved event) {
		this.actualX = event.getX();
		this.actualY = event.getY();
		this.events.add(event);
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
			application.handle(moveForward(terrain, actualX, actualY, actualHeading));
		}
		assertThat(events).filteredOn(type(RoverMoved.class)).hasSize(times);

	}

	@When("rover moves backward {int} times")
	public void rover_moves_backward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			application.handle(moveBackward(terrain, actualX, actualY, actualHeading));
		}
		assertThat(events).filteredOn(type(RoverMoved.class)).hasSize(times);
	}

	@When("rover turns right")
	public void rover_turns_right() {
		application.handle(turnRight(actualHeading));
	}

	@When("rover turns left")
	public void rover_turns_left() {
		application.handle(turnLeft(actualHeading));
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

	private static Predicate<? super Object> type(final Class<RoverMoved> type) {
		return o -> o.getClass().isAssignableFrom(type);
	}
}