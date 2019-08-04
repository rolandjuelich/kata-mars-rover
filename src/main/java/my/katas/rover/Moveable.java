package my.katas.rover;

public interface Moveable extends Locatable {

	Locatable moveForward(final Integer times);

	Locatable moveBackward(final Integer times);

}