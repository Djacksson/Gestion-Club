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
import com.club.dao.ActiviteDAO;
import com.club.model.Activite;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.awt.Font;

public class ActiviteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JTextField niveauTextField;

	private ActiviteDAO activiteDAO;

	private ActiviteSearchApp activiteSearchApp;

	private Activite previousActivite = null;
	private boolean updateMode = false;

	public ActiviteDialog(ActiviteSearchApp theActiviteSearchApp,
			ActiviteDAO theActiviteDAO, Activite thePreviousActivite, boolean theUpdateMode) {
		this();
		activiteDAO = theActiviteDAO;
		activiteSearchApp = theActiviteSearchApp;

		previousActivite = thePreviousActivite;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Task");
			
			populateGui(previousActivite);
		}
	}

	private void populateGui(Activite theActivite) {

		firstNameTextField.setText(theActivite.getMembreactivite());
		lastNameTextField.setText(theActivite.getNomactivite());
		emailTextField.setText(theActivite.getDatedebut());
		passwordTextField.setText(theActivite.getDatefin());
		niveauTextField.setText(theActivite.getDescription());

	}

	public ActiviteDialog(ActiviteSearchApp theActiviteSearchApp,
			ActiviteDAO theActiviteDAO) {
		this(theActiviteSearchApp, theActiviteDAO, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public ActiviteDialog() {
		setTitle("Add Task");
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(245, 245, 220));
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("Task Name");
			lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFirstName.setBounds(80, 107, 109, 17);
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
			firstNameTextField.setBounds(199, 104, 480, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Who have to do it");
			lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblLastName.setBounds(46, 148, 143, 17);
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(199, 145, 480, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Start Date (yyyy-mm-dd)");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(11, 182, 178, 20);
			contentPanel.add(lblNewLabel);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(199, 182, 480, 20);
			contentPanel.add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("End Date (yyyy-mm-dd)");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(17, 227, 172, 17);
			contentPanel.add(lblNewLabel);
		}
		{
			passwordTextField = new JTextField();
			passwordTextField.setBounds(199, 224, 480, 20);
			contentPanel.add(passwordTextField);
			passwordTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Description");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(80, 263, 109, 17);
			contentPanel.add(lblNewLabel);
		}
		{
			niveauTextField = new JTextField();
			niveauTextField.setBounds(199, 260, 480, 20);
			contentPanel.add(niveauTextField);
			niveauTextField.setColumns(10);
		}
		
		JLabel lblEnterTheInformations = new JLabel("Enter the informations of tasks");
		lblEnterTheInformations.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblEnterTheInformations.setBounds(230, 43, 243, 34);
		contentPanel.add(lblEnterTheInformations);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setBackground(new Color(0, 0, 128));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveActivite();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(0, 0, 128));
				cancelButton.setForeground(new Color(255, 255, 255));
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


	protected void saveActivite() {

		// get the member info from gui
		String membreactivite = firstNameTextField.getText();
		String nomactivite = lastNameTextField.getText();
		String datedebut = emailTextField.getText();
		String datefin = passwordTextField.getText();
		String description = niveauTextField.getText();



		Activite tempActivite = null;

		if (updateMode) {
			tempActivite = previousActivite;
			
			tempActivite.setNomactivite(nomactivite);
			tempActivite.setMembreactivite(membreactivite);
			tempActivite.setDatedebut(datedebut);
			tempActivite.setDatedebut(datefin);
			tempActivite.setDescription(description);
			
		} else {
			tempActivite = new Activite(nomactivite, membreactivite, datedebut,datefin,description);
		}

		try {
			// save to the database
			if (updateMode) {
				activiteDAO.updateActivite(tempActivite);
			} else {
				activiteDAO.addActivite(tempActivite);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			activiteSearchApp.refreshActiviteView();

			// show success message
			JOptionPane.showMessageDialog(activiteSearchApp,
					"Task saved succesfully.", "Task Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(activiteSearchApp,
					"Error saving Task: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
