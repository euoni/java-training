package interpret.component;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import interpret.cell.ArrayEditor;
import interpret.cell.ArrayRenderer;

@SuppressWarnings("serial")
public class ArrayViewDialog extends JDialog {
	private final JTable table;
	private final Object[] array;

	public ArrayViewDialog(Component owner, Object[] array) {
		super(SwingUtilities.getWindowAncestor(owner));

		this.array = array;

		setModal(true);
		setTitle("Array View");
		setBounds(100, 100, 450, 300);

		final JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new ArrayModel());
		table.getColumnModel().getColumn(1).setCellRenderer(new ArrayRenderer());
		table.getColumnModel().getColumn(1).setCellEditor(new ArrayEditor());
		scrollPane.setViewportView(table);

		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE).addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}

	private class ArrayModel extends AbstractTableModel {
		private final String[] columnNames = { "No", "Value" };

		@Override
		public int getRowCount() {
			return array.length;
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
			if (columnIndex == 0)
				return rowIndex;
			else
				return array[rowIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 1)
				return true;
			else
				return false;
		}
	}
}
