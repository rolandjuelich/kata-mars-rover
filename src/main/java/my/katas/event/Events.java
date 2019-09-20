package my.katas.event;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Events<T> {

	private final Class<T> type;

	private final List<T> elements;


	public static <T> Events<T> of(final Class<T> type) {
		return new Events<T>(type, new ArrayList<T>());
	}

	@SuppressWarnings("unchecked")
	public Events<T> from(final List<?> source) {
		elements.addAll((List<T>) source.stream().filter(e -> type.isAssignableFrom(e.getClass())).collect(toList()));
		return this;
	}

	public T mostRecent() {
		return elements.isEmpty() ? null : elements.get(elements.size() - 1);
	}

	public List<T> all() {
		return elements;
	}

	public boolean isNotEmpty() {
		return !elements.isEmpty();
	}


}
