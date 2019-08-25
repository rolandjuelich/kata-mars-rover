package my.katas.hexagonal.command;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class CommandBus {

	private final EventBus bus = new EventBus();

	public void post(final Command command) {
		this.bus.post(command);
	}

	public void register(final CommandHandler<?> handler) {
		this.bus.register(handler);
	}

	public void unregister(final CommandHandler<?> handler) {
		this.bus.unregister(handler);
	}

}
