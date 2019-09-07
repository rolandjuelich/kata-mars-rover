package my.katas.interaction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Interaction<C, E> {

	private final C command;
	private final Class<E> event;

	public static <C, E> Interaction<C, E> of(final C command, final Class<E> event) {
		return new Interaction<C, E>(command, event);
	}

}