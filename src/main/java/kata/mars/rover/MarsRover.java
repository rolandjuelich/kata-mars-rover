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
			if(this.x < 100) {
				this.x++;
			} else {
				this.x = 0;
			}
			break;
		case 'W':
			this.x--;
			break;
		}
	}

	public void backward() {
		switch (heading) {
		case 'N':
			this.y--;
			break;
		case 'S':
			this.y++;
			break;
		case 'W':
			this.x++;
			break;
		case 'E':
			this.x--;
			break;
		}

	}

}
