package my.katas.rover.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoverMoved {

	private final Integer x;
	private final Integer y;
}
