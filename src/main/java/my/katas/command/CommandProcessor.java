package my.katas.command;

import static java.util.Arrays.asList;
import static org.apache.commons.logging.LogFactory.getLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import my.katas.event.EventCaptor;
import my.katas.rover.Commands;

@Component
@AllArgsConstructor
public class CommandProcessor {

	@Autowired
	private Commands commandBus;

	@Autowired
	private EventBus eventBus;

	@Getter
	@RequiredArgsConstructor
	public class ProcessResult {

		private final EventCaptor allCaptured;

		public <E> E recent(final Class<E> type) {
			final List<E> events = allCaptured.filterOn(type);
			return events.isEmpty() ? null : events.get(events.size() - 1);
		}

	}

	public ProcessResult process(final Object command, final Class<?>... expectedEvents) {

		final EventCaptor captor = new EventCaptor(eventBus);

		try {
			captor.open();
			log("execute " + command);
			commandBus.execute(command);
			if (expectedEvents != null)
				asList(expectedEvents).forEach(expectedEvent -> captor.await(expectedEvent));

			return new ProcessResult(captor);

		} finally {
			captor.close();
		}
	}

	private void log(final String msg) {
		getLog(getClass()).debug(" ### " + msg);
	}

}