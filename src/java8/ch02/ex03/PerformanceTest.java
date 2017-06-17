package java8.ch02.ex03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PerformanceTest {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final String contents = new String(
				Files.readAllBytes(Paths.get(PerformanceTest.class.getResource("/java8/ch02/alice.txt").toURI())),
				StandardCharsets.UTF_8);
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		{
			final long startTime = System.nanoTime();
			words.stream().filter(w -> w.length() > 12).count();
			final long endTime = System.nanoTime();
			System.out.printf("stream(): %,3d ns%n", endTime - startTime);
		}
		{
			final long startTime = System.nanoTime();
			words.parallelStream().filter(w -> w.length() > 12).count();
			final long endTime = System.nanoTime();
			System.out.printf("parallelStream(): %,3d ns%n", endTime - startTime);
		}
	}
}
