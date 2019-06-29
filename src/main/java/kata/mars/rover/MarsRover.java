package kata.mars.rover;

import static java.util.Arrays.asList;

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
		switch (heading) {
		case 'N':
			if (this.y < 100) {
				this.y++;
			} else {
				this.y = 0;
			}
			break;
		case 'S':
			if (this.y > 0) {
				this.y--;
			} else {
				this.y = 100;
			}
			break;
		case 'E':
			if (this.x < 100) {
				this.x++;
			} else {
				this.x = 0;
			}
			break;
		case 'W':
			if (this.x > 0) {
				this.x--;
			} else {
				this.x = 100;
			}
			break;
		}
	}

	public void backward() {
		switch (heading) {
		case 'N':
			if (this.y > 0) {
				this.y--;
			} else {
				this.y = 100;
			}
			break;
		case 'S':
			if (this.y < 100) {
				this.y++;
			} else {
				this.y = 0;
			}
			break;
		case 'W':
			if (this.x < 100) {
				this.x++;
			} else {
				this.x = 0;
			}
			break;
		case 'E':
			if (this.x > 0) {
				this.x--;
			} else {
				this.x = 100;
			}
			break;
		}

	}

	public static MarsRover aRoverFor(int x, int y, char heading) {
		if (!asList("N", "S", "E", "W").contains(heading + "")) {
			throw new IllegalArgumentException("given heading('" + heading + "') is not one of N,S,E,W");
		}
	
		if (x < 0 || x > 100) {
			throw new IllegalArgumentException("X (" + x + ") must be between 0 and 100");
		}
	
		if (y < 0 || y > 100) {
			throw new IllegalArgumentException("Y (" + y + ") must be between 0 and 100");
		}
	
		return new MarsRover(x, y, heading);
	}

	public void turnRight() {
		switch (this.heading) {
		case 'N':
			this.heading = 'E';
			break;
		case 'E':
			this.heading = 'S';
			break;
		case 'S':
			this.heading = 'W';
			break;
		case 'W':
			this.heading = 'N';
			break;
		}
	}

	public void turnLeft() {
		this.heading = 'W';
	}

}
