package my.katas.rover;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoverStepDefs {

	@Given("rover is heading {string}")
	public void rover_is_heading(final String heading) {
		throw new PendingException();
	}

	@Given("rover is heading {string} at {int}, {int}")
	public void rover_is_heading_at(final String heading, final Integer x, final Integer y) {
		throw new PendingException();
	}

	@When("rover moves forward {int} times")
	public void rover_moves_forward_times(final Integer times) {
		throw new PendingException();
	}

	@When("rover moves backward {int} times")
	public void rover_moves_backward_times(final Integer times) {
		throw new PendingException();
	}

	@When("rover turns right")
	public void rover_turns_right() {
		throw new PendingException();
	}

	@When("rover turns left")
	public void rover_turns_left() {
		throw new PendingException();
	}

	@Then("rover should be heading {string}")
	public void rover_should_be_heading(final String expected) {
		throw new PendingException();
	}

	@Then("rover should be heading {string} at {int}, {int}")
	public void rover_should_be_heading_at(final String heading, final Integer x, final Integer y) {
		throw new PendingException();
	}

}