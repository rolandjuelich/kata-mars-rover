package my.katas.rover;

public class Rover {

	private int x;
	private int y;
	private char heading;

	public Rover(int x, int y, char heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public char heading() {
		return this.heading;
	}

	public void forward() {
		if (heading == 'N') {
			x = 0;
			y = 1;
		} else if (heading == 'E') {
			x = 1;
			y = 0;
		}
	}

}
