package my.katas.rover.commands.move.forward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.Command;

@Getter
@AllArgsConstructor
public class MoveForward implements Command {

	private String terrain;
	private Integer x;
	private Integer y;
	private String heading;

}
