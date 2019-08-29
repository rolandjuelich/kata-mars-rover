package my.katas.rover.move;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoverMoved {

	private final Integer x;
	private final Integer y;
}
