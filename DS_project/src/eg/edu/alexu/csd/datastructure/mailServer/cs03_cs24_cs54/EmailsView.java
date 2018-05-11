package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.SLinkedList;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EmailsView {

	private JFrame frame;
	String temp, email, password, cont, folderchosen;
	private JTable table;
	static DLinkedList emails = new DLinkedList();
	static DLinkedList required = new DLinkedList();
	static SLinkedList content = new SLinkedList();
	boolean flag;
	String selected;

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
	class DirectoryRestrictedFileSystemView extends FileSystemView
	{
	    private final File[] rootDirectories;

	    DirectoryRestrictedFileSystemView(File rootDirectory)
	    {
	        this.rootDirectories = new File[] {rootDirectory};
	    }

	    DirectoryRestrictedFileSystemView(File[] rootDirectories)
	    {
	        this.rootDirectories = rootDirectories;
	    }

	    @Override
	    public File createNewFolder(File containingDir) throws IOException
	    {       
	        throw new UnsupportedOperationException("Unable to create directory");
	    }
	    @Override
	    public File getHomeDirectory()
	    {
	      return rootDirectories[0];
	    }

	    @Override
	    public File[] getRoots()
	    {
	        return rootDirectories;
	    }

	    @Override
	    public boolean isRoot(File file)
	    {
	        for (File root : rootDirectories) {
	            if (root.equals(file)) {
	                return true;
	            }
	        }
	        return false;
	    }
	}

	/**
	 * Create the application.
	 */
	public EmailsView() {
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
	@SuppressWarnings("serial")
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		email = Contact.emal;
		password = Contact.password;
		cont = Contact.contact_name;
		folderchosen = folderr.folderchosen;
		Sorting.save_state(1);

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFoldername = new JLabel(cont);

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
				return column == 6;
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
					return String.class;
				case 6:
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
		xx.addColumn("content");
		xx.addColumn("Check");
		int y = Sorting.get_page();
		emails = Sorting.read_sorted();
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
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 0; i < 10; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 2:
			if (emails.size() < 20) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 10; i < 20; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 3:
			if (emails.size() < 30) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 20; i < 30; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 4:
			if (emails.size() < 40) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 30; i < 40; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			}
			break;
		case 5:
			if (emails.size() < 50) {
				for (int i = 10; i < emails.size(); i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			} else {
				for (int i = 40; i < 50; i++) {
					xx.addRow(new Object[] { ((Sorting) emails.get(i)).pq, ((Sorting) emails.get(i)).Subject,
							((Sorting) emails.get(i)).from, ((Sorting) emails.get(i)).to,
							((Sorting) emails.get(i)).time, ((Sorting) emails.get(i)).body });
				}
			}
			break;
		default:
			break;
		}

		JLabel lblFoldername_1 = new JLabel("Folder_name");
		lblFoldername_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblFoldername_1.setBounds(25, 45, 107, 28);
		frame.getContentPane().add(lblFoldername_1);

		JButton btnMoveEmails = new JButton("Move emails");
		btnMoveEmails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileSystemView fsv = new DirectoryRestrictedFileSystemView( new File[] {
					    new File("C:\\Users\\Ahmed Molahez\\git\\src\\DS_project\\Users\\"+cont)
					   
					});
				JFileChooser fs = new JFileChooser(fsv.getHomeDirectory(),fsv);
				fs.setAcceptAllFileFilterUsed(false);
				fs.setDialogTitle("move");
				fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				@SuppressWarnings("unused")
				int result = fs.showSaveDialog(null);
				File fi = fs.getSelectedFile();
				if (fi == null) {
					JOptionPane.showMessageDialog(null, " No folder choosed");

				} else {
					System.out.println(fi);
					JOptionPane.showMessageDialog(null, " done");

				}


			}
		});
		btnMoveEmails.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		btnMoveEmails.setBounds(789, 204, 161, 35);
		frame.getContentPane().add(btnMoveEmails);
		table.addMouseListener(new java.awt.event.MouseAdapter()

		{

			public void mouseClicked(java.awt.event.MouseEvent e)

			{
				
				int row = table.rowAtPoint(e.getPoint());

				int col = table.columnAtPoint(e.getPoint());
				if (col != 6 && e.getClickCount() == 2) {
					
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

				}

			}
		}

		);

		table.setModel(xx);

		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column == 6) {
					
					TableModel model = (TableModel) e.getSource();
					String columnName = model.getColumnName(column);
					Boolean checked = (Boolean) model.getValueAt(row, column);
					if (checked) {
						required.add(row + (Sorting.get_page() - 1) * 10,
								new Sorting(((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).pq,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).to,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).from,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).Subject,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).body,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).time,
										((Sorting) emails.get(row + (Sorting.get_page() - 1) * 10)).order));

						String col = table.getValueAt(row, 0).toString();
						System.out.println(col);
						System.out.println(columnName + ": " + true);
					} else {
						required.set(row + (Sorting.get_page() - 1) * 10, new Sorting("", "", "", "", "", "", ""));
						System.out.println(columnName + ": " + false);
					}
				} 

			}
		});
		JButton btnDeleteEmails = new JButton("Delete emails");
		btnDeleteEmails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLinkedList y = new DLinkedList();
				DLinkedList.contact = cont;
				DLinkedList.chosen_folder = folderchosen;
				System.out.println(folderchosen);
				Appp x = new Appp();
				x.deleteEmails(required);
				frame.dispose();
				Sorting xx = new Sorting();
				folderr fold = new folderr();
				fold.folderChosen(folderchosen, cont);
				fold.label(email);
				Filter switch_index = new Filter();
				System.out.println(Sorting.get_val());
				switch_index.var(Sorting.get_val(), null, null);
				Appp zz = new Appp();
				zz.setViewingOptions(fold, switch_index, xx);
				EmailsView kk = new EmailsView();
				kk.main(new String[5]);
				Appp.writee(false);


			}
		});
		btnDeleteEmails.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnDeleteEmails.setBounds(789, 143, 161, 35);
		frame.getContentPane().add(btnDeleteEmails);

	}
}
