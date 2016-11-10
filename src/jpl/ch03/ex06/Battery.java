package jpl.ch03.ex06;

public class Battery extends EnergySource {
	private double power;

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	@Override
	public boolean empty() {
		return power == 0.;
	}

}
