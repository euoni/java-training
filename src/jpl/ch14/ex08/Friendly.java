package jpl.ch14.ex08;

public class Friendly {
	private Friendly partner;
	private final String name;

	public Friendly(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke " + partner.name
				+ ".hugBack()");
		partner.hugBack();
	}

	private synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
	}

	public void becomeFrined(Friendly partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final Friendly jareth = new Friendly("jareth");
		final Friendly cory = new Friendly("cory");

		jareth.becomeFrined(cory);
		cory.becomeFrined(jareth);

		new Thread(new Runnable() {
			@Override
			public void run() {
				jareth.hug();
			}
		}, "Thread1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				cory.hug();
			}
		}, "Thread2").start();
	}
}
