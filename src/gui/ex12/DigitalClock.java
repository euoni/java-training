package gui.ex12;

import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class DigitalClock extends Frame {
	private static final double FPS = 10;

	private final Timer repaintTimer = new Timer("repaint timer");
	private Option option = new Option();
	private final Panel space = new Panel();

	public Image buf;

	public DigitalClock() {
		setTitle("Digital Clock");
		setResizable(false);

		// set menu
		final MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);

		final Menu menuOpt = menuBar.add(new Menu("Edit"));
		final MenuItem menuPref = menuOpt.add(new MenuItem("Preferences", new MenuShortcut('P')));
		final DigitalClock me = this;
		menuPref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Option selected = OptionDialog.select(me, option);
				initBuf(selected);
			}
		});

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

			@Override
			public void windowOpened(WindowEvent e) {
				initBuf(null);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		drawTime(buf.getGraphics());
		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private void drawTime(Graphics g) {
		g.setColor(option.background);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(option.foreground);
		g.setFont(option.font);
		final String text = getDateString();
		final FontMetrics fm = g.getFontMetrics();
		final Rectangle bounds = fm.getStringBounds(text, g).getBounds();
		final int x = getWidth() / 2 - bounds.width / 2;
		final int y = getHeight() - Math.abs(fm.getDescent()) - fm.getLeading() - 1;
		g.drawString(text, x, y);
	}

	private void initBuf(Option option) {
		if (option != null) {
			this.option = option;
		}

		if (buf == null) {
			buf = createImage(1, 1);
		}

		final String text = getDateString();
		final Graphics g = buf.getGraphics();
		g.setFont(this.option.font);
		final FontMetrics fm = g.getFontMetrics();
		final Rectangle bounds = fm.getStringBounds(text, g).getBounds();

		setLayout(new GridLayout(1, 1));
		space.setPreferredSize(bounds.getSize());
		add(space);
		pack();
		remove(space);

		buf = createImage(getWidth(), getHeight());
	}

	private String getDateString() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> new DigitalClock().setVisible(true));
	}
}
