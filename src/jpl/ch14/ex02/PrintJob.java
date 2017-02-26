package jpl.ch14.ex02;

public class PrintJob {
	private final String content;

	public PrintJob(String content) {
		this.content = content;
	}

	public void print() {
		System.out.println(content);
	}
}
