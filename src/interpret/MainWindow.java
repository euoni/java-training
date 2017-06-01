package interpret;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import interpret.component.InstanceView;
import interpret.component.MemberView;
import interpret.data.NamedObject;

public class MainWindow {
	private JFrame frmInterpreter;
	private InstanceView instanceView;
	private MemberView memberView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					final MainWindow window = new MainWindow();
					window.show();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInterpreter = new JFrame();
		frmInterpreter.setTitle("Interpreter");
		frmInterpreter.setBounds(100, 100, 600, 300);
		frmInterpreter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInterpreter.getContentPane().setLayout(new BorderLayout(0, 0));

		final JSplitPane splitPane = new JSplitPane();
		frmInterpreter.getContentPane().add(splitPane, BorderLayout.CENTER);

		instanceView = new InstanceView();
		instanceView.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				final NamedObject selected = instanceView.getSelectedObject();
				memberView.setObject(selected == null ? null : selected.getObj());
			}
		});
		instanceView.register("this", this);
		instanceView.register("sample", new Sample());
		splitPane.setLeftComponent(instanceView);

		memberView = new MemberView(instanceView);
		splitPane.setRightComponent(memberView);
	}

	public void show() {
		frmInterpreter.setVisible(true);
	}
}
