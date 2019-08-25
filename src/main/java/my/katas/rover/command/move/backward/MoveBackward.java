package my.katas.rover.command.move.backward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.Command;

@Getter
@AllArgsConstructor
public class MoveBackward implements Command {

	private String terrain;
	private Integer x;
	private Integer y;
	private String heading;
	
}
