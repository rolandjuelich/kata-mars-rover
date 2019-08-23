package my.katas.rover.commands;

import com.google.common.eventbus.EventBus;

public class CommandBus {

	private final EventBus bus = new EventBus();

	public void post(final Object command) {
		this.bus.post(command);
	}

	public void register(final CommandHandler<?> handler) {
		this.bus.register(handler);
	}

	public void unregister(final CommandHandler<?> handler) {
		this.bus.unregister(handler);
	}

}
