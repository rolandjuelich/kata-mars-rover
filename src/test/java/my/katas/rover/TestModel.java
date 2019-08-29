package my.katas.rover;

import static my.katas.rover.move.Location.location;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import my.katas.rover.move.Location;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.turn.Heading;

public interface TestModel {

	static Terrain randomTerrain() {
		return new Terrain(randomAlphanumeric(10), nextInt(), nextInt(), nextInt(), nextInt());
	}

	static Location randomLocation() {
		return location(nextInt(), nextInt());
	}

	static Heading randomHeading() {
		final Heading[] values = Heading.values();
		final int index = nextInt(0, values.length);
		return values[index];
	}

	public static Rover randomRoverOn(final Terrain terrain) {
		return Rover.initialize(terrain, randomLocation(), randomHeading());
	}

}
