package kata.mars.rover;

public class MarsRover {

	private int x;
	private int y;
	private char heading;

	public MarsRover(int x, int y, char heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public char heading() {
		return this.heading;
	}

	public void forward() {
		if ('S' == heading) {
			this.y--;
		} else if ('E' == heading) {
			this.x++;
		} else if ('W' == heading) {
			this.x--;
		} else if ('N' == heading) {
			this.y++;
		}
	}

}
