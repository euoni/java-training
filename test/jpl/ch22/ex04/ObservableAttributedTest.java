package jpl.ch22.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import jpl.ch05.ex01.Attributed.Attr;
import jpl.ch22.ex04.ObservableAttributed.Event;

public class ObservableAttributedTest {
	private boolean called = false;

	@Test
	public void testAdd() {
		final ObservableAttributed.Attr attr = new ObservableAttributed.Attr("name", "value");

		final ObservableAttributed attributed = new ObservableAttributed();
		attributed.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				final Event event = (Event) arg;
				assertThat(event.getAttr(), is(attr));
				assertThat(event.getType(), is("add"));
				called = true;
			}
		});

		attributed.add(attr);
		assertThat(called, is(true));
	}

	@Test
	public void testFind() {
		final ObservableAttributed.Attr attr = new ObservableAttributed.Attr("name", "value");
		final ObservableAttributed attributed = new ObservableAttributed();
		attributed.add(attr);

		assertThat(attributed.find("name"), is(attr));
		assertThat(attributed.find("NAME"), nullValue());
	}

	@Test
	public void testRemove() {
		final ObservableAttributed.Attr attr = new ObservableAttributed.Attr("name", "value");
		final ObservableAttributed attributed = new ObservableAttributed();
		attributed.add(attr);

		attributed.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				final Event event = (Event) arg;
				assertThat(event.getAttr(), is(attr));
				assertThat(event.getType(), is("remove"));
				called = true;
			}
		});

		attributed.remove("name");
		assertThat(called, is(true));
	}

	@Test
	public void testAttrs() {
		final ObservableAttributed.Attr attr = new ObservableAttributed.Attr("name", "value");
		final ObservableAttributed attributed = new ObservableAttributed();
		attributed.add(attr);
		final Iterator<Attr> iterator = attributed.attrs();

		assertThat(iterator.next(), is(attr));
		assertThat(iterator.hasNext(), is(false));
	}
}
