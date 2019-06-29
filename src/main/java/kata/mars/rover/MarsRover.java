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
		
		if (this.y == 0) {
			this.y = 1;
		} 
		
		else if (this.y == 1) {
			this.y = 2;
		} 
		
		else if (this.y == 2) {
			this.y = 3;
		}
		
		else {
			this.y = 0;
		}
	}

}
