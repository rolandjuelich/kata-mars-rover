package my.katas.event;

import static org.awaitility.Duration.FIVE_SECONDS;

import java.util.ArrayList;
import java.util.List;

import org.awaitility.Awaitility;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventStore {

	private final List<Object> events = new ArrayList<>();
	private final EventBus bus;

	public EventStore(final EventBus bus) {
		this.bus = bus;
	}

	public List<Object> events() {
		return events;
	}

	@Subscribe
	private void doHandle(final Object event) {
		events.add(event);
	}

	public boolean isEmpty() {
		return events.isEmpty();
	}

	public <T> boolean contains(final Class<T> type) {
		return count(type) > 0;
	}

	public <T> long count(final Class<T> type) {
		return events.stream().filter(o -> o.getClass().isAssignableFrom(type)).count();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> await(final Class<T> type) {
		final List<Object> elements = new ArrayList<Object>();
		Awaitility.await().atMost(FIVE_SECONDS).until(() -> {
			events.stream().filter(o1 -> o1.getClass().isAssignableFrom(type)).forEach(o2 -> elements.add(o2));
			return !elements.isEmpty();
		});
		return (List<T>) elements;
	}

	public void open() {
		this.bus.register(this);
	}

	public void close() {
		this.bus.unregister(this);
	}
}