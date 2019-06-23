package kata.mars.rover;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class MarsRoverTest {

	public class MarsRover {

		public int getX() {
			return 0;
		}

		public int getY() {
			return 0;
		}

		public char getDirection() {
			return 'N';
		}

	}

	@Test
	public void shouldInitializeRover() {
		// given
		int x = 0;
		int y = 0;
		char direction = 'N';

		// when
		MarsRover rover = new MarsRover();

		// then
		assertThat(rover.getX()).isEqualTo(x);
		assertThat(rover.getY()).isEqualTo(y);
		assertThat(rover.getDirection()).isEqualTo(direction);
	}

}
