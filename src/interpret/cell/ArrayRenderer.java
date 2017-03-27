package interpret.cell;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import interpret.component.ArrayViewDialog;

@SuppressWarnings("serial")
public class ArrayRenderer extends DefaultTableCellRenderer {
	private final JPanel panel = new JPanel();
	private Object value;
	private final JButton button;

	public ArrayRenderer() {
		panel.setLayout(new BorderLayout());

		button = new JButton("View");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (value.getClass().isArray()) {
					final Object[] array = new Object[Array.getLength(value)];
					for (int i = 0; i < array.length; i++) {
						array[i] = Array.get(value, i);
					}

					final ArrayViewDialog dialog = new ArrayViewDialog(ArrayRenderer.this, array);
					dialog.setVisible(true);
				}
			}
		});
		panel.add(button, BorderLayout.EAST);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.value = value;

		if (value.getClass().isArray()) {
			button.setVisible(true);
			value = getClassName(value);
		} else
			button.setVisible(false);

		final Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		panel.add(comp, BorderLayout.CENTER);

		if (isSelected)
			panel.setBackground(table.getSelectionBackground());
		else
			panel.setBackground(table.getBackground());

		return panel;
	}

	static String getClassName(Object value) {
		final String name = value.getClass().getName().replace("java.lang.", "");
		if (value.getClass().isArray()) {
			final int dim = name.length() - name.replace("[", "").length();

			String nameArray = name.replaceAll("\\[+L?([^;]+);?", "$1") + "[" + Array.getLength(value) + "]";
			for (int i = 1; i < dim; i++)
				nameArray += "[]";
			return nameArray;
		}

		return name;
	}
}
