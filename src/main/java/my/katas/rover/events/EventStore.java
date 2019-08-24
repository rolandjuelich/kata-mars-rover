package my.katas.rover.events;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

@Component
public class EventStore {

	private final List<Object> events = new ArrayList<>();

	@Subscribe
	public void handle(final RoverTurned event) {
		this.events.add(event);
	}

	@Subscribe
	public void handle(final RoverMoved event) {
		this.events.add(event);
	}

	public List<?> allOf(final Class<?> type) {
		return events.stream().filter(o -> o.getClass().isAssignableFrom(type)).collect(Collectors.toList());
	}

}
