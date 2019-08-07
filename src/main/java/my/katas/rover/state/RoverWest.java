package my.katas.rover.state;

import my.katas.rover.Locatable;
import my.katas.rover.Moveable;
import my.katas.rover.Turnable;

public class RoverWest implements Locatable, Moveable, Turnable {

	private Integer x;
	private Integer y;

	public RoverWest(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Moveable moveForward() {
		if (x <= 0) {
			x = 99;
		} else {
			x--;
		}
		return this;
	}

	@Override
	public Moveable moveBackward() {
		if (x >= 99) {
			x = 0;
		} else {
			x++;
		}
		return this;
	}

	@Override
	public Turnable turnRight() {
		return new RoverNorth(x, y);
	}

	@Override
	public Turnable turnLeft() {
		return new RoverSouth(x, y);
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
		return "West";
	}

}
