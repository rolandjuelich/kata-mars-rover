package my.katas.rover.commands.turn.right;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.Command;

@Getter
@AllArgsConstructor
public class TurnRight implements Command {

	private final String heading;


}
