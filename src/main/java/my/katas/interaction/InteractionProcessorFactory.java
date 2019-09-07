package my.katas.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

import my.katas.rover.Commands;

@Component
public class InteractionProcessorFactory {

	@Autowired
	private Commands commandBus;

	@Autowired
	private EventBus eventBus;

	public <C, E> InteractionProcessor<C, E> createFor(Interaction<C, E> interaction) {
		return new InteractionProcessor<C, E>(interaction, commandBus, eventBus);
	}

}