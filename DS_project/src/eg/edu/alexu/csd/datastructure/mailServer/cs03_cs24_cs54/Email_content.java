package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.SLinkedList;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Email_content {

	private JFrame frame;
	static SLinkedList content = new SLinkedList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Email_content window = new Email_content();
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
	public Email_content() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		content = Sorting.load_email();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSender = new JLabel("Sender:-");
		lblSender.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblSender.setBounds(45, 37, 76, 45);
		frame.getContentPane().add(lblSender);
		
		JLabel lblReceiver = new JLabel("Receiver:-");
		lblReceiver.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblReceiver.setBounds(45, 93, 101, 45);
		frame.getContentPane().add(lblReceiver);
		
		JLabel lblSubject = new JLabel("Subject:-");
		lblSubject.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblSubject.setBounds(45, 149, 101, 45);
		frame.getContentPane().add(lblSubject);
		
		JLabel lblPriority = new JLabel("Body:-");
		lblPriority.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPriority.setBounds(45, 261, 119, 45);
		frame.getContentPane().add(lblPriority);
		
		JLabel lblTimeSent = new JLabel("Time sent:-");
		lblTimeSent.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTimeSent.setBounds(45, 485, 119, 45);
		frame.getContentPane().add(lblTimeSent);
		
		JLabel label = new JLabel("Importance:-");
		label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label.setBounds(45, 205, 119, 45);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel((String) ((Sorting)content.get(0)).from);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_1.setBounds(175, 37, 241, 45);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel((String) ((Sorting)content.get(0)).to);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_2.setBounds(175, 93, 248, 45);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel((String) ((Sorting)content.get(0)).Subject);
		label_3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_3.setBounds(175, 149, 201, 45);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel((String) ((Sorting)content.get(0)).pq);
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_4.setBounds(174, 205, 58, 45);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel((String) ((Sorting)content.get(0)).time);
		label_5.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		label_5.setBounds(168, 485, 179, 45);
		frame.getContentPane().add(label_5);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(174, 275, 688, 175);
		frame.getContentPane().add(textArea);
		textArea.setEditable(false);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Sorting.get_state()==1) {
					Sorting.save_page(1);
					frame.dispose();
					EmailsView kk = new EmailsView();
					Appp.writee(true);
					kk.main(new String[5]);
					
				}
				else {
					Sorting.save_page(1);
				frame.dispose();
				Search_content kk = new Search_content();
				Appp.writee(true);
				kk.main(new String[5]);
			}}
		});
		button.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		button.setBounds(731, 486, 131, 45);
		frame.getContentPane().add(button);
		textArea.append((String) ((Sorting)content.get(0)).body);

	}
}
