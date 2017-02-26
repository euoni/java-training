package jpl.ch14.ex08;

public class SafeFriendly {
	private static Object lock = new Object();
	private SafeFriendly partner;
	private final String name;

	public SafeFriendly(String name) {
		this.name = name;
	}

	public void hug() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke "
					+ partner.name + ".hugBack()");
			partner.hugBack();
		}
	}

	private void hugBack() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
		}
	}

	public void becomeFrined(SafeFriendly partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final SafeFriendly jareth = new SafeFriendly("jareth");
		final SafeFriendly cory = new SafeFriendly("cory");

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
