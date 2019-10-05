package my.katas.rover;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.UUID.randomUUID;
import static java.util.stream.IntStream.range;
import static my.katas.rover.move.Location.location;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.Set;

import my.katas.rover.move.Location;
import my.katas.rover.terrain.Dimension;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainId;
import my.katas.rover.turn.Heading;

public interface TestModel {

	static Terrain randomTerrain() {
		return randomTerrainWithName(randomAlphanumeric(10));
	}

	static Terrain randomTerrainWithName(final String name) {
		return new Terrain(randomTerrainId(), name, randomDimension());
	}

	static TerrainId randomTerrainId() {
		return new TerrainId("TEST-TERRAIN-" + randomUUID().toString().toUpperCase());
	}

	static RoverId randomRoverId() {
		return new RoverId("TEST-ROVER-" + randomUUID().toString().toUpperCase());
	}

	static Location randomLocation() {
		return location(nextInt(), nextInt());
	}

	static Heading randomHeading() {
		final Heading[] values = Heading.values();
		final int index = nextInt(0, values.length);
		return values[index];
	}

	static Rover randomRoverOn(final Terrain terrain) {
		return new Rover(randomRoverId(), terrain.getId(), randomLocation(), randomHeading());
	}

	static Dimension randomDimension() {
		int min = nextInt();
		int max = min + nextInt();
		return Dimension.from(min).to(max);
	}

	static Set<Terrain> randomTerrains(final int count) {
		final Set<Terrain> terrains = newHashSet();
		range(0, count).forEach(value -> terrains.add(randomTerrain()));
		return terrains;
	}

}
