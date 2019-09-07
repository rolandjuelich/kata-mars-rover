package my.katas.rover.port.rest;

import java.util.Optional;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import my.katas.event.EventStore;
import my.katas.rover.Commands;

@AllArgsConstructor
public class InteractionProcessor<C, E> {

	private final Commands commands;
	private final EventBus events;

	public E process(final Interaction<C, E> interaction) {
		final EventStore eventStore = new EventStore(events);
		try {
			eventStore.open();
			commands.execute(interaction.getCommand()); // .within(FIVE_SECONDS); //.orElse
			return eventStore.await(interaction.getEvent()).iterator().next();
		} finally {
			eventStore.close();
		}
	}
	
}