package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Settings {

	private JFrame frame;
	String temp,email, password, cont;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
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
	public Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		email = Contact.emal;
		password = Contact.password;
		cont = Contact.contact_name;
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Email Address:-");
		label.setForeground(Color.GREEN);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label.setBounds(192, 108, 173, 30);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Password:-");
		label_1.setForeground(Color.GREEN);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_1.setBounds(192, 171, 173, 30);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Confirm Password:-");
		label_2.setForeground(Color.GREEN);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_2.setBounds(192, 230, 173, 30);
		frame.getContentPane().add(label_2);
		
		JLabel lblX = new JLabel(email);
		lblX.setForeground(Color.GREEN);
		lblX.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblX.setBounds(386, 108, 173, 30);
		frame.getContentPane().add(lblX);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				MainWindow kk = new MainWindow();
				Appp.writee(true);
				try {
					kk.main(new String[5]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button.setBounds(192, 365, 102, 71);
		frame.getContentPane().add(button);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Contact x = new Contact();
				x.var(email, new String(passwordField.getPassword()),cont);
				if(passwordField.getPassword().length == 0
						|| passwordField_1.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, " Please fill all fields");
					passwordField.setText("");
					passwordField_1.setText("");
				}
				else if (passwordField.getPassword().length < 8) {
					JOptionPane.showMessageDialog(null, " Password is less than 8 characters please try again");
					
					passwordField.setText("");
					passwordField_1.setText("");
					
				} else if (!(Arrays.equals(passwordField.getPassword(),passwordField_1.getPassword()))) {
					
					JOptionPane.showMessageDialog(null, " Password doesn't match please try again");
					
					passwordField.setText("");
					passwordField_1.setText("");
					
				}
				else {
					x.edit_contact(email, new String(passwordField.getPassword()));
					
					frame.dispose();
					MainWindow kk = new MainWindow();
					Appp.writee(true);
					try {
						kk.main(new String[5]);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setForeground(Color.DARK_GRAY);
		btnSave.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnSave.setBounds(457, 365, 102, 71);
		frame.getContentPane().add(btnSave);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(386, 175, 207, 30);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(386, 230, 207, 30);
		frame.getContentPane().add(passwordField_1);
	}

}
