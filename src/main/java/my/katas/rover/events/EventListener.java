package my.katas.rover.events;

public interface EventListener<E> {

	void listenFor(final E event);
}
