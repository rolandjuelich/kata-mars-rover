package my.katas.rover;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class RoverRepository {

	private final Map<RoverId, Rover> storage = new HashMap<RoverId, Rover>();

	public RoverId nextId() {
		return new RoverId(UUID.randomUUID().toString().toUpperCase());
	}

	public void save(final Rover rover) {
		this.storage.put(rover.getId(), rover);
	}

	public Rover findBy(final RoverId id) {
		return this.storage.get(id);
	}

}
