package kata.mars.rover;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MarsRoverSteeringSteps {

	private MarsRover rover;
	private String givenHeading;
	private int givenX;
	private int givenY;
	private String initializationError;

	@Given("a rover heading $heading at $x, $y")
	public void givenRover(String heading, @Named("x") int x, @Named("y") int y) {
		rover = new MarsRover(x, y, headingOf(heading));
	}

	@Given("rover to be initialized with heading $heading at $x, $y")
	public void givenRoverToBeInitializedWithHeadingheadingAt(String givenHeading, @Named("x") int givenX,
			@Named("y") int givenY) {
		this.givenHeading = givenHeading;
		this.givenX = givenX;
		this.givenY = givenY;
	}

	@When("rover moves forward")
	public void whenRoverMovesForward() {
		rover.forward();
	}

	@When("rover moves backward")
	public void whenRoverMovesBackward() {
		rover.backward();
	}

	@When("the rover is initialized")
	public void whenTheRoverIsInitialized() {
		try {
			roverFor(givenX, givenY, headingOf(givenHeading));
		} catch (IllegalArgumentException exception) {
			initializationError = exception.getMessage();
		}
	}

	public static MarsRover roverFor(int x, int y, char heading) {
		if (!asList("N", "S", "E", "W").contains(heading + "")) {
			throw new IllegalArgumentException("given heading('" + heading + "') is not one of N,S,E,W");
		}

		if (x < 0 || x > 100) {
			throw new IllegalArgumentException("X (" + x + ") must be between 0 and 100");
		}

		if (y < 0 || y > 100) {
			throw new IllegalArgumentException("Y (" + y + ") must be between 0 and 100");
		}

		return new MarsRover(x, y, heading);
	}

	@Then("it rejects with message $message")
	public void thenItRejectsWithMessagemessage(final String expected) {
		assertThat(initializationError).withFailMessage("no initialization error has been thrown").isNotNull();
		assertThat(initializationError).describedAs("expected initialization error").isEqualTo(expected);
	}

	@Then("rover is heading $heading at $x, $y")
	public void thenRoverIsExpectedToBe(String heading, @Named("x") int x, @Named("y") int y) {
		assertThat(rover.heading()).describedAs("current heading").isEqualTo(headingOf(heading));
		assertThat(rover.x()).describedAs("current X").isEqualTo(x);
		assertThat(rover.y()).describedAs("current Y").isEqualTo(y);
	}

	private char headingOf(final String value) {
		assertThat(value).describedAs("heading from spec").isNotEmpty();
		return value.toUpperCase().charAt(0);
	}
}
