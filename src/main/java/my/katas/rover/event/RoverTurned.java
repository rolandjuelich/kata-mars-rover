package my.katas.rover.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoverTurned implements Event {

	private String heading;
	
}
