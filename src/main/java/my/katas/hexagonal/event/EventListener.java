package my.katas.hexagonal.event;

public interface EventListener<E> {

	/**
	 * 
	 * To enable the listener this method has to be annotated with {@link com.google.common.eventbus.Subscribe}
	 * 
	 * @param event the actual event
	 * 
	 */
	void listenFor(final E event);
	
}
