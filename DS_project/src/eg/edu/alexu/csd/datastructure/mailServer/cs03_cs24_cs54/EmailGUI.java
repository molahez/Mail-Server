package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EmailGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailGUI window = new EmailGUI();
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
	public EmailGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Sign_in kk = new Sign_in();
				kk.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNewButton.setBounds(410, 251, 127, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnSignUp.setBounds(214, 251, 127, 35);
		frame.getContentPane().add(btnSignUp);
		
		JLabel lblEmailAddress = new JLabel("Email address: - ");
		lblEmailAddress.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblEmailAddress.setBounds(214, 156, 141, 28);
		frame.getContentPane().add(lblEmailAddress);
		
		JLabel lblPassword = new JLabel("Password: - ");
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblPassword.setBounds(214, 200, 95, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(360, 163, 177, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(360, 195, 177, 20);
		frame.getContentPane().add(textField_1);
	}
}
