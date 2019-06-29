package kata.mars.rover;

import static org.assertj.core.api.Assertions.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MarsRoverSteeringSteps {

	private MarsRover rover;

	@Given("a rover heading north at 0, 0")
	public void givenRoverLocationIs00() {
		rover = new MarsRover(0,0, 'N');
	}

	@When("rover moves forward")
	public void whenRoverMovesForward() {
		forward();
	}

	@Then("rover is heading north at 0, 1")
	public void thenRoverIsExpectedToBeAt01() {
		assertThat(heading()).isEqualTo('N');
		assertThat(x()).isEqualTo(0);
		assertThat(y()).isEqualTo(1);
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
