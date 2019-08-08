package my.katas.rover.state;

public interface RoverStateChangedListener {

	void notifyThat(final RoverStateChanged event);
}
