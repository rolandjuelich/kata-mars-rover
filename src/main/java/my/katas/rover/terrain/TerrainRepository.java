package my.katas.rover.terrain;

import static my.katas.rover.terrain.Dimension.from;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class TerrainRepository {

	private Set<Terrain> values = new HashSet<Terrain>();

	public TerrainId nextId() {
		return new TerrainId(UUID.randomUUID().toString().toUpperCase());
	}

	public Terrain findByName(final String name) {
		return new Terrain(nextId(), "Mars", from(0).to(99));
	}

	public Terrain findById(final TerrainId id) {
		return values.stream().filter(terrain -> terrain.getId().equals(id)).findFirst().get();
	}

	public Set<Terrain> findAll() {
		return values;
	}

	public void save(final Terrain terrain) {
		values.add(terrain);
	}

	public void save(final Set<Terrain> terrains) {
		values.addAll(terrains);
	}

}
