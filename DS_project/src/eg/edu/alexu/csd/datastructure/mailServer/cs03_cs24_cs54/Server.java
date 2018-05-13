
package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.SLinkedList;

public class Server {
	// Variables for main window
	Filter x = new Filter();
	Integer val = 0;
	static DLinkedList fol = new DLinkedList();
	static boolean state = false;
	String temp, temp1 = "", email, password, cont, cate = "", re, co;// This is for page main
	int count = 5;
	String temp11, email1, password1, cont1, p = "";// This is for page compose
	// This is for emails view
	String folderchosen;
	private JTable table;
	static DLinkedList emails = new DLinkedList();
	static DLinkedList required = new DLinkedList();
	static SLinkedList content = new SLinkedList();
	boolean flag;
	String selected;
	int pg;
	// This is for emails content
	static SLinkedList content1 = new SLinkedList();

	private JFrame frame;
	//Search_view
	
	static DLinkedList emails1 = new DLinkedList();
	static DLinkedList tempo = new DLinkedList();
	static DLinkedList required1 = new DLinkedList();
	static SLinkedList content11 = new SLinkedList();
	private JTextField textField11;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			fol = Appp.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Appp.writee(false);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class DirectoryRestrictedFileSystemView extends FileSystemView {
		private final File[] rootDirectories;

		DirectoryRestrictedFileSystemView(File rootDirectory) {
			this.rootDirectories = new File[] { rootDirectory };
		}

		DirectoryRestrictedFileSystemView(File[] rootDirectories) {
			this.rootDirectories = rootDirectories;
		}

		@Override
		public File createNewFolder(File containingDir) throws IOException {
			throw new UnsupportedOperationException("Unable to create directory");
		}

		@Override
		public File getHomeDirectory() {
			return rootDirectories[0];
		}

		@Override
		public File[] getRoots() {
			return rootDirectories;
		}

		@Override
		public boolean isRoot(File file) {
			for (File root : rootDirectories) {
				if (root.equals(file)) {
					return true;
				}
			}
			return false;
		}
	}

	class ImagePanel extends JPanel {

