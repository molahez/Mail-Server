package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class Composegui {

	protected static final JLabel textContent = null;
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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("From:");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblNewLabel.setBounds(68, 41, 76, 45);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTo.setBounds(68, 97, 76, 45);
		frame.getContentPane().add(lblTo);

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblSubject.setBounds(68, 149, 76, 45);
		frame.getContentPane().add(lblSubject);

		JLabel lblE = new JLabel("e");
		lblE.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblE.setBounds(167, 41, 76, 45);
		frame.getContentPane().add(lblE);

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField.setBounds(154, 103, 469, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(154, 159, 469, 30);
		frame.getContentPane().add(textField_1);

		JScrollPane x = new JScrollPane();
		x.setBounds(154, 216, 690, 177);
		frame.getContentPane().add(x);

		JTextArea textArea = new JTextArea();
		x.setViewportView(textArea);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("F:\\"));
				fs.setDialogTitle("save");
				fs.setFileFilter(new Filetypefilter(".txt", "Text File"));
				int result = fs.showSaveDialog(null);
				File fi = fs.getSelectedFile();
				File Dir = new File("recources");
				boolean success = fi.renameTo(new File(Dir, fi.getName()));
				if (!success) {
				    // File was not successfully moved
				}
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnNewButton.setBounds(252, 486, 131, 45);
		frame.getContentPane().add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				MainWindow kk = new MainWindow();
				Appp.writee(true);
				kk.main(new String[5]);
			}
		});
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnBack.setBounds(68, 486, 131, 45);
		frame.getContentPane().add(btnBack);
		
		JLabel label = new JLabel("Body");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label.setBounds(68, 216, 76, 45);
		frame.getContentPane().add(label);
		
		JLabel lblAttachments = new JLabel("Attachments:-");
		lblAttachments.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblAttachments.setBounds(68, 404, 102, 45);
		frame.getContentPane().add(lblAttachments);

	}
}
