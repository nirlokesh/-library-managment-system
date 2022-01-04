package com.hexad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Book{
	@Id
	@Column
	private Integer isbn;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Integer count;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getISBN() {
		return isbn;
	}
	public void setISBN(Integer isbn) {
		this.isbn = isbn;
	}
	
}
