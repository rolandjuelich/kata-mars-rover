package my.katas.rover.events;

import java.util.HashMap;
import java.util.Map;

public class EventBus {

	private final Map<Class<?>, EventListeners<?>> registry = new HashMap<>();

	public class Notifier<T> {

		private final Class<T> type;

		private Notifier(Class<T> type) {
			this.type = type;
		}

		public Notifier<T> notify(final EventListener<T> listener) {
			registry.putIfAbsent(type, new EventListeners<T>());
			listenersFor(type).add(listener);
			return this;
		}

	}

	public <T> Notifier<T> forEvery(final Class<T> type) {
		return new Notifier<T>(type);
	}

	public <E> void publish(final E event) {
		listenersFor(classOf(event)).notifyOn(event);
	}

	@SuppressWarnings("unchecked")
	private <T> EventListeners<T> listenersFor(final Class<T> type) {
		return (EventListeners<T>) registry.get(type);
	}

	@SuppressWarnings("unchecked")
	private <E> Class<E> classOf(final E event) {
		return (Class<E>) event.getClass();
	}

}
