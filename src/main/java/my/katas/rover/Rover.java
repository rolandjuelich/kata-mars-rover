package my.katas.rover;

import static my.katas.rover.Heading.EAST;
import static my.katas.rover.Heading.NORTH;
import static my.katas.rover.Heading.SOUTH;
import static my.katas.rover.Heading.WEST;
import static my.katas.rover.functional.Functions.decrease;
import static my.katas.rover.functional.Functions.increase;
import static my.katas.rover.functional.Functions.resetTo;
import static my.katas.rover.functional.Functions.with;
import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;

public class Rover implements Turnable, Moveable, Locatable {

	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private int x;
	private int y;
	private Heading heading;

	private Rover(int minX, int maxX, int minY, int maxY, int x, int y, Heading heading) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	@Override
	public void moveForward() {
		switch (heading) {
		case NORTH:
			y = increaseY();
			break;
		case EAST:
			x = increaseX();
			break;
		case SOUTH:
			y = decreaseY();
			break;
		case WEST:
			x = decreaseX();
			break;
		}
	}

	@Override
	public void moveBackward() {
		switch (heading) {
		case NORTH:
			y = decreaseY();
			break;
		case EAST:
			x = decreaseX();
			break;
		case SOUTH:
			y = increaseY();
			break;
		case WEST:
			x = increaseX();
			break;
		}
	}

	@Override
	public void turnRight() {
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
	}

	@Override
	public void turnLeft() {
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
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public String heading() {
		return heading.name();
	}

	private Integer increaseX() {
		return with(x).apply(increase().andThen(resetTo(minX).onlyIf(greaterThan(maxX))));
	}

	private Integer increaseY() {
		return with(y).apply(increase().andThen(resetTo(minY).onlyIf(greaterThan(maxY))));
	}

	private Integer decreaseX() {
		return with(x).apply(decrease().andThen(resetTo(maxX).onlyIf(smallerThan(minX))));
	}

	private Integer decreaseY() {
		return with(y).apply(decrease().andThen(resetTo(maxY).onlyIf(smallerThan(minY))));
	}

	public static Lander landingOn(final Terrain terrain) {
		return new Lander(terrain);
	}

	static class Lander {

		private final Terrain terrain;
		private Heading heading = Heading.NORTH;

		private Lander(final Terrain terrain) {
			this.terrain = terrain;
		}

		public Lander heading(final Heading heading) {
			this.heading = heading;
			return this;
		}

		public Rover startFrom(final Location location) {
			return new Rover(//
					terrain.getMinX(), //
					terrain.getMaxX(), //
					terrain.getMinY(), //
					terrain.getMaxY(), //
					location.getX(), //
					location.getY(), //
					heading);
		}
	}
}
