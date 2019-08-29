package my.katas.rover;

import static my.katas.rover.functional.Functions.decrease;
import static my.katas.rover.functional.Functions.increase;
import static my.katas.rover.functional.Functions.resetTo;
import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;
import static my.katas.rover.move.Location.location;
import static my.katas.rover.turn.Heading.EAST;
import static my.katas.rover.turn.Heading.NORTH;
import static my.katas.rover.turn.Heading.SOUTH;
import static my.katas.rover.turn.Heading.WEST;

import java.util.function.Function;

import lombok.Getter;
import my.katas.rover.move.Location;
import my.katas.rover.move.Moveable;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.turn.Heading;
import my.katas.rover.turn.Turnable;

@Getter
public class Rover implements Moveable, Turnable {

	private Location location;
	private Heading heading;
	private String terrain;

	private Rover(final Location location, final Heading heading) {
		this.location = location;
		this.heading = heading;
	}

	public Rover(final String terrain, final Location location, final Heading heading) {
		this.terrain = terrain;
		this.location = location;
		this.heading = heading;
	}

	public static Rover initialize(final Terrain terrain, final Location location, final Heading heading) {
		return new Rover(terrain.getName(), location, heading);
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
			location = location.y(increaseY(terrain));
			break;
		case EAST:
			location = location.x(increaseX(terrain));
			break;
		case SOUTH:
			location = location.y(decreaseY(terrain));
			break;
		case WEST:
			location = location.x(decreaseX(terrain));
			break;
		}
		return location;
	}

	@Override
	public Location backwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			location = location.y(decreaseY(terrain));
			break;
		case EAST:
			location = location.x(decreaseX(terrain));
			break;
		case SOUTH:
			location = location.y(increaseY(terrain));
			break;
		case WEST:
			location = location.x(increaseX(terrain));
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
