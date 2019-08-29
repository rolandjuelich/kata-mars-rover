package my.katas.rover.initialize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoverInitialized {
	private final int x;
	private final int y;
	private final String heading;
}
