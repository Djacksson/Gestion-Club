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

import com.club.dao.EventDAO;
import com.club.model.Event;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class EventSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField NomEventTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private EventDAO eventDAO;
	private JPanel panel_1;
	private JButton btnAddEvent;
	private JButton btnUpdateEvent;
	private JButton btnDeleteEvent;
	private JButton btnHome;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventSearchApp frame = new EventSearchApp();
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
	public EventSearchApp() {
		
		// create the DAO
		try {
			eventDAO = new EventDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Event Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);
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
		
		JLabel lblEnterLastName = new JLabel("Enter Name of Event");
		panel.add(lblEnterLastName);
		
		NomEventTextField = new JTextField();
		panel.add(NomEventTextField);
		NomEventTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(255, 165, 0));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				try {
					String nomevent = NomEventTextField.getText();

					List<Event> event = null;

					if (nomevent != null && nomevent.trim().length() > 0) {
						event = eventDAO.searchEvent(nomevent);
					} else {
						event = eventDAO.getAllEvent();
					}
					
					// create the model and update the "table"
					EventTableModel model = new EventTableModel(event);
					
					table.setModel(model);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(EventSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
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
		
		btnAddEvent = new JButton("Add Event");
		btnAddEvent.setForeground(new Color(255, 255, 255));
		btnAddEvent.setBackground(new Color(50, 205, 50));
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				EventDialog dialog = new EventDialog(EventSearchApp.this, eventDAO);

				// show dialog
				dialog.setVisible(true);
			}
		});
		
		btnHome = new JButton("Home");
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(128, 128, 128));
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
		
		btnUpdateEvent = new JButton("Update Event");
		btnUpdateEvent.setForeground(new Color(255, 255, 255));
		btnUpdateEvent.setBackground(new Color(70, 130, 180));
		btnUpdateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(EventSearchApp.this, "You must select an event", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current employee
				Event tempEvent = (Event) table.getValueAt(row, EventTableModel.OBJECT_COL);
				
				// create dialog
				EventDialog dialog = new EventDialog(EventSearchApp.this, eventDAO, 
															tempEvent, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		
		btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setForeground(new Color(255, 255, 255));
		btnDeleteEvent.setBackground(new Color(255, 0, 0));
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(EventSearchApp.this, 
								"You must select an event", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							EventSearchApp.this, "Delete this Event?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current Event
					Event tempEvent = (Event) table.getValueAt(row, EventTableModel.OBJECT_COL);

					// delete the Event
					eventDAO.deleteEvent(tempEvent.getId());

					// refresh GUI
					refreshEventView();

					// show success message
					JOptionPane.showMessageDialog(EventSearchApp.this,
							"Event deleted succesfully.", "Event Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(EventSearchApp.this,
							"Error deleting Event: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(23)
					.addComponent(btnHome)
					.addGap(193)
					.addComponent(btnAddEvent)
					.addGap(5)
					.addComponent(btnUpdateEvent)
					.addGap(5)
					.addComponent(btnDeleteEvent))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddEvent)
							.addComponent(btnHome))
						.addComponent(btnUpdateEvent)
						.addComponent(btnDeleteEvent)))
		);
		panel_1.setLayout(gl_panel_1);
	}

	public void refreshEventView() {

		try {
			List<Event> event = eventDAO.getAllEvent();

			// create the model and update the "table"
			EventTableModel model = new EventTableModel(event);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
