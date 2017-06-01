package interpret.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import interpret.data.NamedObject;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

@SuppressWarnings("serial")
public class InstanceView extends JComponent {
	private final JTree tree;
	private final DefaultMutableTreeNode rootNode;

	/**
	 * Create the panel.
	 */
	public InstanceView() {
		IconFontSwing.register(FontAwesome.getIconFont());

		setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		final JPanel btnPanel = new JPanel();
		panel.add(btnPanel, BorderLayout.EAST);

		final JLabel lblObjects = new JLabel("Objects");
		panel.add(lblObjects, BorderLayout.CENTER);

		final JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final CreateInstanceDialog dialog = new CreateInstanceDialog(InstanceView.this, getVariableMap(), null,
						null);
				dialog.setVisible(true);
				if (!dialog.isCanceled()) {
					register(dialog.getInstanceName(), dialog.getInstance());
					tree.clearSelection();
					tree.addSelectionRow(tree.getRowCount() - 1);
				}
			}
		});
		btnPanel.add(btnAdd);
		btnAdd.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, btnAdd.getFont().getSize()));

		final JButton btnRemove = new JButton("Remove");
		btnPanel.add(btnRemove);
		btnRemove.setEnabled(false);
		btnRemove.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS_CIRCLE, btnRemove.getFont().getSize()));
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionModel().getSelectionPath()
						.getLastPathComponent();
				node.removeFromParent();
				reload(rootNode);
			}
		});

		final JButton btnAssign = new JButton("Assign Item");
		btnAssign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final TreePath path = tree.getSelectionModel().getSelectionPath();
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				final NamedObject namedItem = (NamedObject) node.getUserObject();
				final DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
				final NamedObject namedArray = (NamedObject) parentNode.getUserObject();

				final CreateInstanceDialog dialog = new CreateInstanceDialog(InstanceView.this, getVariableMap(),
						namedItem.getName(), namedArray.getObj().getClass().getComponentType());
				dialog.setVisible(true);
				if (!dialog.isCanceled()) {
					final int index = parentNode.getIndex(node);
					Array.set(namedArray.getObj(), index, dialog.getInstance());
					node.setUserObject(new NamedObject(namedArray, index));
					reload(node);
					tree.clearSelection();
					tree.setSelectionPath(path);
				}
			}
		});
		btnPanel.add(btnAssign);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		rootNode = new DefaultMutableTreeNode();
		tree = new JTree(rootNode);
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				final TreePath path = tree.getSelectionModel().getSelectionPath();
				if (path == null) {
					btnRemove.setEnabled(false);
					btnAssign.setEnabled(false);
					return;
				}
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				btnRemove.setEnabled(node.getParent() == rootNode);

				final NamedObject named = (NamedObject) node.getUserObject();
				btnAssign.setEnabled(node.getParent() != rootNode && named.getObj() == null);
			}
		});
		scrollPane.setViewportView(tree);
	}

	public void register(String name, Object obj) {
		final NamedObject named = new NamedObject(name, obj);
		final DefaultMutableTreeNode node = new DefaultMutableTreeNode(named);
		if (obj.getClass().isArray()) {
			final int length = Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				final DefaultMutableTreeNode child = new DefaultMutableTreeNode(new NamedObject(named, i));
				node.add(child);
			}
		}

		rootNode.add(node);
		reload(rootNode);
	}

	public NamedObject getSelectedObject() {
		final TreePath path = tree.getSelectionModel().getSelectionPath();
		if (path == null)
			return null;

		final DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		final NamedObject named = (NamedObject) node.getUserObject();
		return named;
	}

	public void addTreeSelectionListener(TreeSelectionListener l) {
		tree.getSelectionModel().addTreeSelectionListener(l);
	}

	public void removeTreeSelectionListener(TreeSelectionListener l) {
		tree.getSelectionModel().removeTreeSelectionListener(l);
	}

	public Map<String, Object> getVariableMap() {
		final HashMap<String, Object> map = new HashMap<>();
		@SuppressWarnings("unchecked")
		final ArrayList<Object> list = Collections.list(rootNode.children());
		for (final Object node : list) {
			final DefaultMutableTreeNode typedNode = (DefaultMutableTreeNode) node;
			final NamedObject named = (NamedObject) typedNode.getUserObject();
			map.put(named.getName(), named.getObj());
		}
		return map;
	}

	private void reload(TreeNode node) {
		((DefaultTreeModel) tree.getModel()).reload(node);
	}
}
