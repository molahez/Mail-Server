package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.SLinkedList;

public class Search_content {

	private JFrame frame;
	private JTable table;
	String temp, email, password, cont, folderchosen;
	static DLinkedList emails = new DLinkedList();
	static DLinkedList tempo = new DLinkedList();
	static DLinkedList required = new DLinkedList();
	static SLinkedList content = new SLinkedList();
	boolean flag;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_content window = new Search_content();
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
	public Search_content() {
		initialize();
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				Appp.writee(false);
				Sorting.save_page(1);
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
		folderchosen = folderr.folderchosen;
		Sorting.save_state(2);
		//here we must our search results
		
		tempo = Filter.read_sorted();
		emails = Filter.filter_results(tempo);
		 
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Previous_Page");
		button.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (Sorting.get_page() > 1) {
					Sorting.save_page(Sorting.get_page() - 1);
					frame.dispose();
					Search_content kk = new Search_content();
					Appp.writee(true);
					kk.main(new String[5]);
				}
			}
		});
		button.setBounds(266, 31, 181, 35);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel(cont);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		label.setBounds(27, 35, 107, 28);
		frame.getContentPane().add(label);
		
		JButton button_1 = new JButton("Next_Page");
		button_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (emails.size() > Sorting.get_page() * 10) {
					Sorting.save_page(Sorting.get_page() + 1);
					frame.dispose();
					Search_content kk = new Search_content();
					Appp.writee(true);
					kk.main(new String[5]);

				}
			}
		});
		button_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button_1.setBounds(527, 31, 181, 35);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
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
				Sorting.save_page(1);

			}
		});
		button_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button_2.setBounds(765, 31, 161, 35);
		frame.getContentPane().add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 102, 915, 393);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		scrollPane.setViewportView(table);
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
		xx.addColumn("Content");
		int y = Sorting.get_page();
		for (int i = 0; i < emails.size(); i++) {
			required.add(new Sorting("", "", "", "", "", "", ""));
			content.add(new Sorting("", "", "", "", "", "", ""));
		}
		switch (y) {
		case 1:
			if (emails.size() < 10) {
				for (int i = 0; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 0; i < 10; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 2:
			if (emails.size() < 20) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 10; i < 20; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 3:
			if (emails.size() < 30) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 20; i < 30; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 4:
			if (emails.size() < 40) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 30; i < 40; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 5:
			if (emails.size() < 50) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 40; i < 50; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time,((Sorting) emails.get(i)).body });
				}
			}
			break;
		default:
			break;
		}

		table.setModel(xx);
		table.addMouseListener(new java.awt.event.MouseAdapter()

		{

			public void mouseClicked(java.awt.event.MouseEvent e)

			{

				int row = table.rowAtPoint(e.getPoint());

				int col = table.columnAtPoint(e.getPoint());

				content.add(row + (Sorting.get_page() - 1) * 10,
						new Sorting(((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).pq,
								((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).to,
								((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).from,
								((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).Subject,
								((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).body,
								((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).time,
								((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).order));
				Sorting.save_email(content, row + (Sorting.get_page() - 1) * 10);
				frame.dispose();
				Email_content kk = new Email_content();
				Appp.writee(true);
				Sorting.save_page(1);
				kk.main(new String[5]);

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
