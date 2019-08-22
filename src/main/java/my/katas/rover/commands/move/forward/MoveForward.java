package my.katas.rover.commands.move.forward;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveForward {

	private String terrain;
	private Integer x;
	private Integer y;
	private String heading;

}
