package com.club.model;



public class Activite {

	private int id;
	private String nomactivite;
	private String membreactivite;
	private String datedebut;
	private String datefin;
	private String description;




	public Activite(String nomactivite, String membreactivite, String datedebut,String datefin,String description) {

		this(0, nomactivite, membreactivite, datedebut,datefin,description);
	}
	
	public Activite(int id, String nomactivite, String membreactivite, String datedebut,String datefin,String description) {
		super();
		this.id = id;
		this.nomactivite = nomactivite;
		this.membreactivite=membreactivite;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.description = description;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomactivite() {
		return nomactivite;
	}

	public void setNomactivite(String nomactivite) {
		this.nomactivite = nomactivite;
	}

	@Override
	public String toString() {
		return String.format("Activite [id=%s, lastName=%s, firstName=%s, email=%s, description=%s]",
						id, nomactivite, membreactivite, datedebut,datefin,description);
	}


	public String getMembreactivite() {
		return membreactivite;
	}

	public void setMembreactivite(String membreactivite) {
		this.membreactivite = membreactivite;
	}

	public String getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}

	public String getDatefin() {
		return datefin;
	}

	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
		
}
