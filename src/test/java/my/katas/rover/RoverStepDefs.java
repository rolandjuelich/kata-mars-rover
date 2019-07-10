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
		givenX = x;
		givenY = y;
	}

	@Given("heading is {string}")
	public void heading_is_NORTH(final String given) {
		givenHeading = given.toUpperCase().charAt(0);
	}

	@When("moving forward times {int}")
	public void executing_command_forward_times(Integer times) {
		rover = new Rover(givenX, givenY, givenHeading);
		for (int i = 0; i < times; i++) {
			rover.forward();
		}
	}

	@Then("location should be {int}, {int}")
	public void location_should_be(final Integer expectedX, final Integer expectedY) {
		assertThat(rover.x()).describedAs("current x").isEqualTo(expectedX);
		assertThat(rover.y()).describedAs("current y").isEqualTo(expectedY);
	}

	@Then("heading should be {string}")
	public void heading_should_be_east(final String expected) {
		assertThat(rover.heading()).describedAs("current heading").isEqualTo(expected.toUpperCase().charAt(0));
	}

}