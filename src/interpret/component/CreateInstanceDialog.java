package interpret.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interpret.component.ParamTable.ParamModel;

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
	private boolean canceled;

	public CreateInstanceDialog(Component owner, Map<String, Object> variables) {
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

		final JLabel lblConstructor = new JLabel("Constructor:");
		lblConstructor.setDisplayedMnemonic('s');

		final JLabel lblParameters = new JLabel("Parameters:");
		lblParameters.setDisplayedMnemonic('P');

		final JScrollPane scrollPane = new JScrollPane();

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

		combCtor = new JComboBox<>();
		combCtor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				initParamTable();
			}
		});
		combCtor.setRenderer(new ConstructorListCellRenderer());

		paramTable = new ParamTable(paramModel, variables);
		lblParameters.setLabelFor(paramTable);
		paramTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(paramTable);

		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblConstructor)
								.addComponent(lblName).addComponent(lblClass).addComponent(lblParameters))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
								.addComponent(combClass, 0, 343, Short.MAX_VALUE)
								.addComponent(combCtor, 0, 343, Short.MAX_VALUE)))
						.addComponent(btnOk, Alignment.TRAILING))
				.addContainerGap()));
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
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblConstructor)
						.addComponent(combCtor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
						.addComponent(lblParameters))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnOk).addContainerGap()));
		getContentPane().setLayout(groupLayout);

		boxEditor = new PlaceholderComboBoxEditor("java.awt.Frame");
		boxEditor.getEditorComponent().setInputVerifier(new InputVerifier() {
			private Color bg;

			@Override
			public boolean verify(JComponent input) {
				if (bg == null)
					bg = input.getBackground();

				try {
					Class.forName(boxEditor.getItem().toString());
				} catch (final ClassNotFoundException | NullPointerException e) {
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

		canceled = true;
	}

	private void initParamTable() {
		paramModel.setTypeValueList((Constructor<?>) combCtor.getSelectedItem());
	}

	private Object createInstance() {
		final Constructor<?> ctor = (Constructor<?>) combCtor.getSelectedItem();
		final Object[] args = paramModel.getValues();

		try {
			return ctor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			MessageDialog.showException(this, e);
		}

		return null;
	}

	private boolean isFilled() {
		if (txtName.getText().isEmpty())
			return false;

		if (combCtor.getSelectedItem() == null)
			return false;

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
