package com.club.model;



public class Compta {

	private int id;
	private String nomoperation;
	private String objectifoperation;
	private String dateoperation;
	private String membreoperation;
	private String soldeoperation;

	public Compta(String nomoperation, String objectifoperation, String dateoperation,String membreoperation,String soldeoperation

			) {

		this(0, nomoperation, objectifoperation, dateoperation,membreoperation,soldeoperation);
	}
	
	public Compta(int id, String nomoperation, String objectifoperation, String dateoperation,String membreoperation,String soldeoperation

			) {
		super();
		this.id = id;
		this.nomoperation = nomoperation;
		this.objectifoperation = objectifoperation;
		this.dateoperation = dateoperation;
		this.membreoperation = membreoperation;
		this.soldeoperation = soldeoperation;
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
				.format("Member [id=%s, nomoperation=%s,objectifoperation=%s, dateoperation=%s, membreoperation=%s, soldeoperation=%s]",
						id, nomoperation, objectifoperation,dateoperation,membreoperation,soldeoperation);
	}


	public String getNomoperation() {
		return nomoperation;
	}

	public void setNomoperation(String nomoperation) {
		this.nomoperation = nomoperation;
	}

	public String getObjectifoperation() {
		return objectifoperation;
	}

	public void setObjectifoperation(String objectifoperation) {
		this.objectifoperation = objectifoperation;
	}

	public String getDateoperation() {
		return dateoperation;
	}

	public void setDateoperation(String dateoperation) {
		this.dateoperation = dateoperation;
	}

	public String getMembreoperation() {
		return membreoperation;
	}

	public void setMembreoperation(String membreoperation) {
		this.membreoperation = membreoperation;
	}

	public String getSoldeoperation() {
		return soldeoperation;
	}

	public void setSoldeoperation(String soldeoperation) {
		this.soldeoperation = soldeoperation;
	}		
}
