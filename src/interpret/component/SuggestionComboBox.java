package interpret.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SuggestionComboBox<E> extends JComboBox<E> implements KeyListener {
	private static final int MAX_SUGGESTION = 100;
	private Collection<E> candidates;
	private boolean shouldHide = false;

	public SuggestionComboBox() {
		super();
	}

	public SuggestionComboBox(ComboBoxModel<E> aModel) {
		super(aModel);
	}

	public SuggestionComboBox(E[] items) {
		super(items);
	}

	public SuggestionComboBox(Vector<E> items) {
		super(items);
	}

	@Override
	public void setEditor(ComboBoxEditor anEditor) {
		final ComboBoxEditor oldEditor = getEditor();

		if (oldEditor == anEditor)
			return;

		if (oldEditor != null)
			oldEditor.getEditorComponent().removeKeyListener(this);
		if (anEditor != null)
			anEditor.getEditorComponent().addKeyListener(this);

		super.setEditor(anEditor);
	}

	public Collection<E> getCandidates() {
		return candidates;
	}

	public void setCandidates(Collection<E> candidates) {
		this.candidates = candidates;

		setSuggestionModel(new DefaultComboBoxModel<>(new Vector<>(candidates)), "");
		setSelectedIndex(-1);
	}

	private void setSuggestionModel(ComboBoxModel<E> model, String text) {
		setModel(model);
		((JTextField) getEditor().getEditorComponent()).setText(text);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final String text = ((JTextField) e.getComponent()).getText();
				if (text.isEmpty()) {
					setSuggestionModel(new DefaultComboBoxModel<>(new Vector<>(candidates)), "");
					setSelectedIndex(-1);
					hidePopup();
				} else {
					final List<E> filtered = candidates.stream().filter(c -> c.toString().contains(text))
							.limit(MAX_SUGGESTION).collect(Collectors.toList());
					setSuggestionModel(new DefaultComboBoxModel<>(new Vector<>(filtered)), text);
					((JTextField) getEditor().getEditorComponent()).setText(text);
					if (filtered.isEmpty() || shouldHide) {
						hidePopup();
					} else {
						showPopup();
					}
				}
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		shouldHide = false;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			getEditor().setItem(getSelectedItem());
			shouldHide = true;
			break;
		case KeyEvent.VK_ESCAPE:
			shouldHide = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
