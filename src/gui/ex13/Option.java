package gui.ex13;

import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Menu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

	private <E> void createCheckableMenu(Menu menu, Collection<E> list, Supplier<E> getter, Consumer<E> setter) {
		for (final E item : list) {
			final CheckboxMenuItem menuItem = new CheckboxMenuItem(item.toString(), getter.get().equals(item));
			menu.add(menuItem);
			menuItem.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					for (int i = 0; i < menu.getItemCount(); i++) {
						if (menuItem != menu.getItem(i))
							((CheckboxMenuItem) menu.getItem(i)).setState(false);
						setter.accept(item);
					}
				}
			});
		}
	}

	public void createMenu(Menu menu, Runnable onUpdated) {
		// Font
		final Menu fontMenu = new Menu("Font");
		menu.add(fontMenu);
		final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final List<String> fonts = Arrays.asList(environment.getAvailableFontFamilyNames());
		createCheckableMenu(fontMenu, fonts, font::getFamily, fn -> {
			font = new Font(fn, Font.PLAIN, font.getSize());
			onUpdated.run();
		});

		// Size
		final Menu sizeMenu = new Menu("Size");
		menu.add(sizeMenu);
		final List<Integer> sizes = Arrays.asList(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 28, 32, 36,
				40, 44, 48, 54, 60, 66, 72, 80, 88, 92);
		createCheckableMenu(sizeMenu, sizes, font::getSize, fs -> {
			font = new Font(font.getFamily(), Font.PLAIN, fs);
			onUpdated.run();
		});

		// Foreground
		final Menu fg = new Menu("Foreground");
		menu.add(fg);
		createCheckableMenu(fg, Arrays.asList(ColorID.values()), () -> ColorID.fromColor(foreground), c -> {
			foreground = c.color;
			onUpdated.run();
		});

		// Background
		final Menu bg = new Menu("Background");
		menu.add(bg);
		createCheckableMenu(bg, Arrays.asList(ColorID.values()), () -> ColorID.fromColor(background), c -> {
			background = c.color;
			onUpdated.run();
		});
	}
}
