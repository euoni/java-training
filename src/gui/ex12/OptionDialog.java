package gui.ex12;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.ex12.Option.ColorID;

@SuppressWarnings("serial")
public class OptionDialog extends Dialog {
	private final Choice fontChoice;
	private final Choice sizeChoice;
	private final Choice fgChoice;
	private final Choice bgChoice;
	private boolean cancel = false;

	public static Option select(Frame owner, Option option) {
		final OptionDialog dialog = new OptionDialog(owner, option);
		dialog.setModal(true);
		dialog.setVisible(true);
		return dialog.isCancel() ? null : dialog.getOption();
	}

	public OptionDialog(Frame owner, Option option) {
		super(owner, "Option");

		setSize(400, 230);

		// layout
		final GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		final GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = GridBagConstraints.RELATIVE;
		labelConstraints.anchor = GridBagConstraints.WEST;
		labelConstraints.weightx = 0.;
		labelConstraints.weighty = 0.;
		labelConstraints.insets = new Insets(5, 10, 5, 20);

		final GridBagConstraints valueConstraints = new GridBagConstraints();
		valueConstraints.gridx = 1;
		valueConstraints.gridy = GridBagConstraints.RELATIVE;
		valueConstraints.anchor = GridBagConstraints.WEST;
		valueConstraints.weightx = 1.;
		valueConstraints.weighty = 0.;
		valueConstraints.fill = GridBagConstraints.HORIZONTAL;
		valueConstraints.insets = new Insets(5, 0, 5, 10);

		final GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = GridBagConstraints.RELATIVE;
		buttonConstraints.gridwidth = 2;
		buttonConstraints.anchor = GridBagConstraints.SOUTHEAST;
		buttonConstraints.weightx = 1.;
		buttonConstraints.weighty = 1.;
		buttonConstraints.insets = new Insets(10, 10, 10, 10);

		// fontLabel
		final Label fontLabel = new Label("Font");
		layout.setConstraints(fontLabel, labelConstraints);
		add(fontLabel);

		// fontChoice
		fontChoice = new Choice();
		final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final String[] fonts = environment.getAvailableFontFamilyNames();
		fontChoice.add(Font.DIALOG);
		fontChoice.add(Font.DIALOG_INPUT);
		fontChoice.add(Font.MONOSPACED);
		fontChoice.add(Font.SANS_SERIF);
		fontChoice.add(Font.SERIF);
		for (final String font : fonts) {
			fontChoice.add(font);
		}
		layout.setConstraints(fontChoice, valueConstraints);
		add(fontChoice);

		// sizeLabel
		final Label sizeLabel = new Label("Size");
		layout.setConstraints(sizeLabel, labelConstraints);
		add(sizeLabel);

		// sizeChoice
		sizeChoice = new Choice();
		final int[] sizes = new int[] { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 28, 32, 36, 40, 44, 48,
				54, 60, 66, 72, 80, 88, 92 };
		for (final int size : sizes) {
			sizeChoice.add(String.valueOf(size));
		}
		layout.setConstraints(sizeChoice, valueConstraints);
		add(sizeChoice);

		// fgLabel
		final Label fgLabel = new Label("Foreground");
		layout.setConstraints(fgLabel, labelConstraints);
		add(fgLabel);

		// fgChoice
		fgChoice = createColorChoice();
		layout.setConstraints(fgChoice, valueConstraints);
		add(fgChoice);

		// bgLabel
		final Label bgLabel = new Label("Background");
		layout.setConstraints(bgLabel, labelConstraints);
		add(bgLabel);

		// bgChoice
		bgChoice = createColorChoice();
		layout.setConstraints(bgChoice, valueConstraints);
		add(bgChoice);

		// btnPanel
		final Panel btnPanel = new Panel();
		layout.setConstraints(btnPanel, buttonConstraints);
		add(btnPanel);
		btnPanel.setLayout(new FlowLayout());

		final Button okBtn = new Button("OK");
		btnPanel.add(okBtn);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel = false;
				dispose();
			}
		});

		final Button cancelBtn = new Button("Cancel");
		btnPanel.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel = true;
				dispose();
			}
		});

		// register listener
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancel = true;
				dispose();
			}
		});

		setOption(option);
	}

	public boolean isCancel() {
		return cancel;
	}

	private Choice createColorChoice() {
		final Choice c = new Choice();
		for (final ColorID color : Option.ColorID.values()) {
			c.add(color.toString());
		}
		return c;
	}

	public Option getOption() {
		return Option.createFromString(fontChoice.getSelectedItem(), sizeChoice.getSelectedItem(),
				fgChoice.getSelectedItem(), bgChoice.getSelectedItem());
	}

	public void setOption(Option option) {
		fontChoice.select(option.font.getFamily());
		sizeChoice.select(String.valueOf(option.font.getSize()));
		fgChoice.select(Option.ColorID.fromColor(option.foreground).toString());
		bgChoice.select(Option.ColorID.fromColor(option.background).toString());
	}
}
