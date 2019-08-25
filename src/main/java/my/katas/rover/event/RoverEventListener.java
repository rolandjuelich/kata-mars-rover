package my.katas.rover.event;

import my.katas.hexagonal.event.EventListener;

public interface RoverEventListener<E extends RoverEvent> extends EventListener<E> {

}
