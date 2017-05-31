package jpl.ch23.ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineNumberSubprocess {
	public static void main(String[] args) throws IOException, InterruptedException {
		final ProcessBuilder builder = new ProcessBuilder(args);
		builder.redirectErrorStream(true);

		final Process process = builder.start();
		process.getOutputStream().close();

		final BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		for (int i = 1; (line = stdout.readLine()) != null; i++) {
			System.out.printf("% 4d\t%s%n", i, line);
		}

		process.waitFor();
		stdout.close();
	}
}
