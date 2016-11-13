package jpl.ch03.ex07;

public class ColorAttr extends Attr {
	/** 変換された色 */
	private ScreenColor myColor;

	public ColorAttr(String name, Object value) {
		super(name, value);
		decodeColor();
	}

	public ColorAttr(String name) {
		super(name, "transparent");
		decodeColor();
	}

	public ColorAttr(String name, ScreenColor value) {
		super(name, value.toString());
		myColor = value;
	}

	@Override
	public Object setValue(Object newValue) {
		final Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}

	/** 値を記述ではなくScreenColorに設定する */
	public ScreenColor setValue(ScreenColor newValue) {
		super.setValue(newValue.toString());
		final ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}

	/** 変換されたScreenColorオブジェクトを返す */
	public ScreenColor getMyColor() {
		return myColor;
	}

	/** getValue()で得られる記述からScreenColorを設定する */
	private void decodeColor() {
		if (getValue() == null)
			myColor = null;
		else
			myColor = new ScreenColor(getValue());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((myColor == null) ? 0 : myColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ColorAttr other = (ColorAttr) obj;
		if (myColor == null) {
			if (other.myColor != null)
				return false;
		} else if (!myColor.equals(other.myColor))
			return false;
		return true;
	}
}
