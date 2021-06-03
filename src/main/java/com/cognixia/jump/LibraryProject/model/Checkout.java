package com.cognixia.jump.LibraryProject.model;

import java.sql.Date;

public class Checkout {
	private int id, patron_id;
	private String isbn;
	private Date checkedout, due_date, returned;
	
	
	
	public Checkout(int id, int patron_id, String isbn, Date checkedout, Date due_date, Date returned) {
		super();
		this.id = id;
		this.patron_id = patron_id;
		this.isbn = isbn;
		this.checkedout = checkedout;
		this.due_date = due_date;
		this.returned = returned;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatron_id() {
		return patron_id;
	}
	public void setPatron_id(int patron_id) {
		this.patron_id = patron_id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getCheckedout() {
		return checkedout;
	}
	public void setCheckedout(Date checkedout) {
		this.checkedout = checkedout;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public Date getReturned() {
		return returned;
	}
	public void setReturned(Date returned) {
		this.returned = returned;
	}
	
	
}
