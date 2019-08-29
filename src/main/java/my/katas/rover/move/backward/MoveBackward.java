package my.katas.rover.move.backward;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MoveBackward {

	private final String terrain;
	private final Integer x;
	private final Integer y;
	private final String heading;
	
}
