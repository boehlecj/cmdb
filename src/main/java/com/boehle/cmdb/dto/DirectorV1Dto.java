package com.boehle.cmdb.dto;


public class DirectorV1Dto extends DirectorDto {
	private String name;
	
	public DirectorV1Dto(long id) {
		super(id);
	}
	
	public DirectorV1Dto(String name) {
		this.name = name;
	}
	
	public DirectorV1Dto(long id, String name) {
		this(name);
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
