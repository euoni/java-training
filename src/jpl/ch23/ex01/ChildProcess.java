package jpl.ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ChildProcess {
	public static Process userProg(String cmd) throws IOException {
		final Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	private static void plugTogether(OutputStream out, InputStream in) {
		plugTogether(in, out);
	}

	private static void plugTogether(InputStream in, OutputStream out) {
		new Thread(() -> {
			try {
				int b;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
			} catch (final IOException e) {
				return;
			}
		}).start();
	}
}
