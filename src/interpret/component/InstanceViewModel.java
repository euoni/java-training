package interpret.component;

import java.util.LinkedHashMap;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class InstanceViewModel extends AbstractTableModel {
	private final String[] columnNames = { "Name", "Class" };
	private final LinkedHashMap<String, Object> data = new LinkedHashMap<>();
	private boolean isEditable = false;

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final String key = getName(rowIndex);

		if (columnIndex == 0)
			return key;

		final Object obj = data.get(key);
		return obj.getClass().getName().replace("java.lang.", "");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return isEditable && columnIndex == 2;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		throw new UnsupportedOperationException();
	}

	public void add(String name, Object obj) {
		if (data.containsKey(name))
			throw new IllegalArgumentException(name + " already exists");

		data.put(name, obj);

		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public void remove(int index) {
		if (index < 0)
			throw new IllegalArgumentException();

		data.remove(getName(index));

		fireTableRowsDeleted(index, index);
	}

	public String getName(int index) {
		return (String) data.keySet().toArray()[index];
	}

	public Object getObj(int index) {
		return data.get(getName(index));
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
}
