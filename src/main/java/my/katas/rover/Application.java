package my.katas.rover;

import my.katas.rover.state.RoverStateChangedListener;

public class Application {

	private RoverStateChangedListener listener;

	public void initialize(int x, int y, char heading) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void moveForward() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void moveBackward() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void turnLeft() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void turnRight() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public void register(final RoverStateChangedListener handler) {
		this.listener = handler;
	}

	public void unregister(final RoverStateChangedListener handler) {
		this.listener = null;
	}

}
