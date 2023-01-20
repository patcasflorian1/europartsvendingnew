package org.eurovending.pojo;

import java.util.ArrayList;

public class Product {
	private int id;
	private String category;
	private String subCategory;
	private String codProdus;
	private String numeProdus;
	private String descriereProdus;
	private double pretProdus;
	private double pretProdusPromotional;
	private double cantitateProdus;
	private String  unitateMasura;
	private String  dataModificare;
	private String statutProdus; //PUBLICAT ;FORBIDDEN
    private double notaProdus ;  // 1...5
    PhotoProduct photoProduct ; 
    
	public Product() {
		// TODO Auto-generated constructor stub
	}

	

	



	public Product(int id, String category, String subCategory, String codProdus, String numeProdus,
			String descriereProdus, double pretProdus, double pretProdusPromotional, double cantitateProdus,
			String unitateMasura, String dataModificare, String statutProdus, double notaProdus,
			PhotoProduct photoProduct) {
		super();
		this.id = id;
		this.category = category;
		this.subCategory = subCategory;
		this.codProdus = codProdus;
		this.numeProdus = numeProdus;
		this.descriereProdus = descriereProdus;
		this.pretProdus = pretProdus;
		this.pretProdusPromotional = pretProdusPromotional;
		this.cantitateProdus = cantitateProdus;
		this.unitateMasura = unitateMasura;
		this.dataModificare = dataModificare;
		this.statutProdus = statutProdus;
		this.notaProdus = notaProdus;
		this.photoProduct = photoProduct;
	}







	public Product(int id, String category, String subCategory, String codProdus, String numeProdus,
			String descriereProdus, double pretProdus, double pretProdusPromotional, double cantitateProdus,
			String unitateMasura, String dataModificare, String statutProdus, double notaProdus) {
		super();
		this.id = id;
		this.category = category;
		this.subCategory = subCategory;
		this.codProdus = codProdus;
		this.numeProdus = numeProdus;
		this.descriereProdus = descriereProdus;
		this.pretProdus = pretProdus;
		this.pretProdusPromotional = pretProdusPromotional;
		this.cantitateProdus = cantitateProdus;
		this.unitateMasura = unitateMasura;
		this.dataModificare = dataModificare;
		this.statutProdus = statutProdus;
		this.notaProdus = notaProdus;
	}



	



	public Product(String category, String subCategory, String codProdus, String numeProdus, String descriereProdus,
			double pretProdus, double pretProdusPromotional, double cantitateProdus, String unitateMasura,
			String dataModificare, String statutProdus, double notaProdus) {
		super();
		this.category = category;
		this.subCategory = subCategory;
		this.codProdus = codProdus;
		this.numeProdus = numeProdus;
		this.descriereProdus = descriereProdus;
		this.pretProdus = pretProdus;
		this.pretProdusPromotional = pretProdusPromotional;
		this.cantitateProdus = cantitateProdus;
		this.unitateMasura = unitateMasura;
		this.dataModificare = dataModificare;
		this.statutProdus = statutProdus;
		this.notaProdus = notaProdus;
	}


	

	

	public PhotoProduct getPhotoProduct() {
		return photoProduct;
	}







	public void setPhotoProduct(PhotoProduct photoProduct) {
		this.photoProduct = photoProduct;
	}







	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getSubCategory() {
		return subCategory;
	}



	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}



	public String getCodProdus() {
		return codProdus;
	}



	public void setCodProdus(String codProdus) {
		this.codProdus = codProdus;
	}



	public String getNumeProdus() {
		return numeProdus;
	}



	public void setNumeProdus(String numeProdus) {
		this.numeProdus = numeProdus;
	}



	public String getDescriereProdus() {
		return descriereProdus;
	}



	public void setDescriereProdus(String descriereProdus) {
		this.descriereProdus = descriereProdus;
	}



	public double getPretProdus() {
		return pretProdus;
	}



	public void setPretProdus(double pretProdus) {
		this.pretProdus = pretProdus;
	}



	public double getPretProdusPromotional() {
		return pretProdusPromotional;
	}



	public void setPretProdusPromotional(double pretProdusPromotional) {
		this.pretProdusPromotional = pretProdusPromotional;
	}



	public double getCantitateProdus() {
		return cantitateProdus;
	}



	public void setCantitateProdus(double cantitateProdus) {
		this.cantitateProdus = cantitateProdus;
	}



	public String getUnitateMasura() {
		return unitateMasura;
	}



	public void setUnitateMasura(String unitateMasura) {
		this.unitateMasura = unitateMasura;
	}



	public String getDataModificare() {
		return dataModificare;
	}



	public void setDataModificare(String dataModificare) {
		this.dataModificare = dataModificare;
	}



	public String getStatutProdus() {
		return statutProdus;
	}



	public void setStatutProdus(String statutProdus) {
		this.statutProdus = statutProdus;
	}



	public double getNotaProdus() {
		return notaProdus;
	}



	public void setNotaProdus(double notaProdus) {
		this.notaProdus = notaProdus;
	}
	
	
	

}
