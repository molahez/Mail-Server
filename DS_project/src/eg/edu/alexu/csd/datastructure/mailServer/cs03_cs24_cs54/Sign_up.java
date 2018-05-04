package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class Sign_up {
	

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JLabel lblEmailAddress;
	private JLabel lblPassword;
	private JLabel lblName;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;
	private JLabel lblConfirmPassword;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign_up window = new Sign_up();
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
	public Sign_up() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(468, 108, 169, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_2.setBounds(468, 259, 169, 33);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Contact x = new Contact();
				x.var(textField.getText(), new String(passwordField.getPassword()), textField_2.getText());
				Appp y = new Appp();
				if (textField.getText() == "" || textField_2.getText() == "" || passwordField.getPassword().length == 0
						|| passwordField_1.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
					textField.setText("");
					passwordField.setText("");
					passwordField_1.setText("");
					textField_2.setText("");

				} else if (passwordField.getPassword().length < 8) {
					JOptionPane.showMessageDialog(null, " Password is less than 8 characters please try again");
					textField.setText("");
					passwordField.setText("");
					passwordField_1.setText("");
					textField_2.setText("");

				} else if ((Objects.equals(passwordField.getPassword(), passwordField_1.getPassword()))) {
					JOptionPane.showMessageDialog(null, " Password doesn't match please try again");
					textField.setText("");
					passwordField.setText("");
					passwordField_1.setText("");
					textField_2.setText("");

				} else {
					if (y.signup(x)) {

						frame.dispose();
						MainWindow kk = new MainWindow();
						kk.main(new String[5]);
					} else {
						JOptionPane.showMessageDialog(null, " Invalid email address please try again");

						textField.setText("");
						passwordField.setText("");
						passwordField_1.setText("");
						textField_2.setText("");
					}

				}
			}
		});
		btnNewButton.setBounds(518, 338, 119, 71);
		frame.getContentPane().add(btnNewButton);

		lblEmailAddress = new JLabel("Email Address:-");
		lblEmailAddress.setForeground(new Color(0, 255, 0));
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblEmailAddress.setBounds(247, 104, 173, 30);
		frame.getContentPane().add(lblEmailAddress);

		lblPassword = new JLabel("Password:-");
		lblPassword.setForeground(new Color(0, 255, 0));
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPassword.setBounds(247, 155, 173, 30);
		frame.getContentPane().add(lblPassword);

		lblName = new JLabel("Name:-");
		lblName.setForeground(new Color(0, 255, 0));
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblName.setBounds(247, 259, 86, 30);
		frame.getContentPane().add(lblName);

		passwordField = new JPasswordField();
		passwordField.setBounds(468, 159, 169, 30);
		frame.getContentPane().add(passwordField);

		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeGUI kk = new HomeGUI();
				kk.main(new String[5]);

			}

		});
		btnNewButton_1.setBounds(247, 338, 102, 71);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblExUsermailservercom = new JLabel("ex:- user@mailserver.com");
		lblExUsermailservercom.setForeground(new Color(128, 128, 128));
		lblExUsermailservercom.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblExUsermailservercom.setBounds(685, 104, 237, 30);
		frame.getContentPane().add(lblExUsermailservercom);

		JLabel lblExUsermailservercom_1 = new JLabel("Password must contain at least 8 characters");
		lblExUsermailservercom_1.setForeground(Color.GRAY);
		lblExUsermailservercom_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblExUsermailservercom_1.setBounds(685, 155, 289, 30);
		frame.getContentPane().add(lblExUsermailservercom_1);

		lblConfirmPassword = new JLabel("Confirm Password:-");
		lblConfirmPassword.setForeground(Color.GREEN);
		lblConfirmPassword.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblConfirmPassword.setBounds(247, 206, 173, 30);
		frame.getContentPane().add(lblConfirmPassword);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(468, 210, 169, 30);
		frame.getContentPane().add(passwordField_1);
	}
}
