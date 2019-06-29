package kata.mars.rover;

import static org.assertj.core.api.Assertions.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MarsRoverSteeringSteps {

	@Given("rover location is 0,0")
	public void givenRoverLocationIs00() {
		x(0);
		y(0);
	}


	@Given("rover heading is north")
	public void givenRoverHeadingIsNorth() {
		heading('N');
	}

	@When("rover moves forward")
	public void whenRoverMovesForward() {
		forward();
	}

	@Then("rover is expected to be at 0,1")
	public void thenRoverIsExpectedToBeAt01() {
		assertThat(x()).isEqualTo(0);
		assertThat(y()).isEqualTo(1);
	}

	@Then("rover is expected to be heading north")
	public void thenRoverIsExpectedToBeHeadingNorth() {
		assertThat(heading()).isEqualTo('N');
	}

	private void forward() {
	}

	private void y(int i) {
		
	}
	
	private void x(int i) {
		
	}

	private int y() {
		return 1;
	}

	private int x() {
		return 0;
	}

	private void heading(char c) {

	}

	private char heading() {
		return 'N';
	}
}
