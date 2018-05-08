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
import javax.swing.JMenuBar;
import java.awt.Insets;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow {

	static DLinkedList fol = new DLinkedList();
	static boolean state = false;
	String temp,email, password, cont;
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
		password = Contact.password;
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
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter x = new Filter();
				x.read_indexfile("subject","Users/ahmed/Inbox/Index file.json");
			}
		});
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
		btnSignOut.setBounds(34, 489, 113, 61);
		frame.getContentPane().add(btnSignOut);
		
		JButton btnAccountSettings = new JButton("Account Settings");
		btnAccountSettings.addActionListener(new ActionListener() {
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
		
		JButton btnEnterFolder = new JButton("Enter Folder");
		btnEnterFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(temp);
				frame.dispose();
				EmailsView kk = new EmailsView();
				
				
				Appp.writee(true);
				kk.main(new String[5]);
				
				
				
			}
		});
		btnEnterFolder.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnEnterFolder.setEnabled(false);
		btnEnterFolder.setBounds(34, 442, 147, 36);
		frame.getContentPane().add(btnEnterFolder);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 0, 6));
		menuBar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar.setBounds(264, 442, 101, 35);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Sort_By");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Newest");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmOldest = new JMenuItem("Oldest");
		mnNewMenu.add(mntmOldest);
		
		JMenuItem mntmHighestpriority = new JMenuItem("Highest_Priority");
		mnNewMenu.add(mntmHighestpriority);
		
		JMenuItem mntmLowestpriority = new JMenuItem("Lowest_Priority");
		mnNewMenu.add(mntmLowestpriority);
		
		textField_1 = new JTextField();
		textField_1.setBounds(442, 126, 127, 44);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setMargin(new Insets(0, 0, 0, 6));
		menuBar_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar_1.setBounds(601, 132, 101, 35);
		frame.getContentPane().add(menuBar_1);
		
		JMenu mnNewMenu_1 = new JMenu("Search by:-");
		menuBar_1.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Senders");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmReceivers = new JMenuItem("Receivers");
		mnNewMenu_1.add(mntmReceivers);
		
		JMenuItem mntmImportance = new JMenuItem("Importance");
		mnNewMenu_1.add(mntmImportance);
		
		JMenuItem mntmSubject = new JMenuItem("Subject");
		mnNewMenu_1.add(mntmSubject);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(442, 218, 127, 34);
		frame.getContentPane().add(textField_2);
		textField_2.setEditable(false);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(442, 282, 127, 36);
		frame.getContentPane().add(textField_3);
		textField_3.setEditable(false);
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(601, 216, 101, 22);
		frame.getContentPane().add(menuBar_2);
		
		JMenu mnNewMenu_2 = new JMenu("Creating Filter");
		menuBar_2.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Reciever");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setEditable(true);
				textField_3.setEditable(false);
				String re = textField_2.getText();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Subject");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setEditable(true);
				textField_2.setEditable(false);
				String co = textField_3.getText();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Receiver&Subject");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				String re = textField_2.getText();
				String co = textField_3.getText();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JLabel lblSender = new JLabel("Sender");
		lblSender.setBounds(442, 192, 58, 15);
		frame.getContentPane().add(lblSender);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(442, 256, 58, 15);
		frame.getContentPane().add(lblSubject);
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
