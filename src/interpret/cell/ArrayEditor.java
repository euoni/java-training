package interpret.cell;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class ArrayEditor extends AbstractCellEditor implements TableCellEditor {
	private final ArrayRenderer renderer = new ArrayRenderer();
	private Object value;

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.value = value;
		return renderer.getTableCellRendererComponent(table, value, true, true, row, column);
	}

	@Override
	public Object getCellEditorValue() {
		return value;
	}
}
