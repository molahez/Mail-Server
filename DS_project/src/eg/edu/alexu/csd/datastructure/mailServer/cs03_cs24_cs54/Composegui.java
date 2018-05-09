package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Insets;

public class Composegui {
	// to get main data in main window

	protected static final JLabel textContent = null;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	String temp, email, password, cont, p = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Composegui window = new Composegui();
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
	public Composegui() {
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
	private void initialize() {
		email = Contact.emal;
		password = Contact.password;
		cont = Contact.contact_name;

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("From:");
		lblNewLabel.setBounds(68, 41, 76, 45);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(68, 97, 76, 45);
		lblTo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		frame.getContentPane().add(lblTo);

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(68, 149, 76, 45);
		lblSubject.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		frame.getContentPane().add(lblSubject);

		JLabel lblE = new JLabel(email);
		lblE.setBounds(167, 41, 76, 45);
		lblE.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		frame.getContentPane().add(lblE);

		textField = new JTextField();
		textField.setBounds(154, 103, 469, 30);
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(154, 159, 469, 30);
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);

		JScrollPane x = new JScrollPane();
		x.setBounds(154, 216, 690, 177);
		frame.getContentPane().add(x);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 255));
		x.setViewportView(textArea);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 418, 309, 57);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		textArea_1.setBackground(SystemColor.control);
		textArea_1.setEditable(false);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setBounds(252, 486, 131, 45);
		btnNewButton.addActionListener(new ActionListener() {
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
					textArea_1.append(fi.getName() + "\n");
				}

			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(68, 486, 131, 45);
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				MainWindow kk = new MainWindow();
				Appp.writee(true);
				kk.main(new String[5]);
			}
		});
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frame.getContentPane().add(btnBack);

		JLabel label = new JLabel("Body");
		label.setBounds(68, 216, 76, 45);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		frame.getContentPane().add(label);

		JLabel lblAttachments = new JLabel("Attachments:-");
		lblAttachments.setBounds(42, 404, 102, 45);
		lblAttachments.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		frame.getContentPane().add(lblAttachments);

		JButton btnSend = new JButton("Send");
		btnSend.setBounds(471, 486, 131, 45);
		btnSend.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Mail x = new Mail();
				Contact z = new Contact();
				String[] emails = textField.getText().split(";");
				int i = 0;
				boolean flag = false;
				boolean flag1 = false;

				if (textField.getText() == "" || textField_1.getText() == "" || textArea.getText() == "" || p == "") {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
				} else {
					for (i = 0; i < emails.length; i++) {
						if (Objects.equals(email, emails[i])) {
							flag = true;
						}
					}
					if (flag == true) {
						JOptionPane.showMessageDialog(null, "You can't send email to yourself");
						textField.setText("");
					} else {
						for (i = 0; i < emails.length; i++) {
							if (!z.check1(emails[i])) {
								flag1 = true;
							}
						}
						if (flag1 == true) {
							JOptionPane.showMessageDialog(null, " The Reciever is not in our server ");
							textField.setText("");
						} else {
							for (i = 0; i < emails.length; i++) {
								x.var2(email, emails[i], textField_1.getText(), textArea.getText(),
										sdf.format(cal.getTime()), p);

								Appp y = new Appp();
								y.compose(x);
								x.move_attachment(cont, emails[i]);
								frame.dispose();
								MainWindow kk = new MainWindow();
								Appp.writee(true);
								kk.main(new String[5]);

							}
							x.delete_temp();

						}
					}
				}
			}
		});
		btnSend.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		frame.getContentPane().add(btnSend);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		menuBar.setMargin(new Insets(0, 0, 0, 6));
		menuBar.setBounds(630, 60, 101, 22);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("Priority");
		mnNewMenu.setForeground(SystemColor.desktop);
		mnNewMenu.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("1");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p = "1";

			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem menuItem = new JMenuItem("2");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = "2";
			}
		});
		mnNewMenu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("3");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = "3";
			}
		});
		mnNewMenu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("4");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = "4";
			}
		});
		mnNewMenu.add(menuItem_2);

	}
}
