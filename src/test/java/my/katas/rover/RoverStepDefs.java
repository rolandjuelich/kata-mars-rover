package my.katas.rover;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoverStepDefs {

	private Application rover;

	private int currentX;
	private int currentY;
	private char currentHeading;

	@Before
	public void beforeSceanrio() {
		rover = new Application();
		rover.register(new RoverStateChangedListener() {

			@Override
			public void notifyThat(final RoverStateChanged event) {
				currentX = event.getX();
				currentY = event.getY();
				currentHeading = event.getHeading();
			}
		});
	}

	@Given("rover is heading {string}")
	public void rover_is_heading(final String heading) {
		rover.initialize(0, 0, heading.toUpperCase().charAt(0));
	}

	@Given("rover is heading {string} at {int}, {int}")
	public void rover_is_heading_at(final String heading, final Integer x, final Integer y) {
		rover.initialize(x, y, heading.toUpperCase().charAt(0));
	}

	@When("rover moves forward {int} times")
	public void rover_moves_forward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			rover.moveForward();
		}
	}

	@When("rover moves backward {int} times")
	public void rover_moves_backward_times(final Integer times) {
		for (int i = 0; i < times; i++) {
			rover.moveBackward();
		}
	}

	@When("rover turns right")
	public void rover_turns_right() {
		rover.turnRight();
	}

	@When("rover turns left")
	public void rover_turns_left() {
		rover.turnLeft();
	}

	@Then("rover should be heading {string}")
	public void rover_should_be_heading(final String expected) {
		assertThat(currentHeading()).isEqualTo(expected);
	}

	@Then("rover should be heading {string} at {int}, {int}")
	public void rover_should_be_heading_at(final String heading, final Integer x, final Integer y) {
		assertThat(currentX).describedAs("expected x").isEqualTo(x);
		assertThat(currentY).describedAs("expected y").isEqualTo(y);
		assertThat(currentHeading()).describedAs("expected heading").isEqualTo(heading.toLowerCase());
	}

	private String currentHeading() {
		switch (currentHeading) {
		case 'N':
			return "north";
		case 'E':
			return "east";
		case 'S':
			return "south";
		case 'W':
			return "west";
		default:
			return null;
		}
	}

}