		private Image img;

		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}

		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}

	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				Appp.writee(false);
				System.exit(0);
				Sorting.save_page(1);

			}

		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1016, 665);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel Home = new JPanel();
		// ImagePanel Home = new ImagePanel(new
		// ImageIcon("recources/home.png").getImage());
		frame.getContentPane().add(Home);
		/*
		 * frame.pack(); frame.setVisible(true);
		 */

		JPanel Signup = new JPanel();
		// ImagePanel Signup = new ImagePanel(new
		// ImageIcon("recources/home.png").getImage());
		frame.getContentPane().add(Signup, "name_737580920323");
		Signup.setVisible(false);

		JPanel Main = new JPanel();
		// ImagePanel Main = new ImagePanel(new
		// ImageIcon("recources/home.png").getImage());
		frame.getContentPane().add(Main, "name_4931018976449");
		Main.setLayout(null);
		Main.setVisible(false);

		JPanel Settings = new JPanel();
		frame.getContentPane().add(Settings, "name_9586204940422");
		Settings.setLayout(null);
		Settings.setVisible(false);

		JPanel Compose = new JPanel();
		frame.getContentPane().add(Compose, "name_10636674609855");
		Compose.setVisible(false);

		JPanel Emails_view = new JPanel();
		frame.getContentPane().add(Emails_view, "name_13065006020613");
		Emails_view.setLayout(null);
		Emails_view.setVisible(false);

		JPanel Email_content = new JPanel();
		frame.getContentPane().add(Email_content, "name_17188247972653");
		Email_content.setLayout(null);
		Email_content.setVisible(false);
		
		JPanel Search_Content = new JPanel();
		frame.getContentPane().add(Search_Content, "name_5632355689381");
		Search_Content.setLayout(null);
		Search_Content.setVisible(false);

		// Main labels
		JLabel label = new JLabel();
		JLabel label_1 = new JLabel();
		JLabel lblX = new JLabel();
		JLabel lblE = new JLabel();
		lblE.setBounds(167, 41, 76, 45);
		JLabel lblFoldername = new JLabel();
		JLabel label_111 = new JLabel();
		JLabel label_21 = new JLabel();
		JLabel label_3 = new JLabel();
		JLabel label_4 = new JLabel();
		JLabel label_5 = new JLabel();
		JLabel label1111 = new JLabel(cont);
		JTextArea textArea1 = new JTextArea();
		 
		

		

		// Loading Table
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return column == 6;
			}
		};

		table.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		DefaultTableModel xx = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};

		table.setRowHeight(37);

		xx.addColumn("Importance");
		xx.addColumn("Subject");
		xx.addColumn("Sender");
		xx.addColumn("Receiver");
		xx.addColumn("Time");
		xx.addColumn("content");
		xx.addColumn("Check");
		
		
		JTable tablex = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}
		};

		tablex.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		

		DefaultTableModel tb = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
			
				case 1:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		tablex.setRowHeight(20);
		tb.addColumn("Attachement");
		tb.addColumn("Delete");
		
		
		
		//loading search table
		
		JTable table11 = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return column == 6;
			}
		};

		table11.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		
		DefaultTableModel xx1 = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				
				default:
					return String.class;
				}
			}
		};

		table11.setRowHeight(37);

		xx1.addColumn("Importance");
		xx1.addColumn("Subject");
		xx1.addColumn("Sender");
		xx1.addColumn("Receiver");
		xx1.addColumn("Time");
		xx1.addColumn("Content");

		
		
		
		
		
		

		// Home

		JButton btnSignUp = new JButton("New here? Sign up!");
		btnSignUp.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Signup.setVisible(true);
				Home.setVisible(false);
				Main.setVisible(false);
				Settings.setVisible(false);

			}
		});
		Home.setLayout(null);
		btnSignUp.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnSignUp.setBounds(281, 411, 364, 35);
		Home.add(btnSignUp);

		final JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setBounds(281, 166, 364, 36);
		Home.add(textField);
		textField.setColumns(10);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(281, 252, 364, 35);
		Home.add(passwordField);

		JButton btnNewButton = new JButton("Log in to your account");
		btnNewButton.setBounds(281, 341, 364, 35);

		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		Home.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {

				Appp y = new Appp();
				if (passwordField.getPassword().length == 0 || textField.getText() == "") {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
					textField.setText("");
					passwordField.setText("");

				} else if (y.signin(textField.getText(), new String(passwordField.getPassword()))) {

					email = Contact.emal;
					password = Contact.password;
					cont = Contact.contact_name;
					textField.setText("");
					passwordField.setText("");
					label.setText(email);
					label_1.setText(cont);
					lblX.setText(email);
					lblE.setText(email);
					label1111.setText(cont);
					lblFoldername.setText(cont);
					Home.setVisible(false);
					Signup.setVisible(false);
					Main.setVisible(true);

					Mail z = new Mail();

					z.auto_delete("Users/" + cont + "/Trash/Index file.json");

				} else {
					JOptionPane.showMessageDialog(null, " Invalid email address or Password ");

					textField.setText("");
					passwordField.setText("");
				}

			}
		});

		JLabel lblEmailAddress = new JLabel("Email address");
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblEmailAddress.setBounds(94, 167, 135, 26);
		Home.add(lblEmailAddress);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPassword.setBounds(94, 252, 93, 26);
		Home.add(lblPassword);

		// Sign up
		JTextField textField1 = new JTextField();
		textField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Signup.setLayout(null);
		textField1.setBounds(259, 123, 286, 29);
		Signup.add(textField1);
		textField1.setColumns(10);

		JTextField textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_2.setBounds(259, 289, 286, 29);
		Signup.add(textField_2);
		textField_2.setColumns(10);

		JPasswordField passwordField1 = new JPasswordField();
		passwordField1.setBounds(259, 163, 286, 35);
		Signup.add(passwordField1);

		JPasswordField passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(259, 231, 286, 29);
		Signup.add(passwordField_1);

		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Contact x = new Contact();
				x.var(textField1.getText(), new String(passwordField1.getPassword()), textField_2.getText());
				Appp y = new Appp();
				if (textField1.getText() == "" || textField_2.getText() == ""
						|| passwordField1.getPassword().length == 0 || passwordField_1.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
					textField1.setText("");
					passwordField1.setText("");
					passwordField_1.setText("");
					textField_2.setText("");

				} else if (passwordField1.getPassword().length < 8) {
					JOptionPane.showMessageDialog(null, " Password is less than 8 characters please try again");
					textField1.setText("");
					passwordField1.setText("");
					passwordField_1.setText("");
					textField_2.setText("");

				} else if (!(Arrays.equals(passwordField1.getPassword(), passwordField_1.getPassword()))) {

					JOptionPane.showMessageDialog(null, " Password doesn't match please try again");
					textField1.setText("");
					passwordField1.setText("");
					passwordField_1.setText("");
					textField_2.setText("");

				} else {
					if (y.signup(x)) {

						Settings.setVisible(false);
						Main.setVisible(true);
						Home.setVisible(false);
						Signup.setVisible(false);
						email = Contact.emal;
						password = Contact.password;
						cont = Contact.contact_name;
						textField.setText("");
						passwordField.setText("");
						label.setText(email);
						label_1.setText(cont);
						lblX.setText(email);
						lblFoldername.setText(cont);
						lblE.setText(email);
						label1111.setText(cont);

						// Mail z = new Mail();

						// z.auto_delete("Users/" + cont + "/Trash");
					} else {
						JOptionPane.showMessageDialog(null, " Invalid email address please try again");

						textField1.setText("");
						passwordField1.setText("");
						passwordField_1.setText("");
						textField_2.setText("");
					}

				}
			}
		});
		btnNewButton_1.setBounds(259, 372, 286, 35);
		Signup.add(btnNewButton_1);

		lblEmailAddress = new JLabel("Email Address:-");
		lblEmailAddress.setForeground(new Color(0, 255, 0));
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblEmailAddress.setBounds(73, 122, 131, 23);
		Signup.add(lblEmailAddress);

		lblPassword = new JLabel("Password:-");
		lblPassword.setForeground(new Color(0, 255, 0));
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPassword.setBounds(73, 173, 91, 23);
		Signup.add(lblPassword);

		JLabel lblName = new JLabel("Name:-");
		lblName.setForeground(new Color(0, 255, 0));
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblName.setBounds(73, 288, 65, 23);
		Signup.add(lblName);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		try {

			btnNewButton_1.setIcon(new ImageIcon("recources/back.jpg"));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBorderPainted(true);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Signup.setVisible(false);
				Home.setVisible(true);

			}

		});
		btnNewButton_1.setBounds(73, 409, 114, 105);
		Signup.add(btnNewButton_1);

		JLabel lblExUsermailservercom = new JLabel("ex:- user@mailserver.com");
		lblExUsermailservercom.setForeground(Color.WHITE);
		lblExUsermailservercom.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblExUsermailservercom.setBounds(572, 122, 221, 23);
		Signup.add(lblExUsermailservercom);

		JLabel lblExUsermailservercom_1 = new JLabel("Password must contain at least 8 characters");
		lblExUsermailservercom_1.setForeground(Color.WHITE);
		lblExUsermailservercom_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblExUsermailservercom_1.setBounds(572, 182, 261, 16);
		Signup.add(lblExUsermailservercom_1);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:-");
		lblConfirmPassword.setForeground(Color.GREEN);
		lblConfirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblConfirmPassword.setBounds(73, 226, 165, 23);
		Signup.add(lblConfirmPassword);

		// Main window

		JLabel lblEmailAddress1 = new JLabel("Email Address:-");
		lblEmailAddress1.setBounds(36, 42, 131, 23);
		lblEmailAddress1.setForeground(Color.WHITE);
		lblEmailAddress1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		Main.add(lblEmailAddress1);

		JLabel lblNewLabel = new JLabel("Name:-");
		lblNewLabel.setBounds(442, 40, 73, 26);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		Main.add(lblNewLabel);

		JButton btnCompose = new JButton("Compose");
		btnCompose.setBounds(49, 126, 118, 44);
		btnCompose.setForeground(Color.black);

		btnCompose.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Compose.setVisible(true);
				Main.setVisible(false);
			}
		});
		btnCompose.setFont(new Font("Century Gothic", Font.PLAIN, 16));

		Main.add(btnCompose);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(290, 126, 107, 44);
		btnSearch.setForeground(Color.black);

		final JTextField textField_4 = new JTextField();
		textField_4.setBounds(442, 129, 127, 44);
		Main.add(textField_4);
		textField_4.setColumns(10);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter x = new Filter();
				folderr fold = new folderr();
				fold.label(email);
				fold.folderChosen(temp1, cont);
				x.var(5, cate, textField_4.getText());
				Appp y = new Appp();
				if ((Objects.equals(textField_4.getText(), "")) || (cate == "") || (temp1 == "")) {

					JOptionPane.showMessageDialog(null,
							"Please enter value and it's category to serach and folder to search in/n"
									+ "for example: value(CSED) and category (Subject) and folder(Inbox) ");

				} else {
					textField_4.setText("");
					temp1 = "";
					cate = "";
					y.setViewingOptions(fold, x, null);
					Appp.writee(false);
					Search_Content.setVisible(true);
					Main.setVisible(false);
					Sorting.save_state(2);
					
					tempo = Filter.read_sorted();
					emails1 = Filter.filter_results(tempo);
					
					pg = Sorting.get_page();

					for (int i = 0; i < emails1.size(); i++) {
						required1.add(new Sorting("", "", "", "", "", "", ""));
						content11.add(new Sorting("", "", "", "", "", "", ""));
					}
					switch (pg) {
					case 1:
						if (emails1.size() < 10) {
							for (int i = 0; i < emails1.size(); i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						} else {
							for (int i = 0; i < 10; i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						}
						break;
					case 2:
						if (emails1.size() < 20) {
							for (int i = 10; i < emails1.size(); i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						} else {
							for (int i = 10; i < 20; i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						}
						break;
					case 3:
						if (emails1.size() < 30) {
							for (int i = 20; i < emails1.size(); i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						} else {
							for (int i = 20; i < 30; i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						}
						break;
					case 4:
						if (emails1.size() < 40) {
							for (int i = 30; i < emails1.size(); i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						} else {
							for (int i = 30; i < 40; i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						}
						break;
					case 5:
						if (emails1.size() < 50) {
							for (int i = 40; i < emails1.size(); i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						} else {
							for (int i = 40; i < 50; i++) {
								xx1.addRow(
										new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
												((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
												((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
							}
						}
						break;
					default:
						break;
					}
				}

			}
		});
		btnSearch.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		Main.add(btnSearch);

		JTree tree = new JTree();
		tree.setBounds(49, 197, 160, 163);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Folders") {
			{
				for (int i = 0; i < fol.size(); i++) {
					if (i == 2) {
						DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode(fol.get(i));

						node_1.add(new DefaultMutableTreeNode("Sender"));
						add(node_1);
					} else {

						add(new DefaultMutableTreeNode(fol.get(i)));
					}
				}
			}
		}));

		tree.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		Main.add(tree);

		JButton btnAddFolder = new JButton("Add Folder");
		btnAddFolder.setBounds(49, 403, 131, 29);
		btnAddFolder.setForeground(Color.black);

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
		Main.add(btnAddFolder);

		JButton btnEditFolder = new JButton("Edit Folder");
		btnEditFolder.setBounds(210, 403, 146, 29);
		btnEditFolder.setForeground(Color.black);

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
		Main.add(btnEditFolder);

		JButton btnDeleteFolder = new JButton("Delete Folder");
		btnDeleteFolder.setBounds(385, 403, 146, 29);
		btnDeleteFolder.setForeground(Color.black);

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
		Main.add(btnDeleteFolder);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setBounds(49, 473, 131, 35);
		btnSignOut.setForeground(Color.black);

		btnSignOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Signup.setVisible(false);
				Home.setVisible(true);
				Main.setVisible(false);
				Appp.writee(true);

			}
		});
		btnSignOut.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		Main.add(btnSignOut);

		JButton btnAccountSettings = new JButton("Account Settings");
		btnAccountSettings.setBounds(795, 40, 169, 29);
		btnAccountSettings.setForeground(Color.black);
		btnAccountSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Settings.setVisible(true);
				Home.setVisible(false);
				Main.setVisible(false);
				Signup.setVisible(false);

			}
		});
		btnAccountSettings.setFont(new Font("Century Gothic", Font.PLAIN, 16));

		Main.add(btnAccountSettings);

		JButton btnEnterFolder = new JButton("Display");
		btnEnterFolder.setBounds(270, 308, 87, 29);
		btnEnterFolder.setForeground(Color.black);
		btnEnterFolder.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (val == 0 || temp == "") {
					JOptionPane.showMessageDialog(null, "Please choose folder and the type of sorting /n"
							+ "for example: folder(Inbox) and type (Default)");

				} else {

					Sorting x = new Sorting();
					folderr fold = new folderr();
					fold.folderChosen(temp, cont);
					fold.label(email);
					folderchosen = temp;
					Filter switch_index = new Filter();
					switch_index.var(val, null, null);
					x.save_val(val);
					Appp y = new Appp();
					y.setViewingOptions(fold, switch_index, x);
					Main.setVisible(false);
					Emails_view.setVisible(true);
					pg = Sorting.get_page();
					emails = Sorting.read_sorted();
					val = 0;
					temp = "";

					for (int i = 0; i < emails.size(); i++) {
						required.add(new Sorting("", "", "", "", "", "", ""));
						content.add(new Sorting("", "", "", "", "", "", ""));
					}
					switch (pg) {
					case 1:
						if (emails.size() < 10) {
							for (int i = 0; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 0; i < 10; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 2:
						if (emails.size() < 20) {
							for (int i = 10; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 10; i < 20; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 3:
						if (emails.size() < 30) {
							for (int i = 10; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 20; i < 30; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 4:
						if (emails.size() < 40) {
							for (int i = 10; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 30; i < 40; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 5:
						if (emails.size() < 50) {
							for (int i = 10; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 40; i < 50; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					default:
						break;
					}

				}
			}
		});
		btnEnterFolder.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnEnterFolder.setEnabled(false);
		Main.add(btnEnterFolder);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(270, 220, 87, 21);
		menuBar.setMargin(new Insets(0, 0, 0, 6));
		menuBar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		Main.add(menuBar);

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

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(580, 126, 127, 44);
		menuBar_1.setMargin(new Insets(0, 0, 0, 6));
		menuBar_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		Main.add(menuBar_1);

		JMenu mnNewMenu_1 = new JMenu("Search by:-");
		menuBar_1.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Senders");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField11.setEditable(true);

				cate = "sender";

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmReceivers = new JMenuItem("Receivers");
		mntmReceivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField11.setEditable(true);

				cate = "receiver";

			}
		});
		mnNewMenu_1.add(mntmReceivers);

		JMenuItem mntmImportance = new JMenuItem("Importance");
		mntmImportance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField11.setEditable(true);

				cate = "priority";
			}
		});

		JMenuItem mntmSubject = new JMenuItem("Subject");
		mntmSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField11.setEditable(true);
				cate = "subject";

			}
		});
		mnNewMenu_1.add(mntmSubject);
		mnNewMenu_1.add(mntmImportance);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Time");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField11.setEditable(true);
				cate = "time";
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Email's Content");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField11.setEditable(true);
				cate = "body";
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);

		JTextField textField_21 = new JTextField();
		textField_21.setBounds(795, 252, 115, 20);
		textField_21.setColumns(10);
		Main.add(textField_21);
		textField_21.setEditable(false);

		JTextField textField_3 = new JTextField();
		textField_3.setBounds(795, 308, 115, 20);
		textField_3.setColumns(10);
		Main.add(textField_3);
		textField_3.setEditable(false);

		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(795, 195, 93, 22);
		Main.add(menuBar_2);

		JMenu mnNewMenu_2 = new JMenu("Choosing Filter");
		menuBar_2.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Subject");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_21.setEditable(true);
				textField_3.setEditable(false);

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Sender&Subject");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_21.setEditable(true);
				textField_3.setEditable(true);

			}
		});

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Sender");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setEditable(true);
				textField_21.setEditable(false);

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		mnNewMenu_2.add(mntmNewMenuItem_4);

		JLabel lblSender = new JLabel("Subject");
		lblSender.setBounds(795, 222, 52, 19);
		lblSender.setForeground(Color.WHITE);
		lblSender.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		Main.add(lblSender);

		JLabel lblSubject = new JLabel("Sender");
		lblSubject.setBounds(795, 272, 48, 19);
		lblSubject.setForeground(Color.WHITE);
		lblSubject.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		Main.add(lblSubject);

		JButton btnCreateFilter = new JButton("Create Filter");
		btnCreateFilter.setBounds(795, 369, 115, 35);
		btnCreateFilter.setForeground(Color.black);

		btnCreateFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter x = new Filter();
				re = textField_21.getText();
				co = textField_3.getText();
				if (re.isEmpty()) {
					x.choose_filter("Sender", co, cont);
				} else if (co.isEmpty()) {
					x.choose_filter("Subject", re, cont);
				} else {
					x.choose_filter("Sender & Subject", re + "-" + co, cont);
				}

				textField_3.setEditable(false);
				textField_21.setEditable(false);
				textField_21.setText("");
				textField_3.setText("");
			}
		});
		btnCreateFilter.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		Main.add(btnCreateFilter);

		JMenuBar menuBar_3 = new JMenuBar();
		menuBar_3.setBounds(720, 126, 126, 44);
		menuBar_3.setMargin(new Insets(0, 0, 0, 6));
		menuBar_3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		Main.add(menuBar_3);

		JMenu mnNewMenu_3 = new JMenu("Search in:-");
		menuBar_3.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Inbox");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp1 = "Inbox";
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_10);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sent");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp1 = "Sent";
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);

		label.setForeground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label.setBounds(225, 42, 131, 23);
		Main.add(label);

		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_1.setBounds(548, 42, 160, 23);
		Main.add(label_1);
		
		textField11 = new JTextField();
		textField11.setBounds(210, 473, 146, 35);
		Main.add(textField11);
		textField11.setColumns(10);
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
					textField11.setText(selectedNode.getUserObject().toString());
					temp = textField11.getText();

				}

			}
		});
		// Settings
		JPasswordField passwordField11 = new JPasswordField();
		JPasswordField passwordField_11 = new JPasswordField();

		JLabel label1 = new JLabel("Email Address:-");
		label1.setForeground(Color.GREEN);
		label1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label1.setBounds(192, 108, 173, 30);
		Settings.add(label1);

		JLabel label_11 = new JLabel("Password:-");
		label_11.setForeground(Color.GREEN);
		label_11.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_11.setBounds(192, 171, 173, 30);
		Settings.add(label_11);

		JLabel label_2 = new JLabel("Confirm Password:-");
		label_2.setForeground(Color.GREEN);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_2.setBounds(192, 230, 173, 30);
		Settings.add(label_2);

		lblX.setForeground(Color.GREEN);
		lblX.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblX.setBounds(386, 108, 173, 30);
		Settings.add(lblX);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				Settings.setVisible(false);
				Main.setVisible(true);
				Home.setVisible(false);
				Signup.setVisible(false);

			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button.setBounds(192, 365, 102, 71);
		Settings.add(button);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Contact x = new Contact();
				x.var(email, new String(passwordField11.getPassword()), cont);
				if (passwordField11.getPassword().length == 0 || passwordField_11.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
					passwordField11.setText("");
					passwordField_11.setText("");
				} else if (passwordField11.getPassword().length < 8) {
					JOptionPane.showMessageDialog(null, " Password is less than 8 characters please try again");

					passwordField11.setText("");
					passwordField_11.setText("");

				} else if (!(Arrays.equals(passwordField11.getPassword(), passwordField_11.getPassword()))) {

					JOptionPane.showMessageDialog(null, " Password doesn't match please try again");

					passwordField11.setText("");
					passwordField_11.setText("");

				} else {
					x.edit_contact(email, new String(passwordField11.getPassword()));

					Settings.setVisible(false);
					Main.setVisible(true);
					Home.setVisible(false);
					Signup.setVisible(false);
				}
			}
		});
		btnSave.setForeground(Color.DARK_GRAY);
		btnSave.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnSave.setBounds(457, 365, 102, 71);
		Settings.add(btnSave);

		passwordField11.setBounds(386, 175, 207, 30);
		Settings.add(passwordField11);

		passwordField_11.setBounds(386, 230, 207, 30);
		Settings.add(passwordField_11);

		// Compose

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Compose.setLayout(null);

		JLabel lblNewLabel1 = new JLabel("From:");
		lblNewLabel1.setBounds(68, 41, 76, 45);
		lblNewLabel1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		Compose.add(lblNewLabel1);

		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(68, 97, 76, 45);
		lblTo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		Compose.add(lblTo);

		JLabel lblSubject1 = new JLabel("Subject");
		lblSubject1.setBounds(68, 149, 76, 45);
		lblSubject1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		Compose.add(lblSubject1);
		lblE.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		Compose.add(lblE);

		JTextField textFielddd = new JTextField();
		textFielddd.setBounds(154, 103, 469, 30);

		textFielddd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_SPACE) {
					textField.setText(textField.getText() + ";");
				}
			}
		});
		textFielddd.setBackground(new Color(255, 255, 255));
		textFielddd.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		Compose.add(textFielddd);
		textFielddd.setColumns(10);

		JTextField textField_211 = new JTextField();
		textField_211.setBounds(154, 159, 469, 30);
		textField_211.setBackground(new Color(255, 255, 255));
		textField_211.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_211.setColumns(10);
		Compose.add(textField_211);

		JScrollPane x = new JScrollPane();
		x.setBounds(154, 216, 690, 177);
		Compose.add(x);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 255));
		x.setViewportView(textArea);

		JButton btnNewButton1 = new JButton("Insert");
		btnNewButton1.setBounds(231, 555, 131, 45);
		btnNewButton1.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("F:\\"));

				fs.setDialogTitle("upload");

				@SuppressWarnings("unused")
				int result = fs.showSaveDialog(null);
				File fi = fs.getSelectedFile();
				if (fi == null) {
					JOptionPane.showMessageDialog(null, " No Attachement uploaded");

				} else {
					File Dir = new File("Users/temp" + "/" + fi.getName());
					Mail x = new Mail();
					x.save_attachement(fi, Dir);
					tb.addRow(new Object[] {fi.getName()});
					
					
					
				}

			}
		});
		tablex.setModel(tb);
		

		btnNewButton1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		Compose.add(btnNewButton1);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(50, 555, 131, 45);
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				Main.setVisible(true);
				Home.setVisible(false);
				Compose.setVisible(false);
				Settings.setVisible(false);
				Signup.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		Compose.add(btnBack);

		JLabel label11 = new JLabel("Body");
		label11.setBounds(68, 216, 76, 45);
		label11.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		Compose.add(label11);

		JLabel lblAttachments = new JLabel("Attachments:-");
		lblAttachments.setBounds(42, 404, 102, 45);
		lblAttachments.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		Compose.add(lblAttachments);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(435, 555, 131, 45);
		btnSend.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String[] emailss;
				Calendar cal = Calendar.getInstance();
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Mail x = new Mail();
				Contact z = new Contact();
				emailss = textFielddd.getText().split(";");
				int i = 0;
				boolean flag = false;
				boolean flag1 = false;

				if (textFielddd.getText() == "" || textField_211.getText() == "" || textArea.getText() == ""
						|| p == "") {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
				} else {
					for (i = 0; i < emailss.length; i++) {
						if (Objects.equals(email, emailss[i])) {
							flag = true;
						}
					}
					if (flag == true) {
						JOptionPane.showMessageDialog(null, "You can't send email to yourself");
						textField.setText("");
					} else {
						for (i = 0; i < emailss.length; i++) {
							if (!z.check1(emailss[i])) {
								flag1 = true;
							}
						}
						if (flag1 == true) {
							JOptionPane.showMessageDialog(null, " The Reciever is not in our server ");
							textField.setText("");
						} else {
							for (i = 0; i < emailss.length; i++) {
								x.var2(email, emailss[i], textField_211.getText(), textArea.getText(),
										sdf.format(cal.getTime()), p);
								
								x.saveOrdraft(1);
								Appp y = new Appp();
								y.compose(x);
								x.move_attachment(cont, emailss[i]);

							}
							textFielddd.setText("");
							textField_211.setText("");
							textArea.setText("");
							p = "";
							
							Main.setVisible(true);
							Home.setVisible(false);
							Compose.setVisible(false);
							Settings.setVisible(false);
							Signup.setVisible(false);
							

						}
					}
				}
			}
		});
		
		
		
		
		
		btnSend.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		Compose.add(btnSend);

		JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setBounds(630, 60, 101, 22);
		menuBar1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar1.setMargin(new Insets(0, 0, 0, 6));
		Compose.add(menuBar1);

		JMenu mnNewMenu1 = new JMenu("Priority");
		mnNewMenu1.setForeground(SystemColor.desktop);
		mnNewMenu1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		menuBar1.add(mnNewMenu1);

		JMenuItem mntmNewMenuItem1 = new JMenuItem("1");
		mntmNewMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p = "1";

			}
		});
		mnNewMenu1.add(mntmNewMenuItem1);

		JMenuItem menuItem = new JMenuItem("2");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = "2";
			}
		});
		mnNewMenu1.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("3");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = "3";
			}
		});
		mnNewMenu1.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("4");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = "4";
			}
		});
		mnNewMenu1.add(menuItem_2);
		
		JButton btnDraft = new JButton("Draft");
		btnDraft.setBounds(630, 555, 131, 45);
		
		
		btnDraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] emailss;
				Calendar cal = Calendar.getInstance();
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Mail x = new Mail();

				emailss = textFielddd.getText().split(";");
				int i = 0;
				boolean flag = false;


				if (textFielddd.getText() == "" || textField_211.getText() == "" || textArea.getText() == ""
						|| p == "") {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
				} else {
					for (i = 0; i < emailss.length; i++) {
						if (Objects.equals(email, emailss[i])) {
							flag = true;
						}
					}
					if (flag == true) {
						JOptionPane.showMessageDialog(null, "You can't send email to yourself");
						textField.setText("");
					} else {
					
							for (i = 0; i < emailss.length; i++) {
								x.var2(email, emailss[i], textField_211.getText(), textArea.getText(),
										sdf.format(cal.getTime()), p);
								
								x.saveOrdraft(2);
								Appp y = new Appp();
								y.compose(x);
								//x.move_attachment(cont, emailss[i]);
								//wakel stuff

							}
							Main.setVisible(true);
							Home.setVisible(false);
							Compose.setVisible(false);
							Settings.setVisible(false);
							Signup.setVisible(false);

						
					}
			
				
					
				}
				
				
				
			}
		});
		btnDraft.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		Compose.add(btnDraft);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 416, 506, 98);
		Compose.add(scrollPane_1);
		tablex.setModel(tb);
		tablex.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				
				
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column == 1) {

					TableModel model = (TableModel) e.getSource();
					Boolean checked = (Boolean) model.getValueAt(row, column);
					
					if (checked) {
						
						String folder = (String) tablex.getModel().getValueAt(row, 0);
						Mail z = new Mail();
						z.delete_attachment_inst(folder);
						tb.removeRow(row);
						


						
					} else {
						
						
					}
				}

			}
		});
		
		scrollPane_1.setViewportView(tablex);
		
		
		
		
		
		
		

		// Emails_View

		lblFoldername.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblFoldername.setBounds(168, 45, 107, 28);
		Emails_view.add(lblFoldername);

		JButton btnPreviouspage = new JButton("Previous_Page");
		btnPreviouspage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (Sorting.get_page() > 1) {
					Sorting.save_page(Sorting.get_page() - 1);
					Emails_view.setVisible(false);
					pg = Sorting.get_page();
					emails = Sorting.read_sorted();

					for (int i = 0; i < 10; i++) {

						required.add(new Sorting("", "", "", "", "", "", ""));
						content.add(new Sorting("", "", "", "", "", "", ""));
					}
					xx.setRowCount(0);
					switch (pg) {
					case 1:
						if (emails.size() < 10) {
							for (int i = 0; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 0; i < 10; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 2:
						if (emails.size() < 20) {
							for (int i = 10; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 10; i < 20; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 3:
						if (emails.size() < 30) {
							for (int i = 20; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 20; i < 30; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 4:
						if (emails.size() < 40) {
							for (int i = 30; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 30; i < 40; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 5:
						if (emails.size() < 50) {
							for (int i = 40; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 40; i < 50; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					default:
						break;
					}

					Emails_view.setVisible(true);

				}
			}
		});
		btnPreviouspage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnPreviouspage.setBounds(314, 38, 181, 35);
		Emails_view.add(btnPreviouspage);

		JButton btnNextpage = new JButton("Next_Page");
		btnNextpage.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (emails.size() > Sorting.get_page() * 10) {

					Sorting.save_page(Sorting.get_page() + 1);
					Emails_view.setVisible(false);
					pg = Sorting.get_page();
					emails = Sorting.read_sorted();

					for (int i = 0; i < emails.size(); i++) {
						required.add(new Sorting("", "", "", "", "", "", ""));
						content.add(new Sorting("", "", "", "", "", "", ""));

					}
					xx.setRowCount(0);
					switch (pg) {
					case 1:
						if (emails.size() < 10) {
							for (int i = 0; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 0; i < 10; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 2:
						if (emails.size() < 20) {
							for (int i = 10; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 10; i < 20; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 3:
						if (emails.size() < 30) {
							for (int i = 20; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 20; i < 30; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 4:
						if (emails.size() < 40) {
							for (int i = 30; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 30; i < 40; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					case 5:
						if (emails.size() < 50) {
							for (int i = 40; i < emails.size(); i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						} else {
							for (int i = 40; i < 50; i++) {
								xx.addRow(
										new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
												((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
												((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
							}
						}
						break;
					default:
						break;
					}

					Emails_view.setVisible(true);

				}
			}
		});
		btnNextpage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNextpage.setBounds(536, 38, 181, 35);
		Emails_view.add(btnNextpage);

		JButton button1 = new JButton("Back");
		button1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				xx.setRowCount(0);
				Sorting.save_page(1);
				Emails_view.setVisible(false);
				Main.setVisible(true);
			}
		});
		button1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button1.setBounds(750, 38, 161, 35);
		Emails_view.add(button1);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(25, 131, 729, 393);
		Emails_view.add(scrollPane1);
		scrollPane1.setViewportView(table);

		JLabel lblFoldername_1 = new JLabel("Folder_name");
		lblFoldername_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblFoldername_1.setBounds(25, 45, 107, 28);
		Emails_view.add(lblFoldername_1);

		JButton btnMoveEmails = new JButton("Move emails");
		btnMoveEmails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileSystemView fsv = new DirectoryRestrictedFileSystemView( new File[] {
					    new File("C:\\Users\\Ahmed Molahez\\git\\src\\DS_project\\Users\\"+cont)
					   
					});
				JFileChooser fs = new JFileChooser(fsv.getHomeDirectory(),fsv);
				fs.setAcceptAllFileFilterUsed(false);
				fs.setDialogTitle("move");
				fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				@SuppressWarnings("unused")
				int result = fs.showSaveDialog(null);
				File fi = fs.getSelectedFile();
				if (fi == null) {
					JOptionPane.showMessageDialog(null, " No folder choosed");

				} else {
					folderr y = new folderr();
					Appp x = new Appp();
					folderr.path = fi.getAbsolutePath();
					DLinkedList.contact = cont;
					DLinkedList.chosen_folder = folderchosen;
					x.moveEmails(required, y);
					System.out.println(fi);
					JOptionPane.showMessageDialog(null, " done");

				}
				

			}
		});
		btnMoveEmails.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnMoveEmails.setBounds(789, 204, 161, 35);
		Emails_view.add(btnMoveEmails);
		table.addMouseListener(new java.awt.event.MouseAdapter()

		{

			public void mouseClicked(java.awt.event.MouseEvent e)

			{

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (col != 6 && e.getClickCount() == 2) {

					content.add(row + (Sorting.get_page() - 1) * 10,
							new Sorting(((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).pq,
									((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).to,
									((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).from,
									((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).Subject,
									((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).body,
									((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).time,
									((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).order));
					Sorting.save_email(content, row + (Sorting.get_page() - 1) * 10);
					content1 = Sorting.load_email();
					label_111.setText((String) ((Sorting) content1.get(0)).from);
					label_21.setText((String) ((Sorting) content1.get(0)).to);
					label_3.setText((String) ((Sorting) content1.get(0)).Subject);
					label_4.setText((String) ((Sorting) content1.get(0)).pq);
					label_5.setText((String) ((Sorting) content1.get(0)).time);
					textArea1.append((String) ((Sorting) content1.get(0)).body);
					String order = Integer.toString((Sorting.get_page() - 1) * 10);
					String z = folderchosen;
					String contact = cont;
					Emails_view.setVisible(false);
					Email_content.setVisible(true);
					try {
					java.awt.Desktop.getDesktop().open(new File("recources/home.png"));
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				}

			}
		}

		);

		table.setModel(xx);
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				Sorting z = new Sorting();
				z.save_state(1);
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column == 6) {

					TableModel model = (TableModel) e.getSource();
					Boolean checked = (Boolean) model.getValueAt(row, column);
					if (checked) {
						required.add(row + (Sorting.get_page() - 1) * 10,
								new Sorting(((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).pq,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).to,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).from,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).Subject,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).body,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).time,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).order));


						
					} else {
						required.set(row + (Sorting.get_page() - 1) * 10, new Sorting("", "", "", "", "", "", ""));
						
					}
				}

			}
		});
		JButton btnDeleteEmails = new JButton("Delete emails");
		btnDeleteEmails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Emails_view.setVisible(false);
				xx.setRowCount(0);
				DLinkedList.contact = cont;
				DLinkedList.chosen_folder = folderchosen;
				System.out.println(folderchosen);
				Appp x = new Appp();
				x.deleteEmails(required);
				
				Sorting xxx = new Sorting();
				folderr fold = new folderr();
				
				fold.folderChosen(folderchosen, cont);
				fold.label(email);
				Filter switch_index = new Filter();
				System.out.println(Sorting.get_val());
				switch_index.var(Sorting.get_val(), null, null);
				x.setViewingOptions(fold, switch_index, xxx);
				pg = Sorting.get_page();
				emails = Sorting.read_sorted();


				for (int i = 0; i < emails.size(); i++) {
					required.add(new Sorting("", "", "", "", "", "", ""));
					content.add(new Sorting("", "", "", "", "", "", ""));
				}
				switch (pg) {
				case 1:
					if (emails.size() < 10) {
						for (int i = 0; i < emails.size(); i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					} else {
						for (int i = 0; i < 10; i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					}
					break;
				case 2:
					if (emails.size() < 20) {
						for (int i = 10; i < emails.size(); i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					} else {
						for (int i = 10; i < 20; i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					}
					break;
				case 3:
					if (emails.size() < 30) {
						for (int i = 10; i < emails.size(); i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					} else {
						for (int i = 20; i < 30; i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					}
					break;
				case 4:
					if (emails.size() < 40) {
						for (int i = 10; i < emails.size(); i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					} else {
						for (int i = 30; i < 40; i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					}
					break;
				case 5:
					if (emails.size() < 50) {
						for (int i = 10; i < emails.size(); i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					} else {
						for (int i = 40; i < 50; i++) {
							xx.addRow(
									new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
											((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
											((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
						}
					}
					break;
				default:
					break;
				}
				Emails_view.setVisible(true);
				
				

			}
		});
		btnDeleteEmails.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnDeleteEmails.setBounds(789, 143, 161, 35);
		Emails_view.add(btnDeleteEmails);

		// Email_content

		JLabel lblSender1 = new JLabel("Sender:-");
		lblSender1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblSender1.setBounds(45, 37, 76, 45);
		Email_content.add(lblSender1);

		JLabel lblReceiver = new JLabel("Receiver:-");
		lblReceiver.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblReceiver.setBounds(45, 93, 101, 45);
		Email_content.add(lblReceiver);

		JLabel lblSubject11 = new JLabel("Subject:-");
		lblSubject11.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblSubject11.setBounds(45, 149, 101, 45);
		Email_content.add(lblSubject11);

		JLabel lblPriority = new JLabel("Body:-");
		lblPriority.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPriority.setBounds(45, 261, 119, 45);
		Email_content.add(lblPriority);

		JLabel lblTimeSent = new JLabel("Time sent:-");
		lblTimeSent.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTimeSent.setBounds(45, 485, 119, 45);
		Email_content.add(lblTimeSent);

		JLabel label111 = new JLabel("Importance:-");
		label111.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label111.setBounds(45, 205, 119, 45);
		Email_content.add(label111);

		label_111.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_111.setBounds(175, 37, 241, 45);
		Email_content.add(label_111);

		
		label_21.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_21.setBounds(175, 93, 248, 45);
		Email_content.add(label_21);

		
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_3.setBounds(175, 149, 201, 45);
		Email_content.add(label_3);

		
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_4.setBounds(174, 205, 58, 45);
		Email_content.add(label_4);

		
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_5.setBounds(168, 485, 179, 45);
		Email_content.add(label_5);

		
		textArea1.setBackground(Color.WHITE);
		textArea1.setBounds(174, 275, 688, 175);
		Email_content.add(textArea1);
		textArea1.setEditable(false);

		JButton button11 = new JButton("Back");
		button11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Sorting.get_state() == 1) {
					Emails_view.setVisible(true);
					Email_content.setVisible(false);
					textArea1.setText("");
					
				} else {
					
					Search_Content.setVisible(true);
					Email_content.setVisible(false);
					textArea1.setText("");
					
				}
			}
		});
		button11.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		button11.setBounds(731, 486, 131, 45);
		Email_content.add(button11);
		
		
		
	
	//Search view
	
	JButton button111 = new JButton("Previous_Page");
	button111.setFont(new Font("Century Gothic", Font.PLAIN, 16));
	button.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	button.addActionListener(new ActionListener() {
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			if (Sorting.get_page() > 1) {
				Sorting.save_page(Sorting.get_page() - 1);
				
				xx1.setRowCount(0);
				Sorting.save_state(2);
				Search_Content.setVisible(false);
				tempo = Filter.read_sorted();
				emails1 = Filter.filter_results(tempo);
				
				pg = Sorting.get_page();

				for (int i = 0; i < emails1.size(); i++) {
					required1.add(new Sorting("", "", "", "", "", "", ""));
					
				}
				switch (pg) {
				case 1:
					if (emails1.size() < 10) {
						for (int i = 0; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 0; i < 10; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 2:
					if (emails1.size() < 20) {
						for (int i = 10; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 10; i < 20; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 3:
					if (emails1.size() < 30) {
						for (int i = 20; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 20; i < 30; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 4:
					if (emails1.size() < 40) {
						for (int i = 30; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 30; i < 40; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 5:
					if (emails1.size() < 50) {
						for (int i = 40; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 40; i < 50; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				default:
					break;
				}
				Search_Content.setVisible(true);
			}
		}
	});
	button111.setBounds(266, 31, 181, 35);
	Search_Content.add(button111);
	
	
	label1111.setFont(new Font("Century Gothic", Font.PLAIN, 16));
	label1111.setBounds(27, 35, 107, 28);
	Search_Content.add(label1111);
	
	JButton button_1 = new JButton("Next_Page");
	button_1.addActionListener(new ActionListener() {
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent arg0) {
			if (emails.size() > Sorting.get_page() * 10) {
				Sorting.save_page(Sorting.get_page() + 1);
				
				Search_Content.setVisible(false);
				tempo = Filter.read_sorted();
				emails1 = Filter.filter_results(tempo);
				
				pg = Sorting.get_page();

				for (int i = 0; i < emails1.size(); i++) {
					required1.add(new Sorting("", "", "", "", "", "", ""));
					
				}
				xx1.setRowCount(0);
				switch (pg) {
				case 1:
					if (emails1.size() < 10) {
						for (int i = 0; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 0; i < 10; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 2:
					if (emails1.size() < 20) {
						for (int i = 10; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 10; i < 20; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 3:
					if (emails1.size() < 30) {
						for (int i = 20; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 20; i < 30; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 4:
					if (emails1.size() < 40) {
						for (int i = 30; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 30; i < 40; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				case 5:
					if (emails1.size() < 50) {
						for (int i = 40; i < emails1.size(); i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					} else {
						for (int i = 40; i < 50; i++) {
							xx1.addRow(
									new Object[] { ((Sorting) emails1.get(i)).pq, ((Sorting) emails1.get(i)).Subject,
											((Sorting) emails1.get(i)).from, ((Sorting) emails1.get(i)).to,
											((Sorting) emails1.get(i)).time, ((Sorting) emails1.get(i)).body });
						}
					}
					break;
				default:
					break;
				}
				Search_Content.setVisible(true);

			}
		}
	});
	button_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	button_1.setBounds(527, 31, 181, 35);
	Search_Content.add(button_1);
	
	JButton button_2 = new JButton("Back");
	button_2.addActionListener(new ActionListener() {
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			xx1.setRowCount(0);
			Search_Content.setVisible(false);
			Main.setVisible(true);
			Sorting.save_page(1);

		}
	});
	button_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	button_2.setBounds(765, 31, 161, 35);
	Search_Content.add(button_2);
	
	JScrollPane scrollPane11 = new JScrollPane();
	scrollPane11.setBounds(27, 102, 915, 393);
	Search_Content.add(scrollPane11);
	scrollPane11.setViewportView(table11);
	
	

	table11.setModel(xx1);
	table11.addMouseListener(new java.awt.event.MouseAdapter()

	{

		public void mouseClicked(java.awt.event.MouseEvent e)

		{

			int row = table11.rowAtPoint(e.getPoint());
			
			
			if( e.getClickCount() == 2) {
			content11.add(row + (Sorting.get_page() - 1) * 10,
					new Sorting(((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).pq,
							((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).to,
							((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).from,
							((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).Subject,
							((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).body,
							((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).time,
							((Sorting) emails1.get(row + (Sorting.get_page() - 1) * 10)).order));
			
			Sorting.save_email(content11, row + (Sorting.get_page() - 1) * 10);
			
			content1 = Sorting.load_email();
			label_111.setText((String) ((Sorting) content1.get(0)).from);
			label_21.setText((String) ((Sorting) content1.get(0)).to);
			label_3.setText((String) ((Sorting) content1.get(0)).Subject);
			label_4.setText((String) ((Sorting) content1.get(0)).pq);
			label_5.setText((String) ((Sorting) content1.get(0)).time);
			textArea1.append((String) ((Sorting) content1.get(0)).body);
			Search_Content.setVisible(false);
			Email_content.setVisible(true);
			Appp.writee(true);}
			
		


			/*
			 * JOptionPane.showMessageDialog(null, " Value in the cell clicked :" + "" +
			 * table.getValueAt(row, col).toString());
			 * 
			 * System.out.println(" Value in the cell clicked :" + "" +
			 * table.getValueAt(row, col).toString());
			 */

		}

	}

	);
	
}
}
