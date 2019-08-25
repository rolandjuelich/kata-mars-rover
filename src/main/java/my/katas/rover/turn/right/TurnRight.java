package my.katas.rover.turn.right;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.RoverCommand;

@Getter
@AllArgsConstructor
public class TurnRight implements RoverCommand {

	private final String heading;


}
