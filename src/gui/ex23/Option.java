package gui.ex23;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

public class Option {
	private static final String FONT_PATH = "/DSEG7Modern-Regular.ttf";
	private static final int FONT_SIZE = 48;

	enum ColorID {
		WHITE(Color.WHITE), LIGHT_GRAY(Color.LIGHT_GRAY), GRAY(Color.GRAY), DARK_GRAY(Color.DARK_GRAY), BLACK(
				Color.BLACK), RED(Color.RED), PINK(Color.PINK), ORANGE(Color.ORANGE), YELLOW(
						Color.YELLOW), GREEN(Color.GREEN), MAGENTA(Color.MAGENTA), CYAN(Color.CYAN), BLUE(Color.BLUE);

		private final Color color;

		private ColorID(Color color) {
			this.color = color;
		}

		public Color getColor() {
			return color;
		}

		static ColorID fromColor(Color color) {
			for (final ColorID id : values()) {
				if (id.getColor() == color) {
					return id;
				}
			}
			return WHITE;
		}
	}

	private Font font;
	private Color foreground;
	private Color background;

	public Font getFont() {
		return font;
	}

	public Color getForeground() {
		return foreground;
	}

	public Color getBackground() {
		return background;
	}

	public Option() {
		// set default font
		final URL url = getClass().getResource(FONT_PATH);
		try (InputStream is = url.openStream()) {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) FONT_SIZE);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (FontFormatException | IOException | NullPointerException e) {
			font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
		}

		// set default color
		foreground = Color.BLACK;
		background = Color.WHITE;
	}

	private <E> void createCheckableMenu(JMenu menu, List<E> list, Supplier<E> getter, Consumer<E> setter,
			List<ColorID> colors) {
		for (int i = 0; i < list.size(); i++) {
			final E item = list.get(i);

			final JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(item.toString(), getter.get().equals(item));

			if (colors != null) {
				final ColorID color = colors.get(i);
				menuItem.setIcon(new ColorIcon(color.getColor(), menuItem.getFont().getSize()));
			}

			menu.add(menuItem);
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < menu.getItemCount(); i++) {
						((JCheckBoxMenuItem) menu.getItem(i)).setState(menuItem == menu.getItem(i));
						setter.accept(item);
					}
				}
			});
		}
	}

	public void createMenu(JPopupMenu menu, Runnable onUpdated) {
		// Font
		final JMenu fontMenu = new JMenu("Font");
		menu.add(fontMenu);
		final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final List<String> fonts = Arrays.asList(environment.getAvailableFontFamilyNames());
		createCheckableMenu(fontMenu, fonts, font::getFamily, fn -> {
			font = new Font(fn, Font.PLAIN, font.getSize());
			onUpdated.run();
		}, null);

		// Size
		final JMenu sizeMenu = new JMenu("Size");
		menu.add(sizeMenu);
		final List<Integer> sizes = Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 28, 32, 36,
				40, 44, 48, 54, 60, 66, 72, 80, 88, 92);
		createCheckableMenu(sizeMenu, sizes, font::getSize, fs -> {
			font = new Font(font.getFamily(), Font.PLAIN, fs);
			onUpdated.run();
		}, null);

		// Foreground
		final JMenu fg = new JMenu("Foreground");
		menu.add(fg);
		createCheckableMenu(fg, Arrays.asList(ColorID.values()), () -> ColorID.fromColor(foreground), c -> {
			foreground = c.color;
			onUpdated.run();
		}, Arrays.asList(ColorID.values()));

		// Background
		final JMenu bg = new JMenu("Background");
		menu.add(bg);
		createCheckableMenu(bg, Arrays.asList(ColorID.values()), () -> ColorID.fromColor(background), c -> {
			background = c.color;
			onUpdated.run();
		}, Arrays.asList(ColorID.values()));
	}
}
