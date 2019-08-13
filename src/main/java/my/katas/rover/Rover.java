package my.katas.rover;

import static my.katas.rover.functional.Functions.decrease;
import static my.katas.rover.functional.Functions.increase;
import static my.katas.rover.functional.Functions.resetTo;
import static my.katas.rover.functional.Functions.with;
import static my.katas.rover.functional.Predicates.greaterThan;
import static my.katas.rover.functional.Predicates.smallerThan;

public class Rover implements Turnable, Moveable, Locatable {

	private int x;
	private int y;
	private char heading;

	public Rover(int x, int y, char heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	@Override
	public void moveForward() {
		switch (heading) {
		case 'N':
			y = with(y).apply(increase().andThen(resetTo(0).onlyIf(greaterThan(99))));
			break;
		case 'E':
			x = with(x).apply(increase().andThen(resetTo(0).onlyIf(greaterThan(99))));
			break;
		case 'S':
			y = with(y).apply(decrease().andThen(resetTo(99).onlyIf(smallerThan(0))));
			break;
		case 'W':
			x = with(x).apply(decrease().andThen(resetTo(99).onlyIf(smallerThan(0))));
			break;
		}
	}

	@Override
	public void moveBackward() {
		switch (heading) {
		case 'N':
			y = with(y).apply(decrease().andThen(resetTo(99).onlyIf(smallerThan(0))));
			break;
		case 'E':
			x = with(x).apply(decrease().andThen(resetTo(99).onlyIf(smallerThan(0))));
			break;
		case 'S':
			y = with(y).apply(increase().andThen(resetTo(0).onlyIf(greaterThan(99))));
			break;
		case 'W':
			x = with(x).apply(increase().andThen(resetTo(0).onlyIf(greaterThan(99))));
			break;
		}
	}

	@Override
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
		}
	}

	@Override
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
		}
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public String heading() {
		switch (heading) {
		case 'N':
			return "North";
		case 'E':
			return "East";
		case 'S':
			return "South";
		case 'W':
			return "West";
		default:
			return null;
		}
	}

}
