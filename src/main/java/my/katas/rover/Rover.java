package my.katas.rover;

public class Rover {

	private Integer x;
	private Integer y;
	private Character heading;

	public Rover(final Integer x, final Integer y, final Character heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public Rover moveForward(final Integer times) {
		for (int i = 0; i < times; i++) {
			if (heading.equals('N')) {
				if (y >= 99) {
					y = 0;
				} else {
					y++;
				}
			}
			if (heading.equals('E')) {
				if (x >= 99) {
					x = 0;
				} else {
					x++;
				}
			}
			if (heading.equals('S')) {
				if (y <= 0) {
					y = 99;
				} else {
					y--;
				}
			}
			if (heading.equals('W')) {
				if (x <= 0) {
					x = 99;
				} else {
					x--;
				}
			}
		}
		return this;
	}

	public Rover moveBackward(final Integer times) {
		for (int i = 0; i < times; i++) {
			if (heading.equals('S')) {
				if (y >= 99) {
					y = 0;
				} else {
					y++;
				}
			}
			if (heading.equals('W')) {
				if (x >= 99) {
					x = 0;
				} else {
					x++;
				}
			}
			if (heading.equals('N')) {
				if (y <= 0) {
					y = 99;
				} else {
					y--;
				}
			}
			if (heading.equals('E')) {
				if (x <= 0) {
					x = 99;
				} else {
					x--;
				}
			}
		}
		return this;
	}

	public Rover turnRight() {
		if (heading.equals('N')) {
			heading = 'E';
		} else if (heading.equals('E')) {
			heading = 'S';
		} else if (heading.equals('S')) {
			heading = 'W';
		} else if (heading.equals('W')) {
			heading = 'N';
		}
		return this;
	}

	public Rover turnLeft() {
		if (heading.equals('N')) {
			heading = 'W';
		} else if (heading.equals('E')) {
			heading = 'N';
		} else if (heading.equals('S')) {
			heading = 'E';
		} else if (heading.equals('W')) {
			heading = 'S';
		}
		return this;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public String heading() {
		if (heading.equals('N')) {
			return "north";
		}
		if (heading.equals('S')) {
			return "south";
		}
		if (heading.equals('E')) {
			return "east";
		}
		if (heading.equals('W')) {
			return "west";
		}
		return heading.toString();
	}

}
