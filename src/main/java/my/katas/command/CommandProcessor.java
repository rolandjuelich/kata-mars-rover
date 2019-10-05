package my.katas.command;

import static org.apache.commons.logging.LogFactory.getLog;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import my.katas.event.EventCaptor;
import my.katas.event.Events;

@AllArgsConstructor
public class CommandProcessor {

	private final CommandBus commandBus;

	private final EventBus eventBus;

	public void process(final Object command) {
		log("execute " + command);
		commandBus.dispatch(command);
	}

	public <E> E process(final Object command, final Class<E> expectedEvent) {
		return new EventCapturingCommandProcessor(commandBus, new EventCaptor(eventBus)).process(command,
				expectedEvent);
	}

	private void log(final String msg) {
		getLog(getClass()).debug(" ### " + msg);
	}

	@AllArgsConstructor
	static class EventCapturingCommandProcessor {

		private final CommandBus commandBus;
		private final EventCaptor captor;

		public <E> E process(final Object command, final Class<E> expectedEvent) {
			log("execute " + command);
			captor.start();
			commandBus.dispatch(command);
			final Events<E> events = captor.waitFor(expectedEvent);
			captor.stop();
			return events.isNotEmpty() ? events.mostRecent() : null;
		}

		private void log(final String msg) {
			getLog(getClass()).debug(" ### " + msg);
		}
	}

}