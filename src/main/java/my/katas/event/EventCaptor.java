package my.katas.event;

import static java.lang.String.format;
import static org.apache.commons.logging.LogFactory.getLog;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ZERO;

import java.util.ArrayList;
import java.util.List;

import org.awaitility.Duration;
import org.awaitility.core.ConditionTimeoutException;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventCaptor {

	private final List<Object> captured = new ArrayList<>();

	private final EventBus bus;

	public EventCaptor(final EventBus bus) {
		this.bus = bus;
	}

	public <T> Events<T> waitFor(final Class<T> type) {
		final Duration duration = Duration.FIVE_HUNDRED_MILLISECONDS;
		log("wait for event " + type.getSimpleName() + " at most " + asString(duration));
		try {
			return await().atMost(duration).pollInterval(ZERO)
					.until(() -> Events.of(type).from(captured), events -> events.isNotEmpty());
		} catch (final ConditionTimeoutException timeout) {
			return Events.of(type);
		}
	}

	public void start() {
		this.bus.register(this);
		log("capturing events");
	}

	public void stop() {
		this.bus.unregister(this);
		log("stopped capturing events");
	}

	private String asString(final Duration duration) {
		return format("%s %s", duration.getValue(), duration.getTimeUnitAsString());
	}

	@Subscribe
	private void handle(final Object event) {
		captured.add(event);
		log("received event " + event.getClass().getSimpleName());
	}

	private void log(final String message) {
		getLog(getClass()).debug(" ### " + message);
	}

}