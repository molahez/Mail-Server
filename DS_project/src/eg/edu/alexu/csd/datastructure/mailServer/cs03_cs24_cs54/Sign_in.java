package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.Node;
import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import javax.swing.JTextField;

public class Sign_in extends JFrame {
	static DLinkedList fol = new DLinkedList();
	String temp;
	
	
	int count = 5;

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			fol=Appp.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign_in frame = new Sign_in();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sign_in() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("New mail");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = 3;

			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(10, 29, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblEmailAdresss = new JLabel("Email address:-");
		lblEmailAdresss.setBounds(10, 0, 89, 14);
		contentPane.add(lblEmailAdresss);

		JLabel lblXx = new JLabel("xx");
		lblXx.setBounds(93, 0, 46, 14);
		contentPane.add(lblXx);

		JLabel lblName = new JLabel("Name:-");
		lblName.setBounds(130, 0, 46, 14);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("xx");
		lblNewLabel.setBounds(186, 0, 46, 14);
		contentPane.add(lblNewLabel);
		
		
		

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Folders") {

			{
				for (int i = 0; i < fol.size(); i++) {
					add(new DefaultMutableTreeNode(fol.get(i)));
									}
			}
		}));
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TreeSelectionModel smd = tree.getSelectionModel();
				if (smd.getSelectionCount() > 0) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
							.getLastPathComponent();
					textField.setText(selectedNode.getUserObject().toString());
					temp = textField.getText();
					
				}

			}
		});

		tree.setBounds(10, 76, 118, 168);
		contentPane.add(tree);

		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setBounds(322, 29, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnAddFolder = new JButton("add folder");
		btnAddFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(textField.getText());
				selectedNode.add(newNode);
				// reload tree model
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.reload();
				fol.add(textField.getText());
				Appp.write(fol);
				
			}
		});
		btnAddFolder.setBounds(143, 73, 112, 23);
		contentPane.add(btnAddFolder);

		JButton btnNewButton_2 = new JButton("edit folder");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();
                
                
				selectedNode.setUserObject(textField.getText());
				// reload tree model
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.reload();
				
				
				for(int i =0;i<fol.size();i++) {
					
					
					if(Objects.equals(temp,fol.get(i))) {
						
						
						fol.set(i, textField.getText());
					}
				}
				Appp.write(fol);
				

			}
		});
		btnNewButton_2.setBounds(143, 113, 112, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("delete folder");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
						.getLastPathComponent();

				if (selectedNode != tree.getModel().getRoot()) {
					DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

					model.removeNodeFromParent(selectedNode);

					model.reload();
				}
				for(int i =0;i<fol.size();i++) {
					
					
					if(Objects.equals(temp,fol.get(i))) {
						
						
						fol.remove(i);
					}
				}
				Appp.write(fol);

			}
		});
		btnNewButton_3.setBounds(143, 147, 112, 23);
		contentPane.add(btnNewButton_3);

		textField = new JTextField();
		textField.setBounds(169, 181, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
