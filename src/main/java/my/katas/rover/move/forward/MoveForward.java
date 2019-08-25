package my.katas.rover.move.forward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.RoverCommand;

@Getter
@AllArgsConstructor
public class MoveForward implements RoverCommand {

	private String terrain;
	private Integer x;
	private Integer y;
	private String heading;

}
