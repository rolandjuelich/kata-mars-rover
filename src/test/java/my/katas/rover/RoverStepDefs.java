package my.katas.rover;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoverStepDefs {

	private Rover rover;

	@Given("rover is heading {string}")
	public void rover_is_heading(final String heading) {
		this.rover = new Rover(0, 0, heading.toUpperCase().charAt(0));
	}

	@Given("rover is heading {string} at {int}, {int}")
	public void rover_is_heading_at(final String heading, final Integer x, final Integer y) {
		this.rover = new Rover(x, y, heading.toUpperCase().charAt(0));
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
		assertThat(rover.heading().toLowerCase()).isEqualTo(expected);
	}

	@Then("rover should be heading {string} at {int}, {int}")
	public void rover_should_be_heading_at(final String heading, final Integer x, final Integer y) {
		assertThat(rover.x()).describedAs("expected x").isEqualTo(x);
		assertThat(rover.y()).describedAs("expected y").isEqualTo(y);
		assertThat(rover.heading().toLowerCase()).describedAs("expected heading").isEqualTo(heading.toLowerCase());
	}

}