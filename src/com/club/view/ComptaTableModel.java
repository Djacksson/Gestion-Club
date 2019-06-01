package com.club.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.club.model.Compta;

class ComptaTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int PASSWORD_COL = 3;
	private static final int NIVEAU_COL = 4;

	private String[] columnNames = { "Name Operation ", "Objectif ", "Date " ,"Member","Solde"};
	private List<Compta> compta;

	public ComptaTableModel(List<Compta> theCompta) {
		compta = theCompta;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return compta.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Compta tempCompta = compta.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempCompta.getNomoperation();
		case FIRST_NAME_COL:
			return tempCompta.getObjectifoperation();
		case EMAIL_COL:
			return tempCompta.getDateoperation();
		case PASSWORD_COL:
			return tempCompta.getMembreoperation();
		case NIVEAU_COL:
			return tempCompta.getSoldeoperation();
		case OBJECT_COL:
			return tempCompta;
		default:
			return tempCompta.getNomoperation();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
