package interpret.cell;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class ColorEditor extends AbstractCellEditor implements TableCellEditor {
	private Color currentColor;
	private final JButton button;
	private final JColorChooser colorChooser;

	public ColorEditor() {
		button = new JButton();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorChooser.setColor(currentColor);

				final JDialog dialog = JColorChooser.createDialog(button, "Pick a Color", true, colorChooser,
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								currentColor = colorChooser.getColor();
							}
						}, null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				fireEditingStopped();
			}
		});
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);

		colorChooser = new JColorChooser();
	}

	@Override
	public Object getCellEditorValue() {
		return currentColor;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		currentColor = (Color) value;
		button.setIcon(new ColorIcon(currentColor, button.getFont().getSize()));
		button.setText(ColorRenderer.getColorString(currentColor));
		return button;
	}
}
