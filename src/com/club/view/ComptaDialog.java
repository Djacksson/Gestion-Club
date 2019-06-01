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
import com.club.dao.ComptaDAO;
import com.club.model.Compta;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.awt.Font;

public class ComptaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JTextField niveauTextField;
	private JTextField phoneTextField;

	private ComptaDAO comptaDAO;

	private ComptaSearchApp comptaSearchApp;

	private Compta previousCompta = null;
	private boolean updateMode = false;

	public ComptaDialog(ComptaSearchApp theComptaSearchApp,
			ComptaDAO theComptaDAO, Compta thePreviousCompta, boolean theUpdateMode) {
		this();
		comptaDAO = theComptaDAO;
		comptaSearchApp = theComptaSearchApp;

		previousCompta = thePreviousCompta;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Opertion");
			
			populateGui(previousCompta);
		}
	}

	private void populateGui(Compta theCompta) {

		firstNameTextField.setText(theCompta.getNomoperation());
		lastNameTextField.setText(theCompta.getObjectifoperation());
		emailTextField.setText(theCompta.getDateoperation());
		passwordTextField.setText(theCompta.getMembreoperation());
		niveauTextField.setText(theCompta.getSoldeoperation());

	}

	public ComptaDialog(ComptaSearchApp theComptaSearchApp,
			ComptaDAO theComptaDAO) {
		this(theComptaSearchApp, theComptaDAO, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public ComptaDialog() {
		setTitle("Add Operation");
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(245, 245, 220));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("Operation Name");
			lblFirstName.setBounds(49, 150, 120, 14);
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setBounds(179, 147, 500, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Objectif");
			lblLastName.setBounds(67, 193, 102, 14);
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(179, 190, 500, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Date (yyyy-mm-dd)");
			lblNewLabel.setBounds(40, 238, 129, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(179, 235, 500, 20);
			contentPanel.add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Member");
			lblNewLabel.setBounds(67, 284, 102, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			passwordTextField = new JTextField();
			passwordTextField.setBounds(179, 281, 500, 20);
			contentPanel.add(passwordTextField);
			passwordTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Amount");
			lblNewLabel.setBounds(67, 328, 102, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			niveauTextField = new JTextField();
			niveauTextField.setBounds(179, 325, 500, 20);
			contentPanel.add(niveauTextField);
			niveauTextField.setColumns(10);
		}
		{
			JLabel lblEnterTheInformations = new JLabel("Enter the informations of operation");
			lblEnterTheInformations.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblEnterTheInformations.setBounds(194, 67, 287, 27);
			contentPanel.add(lblEnterTheInformations);
		}
		
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
						saveCompta();
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


	protected void saveCompta() {

		// get the member info from gui
		String nomoperation = firstNameTextField.getText();
		String objectifoperation = lastNameTextField.getText();
		String dateoperation = emailTextField.getText();
		String membreoperation = passwordTextField.getText();
		String soldeoperation = niveauTextField.getText();

		Compta tempCompta = null;

		if (updateMode) {
			tempCompta = previousCompta;
			
			tempCompta.setNomoperation(nomoperation);
			tempCompta.setObjectifoperation(objectifoperation);
			tempCompta.setDateoperation(dateoperation);
			tempCompta.setMembreoperation(membreoperation);
			tempCompta.setSoldeoperation(soldeoperation);
			
		} else {
			tempCompta = new Compta(nomoperation, objectifoperation, dateoperation,membreoperation,soldeoperation);
		}

		try {
			// save to the database
			if (updateMode) {
				comptaDAO.updateCompta(tempCompta);
			} else {
				comptaDAO.addCompta(tempCompta);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			comptaSearchApp.refreshComptaView();

			// show success message
			JOptionPane.showMessageDialog(comptaSearchApp,
					"Operation saved succesfully.", "Operation Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(comptaSearchApp,
					"Error saving Operation: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
