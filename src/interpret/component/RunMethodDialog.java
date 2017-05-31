package interpret.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import interpret.component.ParamTable.ParamModel;

@SuppressWarnings("serial")
public class RunMethodDialog extends JDialog {
	private final ParamModel paramModel = new ParamModel();
	private final SuggestionComboBox<Method> combMethod;
	private final ParamTable paramTable;
	private final JButton btnOk;
	private boolean canceled;
	private final Class<?> klass;

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

		combMethod = new SuggestionComboBox<>();
		combMethod.setEditable(true);
		combMethod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initParamTable();
			}
		});

		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblConstructor).addGap(26)
										.addComponent(combMethod, 0, 345, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblParameters)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
								.addComponent(btnOk, Alignment.TRAILING))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblConstructor)
								.addComponent(combMethod, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblParameters)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnOk).addContainerGap()));

		paramTable = new ParamTable(paramModel);
		lblParameters.setLabelFor(paramTable);
		paramTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(paramTable);
		getContentPane().setLayout(groupLayout);

		canceled = true;
		setMethod();
	}

	private void setMethod() {
		combMethod.setCandidates(Arrays.asList(klass.getMethods()));
		initParamTable();
	}

	private void initParamTable() {
		final Object item = combMethod.getSelectedItem();
		if (item instanceof Method) {
			paramModel.setTypeValueList((Method) item);
			btnOk.setEnabled(true);
		} else
			btnOk.setEnabled(false);
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
