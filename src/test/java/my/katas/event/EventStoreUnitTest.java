package my.katas.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class EventStoreUnitTest {

	private EventBus bus = new EventBus();

	private EventStore store = new EventStore(bus);

	@Test
	public void shouldGatherAllEvents() {
		// given
		final SomeEvent someEvent = new SomeEvent();
		final AnotherEvent anotherEvent = new AnotherEvent();

		// when
		bus.post(someEvent);
		bus.post(anotherEvent);

		// then
		assertThat(store.events()).containsExactly(someEvent, anotherEvent);
	}

	private class SomeEvent {

	}

	private class AnotherEvent {

	}
}
