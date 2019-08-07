package my.katas.rover.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TurnLeft {

	private int x;
	private int y;
	private char heading;

}
