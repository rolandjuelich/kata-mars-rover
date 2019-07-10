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
		if(heading == 'N') {
			x = 0 ;
		}
		if(heading == 'E') {
			x = 1 ;
		}
		return x;
	}

	public int y() {
		if(heading == 'N') {
			y = 1;
		}
		if(heading == 'E') {
			y = 0;
		}
		return y;
	}

	public char heading() {
		return this.heading;
	}

}
