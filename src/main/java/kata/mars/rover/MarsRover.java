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
		if('E' == heading) {
			if(this.x == 0) {
				this.x = 1;
			} 
			else if(this.x == 1) {
				this.x = 2;
			} 
			else {
				this.x = 0;
			}
		} else {
			this.y++;
		}
	}

}
