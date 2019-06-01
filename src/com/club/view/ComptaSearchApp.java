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

import com.club.dao.ComptaDAO;
import com.club.model.Compta;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;

public class ComptaSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private ComptaDAO comptaDAO;
	private JPanel panel_1;
	private JButton btnAddCompta;
	private JButton btnUpdateCompta;
	private JButton btnDeleteCompta;
	private JButton btnHome;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComptaSearchApp frame = new ComptaSearchApp();
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
	public ComptaSearchApp() {
		
		// create the DAO
		try {
			comptaDAO = new ComptaDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Compta Search App");
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
		panel.add(lblEnterLastName);
		
		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(0, 0, 128));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				
				try {
					String lastName = lastNameTextField.getText();

					List<Compta> compta = null;

					if (lastName != null && lastName.trim().length() > 0) {
						compta = comptaDAO.searchCompta(lastName);
					} else {
						compta = comptaDAO.getAllCompta();
					}
					
					// create the model and update the "table"
					ComptaTableModel model = new ComptaTableModel(compta);
					
					table.setModel(model);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ComptaSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
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
		
		btnAddCompta = new JButton("Add a new Operation");
		btnAddCompta.setBackground(new Color(0, 0, 128));
		btnAddCompta.setForeground(new Color(255, 255, 255));
		btnAddCompta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				ComptaDialog dialog = new ComptaDialog(ComptaSearchApp.this, comptaDAO);

				// show dialog
				dialog.setVisible(true);
			}
		});
		
		btnHome = new JButton("Home");
		btnHome.setBackground(new Color(0, 0, 128));
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		
		btnUpdateCompta = new JButton("Update Operation");
		btnUpdateCompta.setBackground(new Color(0, 0, 128));
		btnUpdateCompta.setForeground(new Color(255, 255, 255));
		btnUpdateCompta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(ComptaSearchApp.this, "You must select an Operation", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current employee
				Compta tempMembre = (Compta) table.getValueAt(row, MembreTableModel.OBJECT_COL);
				
				// create dialog
				ComptaDialog dialog = new ComptaDialog(ComptaSearchApp.this, comptaDAO, 
															tempMembre, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		
		btnDeleteCompta = new JButton("Delete Operation");
		btnDeleteCompta.setBackground(new Color(0, 0, 128));
		btnDeleteCompta.setForeground(new Color(255, 255, 255));
		btnDeleteCompta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(ComptaSearchApp.this, 
								"You must select an Operation", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							ComptaSearchApp.this, "Delete this Operation?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current Operation
					Compta tempCompta = (Compta) table.getValueAt(row, ComptaTableModel.OBJECT_COL);

					// delete the employee
					comptaDAO.deleteCompta(tempCompta.getId());

					// refresh GUI
					refreshComptaView();

					// show success message
					JOptionPane.showMessageDialog(ComptaSearchApp.this,
							"Operation deleted succesfully.", "Operation Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ComptaSearchApp.this,
							"Error deleting Member: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnAddCompta)
					.addGap(49)
					.addComponent(btnUpdateCompta)
					.addGap(58)
					.addComponent(btnDeleteCompta)
					.addGap(39))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHome)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDeleteCompta)
							.addComponent(btnUpdateCompta)
							.addComponent(btnAddCompta))))
		);
		panel_1.setLayout(gl_panel_1);
	}

	public void refreshComptaView() {

		try {
			List<Compta> compta = comptaDAO.getAllCompta();

			// create the model and update the "table"
			ComptaTableModel model = new ComptaTableModel(compta);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
