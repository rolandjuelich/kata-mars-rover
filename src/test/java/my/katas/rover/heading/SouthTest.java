package my.katas.rover.heading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SouthTest {

	@Test
	public void shouldTurnRight() {
		assertThat(new South().turnRight()).isInstanceOf(West.class);
	}

	@Test
	public void shouldTurnLeft() {
		assertThat(new South().turnLeft()).isInstanceOf(East.class);
	}

}
