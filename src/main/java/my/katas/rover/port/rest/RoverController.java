package my.katas.rover.port.rest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import my.katas.command.CommandProcessor;
import my.katas.rover.RoverCommands;
import my.katas.rover.initialize.InitializeRover;
import my.katas.rover.initialize.RoverInitialized;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.move.backward.MoveBackward;
import my.katas.rover.move.forward.MoveForward;
import my.katas.rover.turn.Heading;

@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RoverController {

	@Autowired
	private CommandProcessor application;

	@RequestMapping("/initialize")
	public String initialize(@RequestParam("terrain") final String terrainId) {
		final InitializeRover command = RoverCommands.initialize(terrainId, 0, 0, Heading.NORTH.name());
		final Class<RoverInitialized> event = RoverInitialized.class;
		return process(command, event);
	}

	@RequestMapping("/forward")
	public String forward(@RequestParam("rover") final String roverId) {
		final MoveForward command = RoverCommands.moveForward(roverId);
		final Class<RoverMoved> event = RoverMoved.class;
		return process(command, event);
	}

	@RequestMapping("/backward")
	public String backward(@RequestParam("rover") final String roverId) {
		final MoveBackward command = RoverCommands.moveBackward(roverId);
		final Class<RoverMoved> event = RoverMoved.class;
		return process(command, event);
	}

	private <C, E> String process(final C command, final Class<E> event) {
		final E result = application.process(command, event);
		return ObjectUtils.defaultIfNull(result, "NO_EVENTS_PUBLISHED").toString();
	}

}
