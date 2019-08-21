package my.katas.rover;

import static my.katas.rover.Heading.EAST;
import static my.katas.rover.Heading.NORTH;
import static my.katas.rover.Heading.SOUTH;
import static my.katas.rover.Heading.WEST;
import static my.katas.rover.Location.location;
import static my.katas.rover.functional.Functions.decrease;
import static my.katas.rover.functional.Functions.increase;
import static my.katas.rover.functional.Functions.resetTo;
import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;

import java.util.function.Function;

import my.katas.rover.terrain.Terrain;

public class Rover implements Moveable, Turnable {

	private final Location location;
	private final Heading heading;

	private Rover(final Location location, final Heading heading) {
		this.location = location;
		this.heading = heading;
	}

	public static Moveable moveFrom(final Location location, final Heading heading) {
		return new Rover(location, heading);
	}

	public static Turnable turnFrom(final Heading heading) {
		return new Rover(location(0, 0), heading);
	}

	@Override
	public Location forwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			return location.y(increaseY(terrain));
		case EAST:
			return location.x(increaseX(terrain));
		case SOUTH:
			return location.y(decreaseY(terrain));
		case WEST:
			return location.x(decreaseX(terrain));
		default:
			return location;
		}
	}

	@Override
	public Location backwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			return location.y(decreaseY(terrain));
		case EAST:
			return location.x(decreaseX(terrain));
		case SOUTH:
			return location.y(increaseY(terrain));
		case WEST:
			return location.x(increaseX(terrain));
		default:
			return location;
		}
	}

	@Override
	public Heading right() {
		switch (heading) {
		case NORTH:
			return EAST;
		case EAST:
			return SOUTH;
		case SOUTH:
			return WEST;
		case WEST:
			return NORTH;
		default:
			return heading;
		}
	}

	@Override
	public Heading left() {
		switch (heading) {
		case NORTH:
			return WEST;
		case WEST:
			return SOUTH;
		case SOUTH:
			return EAST;
		case EAST:
			return NORTH;
		default:
			return heading;
		}
	}

	private static Function<Integer, Integer> increaseX(final Terrain terrain) {
		return increase().andThen(resetTo(terrain.getMinX()).onlyIf(greaterThan(terrain.getMaxX())));
	}

	private static Function<Integer, Integer> increaseY(final Terrain terrain) {
		return increase().andThen(resetTo(terrain.getMinY()).onlyIf(greaterThan(terrain.getMaxY())));
	}

	private static Function<Integer, Integer> decreaseX(final Terrain terrain) {
		return decrease().andThen(resetTo(terrain.getMaxX()).onlyIf(smallerThan(terrain.getMinX())));
	}

	private static Function<Integer, Integer> decreaseY(final Terrain terrain) {
		return decrease().andThen(resetTo(terrain.getMaxY()).onlyIf(smallerThan(terrain.getMinY())));
	}
}
