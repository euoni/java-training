package jpl.ch03.ex06;

public class Vehicle extends jpl.ch03.ex04.Vehicle {
	private final EnergySource source;

	public Vehicle(EnergySource source) {
		this.source = source;
	}

	public EnergySource getSource() {
		return source;
	}

	public void start() {
		if (source.empty())
			throw new IllegalStateException("Energy source is empty");

		System.out.println("start");
	}
}
