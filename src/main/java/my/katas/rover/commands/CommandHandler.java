package my.katas.rover.commands;

public interface CommandHandler<C> {

	void handle(final C command);

}
