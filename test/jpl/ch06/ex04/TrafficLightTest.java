package jpl.ch06.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TrafficLightTest {
	@Test
	public void testGetColor() {
		assertThat(TrafficLight.BLUE.getColor(), is(Color.BLUE));
		assertThat(TrafficLight.YELLOW.getColor(), is(Color.YELLOW));
		assertThat(TrafficLight.RED.getColor(), is(Color.RED));
	}

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
