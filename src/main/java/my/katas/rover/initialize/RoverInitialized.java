package my.katas.rover.initialize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoverInitialized {
	private final Integer x;
	private final Integer y;
	private final String heading;
}
