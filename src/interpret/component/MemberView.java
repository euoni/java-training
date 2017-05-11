package interpret.component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import interpret.component.PropertyTable.PropertyModel;

@SuppressWarnings("serial")
public class MemberView extends JPanel {
	private final CardLayout layout;
	private final PropertyModel model = new PropertyModel();
	private Object obj;

	/**
	 * Create the panel.
	 */
	public MemberView() {
		layout = new CardLayout(0, 0);
		setLayout(layout);

		final JPanel panelEmpty = new JPanel();
		add(panelEmpty, "panelEmpty");
		panelEmpty.setLayout(new BorderLayout(0, 0));

		final JLabel lblSelectObject = new JLabel("Select object");
		lblSelectObject.setHorizontalAlignment(SwingConstants.CENTER);
		panelEmpty.add(lblSelectObject, BorderLayout.CENTER);

		final JPanel panelSelected = new JPanel();
		add(panelSelected, "panelSelected");

		final JButton btnSelectMethod = new JButton("Select method");
		btnSelectMethod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final RunMethodDialog dialog = new RunMethodDialog(MemberView.this, obj.getClass());
				dialog.setVisible(true);
				if (!dialog.isCanceled()) {
					Object ret;
					try {
						ret = dialog.getMethod().invoke(obj, dialog.getParams());
					} catch (IllegalAccessException | IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(MemberView.this, ex.getMessage(), ex.getClass().getName(),
								JOptionPane.ERROR_MESSAGE);
						return;
					} catch (final InvocationTargetException ex) {
						JOptionPane.showMessageDialog(MemberView.this, ex.getCause().getMessage(),
								ex.getCause().getClass().getName(), JOptionPane.ERROR_MESSAGE);
						return;
					}

					JOptionPane.showMessageDialog(MemberView.this, String.valueOf(ret), "Result",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		final JScrollPane scrollPane = new JScrollPane();
		final GroupLayout gl_panelSelected = new GroupLayout(panelSelected);
		gl_panelSelected
				.setHorizontalGroup(
						gl_panelSelected.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSelected.createSequentialGroup().addContainerGap()
										.addGroup(gl_panelSelected.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 426,
														Short.MAX_VALUE)
												.addComponent(btnSelectMethod))
										.addContainerGap()));
		gl_panelSelected.setVerticalGroup(gl_panelSelected.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelected.createSequentialGroup().addContainerGap().addComponent(btnSelectMethod)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE).addContainerGap()));

		final PropertyTable propertyTable = new PropertyTable(model);
		propertyTable.setModel(model);
		propertyTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(propertyTable);
		panelSelected.setLayout(gl_panelSelected);
	}

	public void setObject(Object obj) {
		this.obj = obj;
		model.setTarget(obj);
		layout.show(this, obj == null ? "panelEmpty" : "panelSelected");
	}
}
