package jpl.ch04.ex01;

public class Battery implements EnergySource {
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
