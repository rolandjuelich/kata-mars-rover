package my.katas.rover;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.katas.command.CommandProcessor;
import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.RoverTurned;
import my.katas.rover.turn.left.TurnLeft;
import my.katas.rover.turn.right.TurnRight;

@SpringBootTest
@ActiveProfiles("test")
public class RoverStepDefs {

	@Autowired
	private CommandProcessor commands;

	@Autowired
	private TerrainRepository terrains;

	private Integer actualX;
	private Integer actualY;
	private String actualHeading;
	private String terrain;

	@Before
	public void beforeSceanrio() {
		when(terrains.findByName(any())).thenReturn(new Terrain("NoWorld", 0, 0, 0, 0));
	}

	@Given("target terrain is {string}")
	public void the_target_terrain_is(final String name) {
		this.terrain = name;
	}

	@Given("following terrains")
	public void following_terrains(final DataTable table) {

		final int fromRow = 1;

		table.rows(fromRow).asLists().forEach(row -> {

			final String name = row.get(0);
			final Integer minX = Integer.valueOf(row.get(1));
			final Integer maxX = Integer.valueOf(row.get(2));
			final Integer minY = Integer.valueOf(row.get(3));
			final Integer maxY = Integer.valueOf(row.get(4));

			final Terrain terrain = new Terrain(name, minX, maxX, minY, maxY);
			when(terrains.findByName(name)).thenReturn(terrain);

		});
	}

	@Given("rover is heading {string}")
	public void rover_is_heading(final String heading) {
		final InitializeRover command = Commands.initialize(terrain, 0, 0, heading);
		final Class<RoverInitialized> event = RoverInitialized.class;
		updateFrom(commands.process(command, event).recent(event));
	}

	@Given("rover is heading {string} at {int}, {int}")
	public void rover_is_heading_at(final String heading, final Integer x, final Integer y) {
		final InitializeRover command = Commands.initialize(terrain, x, y, heading);
		final Class<RoverInitialized> event = RoverInitialized.class;
		updateFrom(commands.process(command).recent(event));
	}

	@When("rover moves forward {int} times")
	public void rover_moves_forward_times(final Integer times) {
		final MoveForward command = Commands.moveForward();
		final Class<RoverMoved> event = RoverMoved.class;
		range(0, times).forEach(c -> {
			updateFrom(commands.process(command).recent(event));
		});
	}

	@When("rover moves backward {int} times")
	public void rover_moves_backward_times(final Integer times) {
		final MoveBackward command = Commands.moveBackward();
		final Class<RoverMoved> event = RoverMoved.class;
		range(0, times).forEach(c -> {
			updateFrom(commands.process(command).recent(event));
		});
	}

	@When("rover turns right")
	public void rover_turns_right() {
		final Class<RoverTurned> event = RoverTurned.class;
		final TurnRight command = Commands.turnRight();
		updateFrom(commands.process(command).recent(event));
	}

	@When("rover turns left")
	public void rover_turns_left() {
		final Class<RoverTurned> event = RoverTurned.class;
		final TurnLeft command = Commands.turnLeft();
		updateFrom(commands.process(command, event).recent(event));
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

	private void updateFrom(final RoverInitialized event) {
		actualX = event.getX();
		actualY = event.getY();
		actualHeading = event.getHeading();
	}

	private void updateFrom(final RoverMoved event) {
		actualX = event.getX();
		actualY = event.getY();
	}

	private void updateFrom(final RoverTurned event) {
		actualHeading = event.getHeading();
	}

}