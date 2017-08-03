package java8.ch04.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class ObserverTest {
	@Test
	public void testCtor() {
		new Observer();
	}

	@Test
	public void testObserveFunctionOfTRObservableValueOfT() {
		final SimpleStringProperty property = new SimpleStringProperty();
		final ObservableValue<String> observable = Observer.observe(String::toUpperCase, property);

		property.set("a");
		assertThat(observable.getValue(), is("A"));

		property.set("b");
		assertThat(observable.getValue(), is("B"));
	}

	@Test
	public void testObserveBiFunctionOfTURObservableValueOfTObservableValueOfU() {
		final SimpleStringProperty property1 = new SimpleStringProperty();
		final SimpleStringProperty property2 = new SimpleStringProperty();
		final ObservableValue<Boolean> observable = Observer.observe(String::contains, property1, property2);

		property1.set("aa");
		property2.set("a");
		assertThat(observable.getValue(), is(true));

		property1.set("a");
		property2.set("aa");
		assertThat(observable.getValue(), is(false));
	}
}
