package gui.ex11;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class DigitalClock extends Frame {
	private static final String FONT_PATH = "/DSEG7Modern-Regular.ttf";
	private static final int FONT_SIZE = 48;
	private static final double FPS = 10;

	private final Timer repaintTimer = new Timer("repaint timer");
	private Font font;

	public DigitalClock() {
		setTitle("Digital Clock");
		setSize(300, 150);

		// set default font
		final URL url = getClass().getResource(FONT_PATH);
		try (InputStream is = url.openStream()) {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) FONT_SIZE);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (FontFormatException | IOException e) {
			font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
		}

		// start timer
		repaintTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 0, (int) (1000. / FPS));

		// register listener
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				repaintTimer.cancel();
				dispose();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		final String text = new SimpleDateFormat("HH:mm:ss").format(new Date());

		// draw at center
		g.setFont(font);
		final FontMetrics fm = g.getFontMetrics();
		final Rectangle rectText = fm.getStringBounds(text, g).getBounds();
		final int x = getWidth() / 2 - rectText.width / 2;
		final int y = getHeight() / 2 - rectText.height / 2 + fm.getMaxAscent();
		g.drawString(text, x, y);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> new DigitalClock().setVisible(true));
	}
}
