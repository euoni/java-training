package jpl.ch03.ex06;

public class GasTank extends EnergySource {
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
