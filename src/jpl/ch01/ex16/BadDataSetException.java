package jpl.ch01.ex16;

import java.io.IOException;

@SuppressWarnings("serial")
public class BadDataSetException extends Exception {
	public String setName;
	public IOException innerException;
}
