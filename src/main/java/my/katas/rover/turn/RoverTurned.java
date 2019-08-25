package my.katas.rover.turn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.katas.rover.event.RoverEvent;

@Getter
@AllArgsConstructor
public class RoverTurned implements RoverEvent {

	private String heading;
	
}
