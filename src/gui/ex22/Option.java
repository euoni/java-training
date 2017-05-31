package gui.ex22;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class Option {
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

	Font font;
	Color foreground;
	Color background;

	Option() {
		// set default font
		final URL url = getClass().getResource(FONT_PATH);
		try (InputStream is = url.openStream()) {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) FONT_SIZE);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (FontFormatException | IOException e) {
			font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
		}

		// set default color
		foreground = Color.BLACK;
		background = Color.WHITE;
	}

	static Option createFromString(String font, String size, String fg, String bg) {
		final Option setting = new Option();
		setting.font = new Font(font, Font.PLAIN, Integer.parseInt(size));
		setting.foreground = ColorID.valueOf(fg).getColor();
		setting.background = ColorID.valueOf(bg).getColor();
		return setting;
	}
}
