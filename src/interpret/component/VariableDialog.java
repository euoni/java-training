package interpret.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import interpret.data.NamedObject;
import interpret.data.PrimitiveDefaults;

import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class VariableDialog extends JDialog {
	private boolean canceled = true;
	private final JButton btnBind;
	private final SuggestionComboBox<NamedObject> suggestionComboBox;

	public VariableDialog(Component owner, Class<?> klass, Map<String, Object> variables) {
		super(SwingUtilities.getWindowAncestor(owner));

		setModal(true);
		setTitle("Select a variable");
		setBounds(100, 100, 300, 120);

		final JLabel lblVariable = new JLabel("Variable:");

		suggestionComboBox = new SuggestionComboBox<>();
		suggestionComboBox.setEditable(true);
		suggestionComboBox.setCandidates(getVariables(klass, variables));
		suggestionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnBind.setEnabled(suggestionComboBox.getSelectedItem() instanceof NamedObject);
			}
		});

		btnBind = new JButton("Bind");
		btnBind.setEnabled(false);
		btnBind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canceled = false;
				setVisible(false);
			}
		});
		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblVariable)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(suggestionComboBox,
										GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
						.addComponent(btnBind, Alignment.TRAILING))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVariable).addComponent(
						suggestionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE).addComponent(btnBind)
				.addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}

	private static Collection<NamedObject> getVariables(Class<?> klass, Map<String, Object> variables) {
		klass = PrimitiveDefaults.wrap(klass);
		final List<NamedObject> result = new ArrayList<>();
		for (final Map.Entry<String, Object> entry : variables.entrySet())
			if (klass.isInstance(entry.getValue()))
				result.add(new NamedObject(entry.getKey(), entry.getValue()));

		return result;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public String getSelectedName() {
		final Object item = suggestionComboBox.getSelectedItem();
		if (item instanceof NamedObject) {
			final NamedObject namedObject = (NamedObject) item;
			return namedObject.getName();
		}

		return null;
	}
}
