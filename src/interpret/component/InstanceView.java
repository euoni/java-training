package interpret.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

@SuppressWarnings("serial")
public class InstanceView extends JComponent {
	private final InstanceViewModel model;
	private final JTable table;

	/**
	 * Create the panel.
	 */
	public InstanceView(InstanceViewModel model) {
		this.model = model;

		IconFontSwing.register(FontAwesome.getIconFont());

		setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		final JPanel btnPanel = new JPanel();
		panel.add(btnPanel, BorderLayout.EAST);

		final JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final CreateInstanceDialog dialog = new CreateInstanceDialog(InstanceView.this, model.getVariableMap());
				dialog.setVisible(true);
				if (!dialog.isCanceled()) {
					model.add(dialog.getInstanceName(), dialog.getInstance());
					table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
				}
			}
		});
		btnPanel.add(btnAdd);
		btnAdd.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, btnAdd.getFont().getSize()));

		final JButton btnRemove = new JButton("Remove");
		btnPanel.add(btnRemove);
		btnRemove.setEnabled(false);
		btnRemove.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS_CIRCLE, btnRemove.getFont().getSize()));

		final JLabel lblObjects = new JLabel("Objects");
		panel.add(lblObjects, BorderLayout.CENTER);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int index = table.getSelectionModel().getMinSelectionIndex();
				if (index != -1) {
					if (model.getRowCount() == 1)
						table.setRowSorter(null);
					model.remove(index);
				}
			}
		});

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnRemove.setEnabled(!table.getSelectionModel().isSelectionEmpty());
			}
		});
	}

	public void register(String name, Object obj) {
		model.add(name, obj);
	}

	public String getSelectedName() {
		final int index = table.getSelectionModel().getMinSelectionIndex();
		return index == -1 ? null : model.getName(index);
	}

	public Object getSelectedObject() {
		final int index = table.getSelectionModel().getMinSelectionIndex();
		return index == -1 ? null : model.getObj(index);
	}

	public void addListSelectionListener(ListSelectionListener l) {
		table.getSelectionModel().addListSelectionListener(l);
	}

	public void removeListSelectionListener(ListSelectionListener l) {
		table.getSelectionModel().removeListSelectionListener(l);
	}
}
