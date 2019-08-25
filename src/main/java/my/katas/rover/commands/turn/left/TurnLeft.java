package my.katas.rover.commands.turn.left;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.command.Command;

@Getter
@AllArgsConstructor
public class TurnLeft implements Command {

	private final String heading;

}
