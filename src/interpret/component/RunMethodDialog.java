package interpret.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import interpret.component.ParamTable.ParamModel;

@SuppressWarnings("serial")
public class RunMethodDialog extends JDialog {
	private final ParamModel paramModel = new ParamModel();
	private final JComboBox<Method> combMethod;
	private final ParamTable paramTable;
	private final JButton btnOk;
	private boolean canceled;
	private final Class<?> klass;
	private final JCheckBox chckbxNewCheckBox;

	public RunMethodDialog(Component owner, Class<?> klass) {
		super(SwingUtilities.getWindowAncestor(owner));

		this.klass = klass;

		setModal(true);
		setTitle("Run method");
		setBounds(100, 100, 450, 300);

		final JLabel lblConstructor = new JLabel("Method:");
		lblConstructor.setDisplayedMnemonic('s');

		final JLabel lblParameters = new JLabel("Parameters:");
		lblParameters.setDisplayedMnemonic('P');

		final JScrollPane scrollPane = new JScrollPane();

		btnOk = new JButton("Run");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canceled = false;
				setVisible(false);
			}
		});
		btnOk.setMnemonic('O');

		combMethod = new JComboBox<>();
		combMethod.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				initParamTable();
			}
		});

		chckbxNewCheckBox = new JCheckBox("Show Object methods");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMethod();
			}
		});

		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblParameters)
								.addComponent(lblConstructor))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addComponent(
										combMethod, 0, 345, Short.MAX_VALUE)
								.addComponent(chckbxNewCheckBox)))
						.addComponent(btnOk, Alignment.TRAILING))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblConstructor)
						.addComponent(combMethod, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxNewCheckBox).addGap(6)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblParameters))
				.addGap(18).addComponent(btnOk).addGap(26)));

		paramTable = new ParamTable(paramModel);
		lblParameters.setLabelFor(paramTable);
		paramTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(paramTable);
		getContentPane().setLayout(groupLayout);

		canceled = true;
		setMethod();
	}

	private void setMethod() {
		if (chckbxNewCheckBox.isSelected()) {
			combMethod.setModel(new DefaultComboBoxModel<>(klass.getMethods()));
		} else {
			final ArrayList<Method> list = new ArrayList<>();
			for (final Method method : klass.getMethods()) {
				if (method.getDeclaringClass() != Object.class) {
					list.add(method);
				}
			}

			final Method[] arr = new Method[list.size()];
			list.toArray(arr);

			combMethod.setModel(new DefaultComboBoxModel<>(arr));
		}

		btnOk.setEnabled(combMethod.getModel().getSize() > 0);
		initParamTable();
	}

	private void initParamTable() {
		paramModel.setTypeValueList((Method) combMethod.getSelectedItem());
	}

	public boolean isCanceled() {
		return canceled;
	}

	public Method getMethod() {
		return (Method) combMethod.getSelectedItem();
	}

	public Object[] getParams() {
		return paramModel.getValues();
	}
}
