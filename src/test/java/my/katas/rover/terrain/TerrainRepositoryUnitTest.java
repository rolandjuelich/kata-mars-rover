package my.katas.rover.terrain;

import static my.katas.rover.TestModel.randomTerrainWithName;
import static my.katas.rover.TestModel.randomTerrains;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

public class TerrainRepositoryUnitTest {

	private final TerrainRepository repository = new TerrainRepository();

	@Test
	public void shouldSave() {
		// given
		final Terrain mars = randomTerrainWithName("Mars");
		repository.save(mars);

		// when
		final Set<Terrain> found = repository.findAll();

		// then
		assertThat(found).containsExactly(mars);
	}

	@Test
	public void shouldSaveMultipleInstances() {
		// given
		final Set<Terrain> terrains = randomTerrains(25);
		repository.save(terrains);

		// when
		final Set<Terrain> found = repository.findAll();

		// then
		assertThat(found).containsExactlyElementsOf(terrains);
	}

	@Test
	public void shouldFindById() {
		// given
		repository.save(randomTerrains(10));
		final Terrain mars = randomTerrainWithName("Mars");
		repository.save(mars);
		
		// when
		final Terrain found = repository.findById(mars.getId());

		// then
		assertThat(found).isEqualTo(mars);
	}

}
