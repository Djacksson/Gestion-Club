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
import com.club.dao.MembreDAO;
import com.club.model.Membre;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.awt.Font;

public class MembreDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JTextField niveauTextField;
	private JTextField phoneTextField;

	private MembreDAO membreDAO;

	private MembreSearchApp membreSearchApp;

	private Membre previousMembre = null;
	private boolean updateMode = false;

	public MembreDialog(MembreSearchApp theMembreSearchApp,
			MembreDAO theMembreDAO, Membre thePreviousMembre, boolean theUpdateMode) {
		this();
		membreDAO = theMembreDAO;
		membreSearchApp = theMembreSearchApp;

		previousMembre = thePreviousMembre;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Member");
			
			populateGui(previousMembre);
		}
	}

	private void populateGui(Membre theMembre) {

		firstNameTextField.setText(theMembre.getFirstName());
		lastNameTextField.setText(theMembre.getLastName());
		emailTextField.setText(theMembre.getEmail());
		passwordTextField.setText(theMembre.getPassword());
		niveauTextField.setText(theMembre.getNiveau());
		phoneTextField.setText(theMembre.getPhone());


	}

	public MembreDialog(MembreSearchApp theMembreSearchApp,
			MembreDAO theMembreDAO) {
		this(theMembreSearchApp, theMembreDAO, null, false);
	}

	/**
	 * Create the dialog.
	 */
	public MembreDialog() {
		setTitle("Add Member");
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(245, 245, 220));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("First Name");
			lblFirstName.setBounds(21, 102, 148, 14);
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			firstNameTextField.setBounds(179, 99, 500, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			lblLastName.setBounds(22, 144, 147, 14);
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(179, 141, 500, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Email");
			lblNewLabel.setBounds(48, 186, 121, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(179, 183, 500, 20);
			contentPanel.add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Password");
			lblNewLabel.setBounds(26, 230, 143, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			passwordTextField = new JTextField();
			passwordTextField.setBounds(179, 227, 500, 20);
			contentPanel.add(passwordTextField);
			passwordTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Niveau");
			lblNewLabel.setBounds(39, 273, 130, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			niveauTextField = new JTextField();
			niveauTextField.setBounds(179, 270, 500, 20);
			contentPanel.add(niveauTextField);
			niveauTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Phone");
			lblNewLabel.setBounds(42, 315, 127, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			phoneTextField = new JTextField();
			phoneTextField.setBounds(179, 312, 500, 20);
			contentPanel.add(phoneTextField);
			phoneTextField.setColumns(10);
		}
		{
			JLabel lblEnterTheInformation = new JLabel("Enter the information of member");
			lblEnterTheInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblEnterTheInformation.setBounds(210, 42, 273, 20);
			contentPanel.add(lblEnterTheInformation);
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
						saveMembre();
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


	protected void saveMembre() {

		// get the member info from gui
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String email = emailTextField.getText();
		String password = passwordTextField.getText();
		String niveau = niveauTextField.getText();
		String phone = phoneTextField.getText();



		Membre tempMembre = null;

		if (updateMode) {
			tempMembre = previousMembre;
			
			tempMembre.setLastName(lastName);
			tempMembre.setFirstName(firstName);
			tempMembre.setEmail(email);
			tempMembre.setPassword(password);
			tempMembre.setNiveau(niveau);
			tempMembre.setNiveau(phone);

			
		} else {
			tempMembre = new Membre(lastName, firstName, email,password,niveau,phone);
		}

		try {
			// save to the database
			if (updateMode) {
				membreDAO.updateMembre(tempMembre);
			} else {
				membreDAO.addMembre(tempMembre);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			membreSearchApp.refreshMembreView();

			// show success message
			JOptionPane.showMessageDialog(membreSearchApp,
					"Member saved succesfully.", "Member Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(membreSearchApp,
					"Error saving Member: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
