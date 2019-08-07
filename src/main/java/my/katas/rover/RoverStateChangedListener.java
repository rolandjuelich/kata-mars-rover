package my.katas.rover;

public interface RoverStateChangedListener {

	void notifyThat(final RoverStateChanged event);
}
