package my.katas.rover.move.backward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.RoverCommand;

@Getter
@AllArgsConstructor
public class MoveBackward implements RoverCommand {

	private String terrain;
	private Integer x;
	private Integer y;
	private String heading;
	
}
