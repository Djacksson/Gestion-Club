package com.club.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.club.model.Membre;

class MembreTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int PASSWORD_COL = 3;
	private static final int NIVEAU_COL = 4;
	private static final int PHONE_COL = 5;


	private String[] columnNames = { "Last Name", "First Name", "Email" ,"Password","Niveau","Phone"};
	private List<Membre> membre;

	public MembreTableModel(List<Membre> theMembre) {
		membre = theMembre;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return membre.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Membre tempMembre = membre.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempMembre.getLastName();
		case FIRST_NAME_COL:
			return tempMembre.getFirstName();
		case EMAIL_COL:
			return tempMembre.getEmail();
		case PASSWORD_COL:
			return tempMembre.getPassword();
		case NIVEAU_COL:
			return tempMembre.getNiveau();
		case PHONE_COL:
			return tempMembre.getPhone();
		case OBJECT_COL:
			return tempMembre;
		default:
			return tempMembre.getLastName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
