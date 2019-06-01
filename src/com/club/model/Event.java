package com.club.model;

public class Event {

	private int id;
	private String nomevent;
	private String objectifevent;
	private String dateevent;
	private String descriptionevent;

	public Event(String nomevent, String objectifevent, String dateevent,
			String descriptionevent) {

		this(0, nomevent, objectifevent, dateevent, descriptionevent);
	}
	
	public Event(int id, String nomevent, String objectifevent, String dateevent,
			String descriptionevent) {
		super();
		this.id = id;
		this.setNomevent(nomevent);
		this.setObjectifevent(objectifevent);
		this.setDateevent(dateevent);
		this.setDescriptionevent(descriptionevent);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String
				.format("Event [id=%s, nom_evenement=%s, objectifevent=%s, dateevent=%s, descriptionevent=%s]",
						id, nomevent, objectifevent,dateevent, descriptionevent);
	}

	public String getNomevent() {
		return nomevent;
	}

	public void setNomevent(String nomevent) {
		this.nomevent = nomevent;
	}

	public String getObjectifevent() {
		return objectifevent;
	}

	public void setObjectifevent(String objectifevent) {
		this.objectifevent = objectifevent;
	}

	public String getDateevent() {
		return dateevent;
	}

	public void setDateevent(String dateevent) {
		this.dateevent = dateevent;
	}

	public String getDescriptionevent() {
		return descriptionevent;
	}

	public void setDescriptionevent(String descriptionevent) {
		this.descriptionevent = descriptionevent;
	}
	
		
}
