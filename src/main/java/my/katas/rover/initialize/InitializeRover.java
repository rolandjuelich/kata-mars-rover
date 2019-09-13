package my.katas.rover.initialize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class InitializeRover {

	private final String terrain;
	private final Integer x;
	private final Integer y;
	private final String heading;


}
