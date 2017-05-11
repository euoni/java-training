package gui.ex21;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DigitalClock extends JFrame {

	public DigitalClock() {
		setTitle("Digital Clock");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final ClockPanel panel = new ClockPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new DigitalClock().setVisible(true));
	}

	private static class ClockPanel extends JPanel {
		private static final String FONT_PATH = "/DSEG7Modern-Regular.ttf";
		private static final int FONT_SIZE = 48;
		private static final double FPS = 10;

		private final Timer repaintTimer = new Timer("repaint timer");
		private Font font;

		public ClockPanel() {
			// set default font
			final URL url = getClass().getResource(FONT_PATH);
			try (InputStream is = url.openStream()) {
				font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) FONT_SIZE);
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			} catch (FontFormatException | IOException | NullPointerException e) {
				font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
			}

			// start timer
			repaintTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					repaint();
				}
			}, 0, (int) (1000. / FPS));
		}

		@Override
		public void paintComponent(Graphics g) {
			final String text = new SimpleDateFormat("HH:mm:ss").format(new Date());

			// draw at center
			g.setFont(font);
			final FontMetrics fm = g.getFontMetrics();
			final Rectangle rectText = fm.getStringBounds(text, g).getBounds();
			final int x = getWidth() / 2 - rectText.width / 2;
			final int y = getHeight() / 2 - rectText.height / 2 + fm.getMaxAscent();
			g.drawString(text, x, y);
		}
	}
}
