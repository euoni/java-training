package jpl.ch04.ex01;

public class GasTank implements EnergySource {
	private double gas;

	public double getGas() {
		return gas;
	}

	public void setGas(double gas) {
		this.gas = gas;
	}

	@Override
	public boolean empty() {
		return gas == 0.;
	}
}
