package com.boehle.cmdb.dto;


public class DirectorV2Dto extends DirectorDto {
	private String firstName;
	private String lastName;
	
	public DirectorV2Dto(long id) {
		super(id);
	}
	
	public DirectorV2Dto(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public DirectorV2Dto(long id, String firstName, String lastName) {
		this(firstName, lastName);
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}

