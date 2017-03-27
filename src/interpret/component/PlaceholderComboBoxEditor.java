package interpret.component;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class PlaceholderComboBoxEditor extends BasicComboBoxEditor {
	public PlaceholderComboBoxEditor(String placeholder) {
		super();

		getEditorComponent().setPlaceholder(placeholder);
	}

	@Override
	protected JTextField createEditorComponent() {
		final PlaceholderTextField editor = new PlaceholderTextField();
		editor.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		return editor;
	}

	@Override
	public PlaceholderTextField getEditorComponent() {
		return (PlaceholderTextField) super.getEditorComponent();
	}
}
