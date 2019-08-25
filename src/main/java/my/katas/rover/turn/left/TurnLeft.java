package my.katas.rover.turn.left;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.RoverCommand;

@Getter
@AllArgsConstructor
public class TurnLeft implements RoverCommand {

	private final String heading;

}
