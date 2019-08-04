package my.katas.rover;

public class RoverWest implements Locatable, Moveable, Turnable {

	private Integer x;
	private Integer y;

	public RoverWest(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Moveable moveForward(final Integer times) {
		for (int i = 0; i < times; i++) {
			if (x <= 0) {
				x = 99;
			} else {
				x--;
			}
		}
		return this;
	}

	@Override
	public Moveable moveBackward(final Integer times) {
		for (int i = 0; i < times; i++) {
			if (x >= 99) {
				x = 0;
			} else {
				x++;
			}
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
