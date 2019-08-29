package my.katas.rover.move.forward;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MoveForward {

	private final String terrain;
	private final Integer x;
	private final Integer y;
	private final String heading;

}
