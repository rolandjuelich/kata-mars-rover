package my.katas.rover.command.turn.right;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.Command;

@Getter
@AllArgsConstructor
public class TurnRight implements Command {

	private final String heading;


}
