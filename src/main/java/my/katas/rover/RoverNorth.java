package my.katas.rover;

public class RoverNorth implements Moveable, Turnable {

	private Integer x;
	private Integer y;

	public RoverNorth(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Moveable moveForward(final Integer times) {
		for (int i = 0; i < times; i++) {
			if (y >= 99) {
				y = 0;
			} else {
				y++;
			}
		}
		return this;
	}

	@Override
	public Moveable moveBackward(final Integer times) {
		for (int i = 0; i < times; i++) {
			if (y <= 0) {
				y = 99;
			} else {
				y--;
			}
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
