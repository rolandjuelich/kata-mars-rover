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
		switch (heading) {
		case 'S':
			this.y--;
			break;
		case 'E':
			this.x++;
			break;
		case 'W':
			this.x--;
			break;
		case 'N':
			this.y++;
			break;
		}
	}

	public void backward() {
		
	}


}
