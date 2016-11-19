package gui.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class DigitalClockTest {
	@Test
	public void testDigitalClock() {
		final DigitalClock clock = new DigitalClock();

		assertThat(clock.getTitle(), is("Digital Clock"));
		assertThat(clock.getSize(), is(new Dimension(300, 150)));
	}

	@Test
	public void testMain() throws InvocationTargetException, InterruptedException {
		DigitalClock.main(null);

		// wait
		java.awt.EventQueue.invokeAndWait(() -> {
		});
		Thread.sleep(100);

		final Frame[] frames = Frame.getFrames();
		assertThat(frames.length, is(1));
		assertThat(frames[0], is(instanceOf(DigitalClock.class)));

		final DigitalClock clock = (DigitalClock) frames[0];
		assertThat(clock.isVisible(), is(true));

		// close
		clock.dispatchEvent(new WindowEvent(clock, WindowEvent.WINDOW_CLOSING));
		assertThat(clock.isDisplayable(), is(false));
	}
}
