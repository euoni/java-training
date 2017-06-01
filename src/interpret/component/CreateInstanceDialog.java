package interpret.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interpret.component.ParamTable.ParamModel;
import interpret.data.ClassList;

@SuppressWarnings("serial")
public class CreateInstanceDialog extends JDialog {
	private final ParamModel paramModel = new ParamModel();
	private final PlaceholderComboBoxEditor boxEditor;
	private final PlaceholderTextField txtName;
	private final SuggestionComboBox<String> combClass;
	private final JComboBox<Constructor<?>> combCtor;
	private final ParamTable paramTable;
	private JButton btnOk;
	private Object instance;
	private boolean canceled = true;
	private JTabbedPane tabbedPane;
	private JSpinner spinLength;

	public CreateInstanceDialog(Component owner, Map<String, Object> variables, String forceName, Class<?> superClass) {
		super(SwingUtilities.getWindowAncestor(owner));

		setModal(true);
		setTitle("Create instance");
		setBounds(100, 100, 450, 300);

		txtName = new PlaceholderTextField();
		txtName.setPlaceholder("name of new instance");
		txtName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				enableOK();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				enableOK();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				enableOK();
			}
		});

		combClass = new SuggestionComboBox<>();
		combClass.setEditable(true);

		final ClassList classList = new ClassList();
		combClass.setCandidates(classList.getAllClassList());

		final JLabel lblName = new JLabel("Name:");
		lblName.setDisplayedMnemonic('N');
		lblName.setLabelFor(txtName);

		final JLabel lblClass = new JLabel("Class:");
		lblClass.setDisplayedMnemonic('C');
		lblClass.setLabelFor(combClass);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instance = createInstance();
				if (instance != null) {
					canceled = false;
					setVisible(false);
				}
			}
		});
		btnOk.setMnemonic('O');

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblName)
										.addComponent(lblClass))
								.addGap(36)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
										.addComponent(combClass, GroupLayout.PREFERRED_SIZE, 343, Short.MAX_VALUE)))
						.addComponent(btnOk)).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblClass).addComponent(
						combClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnOk).addContainerGap()));

		final JPanel singlePanel = new JPanel();
		tabbedPane.addTab("Single", null, singlePanel, null);
		singlePanel.setName("");

		final JLabel lblParameters = new JLabel("Parameters:");
		lblParameters.setDisplayedMnemonic('P');

		final JScrollPane scrollPane = new JScrollPane();
		paramTable = new ParamTable(paramModel, variables);
		paramTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(paramTable);

		final JLabel lblConstructor = new JLabel("Constructor:");
		lblConstructor.setDisplayedMnemonic('s');

		combCtor = new JComboBox<>();
		combCtor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				initParamTable();
			}
		});
		combCtor.setRenderer(new ConstructorListCellRenderer());

		final GroupLayout gl_singlePanel = new GroupLayout(singlePanel);
		gl_singlePanel.setHorizontalGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_singlePanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING).addComponent(lblConstructor)
								.addComponent(lblParameters))
						.addGap(12)
						.addGroup(gl_singlePanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE).addComponent(
										combCtor, 0, 314, Short.MAX_VALUE))
						.addContainerGap()));
		gl_singlePanel
				.setVerticalGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_singlePanel.createSequentialGroup().addContainerGap()
								.addGroup(gl_singlePanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(combCtor, GroupLayout.PREFERRED_SIZE, 17,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConstructor))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_singlePanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addComponent(lblParameters))
								.addContainerGap()));
		singlePanel.setLayout(gl_singlePanel);

		lblParameters.setLabelFor(paramTable);

		final JPanel arrayPanel = new JPanel();
		tabbedPane.addTab("Array", null, arrayPanel, null);
		arrayPanel.setName("array");

		final JLabel lblLength = new JLabel("Length:");

		spinLength = new JSpinner();
		spinLength.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		final GroupLayout gl_arrayPanel = new GroupLayout(arrayPanel);
		gl_arrayPanel.setHorizontalGroup(gl_arrayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_arrayPanel.createSequentialGroup().addContainerGap().addComponent(lblLength)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(spinLength, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE).addContainerGap()));
		gl_arrayPanel.setVerticalGroup(gl_arrayPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_arrayPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_arrayPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblLength).addComponent(
						spinLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(110, Short.MAX_VALUE)));
		arrayPanel.setLayout(gl_arrayPanel);
		getContentPane().setLayout(groupLayout);

		boxEditor = new PlaceholderComboBoxEditor("java.awt.Frame");
		boxEditor.getEditorComponent().setInputVerifier(new InputVerifier() {
			private Color bg;

			@Override
			public boolean verify(JComponent input) {
				if (bg == null)
					bg = input.getBackground();

				Class<?> klass;
				try {
					klass = Class.forName(boxEditor.getItem().toString());
				} catch (final ClassNotFoundException | NullPointerException e) {
					input.setBackground(Color.PINK);
					return false;
				}

				if (superClass != null && !superClass.isAssignableFrom(klass)) {
					input.setBackground(Color.PINK);
					return false;
				}

				input.setBackground(bg);
				return true;
			}
		});
		boxEditor.getEditorComponent().getDocument().addDocumentListener(new DocumentListener() {
			private void checkClass() {
				try {
					final Constructor<?>[] constructors = Class.forName(boxEditor.getItem().toString())
							.getConstructors();
					combCtor.setModel(new DefaultComboBoxModel<>(constructors));
					initParamTable();
				} catch (final ClassNotFoundException | NullPointerException e) {
					if (combCtor.getModel().getSize() > 0) {
						combCtor.setModel(new DefaultComboBoxModel<>());
						initParamTable();
					}
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkClass();
				enableOK();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkClass();
				enableOK();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkClass();
				enableOK();
			}
		});
		combClass.setEditor(boxEditor);

		if (forceName != null) {
			txtName.setText(forceName);
			txtName.setEnabled(false);
			tabbedPane.setEnabledAt(1, false);
		}

		if (superClass != null) {
			combClass.setSelectedItem(superClass.getCanonicalName());
		}
	}

	private void initParamTable() {
		paramModel.setTypeValueList((Constructor<?>) combCtor.getSelectedItem());
	}

	private Object createInstance() {
		if (tabbedPane.getSelectedIndex() == 0) {
			final Constructor<?> ctor = (Constructor<?>) combCtor.getSelectedItem();
			final Object[] args = paramModel.getValues();

			try {
				return ctor.newInstance(args);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				MessageDialog.showException(this, e);
				return null;
			}
		} else {
			Class<?> klass;
			try {
				klass = Class.forName((String) combClass.getSelectedItem());
			} catch (final ClassNotFoundException e) {
				MessageDialog.showException(this, e);
				return null;
			}
			return Array.newInstance(klass, (int) spinLength.getValue());
		}
	}

	private boolean isFilled() {
		if (txtName.getText().isEmpty())
			return false;

		if (tabbedPane.getSelectedIndex() == 0) {
			if (combCtor.getSelectedItem() == null)
				return false;
		}

		return true;
	}

	private void enableOK() {
		btnOk.setEnabled(isFilled());
	}

	public Object getInstance() {
		return instance;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public String getInstanceName() {
		return txtName.getText();
	}
}
