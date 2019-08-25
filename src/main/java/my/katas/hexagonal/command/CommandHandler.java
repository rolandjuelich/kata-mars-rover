package my.katas.hexagonal.command;

public interface CommandHandler<C> {

	/**
	 * To enable the handler this method has to be annotated with
	 * {@link com.google.common.eventbus.Subscribe}
	 * 
	 * @param command the actual command
	 */
	void handle(final C command);

}
