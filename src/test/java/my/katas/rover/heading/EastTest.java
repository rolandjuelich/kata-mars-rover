package my.katas.rover.heading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EastTest {

	@Test
	public void shouldTurnRight() {
		assertThat(new East().turnRight()).isInstanceOf(South.class);
	}

	@Test
	public void shouldTurnLeft() {
		assertThat(new East().turnLeft()).isInstanceOf(North.class);
	}

}
