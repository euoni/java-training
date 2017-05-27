package gui.ex22;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorIcon implements Icon {
	private final Color color;
	private final int size;

	public ColorIcon(Color color, int size) {
		this.color = color;
		this.size = size;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		final Graphics g2 = g.create();
		g2.translate(x, y);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getIconWidth(), getIconHeight());
		g2.setColor(color);
		g2.fillRect(1, 1, getIconWidth() - 2, getIconHeight() - 2);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return size;
	}

	@Override
	public int getIconHeight() {
		return size;
	}

}
