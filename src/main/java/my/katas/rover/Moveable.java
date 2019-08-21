package my.katas.rover;

import my.katas.rover.terrain.Terrain;

public interface Moveable {

	Location forwardOn(final Terrain terrain);

	Location backwardOn(final Terrain terrain);

}