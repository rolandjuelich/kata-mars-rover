package my.katas.rover.model.terrain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Terrain {

	private String name;
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;

}
