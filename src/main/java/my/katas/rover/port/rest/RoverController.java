package my.katas.rover.port.rest;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.StringUtils.defaultString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import my.katas.command.CommandProcessor;
import my.katas.rover.Commands;
import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.forward.MoveForward;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RoverController {

	@Autowired
	private CommandProcessor processor;

	@RequestMapping("/initialize")
	public String initialize() {
		final InitializeRover command = Commands.initialize("Mars", nextInt(), nextInt());
		final Class<RoverInitialized> event = RoverInitialized.class;
		return process(command, event);
	}


	@RequestMapping("/forward")
	public String forward() {
		final MoveForward command = Commands.moveForward();
		final Class<RoverMoved> event = RoverMoved.class;
		return process(command, event);
	}

	@RequestMapping("/backward")
	public String backward() {
		final MoveBackward command = Commands.moveBackward();
		final Class<RoverMoved> event = RoverMoved.class;
		return process(command, event);
	}

	private <C, E> String process(final C command, Class<E> event) {
		final E result = processor.process(command,event).recent(event);
		return defaultString(result.toString(), "no answer");
	}

}
