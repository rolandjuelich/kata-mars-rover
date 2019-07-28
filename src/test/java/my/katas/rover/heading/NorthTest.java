package my.katas.rover.heading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NorthTest {

	@Test
	public void shouldTurnRight() {
		assertThat(new North().turnRight()).isInstanceOf(East.class);
	}

	@Test
	public void shouldTurnLeft() {
		assertThat(new North().turnLeft()).isInstanceOf(West.class);
	}

}
