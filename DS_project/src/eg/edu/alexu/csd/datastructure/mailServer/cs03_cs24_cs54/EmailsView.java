package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;

public class EmailsView {

	private JFrame frame;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		/*int k = 0;
		String s = "a";
		for (int i = 0; i < 10; i++) {
			JLabel label1 = new JLabel("Sender-Subject");
			s=s+"a";
			label1.addMouseListener(new MouseAdapter() {
			
				public void mouseClicked(MouseEvent arg0) {
					JOptionPane.showMessageDialog(null,s);
				}

			});
			label1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
			label1.setBounds(25, 87 + k, 272, 28);
			frame.getContentPane().add(label1);
			k = k + 44;
			

		}*/

		

		JButton btnPreviouspage = new JButton("Previous_Page");
		btnPreviouspage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnPreviouspage.setBounds(243, 38, 181, 35);
		frame.getContentPane().add(btnPreviouspage);

		JButton btnNextpage = new JButton("Next_Page");
		btnNextpage.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnNextpage.setBounds(493, 38, 181, 35);
		frame.getContentPane().add(btnNextpage);

		JButton button = new JButton("Back");
		button.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		button.setBounds(750, 38, 161, 35);
		frame.getContentPane().add(button);
		
		JTree tree = new JTree();
		tree.setForeground(Color.GRAY);
		tree.setBackground(Color.WHITE);
		tree.setVisibleRowCount(30);
		tree.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tree.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Inbox") {
				{
					add(new DefaultMutableTreeNode("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"));
					add(new DefaultMutableTreeNode("2"));
					add(new DefaultMutableTreeNode("3"));
					add(new DefaultMutableTreeNode("4"));
					add(new DefaultMutableTreeNode("5"));
					add(new DefaultMutableTreeNode("6"));
					add(new DefaultMutableTreeNode("7"));
					add(new DefaultMutableTreeNode("8"));
					add(new DefaultMutableTreeNode("9"));
					add(new DefaultMutableTreeNode("10\t"));
				}
			}
		));
		tree.setBounds(43, 95, 334, 298);
		frame.getContentPane().add(tree);
	}
}
