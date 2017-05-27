package gui.ex22;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import gui.ex22.Option.ColorID;

@SuppressWarnings("serial")
public class OptionDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
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
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		final JLabel lblFont = new JLabel("Font");
		final JLabel lblSize = new JLabel("Size");
		final JLabel lblForeground = new JLabel("Foreground");
		final JLabel lblBackground = new JLabel("Background");

		cboxFont = new FontCombo();

		final int[] sizes = new int[] { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 28, 32, 36, 40, 44, 48,
				54, 60, 66, 72, 80, 88, 92 };
		cboxSize = new JComboBox<>(
				new DefaultComboBoxModel<>(Arrays.stream(sizes).mapToObj(String::valueOf).toArray(String[]::new)));
		cboxForeground = new ColorCombo();// createColorCombobox();
		cboxBackground = new ColorCombo();// createColorCombobox();

		final GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblFont)
								.addComponent(lblSize).addComponent(lblForeground).addComponent(lblBackground))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cboxSize, 0, 297, Short.MAX_VALUE)
								.addComponent(cboxFont, 0, 297, Short.MAX_VALUE)
								.addComponent(cboxForeground, 0, 297, Short.MAX_VALUE)
								.addComponent(cboxBackground, Alignment.TRAILING, 0, 347, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblFont).addComponent(
						cboxFont, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboxSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSize))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblForeground)
						.addComponent(cboxForeground, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblBackground)
						.addComponent(cboxBackground, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(26, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);

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
		return Option.createFromString((String) cboxFont.getSelectedItem(), (String) cboxSize.getSelectedItem(),
				(String) cboxForeground.getSelectedItem(), (String) cboxBackground.getSelectedItem());
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
