package my.katas.rover;

import my.katas.rover.terrain.Terrain;

public interface Moveable {

	Location moveForwardOn(final Terrain terrain);

	Location moveBackwardOn(final Terrain terrain);

}