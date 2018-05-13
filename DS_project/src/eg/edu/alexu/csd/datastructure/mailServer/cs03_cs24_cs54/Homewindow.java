package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Homewindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homewindow window = new Homewindow();
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
	public Homewindow() {
		initialize();
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				Appp.writee(false);
				System.exit(0);

			}

		});
	}

	/**
	 * Initialize the contents of the frame   .
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/*try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("recources/home.png")))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setResizable(false);
		frame.pack();*/
		JButton btnNewButton = new JButton("Log in to your account");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				
				Appp y = new Appp();
				if (passwordField.getPassword().length == 0 || textField.getText() == "") {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
					textField.setText("");
					passwordField.setText("");
					
				} else if (y.signin(textField.getText(), new String(passwordField.getPassword()))) {
					frame.dispose();
					
					MainWindow kk = new MainWindow();

					try {
						kk.main(new String[5]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, " Invalid email address or Password ");

					textField.setText("");
					passwordField.setText("");
				}

			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton.setBounds(224, 251, 313, 58);
		frame.getContentPane().add(btnNewButton);

		JButton btnSignUp = new JButton("New here? Sign up!");
		btnSignUp.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Sign_up kk = new Sign_up();
				kk.main(new String[5]);
			}
		});
		btnSignUp.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnSignUp.setBounds(224, 338, 313, 58);
		frame.getContentPane().add(btnSignUp);

		JLabel lblEmailAddress = new JLabel("Email address");
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblEmailAddress.setBounds(587, 124, 141, 28);
		frame.getContentPane().add(lblEmailAddress);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblPassword.setBounds(587, 200, 141, 28);
		frame.getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setBounds(224, 124, 313, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(224, 200, 313, 28);
		frame.getContentPane().add(passwordField);
		
		JLabel lblLogin = new JLabel("Log in");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		lblLogin.setBounds(224, 36, 154, 58);
		frame.getContentPane().add(lblLogin);
	}
}
