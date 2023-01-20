package org.eurovending.pojo;

import org.springframework.web.multipart.MultipartFile;

public class PhotoProduct {

	private int id;
	private int idProdus;
	private String videoLink;
	
	private String base64Image1;
	private String base64Image2;
	private String base64Image3;
	private String base64Image4;
	private String base64Image5;
	
	private MultipartFile photo1;
	private MultipartFile photo2;
	private MultipartFile photo3;
	private MultipartFile photo4;
	private MultipartFile photo5;

	public PhotoProduct() {
		// TODO Auto-generated constructor stub
	}

	public PhotoProduct(int id, int idProdus, String videoLink, MultipartFile photo1, MultipartFile photo2,
			MultipartFile photo3, MultipartFile photo4, MultipartFile photo5) {
		super();
		this.id = id;
		this.idProdus = idProdus;
		this.videoLink = videoLink;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.photo4 = photo4;
		this.photo5 = photo5;
	}



	public PhotoProduct(int id, int idProdus, String videoLink, String base64Image1, String base64Image2,
			String base64Image3, String base64Image4, String base64Image5) {
		super();
		this.id = id;
		this.idProdus = idProdus;
		this.videoLink = videoLink;
		this.base64Image1 = base64Image1;
		this.base64Image2 = base64Image2;
		this.base64Image3 = base64Image3;
		this.base64Image4 = base64Image4;
		this.base64Image5 = base64Image5;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getIdProdus() {
		return idProdus;
	}



	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}



	public String getBase64Image1() {
		return base64Image1;
	}

	public void setBase64Image1(String base64Image1) {
		this.base64Image1 = base64Image1;
	}

	public String getBase64Image2() {
		return base64Image2;
	}

	public void setBase64Image2(String base64Image2) {
		this.base64Image2 = base64Image2;
	}

	public String getBase64Image3() {
		return base64Image3;
	}

	public void setBase64Image3(String base64Image3) {
		this.base64Image3 = base64Image3;
	}

	public String getBase64Image4() {
		return base64Image4;
	}

	public void setBase64Image4(String base64Image4) {
		this.base64Image4 = base64Image4;
	}

	public String getBase64Image5() {
		return base64Image5;
	}

	public void setBase64Image5(String base64Image5) {
		this.base64Image5 = base64Image5;
	}
	
	

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public MultipartFile getPhoto1() {
		return photo1;
	}

	public void setPhoto1(MultipartFile photo1) {
		this.photo1 = photo1;
	}

	public MultipartFile getPhoto2() {
		return photo2;
	}

	public void setPhoto2(MultipartFile photo2) {
		this.photo2 = photo2;
	}

	public MultipartFile getPhoto3() {
		return photo3;
	}

	public void setPhoto3(MultipartFile photo3) {
		this.photo3 = photo3;
	}

	public MultipartFile getPhoto4() {
		return photo4;
	}

	public void setPhoto4(MultipartFile photo4) {
		this.photo4 = photo4;
	}

	public MultipartFile getPhoto5() {
		return photo5;
	}

	public void setPhoto5(MultipartFile photo5) {
		this.photo5 = photo5;
	}
	
	



}
