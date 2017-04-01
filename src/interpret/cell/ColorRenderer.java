package interpret.cell;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ColorRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		final JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (value instanceof Color) {
			final Color color = (Color) value;
			l.setHorizontalAlignment(CENTER);
			l.setIcon(new ColorIcon(color, l.getFont().getSize()));
			l.setText(getColorString(color));
		}
		return l;
	}

	static String getColorString(Color color) {
		if (color == null)
			return "";
		else
			return String.format("#%02X%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue(),
					color.getAlpha());
	}
}
