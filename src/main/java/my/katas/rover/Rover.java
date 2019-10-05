package my.katas.rover;

import static my.katas.rover.functional.Functions.decreaseByOne;
import static my.katas.rover.functional.Functions.increaseByOne;
import static my.katas.rover.turn.Heading.EAST;
import static my.katas.rover.turn.Heading.NORTH;
import static my.katas.rover.turn.Heading.SOUTH;
import static my.katas.rover.turn.Heading.WEST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import my.katas.rover.move.Location;
import my.katas.rover.move.Moveable;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainId;
import my.katas.rover.turn.Heading;
import my.katas.rover.turn.Turnable;

@Getter
@ToString
@AllArgsConstructor
public class Rover implements Moveable, Turnable {

	private RoverId id;
	private TerrainId terrainId;
	private Location location;
	private Heading heading;

	public static Rover create(final TerrainId terrain, final Location location, final Heading heading) {
		return new Rover(new RoverId("staticFakeRoverId"), terrain, location, heading);
	}

	@Override
	public Location forwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			location = location.y(increaseByOne(terrain.getY()));
			break;
		case EAST:
			location = location.x(increaseByOne(terrain.getX()));
			break;
		case SOUTH:
			location = location.y(decreaseByOne(terrain.getY()));
			break;
		case WEST:
			location = location.x(decreaseByOne(terrain.getX()));
			break;
		}
		return location;
	}

	@Override
	public Location backwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			location = location.y(decreaseByOne(terrain.getY()));
			break;
		case EAST:
			location = location.x(decreaseByOne(terrain.getX()));
			break;
		case SOUTH:
			location = location.y(increaseByOne(terrain.getY()));
			break;
		case WEST:
			location = location.x(increaseByOne(terrain.getX()));
			break;
		}
		return location;
	}

	@Override
	public Heading right() {
		switch (heading) {
		case NORTH:
			heading = EAST;
			break;
		case EAST:
			heading = SOUTH;
			break;
		case SOUTH:
			heading = WEST;
			break;
		case WEST:
			heading = NORTH;
			break;
		}
		return heading;
	}

	@Override
	public Heading left() {
		switch (heading) {
		case NORTH:
			heading = WEST;
			break;
		case WEST:
			heading = SOUTH;
			break;
		case SOUTH:
			heading = EAST;
			break;
		case EAST:
			heading = NORTH;
			break;
		}
		return heading;
	}

}
