package my.katas.rover.port.rest;

import static my.katas.rover.Commands.initialize;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import my.katas.rover.Commands;
import my.katas.rover.initialize.RoverInitialized;

@RestController
@Scope(SCOPE_REQUEST)
public class RoverController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private Commands commands;

	@Autowired
	private EventBus events;

	private RoverInitialized initialized;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}

	@RequestMapping("/hello")
	public String hello() {

		Assert.isNull(initialized, "should be null");

		events.register(this);

		String out = "no answer";

		try {
			commands.execute(initialize("Mars", nextInt(), nextInt()));
			await().atMost(FIVE_SECONDS).until(() -> initialized != null);
			out = initialized.toString();
		} finally {
			events.unregister(this);
		}

		return out;
	}

	@Subscribe
	void listenFor(final RoverInitialized event) {
		this.initialized = event;
	};
}
