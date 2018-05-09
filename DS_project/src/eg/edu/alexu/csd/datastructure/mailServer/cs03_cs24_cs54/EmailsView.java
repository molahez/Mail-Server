package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;

public class EmailsView {

	private JFrame frame;
	String temp, email, password, cont;
	private JTable table;
	static DLinkedList emails = new DLinkedList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailsView window = new EmailsView();
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
	public EmailsView() {
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
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFoldername = new JLabel("Folder_name");
		lblFoldername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, " hwdek ");
			}

		});
		lblFoldername.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblFoldername.setBounds(25, 45, 141, 28);
		frame.getContentPane().add(lblFoldername);

		JButton btnPreviouspage = new JButton("Previous_Page");
		btnPreviouspage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Sorting.get_page()>1) {
				Sorting.save_page(Sorting.get_page()-1);
				frame.dispose();
				EmailsView kk = new EmailsView();
				Appp.writee(true);
				kk.main(new String[5]);
			}}
		});
		btnPreviouspage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnPreviouspage.setBounds(243, 38, 181, 35);
		frame.getContentPane().add(btnPreviouspage);

		JButton btnNextpage = new JButton("Next_Page");
		btnNextpage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(emails.size()>Sorting.get_page()*10) {
				Sorting.save_page(Sorting.get_page()+1);
				frame.dispose();
				EmailsView kk = new EmailsView();
				Appp.writee(true);
				kk.main(new String[5]);
				
			}}
		});
		btnNextpage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNextpage.setBounds(493, 38, 181, 35);
		frame.getContentPane().add(btnNextpage);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow kk = new MainWindow();
				Appp.writee(true);
				kk.main(new String[5]);

			}
		});
		button.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button.setBounds(750, 38, 161, 35);
		frame.getContentPane().add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 131, 729, 402);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		scrollPane.setViewportView(table);
		DefaultTableModel xx = new DefaultTableModel();
		table.setRowHeight(40);

		xx.addColumn("Importance");
		xx.addColumn("Subject");
		xx.addColumn("Sender");
		xx.addColumn("Receiver");
		xx.addColumn("Time");
		int y = Sorting.get_page();
		emails = Sorting.read_sorted();
		switch (y) {
		case 1:
			for (int i = 0; i < 10; i++) {
				xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
						((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to, ((Sorting) emails.get(i)).time });
			}
			break;
		case 2:
			for (int i = 10; i < 20; i++) {
				xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
						((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to, ((Sorting) emails.get(i)).time });
			}
			break;
		case 3:
			for (int i = 20; i < 30; i++) {
				xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
						((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to, ((Sorting) emails.get(i)).time });
			}
			break;
		case 4:
			for (int i = 30; i < 40; i++) {
				xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
						((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to, ((Sorting) emails.get(i)).time });
			}
			break;
		case 5:
			for (int i = 40; i < 50; i++) {
				xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
						((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to, ((Sorting) emails.get(i)).time });
			}
			break;
		default:
			break;
		}

		table.setModel(xx);

	}
}
