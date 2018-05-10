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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;

public class EmailsView {

	private JFrame frame;
	String temp, email, password, cont,folderchosen;
	private JTable table;
	static DLinkedList emails = new DLinkedList();
	static DLinkedList required = new DLinkedList();
	boolean flag;

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
				Sorting.save_page(1);

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
		folderchosen = folderr.folderchosen;
	
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFoldername = new JLabel(cont);
		lblFoldername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, " hwdek ");
			}

		});
		lblFoldername.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblFoldername.setBounds(168, 45, 107, 28);
		frame.getContentPane().add(lblFoldername);

		JButton btnPreviouspage = new JButton("Previous_Page");
		btnPreviouspage.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (Sorting.get_page() > 1) {
					Sorting.save_page(Sorting.get_page() - 1);
					frame.dispose();
					EmailsView kk = new EmailsView();
					Appp.writee(true);
					kk.main(new String[5]);
				}
			}
		});
		btnPreviouspage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnPreviouspage.setBounds(314, 38, 181, 35);
		frame.getContentPane().add(btnPreviouspage);

		JButton btnNextpage = new JButton("Next_Page");
		btnNextpage.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if (emails.size() > Sorting.get_page() * 10) {
					Sorting.save_page(Sorting.get_page() + 1);
					frame.dispose();
					EmailsView kk = new EmailsView();
					Appp.writee(true);
					kk.main(new String[5]);

				}
			}
		});
		btnNextpage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNextpage.setBounds(536, 38, 181, 35);
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
		scrollPane.setBounds(25, 131, 729, 393);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return column == 5;
			}
		};

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
				case 5:
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
		xx.addColumn("Check");
		int y = Sorting.get_page();
		emails = Sorting.read_sorted();
		for (int i = 0; i < emails.size(); i++) {
			required.add(new Sorting("","","","","","",""));
		}
		switch (y) {
		case 1:
			if (emails.size() < 10) {
				for (int i = 0; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			} else {
				for (int i = 0; i < 10; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			}
			break;
		case 2:
			if (emails.size() < 20) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			} else {
				for (int i = 10; i < 20; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			}
			break;
		case 3:
			if (emails.size() < 30) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			} else {
				for (int i = 20; i < 30; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			}
			break;
		case 4:
			if (emails.size() < 40) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			} else {
				for (int i = 30; i < 40; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			}
			break;
		case 5:
			if (emails.size() < 50) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			} else {
				for (int i = 40; i < 50; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time });
				}
			}
			break;
		default:
			break;
		}

		table.setModel(xx);

		JLabel lblFoldername_1 = new JLabel("Folder_name");
		lblFoldername_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblFoldername_1.setBounds(25, 45, 107, 28);
		frame.getContentPane().add(lblFoldername_1);

		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column == 5) {
					TableModel model = (TableModel) e.getSource();
					String columnName = model.getColumnName(column);
					Boolean checked = (Boolean) model.getValueAt(row, column);
					if (checked) {
						required.add(row,
								new Sorting(((Sorting) emails.get(row)).pq, ((Sorting) emails.get(row)).to,
										((Sorting) emails.get(row)).from, ((Sorting) emails.get(row)).Subject,
										((Sorting) emails.get(row)).body, ((Sorting) emails.get(row)).time,
										((Sorting) emails.get(row)).order));

						String col = table.getValueAt(row, 0).toString();
						System.out.println(col);
						System.out.println(columnName + ": " + true);
					} else {
						required.set(row, new Sorting("","","","","","",""));
						System.out.println(columnName + ": " + false);
					}
				}

			}
		});

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnTest.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnTest.setBounds(799, 143, 161, 35);
		frame.getContentPane().add(btnTest);

	}
}
