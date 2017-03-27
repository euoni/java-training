package interpret.component;

import java.awt.Color;
import java.lang.reflect.Executable;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import interpret.cell.ColorEditor;
import interpret.cell.ColorRenderer;

@SuppressWarnings("serial")
public class ParamTable extends JTable {
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

	private Class<?> editingClass;

	public ParamTable(ParamModel model) {
		super(model);

		putClientProperty("terminateEditOnFocusLost", true);

		setDefaultRenderer(Color.class, new ColorRenderer());
		setDefaultEditor(Color.class, new ColorEditor());
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

	public static class PrimitiveDefaults {
		private static boolean DEFAULT_BOOLEAN;
		private static byte DEFAULT_BYTE;
		private static short DEFAULT_SHORT;
		private static int DEFAULT_INT;
		private static long DEFAULT_LONG;
		private static float DEFAULT_FLOAT;
		private static double DEFAULT_DOUBLE;

		public static Object getDefaultValue(Class<?> klass) {
			if (klass == boolean.class) {
				return DEFAULT_BOOLEAN;
			} else if (klass == byte.class) {
				return DEFAULT_BYTE;
			} else if (klass == short.class) {
				return DEFAULT_SHORT;
			} else if (klass == int.class) {
				return DEFAULT_INT;
			} else if (klass == long.class) {
				return DEFAULT_LONG;
			} else if (klass == float.class) {
				return DEFAULT_FLOAT;
			} else if (klass == double.class) {
				return DEFAULT_DOUBLE;
			} else {
				throw new IllegalArgumentException("Class type " + klass + " not supported");
			}
		}

		public static Class<?> wrap(Class<?> klass) {
			if (klass == boolean.class) {
				return Boolean.class;
			} else if (klass == byte.class) {
				return Byte.class;
			} else if (klass == short.class) {
				return Short.class;
			} else if (klass == int.class) {
				return Integer.class;
			} else if (klass == long.class) {
				return Long.class;
			} else if (klass == float.class) {
				return Float.class;
			} else if (klass == double.class) {
				return Double.class;
			}

			return klass;
		}

		private PrimitiveDefaults() {
		}
	}
}
