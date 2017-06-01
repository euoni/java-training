package interpret.component;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

public class MessageDialog {
	public static void showException(Component parent, Throwable e) {
		if (e instanceof InvocationTargetException)
			showException(parent, e.getCause());
		else {
			String msg = e.getClass().getName();
			if (e.getMessage() != null)
				msg += System.lineSeparator() + e.getMessage();
			JOptionPane.showMessageDialog(parent, msg, e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
