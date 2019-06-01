package com.club.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.club.model.Event;

class EventTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int NAME_COL = 0;
	private static final int PURPOSE_COL = 1;
	private static final int DATE_COL = 2;
	private static final int DESCRIPTION_COL = 3;

	private String[] columnNames = { "Name of Event", "The Purpose", "Date",
			"Description" };
	private List<Event> event;

	public EventTableModel(List<Event> theEvent) {
		event = theEvent;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return event.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Event tempEvent = event.get(row);

		switch (col) {
		case NAME_COL:
			return tempEvent.getNomevent();
		case PURPOSE_COL:
			return tempEvent.getObjectifevent();
		case DATE_COL:
			return tempEvent.getDateevent();
		case DESCRIPTION_COL:
			return tempEvent.getDescriptionevent();
		case OBJECT_COL:
			return tempEvent;
		default:
			return tempEvent.getNomevent();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
