package my.katas.interaction;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import my.katas.event.EventStore;
import my.katas.rover.Commands;

@AllArgsConstructor
public class InteractionProcessor<C, E> {

	private final Interaction<C, E> interaction;
	private final Commands commandBus;
	private final EventBus eventBus;

	public E process() {
		final EventStore eventStore = new EventStore(eventBus);
		try {
			eventStore.open();
			commandBus.execute(interaction.getCommand()); // .within(FIVE_SECONDS); //.orElse
			return eventStore.await(interaction.getEvent()).iterator().next();
		} finally {
			eventStore.close();
		}
	}

}