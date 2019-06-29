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
		forward();
	}

	@Then("rover is heading $heading at $x, $y")
	public void thenRoverIsExpectedToBe(String heading, @Named("x") int x, @Named("y") int y) {
		assertThat(heading()).isEqualTo(headingOf(heading));
		assertThat(x()).isEqualTo(x);
		assertThat(y()).isEqualTo(y);
	}

	private Character headingOf(final String value) {
		if (isEmpty(value)) {
			return null;
		}
		return value.toUpperCase().charAt(0);
	}

	private void forward() {
		rover.forward();
	}

	private int y() {
		return rover.y();
	}

	private int x() {
		return rover.x();
	}

	private char heading() {
		return rover.heading();
	}
}
