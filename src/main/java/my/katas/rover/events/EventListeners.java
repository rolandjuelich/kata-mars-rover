package my.katas.rover.events;

import java.util.ArrayList;
import java.util.List;

public class EventListeners<E> {

	private final List<EventListener<E>> listeners = new ArrayList<>();

	public void add(final EventListener<E> listener) {
		listeners.add(listener);
	}

	public void notifyOn(final E event) {
		listeners.forEach(listener -> listener.listenFor(event));
	}

}
