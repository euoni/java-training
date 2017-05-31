package gui.ex22;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DigitalClock extends JFrame {
	private Option option = new Option();
	private final ClockPanel panel;

	public DigitalClock() {
		setTitle("Digital Clock");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		panel = new ClockPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Option selected = OptionDialog.select(option);
				initBuf(selected);
			}
		});
		menuBar.add(mntmPreferences);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				initBuf(null);
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new DigitalClock().setVisible(true));
	}

	private void initBuf(Option option) {
		if (option != null) {
			this.option = option;
		}

		// calc size
		final Image buf = createImage(1, 1);
		final String text = getDateString();
		final Graphics g = buf.getGraphics();
		g.setFont(this.option.font);
		final FontMetrics fm = g.getFontMetrics();
		final Rectangle bounds = fm.getStringBounds(text, g).getBounds();

		panel.setPreferredSize(bounds.getSize());
		setResizable(true);
		pack();
		setResizable(false);
	}

	private String getDateString() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	private class ClockPanel extends JPanel {
		private static final double FPS = 10;

		private final Timer repaintTimer = new Timer("repaint timer");

		public ClockPanel() {
			// start timer
			repaintTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					repaint();
				}
			}, 0, (int) (1000. / FPS));
			addHierarchyListener(new HierarchyListener() {
				@Override
				public void hierarchyChanged(HierarchyEvent e) {
					if (e.getChangeFlags() == HierarchyEvent.DISPLAYABILITY_CHANGED)
						if (!isDisplayable())
							repaintTimer.cancel();
				}
			});
		}

		@Override
		public void paintComponent(Graphics g) {
			g.setColor(option.background);
			g.fillRect(0, 0, getWidth(), getHeight());

			final String text = new SimpleDateFormat("HH:mm:ss").format(new Date());

			// draw at center
			g.setColor(option.foreground);
			g.setFont(option.font);
			final FontMetrics fm = g.getFontMetrics();
			final Rectangle rectText = fm.getStringBounds(text, g).getBounds();
			final int x = getWidth() / 2 - rectText.width / 2;
			final int y = getHeight() / 2 - rectText.height / 2 + fm.getMaxAscent();
			g.drawString(text, x, y);
		}
	}
}
