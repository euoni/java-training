package gui.ex13;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class DigitalClock extends Window {
	private static final double FPS = 10;

	private final Timer repaintTimer = new Timer("repaint timer");
	private Option option = new Option();
	private final Panel space = new Panel();
	private Point startPos;

	public Image buf;

	public DigitalClock() {
		super(null);
		final DigitalClock me = this;
		setAlwaysOnTop(true);

		// set menu
		final PopupMenu menu = new PopupMenu();
		add(menu);
		option.createMenu(menu, () -> initBuf(null));
		menu.addSeparator();
		menu.add(new MenuItem("Close")).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaintTimer.cancel();
				dispose();
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
			public void windowOpened(WindowEvent e) {
				initBuf(null);
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startPos = e.getPoint();
				if (e.isPopupTrigger()) {
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				final Point eventLocationOnScreen = e.getLocationOnScreen();
				me.setLocation(eventLocationOnScreen.x - startPos.x, eventLocationOnScreen.y - startPos.y);
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
		g.setColor(option.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(option.getForeground());
		g.setFont(option.getFont());
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
		g.setFont(this.option.getFont());
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
