package kata.mars.rover;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.assertj.core.api.Assertions.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MarsRoverSteeringSteps {

	private MarsRover rover;

	@Given("a rover heading $heading at $x, $y")
	public void givenRover(String heading, @Named("x") int x, @Named("y") int y) {
		rover = new MarsRover(x, y, headingOf(heading));
	}

	@When("rover moves forward")
	public void whenRoverMovesForward() {
		rover.forward();
	}

	@Then("rover is heading $heading at $x, $y")
	public void thenRoverIsExpectedToBe(String heading, @Named("x") int x, @Named("y") int y) {
		assertThat(rover.heading()).describedAs("current heading").isEqualTo(headingOf(heading));
		assertThat(rover.x()).describedAs("current X").isEqualTo(x);
		assertThat(rover.y()).describedAs("current Y").isEqualTo(y);
	}

	private Character headingOf(final String value) {
		if (isEmpty(value)) {
			return null;
		}
		return value.toUpperCase().charAt(0);
	}
}
