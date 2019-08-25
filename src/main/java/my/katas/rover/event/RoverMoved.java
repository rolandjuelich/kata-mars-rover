package my.katas.rover.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoverMoved implements Event {

	private final Integer x;
	private final Integer y;
}
