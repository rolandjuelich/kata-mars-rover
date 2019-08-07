package my.katas.rover.state;

import my.katas.rover.Moveable;
import my.katas.rover.Turnable;

public class RoverNorth implements Moveable, Turnable {

	private Integer x;
	private Integer y;

	public RoverNorth(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Moveable moveForward() {
		if (y >= 99) {
			y = 0;
		} else {
			y++;
		}
		return this;
	}

	@Override
	public Moveable moveBackward() {
		if (y <= 0) {
			y = 99;
		} else {
			y--;
		}
		return this;
	}

	@Override
	public Turnable turnRight() {
		return new RoverEast(x, y);
	}

	@Override
	public Turnable turnLeft() {
		return new RoverWest(x, y);
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
		return "North";
	}

}
