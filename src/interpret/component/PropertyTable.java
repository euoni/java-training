package interpret.component;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import interpret.cell.ArrayEditor;
import interpret.cell.ArrayRenderer;
import interpret.cell.ColorEditor;
import interpret.cell.ColorRenderer;
import interpret.cell.FieldRenderer;

@SuppressWarnings("serial")
public class PropertyTable extends JTable {
	public static class PropertyModel extends AbstractTableModel {
		private final String[] columnNames = { "Name", "Value" };
		private Object target;

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return target == null ? 0 : target.getClass().getDeclaredFields().length;
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			final Field field = target.getClass().getDeclaredFields()[rowIndex];
			field.setAccessible(true);
			if (columnIndex == 0)
				return field;
			else {
				try {
					return field.get(target);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (columnIndex == 1) {
				final Field field = target.getClass().getDeclaredFields()[rowIndex];
				field.setAccessible(true);

				try {
					final int mod = field.getModifiers();
					if (Modifier.isStatic(mod) && Modifier.isFinal(mod))
						setFinalStatic(field, aValue);
					else
						field.set(target, aValue);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().getName(),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		static void setFinalStatic(Field field, Object newValue)
				throws IllegalArgumentException, IllegalAccessException {
			Field modifiersField = null;
			try {
				field = field.getDeclaringClass().getField(field.getName());
				modifiersField = Field.class.getDeclaredField("modifiers");
			} catch (final NoSuchFieldException e) {
			}

			field.setAccessible(true);
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, newValue);
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		public Object getTarget() {
			return target;
		}

		public void setTarget(Object target) {
			this.target = target;
			fireTableDataChanged();
		}
	}

	private Class<?> editingClass;
	private static final ArrayRenderer arrayRenderer = new ArrayRenderer();
	private static final ArrayEditor arrayEditor = new ArrayEditor();

	public PropertyTable(PropertyModel model) {
		super(model);

		setDefaultRenderer(Color.class, new ColorRenderer());
		setDefaultEditor(Color.class, new ColorEditor());
		setDefaultEditor(JFrame.class, null);
		getColumnModel().getColumn(0).setCellRenderer(new FieldRenderer());
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		editingClass = null;
		final int modelColumn = convertColumnIndexToModel(column);

		if (modelColumn == 1) {
			try {
				final Class<?> rowClass = getModel().getValueAt(row, modelColumn).getClass();

				if (rowClass.isArray()) {
					return arrayRenderer;
				}

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
			editingClass = getModel().getValueAt(row, modelColumn).getClass();

			if (editingClass == null || editingClass == JFrame.class)
				return null;
			if (editingClass.isArray())
				return arrayEditor;

			return getDefaultEditor(editingClass);
		}

		return super.getCellEditor(row, column);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return editingClass != null ? editingClass : super.getColumnClass(column);
	}
}
