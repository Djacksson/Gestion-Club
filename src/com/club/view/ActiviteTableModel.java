package com.club.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.club.model.Activite;

class ActiviteTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int PASSWORD_COL = 3;
	private static final int NIVEAU_COL = 4;


	private String[] columnNames = { "Task Name", "Task Member", "Start Date" ,"End Date","Description"};
	private List<Activite> activite;

	public ActiviteTableModel(List<Activite> theActivite) {
		activite = theActivite;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return activite.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Activite tempActivite = activite.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempActivite.getNomactivite();
		case FIRST_NAME_COL:
			return tempActivite.getMembreactivite();
		case EMAIL_COL:
			return tempActivite.getDatedebut();
		case PASSWORD_COL:
			return tempActivite.getDatefin();
		case NIVEAU_COL:
			return tempActivite.getDescription();
		case OBJECT_COL:
			return tempActivite;
		default:
			return tempActivite.getNomactivite();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
