package com.club.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import com.club.dao.MembreDAO;
import com.club.model.Membre;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class MembreSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private MembreDAO membreDAO;
	private JPanel panel_1;
	private JButton btnAddMember;
	private JButton btnUpdateMember;
	private JButton btnDeleteMember;
	private JButton btnHome;
	protected Object frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MembreSearchApp frame = new MembreSearchApp();
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
	public MembreSearchApp() {
		
		// create the DAO
		try {
			membreDAO = new MembreDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Membre Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterLastName = new JLabel("Enter last name");
		lblEnterLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblEnterLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBackground(new Color(0, 0, 128));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				
				try {
					String lastName = lastNameTextField.getText();

					List<Membre> membre = null;

					if (lastName != null && lastName.trim().length() > 0) {
						membre = membreDAO.searchMembre(lastName);
					} else {
						membre = membreDAO.getAllMembre();
					}
					
					// create the model and update the "table"
					MembreTableModel model = new MembreTableModel(membre);
					
					table.setModel(model);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(MembreSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel.add(btnSearch);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 220));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnAddMember = new JButton("Add Member");
		btnAddMember.setBackground(new Color(0, 0, 128));
		btnAddMember.setForeground(new Color(255, 255, 255));
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				MembreDialog dialog = new MembreDialog(MembreSearchApp.this, membreDAO);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		
		btnHome = new JButton("Home");
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(0, 0, 128));
		btnHome.setForeground(new Color(255, 255, 255));		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				try {
					Home window = new Home();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		
		btnUpdateMember = new JButton("Update Member");
		btnUpdateMember.setBackground(new Color(0, 0, 128));
		btnUpdateMember.setForeground(new Color(255, 255, 255));
		btnUpdateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(MembreSearchApp.this, "You must select an Member", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current employee
				Membre tempMembre = (Membre) table.getValueAt(row, MembreTableModel.OBJECT_COL);
				
				// create dialog
				MembreDialog dialog = new MembreDialog(MembreSearchApp.this, membreDAO,tempMembre, true);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);			
			}
		});
		
		btnDeleteMember = new JButton("Delete Member");
		btnDeleteMember.setBackground(new Color(0, 0, 128));
		btnDeleteMember.setForeground(new Color(255, 255, 255));
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(MembreSearchApp.this, 
								"You must select an Member", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							MembreSearchApp.this, "Delete this Member?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current employee
					Membre tempMembre = (Membre) table.getValueAt(row, MembreTableModel.OBJECT_COL);

					// delete the employee
					membreDAO.deleteMembre(tempMembre.getId());

					// refresh GUI
					refreshMembreView();

					// show success message
					JOptionPane.showMessageDialog(MembreSearchApp.this,
							"Member deleted succesfully.", "Member Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(MembreSearchApp.this,
							"Error deleting Member: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(72)
					.addComponent(btnAddMember, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(71)
					.addComponent(btnUpdateMember)
					.addGap(72)
					.addComponent(btnDeleteMember)
					.addGap(44))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHome)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddMember)
							.addComponent(btnUpdateMember)
							.addComponent(btnDeleteMember)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
	}

	public void refreshMembreView() {

		try {
			List<Membre> membre = membreDAO.getAllMembre();

			// create the model and update the "table"
			MembreTableModel model = new MembreTableModel(membre);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
