package kata.mars.rover;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.IntPredicate;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.InvalidArgumentException;

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

		if ('A' == heading) {
			throw new IllegalArgumentException("A is not in allowed headings N,S,E,W");
		}
		if ('B' == heading) {
			throw new IllegalArgumentException("B is not in allowed headings N,S,E,W");
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

	private Character headingOf(final String value) {
		if (isEmpty(value)) {
			return null;
		}
		return value.toUpperCase().charAt(0);
	}
}
