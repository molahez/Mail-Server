package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;

public class MainWindow {
	Filter x = new Filter();
	Integer val = 0;
	static DLinkedList fol = new DLinkedList();
	static boolean state = false;
	String temp,temp1="", email, password, cont, cate = "", re, co;
	int count = 5;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
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
		password = Contact.password;
		cont = Contact.contact_name;
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Century Gothic", Font.PLAIN, 20));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("recources/bg.jpg")))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setResizable(false);
		frame.pack();

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
		btnCompose.setBounds(34, 126, 113, 44);
		frame.getContentPane().add(btnCompose);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter x = new Filter();
				folderr fold = new folderr();
				fold.folderChosen(temp1, cont);
				x.var(5, cate, textField_1.getText());
				Appp y = new Appp();
				if ((Objects.equals(textField_1.getText(), "")) || (cate=="") || (temp1=="")) {

					JOptionPane.showMessageDialog(null, "Please enter value and it's category to serach and folder to search in/n"
							+ "for example: value(CSED) and category (Subject) and folder(Inbox) ");

				} else {
					y.setViewingOptions(null, x, null);

				}

			}
		});
		btnSearch.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnSearch.setBounds(281, 126, 113, 44);
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
		tree.setBounds(34, 205, 180, 184);
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
		btnAddFolder.setBounds(34, 415, 147, 36);
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
		btnEditFolder.setBounds(210, 415, 147, 36);
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
		btnDeleteFolder.setBounds(391, 415, 147, 36);
		frame.getContentPane().add(btnDeleteFolder);

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(210, 473, 147, 35);
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
		btnSignOut.setBounds(34, 489, 113, 61);
		frame.getContentPane().add(btnSignOut);

		JButton btnAccountSettings = new JButton("Account Settings");
		btnAccountSettings.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Settings kk = new Settings();

				Appp.writee(true);
				kk.main(new String[5]);

			}
		});
		btnAccountSettings.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnAccountSettings.setBounds(741, 35, 204, 61);
		frame.getContentPane().add(btnAccountSettings);

		JButton btnEnterFolder = new JButton("Display");
		btnEnterFolder.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Sorting x = new Sorting();
				folderr fold = new folderr();
				fold.folderChosen(temp, cont);
				fold.label(email);
				Filter switch_index = new Filter();
				switch_index.var(val, null, null);
				Appp y = new Appp();
				y.setViewingOptions(fold, switch_index, x);
				EmailsView kk = new EmailsView();
				kk.main(new String[5]);

			}
		});
		btnEnterFolder.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnEnterFolder.setEnabled(false);
		btnEnterFolder.setBounds(247, 261, 147, 36);
		frame.getContentPane().add(btnEnterFolder);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 0, 6));
		menuBar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar.setBounds(264, 200, 101, 35);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("Sort_By");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Newest");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				val = 1;

			}
		});
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Default");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				val = 1;
			}
		});
		mnNewMenu.add(mntmNewMenuItem_8);
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmOldest = new JMenuItem("Oldest");
		mntmOldest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				val = 2;
			}
		});
		mnNewMenu.add(mntmOldest);

		JMenuItem mntmHighestpriority = new JMenuItem("Subjects alphabetically");
		mntmHighestpriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				val = 3;
			}
		});
		mnNewMenu.add(mntmHighestpriority);

		JMenuItem mntmLowestpriority = new JMenuItem("Senders alphabetically");
		mntmLowestpriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				val = 4;
			}
		});
		mnNewMenu.add(mntmLowestpriority);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Importance");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				val = 6;
			}
		});
		mnNewMenu.add(mntmNewMenuItem_6);

		textField_1 = new JTextField();
		textField_1.setBounds(442, 126, 127, 44);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setMargin(new Insets(0, 0, 0, 6));
		menuBar_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar_1.setBounds(601, 132, 101, 35);
		frame.getContentPane().add(menuBar_1);

		JMenu mnNewMenu_1 = new JMenu("Search by:-");
		menuBar_1.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Senders");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(true);

				cate = "sender";

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmReceivers = new JMenuItem("Receivers");
		mntmReceivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(true);

				cate = "receiver";

			}
		});
		mnNewMenu_1.add(mntmReceivers);

		JMenuItem mntmImportance = new JMenuItem("Importance");
		mntmImportance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(true);

				cate = "priority";
			}
		});

		JMenuItem mntmSubject = new JMenuItem("Subject");
		mntmSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(true);
				cate = "subject";

			}
		});
		mnNewMenu_1.add(mntmSubject);
		mnNewMenu_1.add(mntmImportance);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Time");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setEditable(true);
				cate = "time";
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Email's Content");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setEditable(true);
				cate = "body";
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(800, 282, 127, 34);
		frame.getContentPane().add(textField_2);
		textField_2.setEditable(false);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(800, 353, 127, 36);
		frame.getContentPane().add(textField_3);
		textField_3.setEditable(false);

		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(800, 216, 101, 22);
		frame.getContentPane().add(menuBar_2);

		JMenu mnNewMenu_2 = new JMenu("Choosing Filter");
		menuBar_2.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Reciever");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setEditable(true);
				textField_3.setEditable(false);

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Subject");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setEditable(true);
				textField_2.setEditable(false);

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Receiver&Subject");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setEditable(true);
				textField_3.setEditable(true);

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);

		JLabel lblSender = new JLabel("Reciever");
		lblSender.setBounds(800, 256, 58, 15);
		frame.getContentPane().add(lblSender);

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(800, 327, 58, 15);
		frame.getContentPane().add(lblSubject);

		JButton btnCreateFilter = new JButton("Create Filter");
		btnCreateFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter x = new Filter();
				re = textField_2.getText();
				co = textField_3.getText();
				if (re.isEmpty()) {
					x.choose_filter("Subject", co, cont);
				} else if (co.isEmpty()) {
					x.choose_filter("Subject", re, cont);
				} else {
					x.choose_filter("Sender & Subject", re + "-" + co, cont);
				}

				textField_3.setEditable(false);
				textField_2.setEditable(false);
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnCreateFilter.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnCreateFilter.setBounds(800, 416, 127, 36);
		frame.getContentPane().add(btnCreateFilter);
		
		JMenuBar menuBar_3 = new JMenuBar();
		menuBar_3.setMargin(new Insets(0, 0, 0, 6));
		menuBar_3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar_3.setBounds(722, 132, 101, 35);
		frame.getContentPane().add(menuBar_3);
		
		JMenu mnNewMenu_3 = new JMenu("Search in:-");
		menuBar_3.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Inbox");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp1="Inbox";
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sent");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp1="Sent";
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAddFolder.setEnabled(true);
				btnEditFolder.setEnabled(true);
				btnDeleteFolder.setEnabled(true);
				btnEnterFolder.setEnabled(true);
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
