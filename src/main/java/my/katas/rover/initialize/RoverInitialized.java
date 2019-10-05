package my.katas.rover.initialize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RoverInitialized {
	private final Integer x;
	private final Integer y;
	private final String heading;
	private final String id;
	
}
