package my.katas.rover.port.rest;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import my.katas.event.EventStore;
import my.katas.rover.Commands;
import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;

@RestController
@Scope(SCOPE_REQUEST)
public class RoverController {

	@Autowired
	private Commands commands;

	@Autowired
	private EventBus events;

	private RoverMoved moved;

	@RequestMapping("/initialize")
	public String initialize() {

		InitializeRover command = Commands.initialize("Mars", nextInt(), nextInt());
		Class<RoverInitialized> event = RoverInitialized.class;
		RoverInitialized foo = foo(command, event);
		if (foo != null) {
			return foo.toString();
		} else {
			return "no answer";
		}

	}

	public <C, E> E foo(final C command, final Class<E> event) {
		final EventStore eventStore = new EventStore(events);
		try {
			eventStore.open();
			commands.execute(command); // .within(FIVE_SECONDS); //.orElse
			return eventStore.await(event).iterator().next();
		} finally {
			eventStore.close();
		}
	}

	@RequestMapping("/forward")
	public String forward() {

		Assert.isNull(moved, "should be null");

		events.register(this);

		String out = "no answer";

		try {
			commands.execute(Commands.moveForward());
			await().atMost(FIVE_SECONDS).until(() -> moved != null);
			out = moved.toString();
		} finally {
			events.unregister(this);
		}

		return out;
	}

	@RequestMapping("/backward")
	public String backward() {

		Assert.isNull(moved, "should be null");

		events.register(this);

		String out = "no answer";

		try {
			commands.execute(Commands.moveBackward());
			await().atMost(FIVE_SECONDS).until(() -> moved != null);
			out = moved.toString();
		} finally {
			events.unregister(this);
		}

		return out;
	}

	@Subscribe
	private void listenFor(final RoverMoved event) {
		this.moved = event;
	};
}
