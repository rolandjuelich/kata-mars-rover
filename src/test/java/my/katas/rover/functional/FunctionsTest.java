package my.katas.rover.functional;

import static my.katas.rover.functional.Functions.decrease;
import static my.katas.rover.functional.Functions.increase;
import static my.katas.rover.functional.Functions.resetTo;
import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class FunctionsTest {

	@Test
	@Parameters({ "0,1", "1,2", "2,3" })
	public void shouldIncreaseByOne(int given, int expected) {
		// given
		int value = given;
		int result = expected;

		// when
		int actual = increase().apply(value);

		// then
		assertThat(actual).isEqualTo(result);
	}

	@Test
	@Parameters({ "3,2", "2,1", "1,0" })
	public void shouldDecreaseByOne(int given, int expected) {
		// given
		int value = given;
		int result = expected;

		// when
		int actual = decrease().apply(value);

		// then
		assertThat(actual).isEqualTo(result);
	}

	@Test
	public void shouldResetWhenExceedingMaximum() {
		int min = 0;
		int max = 1;
		int value = 2;

		int actual = resetTo(min).onlyIf(greaterThan(max)).apply(value);

		assertThat(actual).isEqualTo(min);
	}

	@Test
	public void shouldResetWhenExceedingMinimum() {
		int min = 0;
		int max = 1;
		int value = -1;

		int actual = resetTo(max).onlyIf(smallerThan(min)).apply(value);

		assertThat(actual).isEqualTo(max);
	}

	@Test
	public void shouldIncreaseAndResetWhenExceedingMaximum() {
		// given
		int min = nextInt(0, 10);
		int max = min + nextInt(0, 50);
		int times = (max - min) + 1;
		int givenValue = min;

		// when
		Integer actual = givenValue;
		for (int i = 0; i < times; i++) {
			actual = increase().andThen(resetTo(min).onlyIf(greaterThan(max))).apply(actual);
		}

		// then
		assertThat(actual).isEqualTo(min);
	}

	@Test
	public void shouldDecreaseAndResetWhenExceedingMinimum() {
		// given
		int min = nextInt(0, 10);
		int max = min + nextInt(0, 50);
		int times = (max - min) + 1;
		int givenValue = max;

		// when
		Integer actual = givenValue;
		for (int i = 0; i < times; i++) {
			actual = decrease().andThen(resetTo(max).onlyIf(smallerThan(min))).apply(actual);
		}

		// then
		assertThat(actual).isEqualTo(max);
	}
}
