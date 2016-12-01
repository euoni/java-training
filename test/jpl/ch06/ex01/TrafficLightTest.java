package jpl.ch06.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TrafficLightTest {
	@Test
	public void testValues() {
		assertThat(TrafficLight.values(),
				is(new TrafficLight[] { TrafficLight.BLUE, TrafficLight.YELLOW, TrafficLight.RED }));
	}

	@Test
	public void testValueOf() {
		assertThat(TrafficLight.valueOf("BLUE"), theInstance(TrafficLight.BLUE));
		assertThat(TrafficLight.valueOf("YELLOW"), theInstance(TrafficLight.YELLOW));
		assertThat(TrafficLight.valueOf("RED"), theInstance(TrafficLight.RED));
	}
}
