package my.katas.rover.terrain;

import org.springframework.stereotype.Component;

@Component
public class TerrainRepository {

	public Terrain findByName(final String name) {
		return new Terrain("Mars", 0, 99, 0, 99);
	}

}
