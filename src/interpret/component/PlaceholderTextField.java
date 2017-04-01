package interpret.component;

import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class PlaceholderTextField extends JTextField {
	private String placeholder = "";

	public PlaceholderTextField() {
		super();
	}

	public PlaceholderTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
	}

	public PlaceholderTextField(int columns) {
		super(columns);
	}

	public PlaceholderTextField(String text, int columns) {
		super(text, columns);
	}

	public PlaceholderTextField(String text) {
		super(text);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (placeholder.isEmpty() || !getText().isEmpty()) {
			return;
		}

		g.setColor(getDisabledTextColor());
		g.drawString(placeholder, getInsets().left, getBaseline(getSize().width, getSize().height));
	}
}
