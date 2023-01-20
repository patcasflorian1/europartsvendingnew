package org.eurovending.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Category {

	private int id;
	private String nameCategory;
	private Date dateOfPublish;
	private String statut;
	
	
	
	public Category() {
		// TODO Auto-generated constructor stub
	}



	public Category(String nameCategory, Date dateOfPublish, String statut) {
		super();
		this.nameCategory = nameCategory;
		this.dateOfPublish = dateOfPublish;
		this.statut = statut;
	}



	public Category(int id, String nameCategory, Date dateOfPublish, String statut) {
		super();
		this.id = id;
		this.nameCategory = nameCategory;
		this.dateOfPublish = dateOfPublish;
		this.statut = statut;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNameCategory() {
		return nameCategory;
	}



	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}



	public Date getDateOfPublish() {
		return dateOfPublish;
	}



	public void setDateOfPublish(Date dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}


	

	

	
}
