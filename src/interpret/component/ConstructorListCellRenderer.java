package interpret.component;

import java.awt.Component;
import java.lang.reflect.Constructor;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ConstructorListCellRenderer extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (value instanceof Constructor<?>) {
			final Constructor<?> ctor = (Constructor<?>) value;

			final StringBuilder param = new StringBuilder();
			for (final Class<?> type : ctor.getParameterTypes()) {
				param.append(type.getName().replace("java.lang.", ""));
				param.append(", ");
			}

			value = ctor.getDeclaringClass().getName() + "("
					+ (param.length() > 0 ? param.substring(0, param.length() - 2) : "") + ")";
		}

		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
}
