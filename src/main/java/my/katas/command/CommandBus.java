package my.katas.command;

import com.google.common.eventbus.EventBus;

public class CommandBus {

	private final EventBus commandBus = new EventBus();

	public <H> CommandBus register(final H handler) {
		commandBus.register(handler);
		return this;
	}

	public <H> CommandBus unregister(final H handler) {
		commandBus.unregister(handler);
		return this;
	}

	public <C> void dispatch(final C command) {
		this.commandBus.post(command);
	}

}
