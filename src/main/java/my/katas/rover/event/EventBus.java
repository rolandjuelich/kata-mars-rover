package my.katas.rover.event;

import org.springframework.stereotype.Component;

@Component
public class EventBus {

	private final com.google.common.eventbus.EventBus bus = new com.google.common.eventbus.EventBus();

	public void register(final EventListener<?> listener) {
		this.bus.register(listener);
	}

	public void unregister(final EventListener<?> listener) {
		this.bus.unregister(listener);
	}

	public void post(final Event event) {
		this.bus.post(event);
	}

}
