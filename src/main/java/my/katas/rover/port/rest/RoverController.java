package my.katas.rover.port.rest;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.StringUtils.defaultString;
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

import my.katas.interaction.Interaction;
import my.katas.interaction.InteractionProcessorFactory;
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

	@Autowired
	private InteractionProcessorFactory processorFactory;

	private RoverMoved moved;

	@RequestMapping("/initialize")
	public String initialize() {
		return process(Commands.initialize("Mars", nextInt(), nextInt()), RoverInitialized.class);
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

	private String process(final InitializeRover command, final Class<RoverInitialized> event) {
		final RoverInitialized result = processorFactory.createFor(Interaction.of(command, event)).process();
		return defaultString(result.toString(), "no answer");
	}

	@Subscribe
	private void listenFor(final RoverMoved event) {
		this.moved = event;
	};
}
