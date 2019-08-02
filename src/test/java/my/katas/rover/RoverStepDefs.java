package my.katas.rover;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoverStepDefs {

	private Integer x;
	private Integer y;
	private Character heading;
	private Rover rover;

	@Given("location is at {int}, {int}")
	public void location_is_at(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}

	@Given("heading is {string}")
	public void heading_is(final String given) {
		this.heading = given.toUpperCase().charAt(0);
	}

	@When("moving forward {int} times")
	public void moving_forward(final Integer times) {
		rover = new Rover(x, y, heading).moveForward(times);
	}

	@When("moving backward {int} times")
	public void moving_backward(final Integer times) {
		rover = new Rover(x, y, heading).moveBackward(times);
	}

	@When("turning right")
	public void turning_right() {
		rover = new Rover(x, y, heading).turnRight();
	}

	@When("turning left")
	public void turning_left() {
		rover = new Rover(x, y, heading).turnLeft();
	}

	@Then("location should be {int}, {int}")
	public void location_should_be(final Integer expectedX, final Integer expectedY) {
		assertThat(rover.x()).isEqualTo(expectedX);
		assertThat(rover.y()).isEqualTo(expectedY);
	}

	@Then("heading should be {string}")
	public void heading_should_be(final String expected) {
		assertThat(rover.heading()).isEqualTo(expected);
	}

}