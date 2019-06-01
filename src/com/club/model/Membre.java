package com.club.model;



public class Membre {

	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String password;
	private String niveau;
	private String phone;




	public Membre(String lastName, String firstName, String email,String password,String niveau, String phone

			) {

		this(0, lastName, firstName, email,password,niveau,phone);
	}
	
	public Membre(int id, String lastName, String firstName, String email,String password,String niveau, String phone

			) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.niveau = niveau;
		this.phone = phone;


	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return String
				.format("Member [id=%s, lastName=%s, firstName=%s, email=%s, password=%s,phone=%s]",
						id, lastName, firstName, email,password,niveau,phone);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
		
}
