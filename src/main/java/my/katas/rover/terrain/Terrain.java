package my.katas.rover.terrain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Terrain {

	private final TerrainId id;
	private final String name;
	private final Dimension x;
	private final Dimension y;

	public Terrain(final TerrainId id, final String name, final Dimension dimension) {
		this.id = id;
		this.name = name;
		this.x = dimension;
		this.y = dimension;
	}

	public int getMinX() {
		return x.getMin();
	}

	public int getMaxX() {
		return x.getMax();
	}

	public int getMinY() {
		return y.getMin();
	}

	public int getMaxY() {
		return y.getMax();
	}

}
