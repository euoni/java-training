package jpl.ch03.ex05;

public class LoopBenchmark extends Benchmark {
	private int loopCount;

	public LoopBenchmark(int loopCount) {
		setLoopCount(loopCount);
	}

	public int getLoopCount() {
		return loopCount;
	}

	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	@Override
	void benchmark() {
		for (int i = 0; i < loopCount; i++)
			;
	}
}
