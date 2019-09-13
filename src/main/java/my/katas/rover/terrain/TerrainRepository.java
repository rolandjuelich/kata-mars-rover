package my.katas.rover.terrain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class TerrainRepository {

	public Terrain findByName(final String name) {
		return new Terrain("Mars", 0, 99, 0, 99);
	}

}
