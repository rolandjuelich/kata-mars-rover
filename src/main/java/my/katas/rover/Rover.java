package my.katas.rover;

import static my.katas.rover.functional.Functions.resetTo;
import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;
import static my.katas.rover.turn.Heading.EAST;
import static my.katas.rover.turn.Heading.NORTH;
import static my.katas.rover.turn.Heading.SOUTH;
import static my.katas.rover.turn.Heading.WEST;

import java.util.function.Function;

import lombok.Getter;
import my.katas.rover.functional.Functions;
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

	private Rover(final String terrain, final Location location, final Heading heading) {
		this.terrain = terrain;
		this.location = location;
		this.heading = heading;
	}

	public static Rover initialize(final Terrain terrain, final Location location, final Heading heading) {
		return new Rover(terrain.getName(), location, heading);
	}

	@Override
	public Location forwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			location = location.y(increase(terrain.getMinY(), terrain.getMaxY()));
			break;
		case EAST:
			location = location.x(increase(terrain.getMinX(), terrain.getMaxX()));
			break;
		case SOUTH:
			location = location.y(decrease(terrain.getMinY(), terrain.getMaxY()));
			break;
		case WEST:
			location = location.x(decrease(terrain.getMinX(), terrain.getMaxX()));
			break;
		}
		return location;
	}

	@Override
	public Location backwardOn(final Terrain terrain) {
		switch (heading) {
		case NORTH:
			location = location.y(decrease(terrain.getMinY(), terrain.getMaxY()));
			break;
		case EAST:
			location = location.x(decrease(terrain.getMinX(), terrain.getMaxX()));
			break;
		case SOUTH:
			location = location.y(increase(terrain.getMinY(), terrain.getMaxY()));
			break;
		case WEST:
			location = location.x(increase(terrain.getMinX(), terrain.getMaxX()));
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

	private static Function<Integer, Integer> increase(int min, int max) {
		return Functions.increase().andThen(resetTo(min).onlyIf(greaterThan(max)));
	}

	private static Function<Integer, Integer> decrease(int min, int max) {
		return Functions.decrease().andThen(resetTo(max).onlyIf(smallerThan(min)));
	}

}
