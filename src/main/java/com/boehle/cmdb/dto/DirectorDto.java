package com.boehle.cmdb.dto;

public abstract class DirectorDto extends AbstractDto {
	protected long id;
	
	public DirectorDto() {
		super();
	}
	
	public DirectorDto(long id) {
		super();
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
