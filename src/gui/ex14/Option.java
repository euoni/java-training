package gui.ex14;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

class Option {
	private static final String FONT_PATH = "/DSEG7Modern-Regular.ttf";
	private static final int FONT_SIZE = 48;
	private final Preferences prefs = Preferences.userRoot().node("/euoni/digitalclock");

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
	ColorID foreground;
	ColorID background;
	Point location;

	Option() {
		// set default font
		final URL url = getClass().getResource(FONT_PATH);
		try (InputStream is = url.openStream()) {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) FONT_SIZE);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
		} catch (FontFormatException | IOException | NullPointerException e) {
			font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
		}

		// set default color
		foreground = ColorID.BLACK;
		background = ColorID.WHITE;

		location = new Point();
	}

	void setFromString(String font, String size, String fg, String bg) {
		this.font = new Font(font, Font.PLAIN, Integer.parseInt(size));
		this.foreground = ColorID.valueOf(fg);
		this.background = ColorID.valueOf(bg);
	}

	void load() {
		setFromString(prefs.get("font", font.getFamily()), prefs.get("size", String.valueOf(font.getSize())),
				prefs.get("foreground", foreground.toString()), prefs.get("background", background.toString()));
		location.x = prefs.getInt("location/x", location.x);
		location.y = prefs.getInt("location/y", location.y);
	}

	boolean save() {
		prefs.put("font", font.getFamily());
		prefs.put("size", String.valueOf(font.getSize()));
		prefs.put("foreground", foreground.toString());
		prefs.put("background", background.toString());
		prefs.putInt("location/x", location.x);
		prefs.putInt("location/y", location.y);
		try {
			prefs.flush();
		} catch (final BackingStoreException e) {
			return false;
		}
		return true;
	}
}
