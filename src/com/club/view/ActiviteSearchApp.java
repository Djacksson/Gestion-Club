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
import java.awt.event.MouseAdapter;

import javax.swing.JOptionPane;

import java.util.List;

import com.club.dao.ActiviteDAO;
import com.club.model.Activite;
import com.club.view.Home;
import com.sun.glass.events.MouseEvent;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ActiviteSearchApp extends JFrame {
	
	public Home window;
	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private ActiviteDAO activiteDAO;
	private JPanel panel_1;
	private JButton btnAddActivite;
	private JButton btnUpdateActivite;
	private JButton btnDeleteActivite;
	private JButton btnHome;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActiviteSearchApp frame = new ActiviteSearchApp();
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
	public ActiviteSearchApp() {
		
		// create the DAO
		try {
			activiteDAO = new ActiviteDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Activity Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
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
		
		JLabel lblEnterLastName = new JLabel("Enter Task Name");
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
					String nomactivite = lastNameTextField.getText();

					List<Activite> activite = null;

					if (nomactivite != null && nomactivite.trim().length() > 0) {
						activite = activiteDAO.searchActivite(nomactivite);
					} else {
						activite = activiteDAO.getAllActivite();
					}
					
					// create the model and update the "table"
					ActiviteTableModel model = new ActiviteTableModel(activite);
					
					table.setModel(model);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ActiviteSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
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
		
		btnAddActivite = new JButton("Add Activity");
		btnAddActivite.setBackground(new Color(0, 0, 128));
		btnAddActivite.setForeground(new Color(255, 255, 255));
		btnAddActivite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				ActiviteDialog dialog = new ActiviteDialog(ActiviteSearchApp.this, activiteDAO);

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
		
		btnUpdateActivite = new JButton("Update Activity");
		btnUpdateActivite.setBackground(new Color(0, 0, 128));
		btnUpdateActivite.setForeground(new Color(255, 255, 255));
		btnUpdateActivite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(ActiviteSearchApp.this, "You must select a Task", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current Activity
				Activite tempActivite = (Activite) table.getValueAt(row, ActiviteTableModel.OBJECT_COL);
				
				// create dialog
				ActiviteDialog dialog = new ActiviteDialog(ActiviteSearchApp.this, activiteDAO, 
						tempActivite, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		
		btnDeleteActivite = new JButton("Delete Activity");
		btnDeleteActivite.setBackground(new Color(0, 0, 128));
		btnDeleteActivite.setForeground(new Color(255, 255, 255));
		btnDeleteActivite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(ActiviteSearchApp.this, 
								"You must select an Task", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							ActiviteSearchApp.this, "Delete this Task?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current activity
					Activite tempActivite = (Activite) table.getValueAt(row, ActiviteTableModel.OBJECT_COL);

					// delete the employee
					activiteDAO.deleteActivite(tempActivite.getId());

					// refresh GUI
					refreshActiviteView();

					// show success message
					JOptionPane.showMessageDialog(ActiviteSearchApp.this,
							"Task deleted succesfully.", "Task Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(ActiviteSearchApp.this,
							"Error deleting Task: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(101)
					.addComponent(btnAddActivite, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(btnUpdateActivite)
					.addGap(111)
					.addComponent(btnDeleteActivite)
					.addGap(23))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnHome)
							.addComponent(btnUpdateActivite)
							.addComponent(btnAddActivite))
						.addComponent(btnDeleteActivite)))
		);
		panel_1.setLayout(gl_panel_1);
	}

	public void refreshActiviteView() {

		try {
			List<Activite> activite = activiteDAO.getAllActivite();

			// create the model and update the "table"
			ActiviteTableModel model = new ActiviteTableModel(activite);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
