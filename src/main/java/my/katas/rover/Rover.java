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

	public void moveForward() {
	}

	public void moveBackward() {
	}

	public void turnRight() {
		switch (heading) {
		case 'N':
			heading = 'E';
			break;
		case 'E':
			heading = 'S';
			break;
		case 'S':
			heading = 'W';
			break;
		case 'W':
			heading = 'N';
			break;
		default:
			break;
		}
	}

	public void turnLeft() {
		switch (heading) {
		case 'N':
			heading = 'W';
			break;
		case 'W':
			heading = 'S';
			break;
		case 'S':
			heading = 'E';
			break;
		case 'E':
			heading = 'N';
			break;
		default:
			break;
		}
	}

}
