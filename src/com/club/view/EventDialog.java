package com.club.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.club.dao.EventDAO;
import com.club.model.Event;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class EventDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeventTextField;
	private JTextField objectifeventTextField;
	private JTextField dateeventTextField;
	private JTextField descriptioneventTextField;

	private EventDAO eventDAO;

	private EventSearchApp eventSearchApp;

	private Event previousEvent = null;
	private boolean updateMode = false;

	public EventDialog(EventSearchApp theEventSearchApp,
			EventDAO theEventDAO, Event thePreviousEvent, boolean theUpdateMode) {
		this();
		eventDAO = theEventDAO;
		eventSearchApp = theEventSearchApp;

		previousEvent = thePreviousEvent;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Event");
			
			populateGui(previousEvent);
		}
	}

	private void populateGui(Event theEvent) {

		nomeventTextField.setText(theEvent.getNomevent());
		objectifeventTextField.setText(theEvent.getObjectifevent());
		dateeventTextField.setText(theEvent.getDateevent());
		descriptioneventTextField.setText(theEvent.getDescriptionevent());		
	}

	public EventDialog(EventSearchApp theEventSearchApp,
			EventDAO theEventDAO) {
		this(theEventSearchApp, theEventDAO, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public EventDialog() {
		setTitle("Add Event");
		setBounds(100, 100, 450, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(245, 245, 220));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));
		{
			JLabel lblName = new JLabel("Name of Event");
			contentPanel.add(lblName, "2, 2, right, default");
		}
		{
			nomeventTextField = new JTextField();
			contentPanel.add(nomeventTextField, "4, 2, fill, default");
			nomeventTextField.setColumns(10);
		}
		{
			JLabel lblObjectif = new JLabel("The Purpose");
			contentPanel.add(lblObjectif, "2, 4, right, default");
		}
		{
			objectifeventTextField = new JTextField();
			contentPanel.add(objectifeventTextField, "4, 4, fill, default");
			objectifeventTextField.setColumns(10);
		}
		{
			JLabel lblDate = new JLabel("Date (yyyy-mm-dd)");
			contentPanel.add(lblDate, "2, 6, right, default");
		}
		{
			dateeventTextField = new JTextField();
			contentPanel.add(dateeventTextField, "4, 6, fill, default");
			dateeventTextField.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description");
			contentPanel.add(lblDescription, "2, 8, right, default");
		}
		{
			descriptioneventTextField = new JTextField();
			contentPanel.add(descriptioneventTextField, "4, 8, fill, default");
			descriptioneventTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveEvent();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	protected void saveEvent() {

		// get the employee info from gui
		String nomevent = nomeventTextField.getText();
		String objectifevent = objectifeventTextField.getText();
		String dateevent = dateeventTextField.getText();
		String descriptionevent = descriptioneventTextField.getText();

		Event tempEvent = null;

		if (updateMode) {
			tempEvent = previousEvent;
			
			tempEvent.setNomevent(nomevent);
			tempEvent.setObjectifevent(objectifevent);
			tempEvent.setDateevent(dateevent);
			tempEvent.setDescriptionevent(descriptionevent);
			
		} else {
			tempEvent = new Event(nomevent, objectifevent, dateevent, descriptionevent);
		}

		try {
			// save to the database
			if (updateMode) {
				eventDAO.updateEvent(tempEvent);
			} else {
				eventDAO.addEvent(tempEvent);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			eventSearchApp.refreshEventView();

			// show success message
			JOptionPane.showMessageDialog(eventSearchApp,
					"Event saved succesfully.", "Event Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(eventSearchApp,
					"Error saving event: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
