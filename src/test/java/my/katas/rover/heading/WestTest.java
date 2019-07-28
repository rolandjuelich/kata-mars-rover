package my.katas.rover.heading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WestTest {

	@Test
	public void shouldTurnRight() {
		assertThat(new West().turnRight()).isInstanceOf(North.class);
	}

	@Test
	public void shouldTurnLeft() {
		assertThat(new West().turnLeft()).isInstanceOf(South.class);
	}

}
