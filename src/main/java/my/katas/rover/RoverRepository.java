package my.katas.rover;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class RoverRepository {

	private Rover rover;

	public void save(final Rover rover) {
		this.rover = rover;
	}

	public Rover load() {
		return rover;
	}

}
