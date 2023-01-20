package org.eurovending.pojo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Banner {
	
	private int id;
	private String bannerName;
	private String link;
	private Date dataAdaugare;
	private String statut;
	private MultipartFile photo;
	private String photoString;
	
	public Banner() {
		super();
	}


	public Banner(String bannerName, Date dataAdaugare, String statut, MultipartFile photo) {
		super();
		this.bannerName = bannerName;
		this.dataAdaugare = dataAdaugare;
		this.statut = statut;
		this.photo = photo;
	}


	public Banner(String bannerName, Date dataAdaugare, MultipartFile photo) {
		super();
		this.bannerName = bannerName;
		this.dataAdaugare = dataAdaugare;
		this.photo = photo;
	}


	public Banner(int id, String bannerName, Date dataAdaugare, MultipartFile photo) {
		super();
		this.id = id;
		this.bannerName = bannerName;
		this.dataAdaugare = dataAdaugare;
		this.photo = photo;
	}


	public Banner(String bannerName, String link, Date dataAdaugare, String statut, MultipartFile photo) {
		super();
		this.bannerName = bannerName;
		this.link = link;
		this.dataAdaugare = dataAdaugare;
		this.statut = statut;
		this.photo = photo;
	}


	public Banner(int id, String bannerName, String link, Date dataAdaugare, String statut, MultipartFile photo) {
		super();
		this.id = id;
		this.bannerName = bannerName;
		this.link = link;
		this.dataAdaugare = dataAdaugare;
		this.statut = statut;
		this.photo = photo;
	}


	

	

	public Banner(String bannerName, String link, Date dataAdaugare, String statut, String photoString) {
		super();
		this.bannerName = bannerName;
		this.link = link;
		this.dataAdaugare = dataAdaugare;
		this.statut = statut;
		this.photoString = photoString;
	}


	public Banner(int id, String bannerName, String link, Date dataAdaugare, String statut, String photoString) {
		super();
		this.id = id;
		this.bannerName = bannerName;
		this.link = link;
		this.dataAdaugare = dataAdaugare;
		this.statut = statut;
		this.photoString = photoString;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBannerName() {
		return bannerName;
	}


	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public Date getDataAdaugare() {
		return dataAdaugare;
	}


	public void setDataAdaugare(Date dataAdaugare) {
		this.dataAdaugare = dataAdaugare;
	}


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}



	public String getPhotoString() {
		return photoString;
	}


	public void setPhotoString(String photoString) {
		this.photoString = photoString;
	}


}
