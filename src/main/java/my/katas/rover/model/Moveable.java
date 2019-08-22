package my.katas.rover.model;

import my.katas.rover.model.terrain.Terrain;

public interface Moveable {

	Location forwardOn(final Terrain terrain);

	Location backwardOn(final Terrain terrain);

}