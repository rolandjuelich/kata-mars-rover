package my.katas.rover;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoverStepDefs {

	private Rover rover;
	private int givenX;
	private int givenY;
	private char givenHeading;

	@Given("location is at {int}, {int}")
	public void location_is_at(final Integer x, final Integer y) {
		this.givenX = x;
		this.givenY = y;
	}

	@Given("heading is NORTH")
	public void heading_is_NORTH() {
		this.givenHeading = 'N';
	}

	@Given("heading is EAST")
	public void heading_is_EAST() {
		this.givenHeading = 'E';
	}

	@Then("heading should be EAST")
	public void heading_should_be_EAST() {
	    assertThat(rover.heading()).describedAs("expected heading").isEqualTo('E');
	}
	
	@When("executing command forward")
	public void executing_command_forward() {
		rover = new Rover(givenX, givenY, givenHeading);
	}

	@Then("location should be {int}, {int}")
	public void location_should_be(final Integer expectedX, final Integer expectedY) {
		assertThat(rover.x()).describedAs("expected x").isEqualTo(expectedX);
		assertThat(rover.y()).describedAs("expected y").isEqualTo(expectedY);
	}

	@Then("heading should be NORTH")
	public void heading_should_be_NORTH() {
		assertThat(rover.heading()).describedAs("expected heading").isEqualTo('N');
	}

}