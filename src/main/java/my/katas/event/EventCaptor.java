package my.katas.event;

import static java.lang.String.format;
import static org.apache.commons.logging.LogFactory.getLog;
import static org.awaitility.Duration.FIVE_SECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.awaitility.core.Predicate;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventCaptor {

	private final List<Object> events = new ArrayList<>();
	private final EventBus bus;

	public EventCaptor(final EventBus bus) {
		this.bus = bus;
	}

	public List<Object> events() {
		return events;
	}

	public boolean isEmpty() {
		return events.isEmpty();
	}

	public <T> boolean contains(final Class<T> type) {
		return count(type) > 0;
	}

	public <T> long count(final Class<T> type) {
		return filterOn(type).size();
	}

	public <T> List<T> await(final Class<T> type) {
		final Duration duration = FIVE_SECONDS;
		log("await event " + type.getSimpleName() + " at most " + asString(duration));
		return Awaitility.await().atMost(duration).until(elementsOf(type), isNotEmpty());
	}
	

	@SuppressWarnings("unchecked")
	public <T> List<T> filterOn(final Class<T> type) {
		final List<T> list = new ArrayList<T>();
		events.stream().filter(o1 -> o1.getClass().isAssignableFrom(type)).forEach(o2 -> list.add((T) o2));
		return list;
	}

	public void open() {
		this.bus.register(this);
		log("capturing events");
	}

	public void close() {
		this.bus.unregister(this);
		log("stopped capturing events");
	}
	
	private String asString(final Duration duration) {
		return format("%s %s", duration.getValue(), duration.getTimeUnitAsString());
	}

	@Subscribe
	private void handle(final Object event) {
		events.add(event);
		log("received event " + event.getClass().getSimpleName());
	}

	private void log(final String message) {
		getLog(getClass()).debug(" ### " + message);
	}
	
	private Predicate<List<?>> isNotEmpty() {
		return new Predicate<List<?>>() {

			@Override
			public boolean matches(final List<?> list) {
				return !list.isEmpty();
			}

		};
	}

	private <T> Callable<List<T>> elementsOf(final Class<T> type) {
		return new Callable<List<T>>() {

			@Override
			public List<T> call() throws Exception {
				return filterOn(type);
			}
		};
	}
}