package my.katas.rover.terrain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Terrain {

	private final String name;
	private final int minX;
	private final int maxX;
	private final int minY;
	private final int maxY;

}
