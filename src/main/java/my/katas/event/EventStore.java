package my.katas.event;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventStore {

	private final List<Object> events = new ArrayList<>();

	public EventStore(final EventBus bus) {
		bus.register(this);
	}

	public List<Object> events() {
		return events;
	}

	@Subscribe
	private void doHandle(Object event) {
		events.add(event);
	}
}