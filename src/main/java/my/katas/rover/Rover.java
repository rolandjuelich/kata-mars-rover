package my.katas.rover;

public class Rover implements Turnable, Moveable, Locatable {

	private Locatable state;

	public Rover(final Integer x, final Integer y, final Character heading) {
		this.state = Locatable.of(x, y, heading);
	}

	@Override
	public Rover moveForward() {
		state = ((Moveable) state).moveForward();
		return this;
	}

	@Override
	public Rover moveBackward() {
		state = ((Moveable) state).moveBackward();
		return this;
	}

	@Override
	public Rover turnRight() {
		state = ((Turnable) state).turnRight();
		return this;
	}

	@Override
	public Rover turnLeft() {
		state = ((Turnable) state).turnLeft();
		return this;
	}

	@Override
	public int x() {
		return state.x();
	}

	@Override
	public int y() {
		return state.y();
	}

	@Override
	public String heading() {
		return state.heading();
	}

}
