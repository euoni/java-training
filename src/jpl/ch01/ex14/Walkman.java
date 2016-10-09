package jpl.ch01.ex14;

public class Walkman {
	protected String tape;

	public void setTape(String tape) {
		this.tape = tape;
	}

	public void play() {
		System.out.println(tape);
	}
}
