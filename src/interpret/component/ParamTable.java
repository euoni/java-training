package interpret.component;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import interpret.cell.ColorEditor;
import interpret.cell.ColorRenderer;

@SuppressWarnings("serial")
public class ParamTable extends JTable {
	private final Map<String, Object> variables;
	private Class<?> editingClass;

	public ParamTable(ParamModel model, Map<String, Object> variables) {
		super(model);

		this.variables = variables;

		putClientProperty("terminateEditOnFocusLost", true);

		setDefaultRenderer(Color.class, new ColorRenderer());
		setDefaultEditor(Color.class, new ColorEditor());

		setComponentPopupMenu(new CellMenu());
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		editingClass = null;
		final int modelColumn = convertColumnIndexToModel(column);

		if (modelColumn == 1) {
			try {
				final Class<?> rowClass = getModel().getValueAt(row, modelColumn).getClass();
				return getDefaultRenderer(rowClass);
			} catch (final NullPointerException e) {
				;
			}
		}

		return super.getCellRenderer(row, column);
	}

	@Override
	public TableCellEditor getCellEditor(int row, int column) {
		editingClass = null;
		final int modelColumn = convertColumnIndexToModel(column);

		if (modelColumn == 1) {
			try {
				editingClass = ((ParamModel) getModel()).getTypes()[row];
				editingClass = PrimitiveDefaults.wrap(editingClass);
				return getDefaultEditor(editingClass);
			} catch (final NullPointerException e) {
				;
			}
		}

		return super.getCellEditor(row, column);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return editingClass != null ? editingClass : super.getColumnClass(column);
	}

	public static class ParamModel extends AbstractTableModel {
		private final String[] columnNames = { "Type", "Value" };
		private Class<?>[] types;
		private Object[] values;

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return types == null ? 0 : types.length;
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0)
				return types[rowIndex];
			else {
				return values[rowIndex];
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (columnIndex == 1) {
				values[rowIndex] = aValue;
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		public Class<?>[] getTypes() {
			return types;
		}

		public Object[] getValues() {
			return values;
		}

		public void setTypeValueList(Class<?>[] types, Object[] values) {
			this.types = types;
			this.values = values;

			for (int i = 0; i < types.length; i++) {
				if (types[i].isPrimitive())
					values[i] = PrimitiveDefaults.getDefaultValue(types[i]);
			}

			fireTableDataChanged();
		}

		public void setTypeValueList(Executable exec) {
			if (exec == null) {
				setTypeValueList(new Class<?>[0], new Object[0]);
			} else {
				final Class<?>[] parameterTypes = exec.getParameterTypes();
				setTypeValueList(parameterTypes, new Object[parameterTypes.length]);
			}
		}
	}

	private class CellMenu extends JPopupMenu implements PopupMenuListener {
		private int modelRow;
		private int modelCol;

		public CellMenu() {
			final JMenuItem bind = new JMenuItem("Bind to a variable...");
			bind.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					final ParamModel model = (ParamModel) getModel();
					final Class<?> klass = model.getTypes()[modelRow];
					final VariableDialog dialog = new VariableDialog(ParamTable.this, klass, variables);
					dialog.setVisible(true);
					if (!dialog.isCanceled()) {
						final String name = dialog.getSelectedName();

						model.setValueAt(variables.get(name), modelRow, modelCol);
					}
				}
			});
			add(bind);

			addPopupMenuListener(this);
		}

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			SwingUtilities.invokeLater(() -> {
				final Point point = SwingUtilities.convertPoint(this, new Point(0, 0), ParamTable.this);
				final int row = rowAtPoint(point);
				final int col = columnAtPoint(point);
				if (row == -1 || col != 1) {
					setVisible(false);
				}

				modelRow = convertRowIndexToModel(row);
				modelCol = convertColumnIndexToModel(col);
			});
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
		}
	}
}
