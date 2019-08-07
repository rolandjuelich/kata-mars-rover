package my.katas.rover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoverStateChanged {

	private int x;
	private int y;
	private char heading;
}
