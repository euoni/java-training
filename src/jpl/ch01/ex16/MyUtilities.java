package jpl.ch01.ex16;

import java.io.FileInputStream;
import java.io.IOException;

public class MyUtilities {
	public double[] getDataSet(String setName) throws BadDataSetException {
		final String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (final IOException e) {
			final BadDataSetException ex = new BadDataSetException();
			ex.file = file;
			ex.innerException = e;
			throw ex;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				; // 無視：データの読み込みには成功しているか，あるいは，BadDataSetExceptionをスローしようとしている
			}
		}
	}

	private double[] readDataSet(FileInputStream in) {
		return null; // dummy
	}
}
