package my.katas.rover;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoverStepDefs {

	@Given("location is at {int}, {int}")
	public void location_is_at(final Integer x, final Integer y) {
		throw new PendingException();
	}

	@Given("heading is {string}")
	public void heading_is(final String given) {
		throw new PendingException();
	}

	@When("moving forward times {int}")
	public void executing_command_forward_times(Integer times) {
		throw new PendingException();
	}

	@When("moving backward times {int}")
	public void moving_backward_times(Integer times) {
		throw new PendingException();
	}

	@When("turning right")
	public void turning_right() {
		throw new PendingException();
	}

	@When("turning left")
	public void turning_left() {
		throw new PendingException();
	}

	@Then("location should be {int}, {int}")
	public void location_should_be(final Integer expectedX, final Integer expectedY) {
		throw new PendingException();
	}

	@Then("heading should be {string}")
	public void heading_should_be(final String expected) {
		throw new PendingException();
	}

}