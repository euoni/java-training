package gui.ex24;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import gui.ex24.Option.ColorID;

@SuppressWarnings("serial")
public class OptionDialog extends JDialog {
	private final JComboBox<String> cboxFont;
	private final JComboBox<String> cboxSize;
	private final JComboBox<String> cboxForeground;
	private final JComboBox<String> cboxBackground;
	private boolean cancel = true;

	public static Option select(Option option) {
		final OptionDialog dialog = new OptionDialog(option);
		dialog.setModal(true);
		dialog.setVisible(true);
		return dialog.cancel ? null : dialog.getOption();
	}

	public OptionDialog(Option option) {
		setTitle("Option");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());

		final int[] sizes = new int[] { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 28, 32, 36, 40, 44, 48,
				54, 60, 66, 72, 80, 88, 92 };

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		final GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		final JLabel lblFont = new JLabel("Font");
		final GridBagConstraints gbc_lblFont = new GridBagConstraints();
		gbc_lblFont.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFont.insets = new Insets(0, 0, 5, 5);
		gbc_lblFont.gridx = 0;
		gbc_lblFont.gridy = 0;
		panel.add(lblFont, gbc_lblFont);

		cboxFont = new FontCombo();
		final GridBagConstraints gbc_cboxFont = new GridBagConstraints();
		gbc_cboxFont.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxFont.insets = new Insets(0, 0, 5, 5);
		gbc_cboxFont.gridx = 1;
		gbc_cboxFont.gridy = 0;

		panel.add(cboxFont, gbc_cboxFont);
		final JLabel lblSize = new JLabel("Size");
		final GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 0;
		gbc_lblSize.gridy = 1;
		panel.add(lblSize, gbc_lblSize);

		cboxSize = new JComboBox<>(
				new DefaultComboBoxModel<>(Arrays.stream(sizes).mapToObj(String::valueOf).toArray(String[]::new)));
		final GridBagConstraints gbc_cboxSize = new GridBagConstraints();
		gbc_cboxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxSize.insets = new Insets(0, 0, 5, 5);
		gbc_cboxSize.gridx = 1;
		gbc_cboxSize.gridy = 1;
		panel.add(cboxSize, gbc_cboxSize);

		final JLabel lblForeground = new JLabel("Foreground");
		final GridBagConstraints gbc_lblForeground = new GridBagConstraints();
		gbc_lblForeground.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblForeground.insets = new Insets(0, 0, 5, 5);
		gbc_lblForeground.gridx = 0;
		gbc_lblForeground.gridy = 2;
		panel.add(lblForeground, gbc_lblForeground);

		cboxForeground = new ColorCombo();
		final GridBagConstraints gbc_cboxForeground = new GridBagConstraints();
		gbc_cboxForeground.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxForeground.insets = new Insets(0, 0, 5, 5);
		gbc_cboxForeground.gridx = 1;
		gbc_cboxForeground.gridy = 2;
		panel.add(cboxForeground, gbc_cboxForeground);

		final JLabel lblBackground = new JLabel("Background");
		final GridBagConstraints gbc_lblBackground = new GridBagConstraints();
		gbc_lblBackground.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBackground.insets = new Insets(0, 0, 0, 5);
		gbc_lblBackground.gridx = 0;
		gbc_lblBackground.gridy = 3;
		panel.add(lblBackground, gbc_lblBackground);

		cboxBackground = new ColorCombo();
		final GridBagConstraints gbc_cboxBackground = new GridBagConstraints();
		gbc_cboxBackground.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxBackground.insets = new Insets(0, 0, 0, 5);
		gbc_cboxBackground.gridx = 1;
		gbc_cboxBackground.gridy = 3;
		panel.add(cboxBackground, gbc_cboxBackground);

		{
			final JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cancel = false;
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}

		setOption(option);
	}

	public Option getOption() {
		final Option newOption = new Option();
		newOption.createFromString((String) cboxFont.getSelectedItem(), (String) cboxSize.getSelectedItem(),
				(String) cboxForeground.getSelectedItem(), (String) cboxBackground.getSelectedItem());
		return newOption;
	}

	public void setOption(Option option) {
		cboxFont.setSelectedItem(option.font.getFamily());
		cboxSize.setSelectedItem(String.valueOf(option.font.getSize()));
		cboxForeground.setSelectedItem(Option.ColorID.fromColor(option.foreground).toString());
		cboxBackground.setSelectedItem(Option.ColorID.fromColor(option.background).toString());
	}

	private static class FontCombo extends JComboBox<String> {
		public FontCombo() {
			final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			final String[] fonts = environment.getAvailableFontFamilyNames();
			final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
			model.addElement(Font.DIALOG);
			model.addElement(Font.DIALOG_INPUT);
			model.addElement(Font.MONOSPACED);
			model.addElement(Font.SANS_SERIF);
			model.addElement(Font.SERIF);
			for (final String font : fonts) {
				model.addElement(font);
			}
			setModel(model);

			setRenderer(new ListCellRenderer<String>() {
				private final JLabel label = new JLabel();

				@Override
				public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
						boolean isSelected, boolean cellHasFocus) {
					label.setText(value);
					label.setFont(new Font(value, label.getFont().getStyle(), label.getFont().getSize()));
					return label;
				}
			});
		}
	}

	private static class ColorCombo extends JComboBox<String> {
		public ColorCombo() {
			final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
			for (final ColorID c : Option.ColorID.values())
				model.addElement(c.toString());
			setModel(model);

			setRenderer(new ListCellRenderer<String>() {
				private final JLabel label = new JLabel();

				@Override
				public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
						boolean isSelected, boolean cellHasFocus) {
					label.setText(value);
					label.setIcon(new ColorIcon(ColorID.valueOf(value).getColor(), label.getFont().getSize()));
					return label;
				}
			});
		}
	}
}
