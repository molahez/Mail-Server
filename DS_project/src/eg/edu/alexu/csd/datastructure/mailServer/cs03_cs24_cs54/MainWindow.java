package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import java.awt.Color;

public class MainWindow {

	static DLinkedList fol = new DLinkedList();
	static boolean state = false;
	String temp,email, password, cont;
	int count = 5;
	private JFrame frame;
	private JTextField textField;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		try {
			fol = Appp.read();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Appp.writee(false);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
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
		
		frame.addWindowListener(new WindowAdapter(){
			
			
			public void windowClosing (WindowEvent e) {
				Appp.writee(false);
				System.exit(0);
				
			}
			
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		email = Contact.emal;
		cont = Contact.contact_name;	
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Century Gothic", Font.PLAIN, 20));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblEmailAddress = new JLabel("Email Address:-");
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblEmailAddress.setBounds(34, 31, 155, 67);
		frame.getContentPane().add(lblEmailAddress);

		JLabel lblNewLabel = new JLabel("Name:-");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblNewLabel.setBounds(442, 46, 77, 36);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblXx = new JLabel(email);
		lblXx.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblXx.setBounds(210, 31, 155, 67);
		frame.getContentPane().add(lblXx);

		JLabel lblXx_1 = new JLabel(cont);
		lblXx_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblXx_1.setBounds(568, 46, 77, 36);
		frame.getContentPane().add(lblXx_1);

		JButton btnCompose = new JButton("Compose");
		btnCompose.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Composegui kk = new Composegui();
				kk.main(new String[5]);
			}
		});
		btnCompose.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnCompose.setBounds(34, 109, 113, 61);
		frame.getContentPane().add(btnCompose);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnSearch.setBounds(281, 109, 113, 61);
		frame.getContentPane().add(btnSearch);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Folders") {
			{
				for (int i = 0; i < fol.size(); i++) {

					add(new DefaultMutableTreeNode(fol.get(i)));
				}
			}
		}));
		
		tree.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tree.setBounds(34, 216, 178, 203);
		frame.getContentPane().add(tree);

		JButton btnAddFolder = new JButton("Add Folder");
		btnAddFolder.setEnabled(false);
		btnAddFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(textField.getText());
				selectedNode.add(newNode);
				// reload tree model
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.reload();
				fol.add(textField.getText());

				Appp.write(fol);

			}

		});
		btnAddFolder.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnAddFolder.setBounds(247, 216, 147, 36);
		frame.getContentPane().add(btnAddFolder);

		JButton btnEditFolder = new JButton("Edit Folder");
		btnEditFolder.setEnabled(false);
		btnEditFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();

				selectedNode.setUserObject(textField.getText());
				// reload tree model
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.reload();

				for (int i = 0; i < fol.size(); i++) {

					if (Objects.equals(temp, fol.get(i))) {

						fol.set(i, textField.getText());
					}
				}
				Appp.write(fol);

			}
		});
		btnEditFolder.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnEditFolder.setBounds(247, 266, 147, 36);
		frame.getContentPane().add(btnEditFolder);

		JButton btnDeleteFolder = new JButton("Delete Folder");
		btnDeleteFolder.setEnabled(false);
		btnDeleteFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();

				if (selectedNode != tree.getModel().getRoot()) {
					DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

					model.removeNodeFromParent(selectedNode);

					model.reload();
				}
				for (int i = 0; i < fol.size(); i++) {

					if (Objects.equals(temp, fol.get(i))) {

						fol.remove(i);
					}
				}
				Appp.write(fol);

			}
		});
		btnDeleteFolder.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnDeleteFolder.setBounds(247, 327, 147, 36);
		frame.getContentPane().add(btnDeleteFolder);

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(247, 384, 147, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				Homewindow kk = new Homewindow();
				
				state = true;
				Appp.writee(true);
				kk.main(new String[5]);
			}
		});
		btnSignOut.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnSignOut.setBounds(34, 469, 113, 61);
		frame.getContentPane().add(btnSignOut);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAddFolder.setEnabled(true);
				btnEditFolder.setEnabled(true);
				btnDeleteFolder.setEnabled(true);
				TreeSelectionModel smd = tree.getSelectionModel();
				if (smd.getSelectionCount() > 0) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
							.getLastPathComponent();
					textField.setText(selectedNode.getUserObject().toString());
					temp = textField.getText();

				}

			}
		});
	}
}
