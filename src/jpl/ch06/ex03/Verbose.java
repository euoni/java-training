package jpl.ch06.ex03;

public interface Verbose {
	public enum Level {
		SILENT, TERSE, NORMAL, VERBOSE
	}

	void setVerbosity(Level level);

	Level getVerbosity();
}
