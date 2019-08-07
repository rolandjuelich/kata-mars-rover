package my.katas.rover.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoveForward {

	private int x;
	private int y;
	private char heading;

}
