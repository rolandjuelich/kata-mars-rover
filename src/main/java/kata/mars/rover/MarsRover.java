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

	public char heading() {
		return this.heading;
	}

	public int y() {
		return 1;
	}

	public void forward() {
		
	}

}
