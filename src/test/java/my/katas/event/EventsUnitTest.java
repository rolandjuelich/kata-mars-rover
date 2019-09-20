package my.katas.event;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;

public class EventsUnitTest {

	@Test
	public void shouldFilter() {
		// given
		final List<? super Object> list = newArrayList(new Object(), mock(TestTypeB.class), mock(TestTypeC.class));
		final TestTypeA expectedElement = mock(TestTypeA.class);
		list.add(expectedElement);

		// when
		final List<TestTypeA> filtered = Events.of(TestTypeA.class).from(list).all();

		// then
		assertThat(filtered).containsExactly(expectedElement);
	}

	private interface TestTypeA {

	}

	private interface TestTypeB {

	}

	private interface TestTypeC {

	}
}
