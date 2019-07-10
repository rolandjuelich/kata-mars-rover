package my.katas.rover;

public class Rover {

	private final int x;
	private final int y;
	private char heading;

	public Rover(int x, int y, char heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return 1;
	}

	public char heading() {
		return this.heading;
	}

}
