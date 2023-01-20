package org.eurovending.pojo;

import java.io.InputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class SubCategory {

	private int id ;
	private String nameCategory;
	private String nameSubCategory;
	private MultipartFile photoSubCategory;
	private String photoStringSubCategory;
	private Date dateOfPublish;
	private String statut;
	

	public SubCategory() {
		super();
	}


	public SubCategory(int id, String nameCategory, String nameSubCategory, String photoStringSubCategory,
			Date dateOfPublish, String statut) {
		super();
		this.id = id;
		this.nameCategory = nameCategory;
		this.nameSubCategory = nameSubCategory;
		this.photoStringSubCategory = photoStringSubCategory;
		this.dateOfPublish = dateOfPublish;
		this.statut = statut;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	


	public String getNameSubCategory() {
		return nameSubCategory;
	}


	
	public String getNameCategory() {
		return nameCategory;
	}


	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}


	public void setNameSubCategory(String nameSubCategory) {
		this.nameSubCategory = nameSubCategory;
	}


	public MultipartFile getPhotoSubCategory() {
		return photoSubCategory;
	}


	public void setPhotoSubCategory(MultipartFile photoSubCategory) {
		this.photoSubCategory = photoSubCategory;
	}


	public String getPhotoStringSubCategory() {
		return photoStringSubCategory;
	}


	public void setPhotoStringSubCategory(String photoStringSubCategory) {
		this.photoStringSubCategory = photoStringSubCategory;
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
