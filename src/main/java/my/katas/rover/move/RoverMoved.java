package my.katas.rover.move;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.event.RoverEvent;

@Getter
@AllArgsConstructor
public class RoverMoved implements RoverEvent {

	private final Integer x;
	private final Integer y;
}
