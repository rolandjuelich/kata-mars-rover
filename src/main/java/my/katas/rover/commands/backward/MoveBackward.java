package my.katas.rover.commands.backward;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveBackward {

	private String terrain;
	private Integer x;
	private Integer y;
	private String heading;
	
}