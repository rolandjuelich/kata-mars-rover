package my.katas.rover.model.terrain;

public interface TerrainRepository {

	Terrain findByName(final String name);

}
