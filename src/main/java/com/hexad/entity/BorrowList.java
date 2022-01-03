package com.hexad.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BorrowList")
public class BorrowList {

	@Id
	@Column
	private Integer id;
	
	@Column
	private String userName;
	
	@Column
	private Integer ISBN;
	
	@Column(name ="booking_date")
	private LocalDate bookingDate;
	
	public BorrowList(String userName,Integer ISBN){
		this.userName = userName;
		this.ISBN = ISBN;
		this.bookingDate = LocalDate.now();
	}

	public BorrowList() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getISBN() {
		return ISBN;
	}

	public void setISBN(Integer iSBN) {
		ISBN = iSBN;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
}
