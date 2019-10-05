package my.katas.rover.terrain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Dimension {

	private final int min;
	private final int max;

	public static Dimension from(int min) {
		return new Dimension(min, min);
	}

	public Dimension to(int max) {
		return new Dimension(min,max);
	}
	

}
