package org.eurovending.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.eurovending.dao.BannerDAO;
import org.eurovending.dao.CategoryDAO;
import org.eurovending.dao.PhotoProductDAO;
import org.eurovending.dao.ProductDAO;
import org.eurovending.dao.SubCategoryDAO;
import org.eurovending.pojo.Banner;
import org.eurovending.pojo.Category;
import org.eurovending.pojo.PhotoProduct;
import org.eurovending.pojo.Product;
import org.eurovending.pojo.SubCategory;
import org.eurovending.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig(maxFileSize = 16177215) 
public class HomeController {
	//listare  categorie
	@RequestMapping(value="home1.htm")
	public ModelAndView listareCategoryHome(@ModelAttribute("myUser") User myUser ,Model model,BindingResult result) throws SQLException, ServletException, IOException {
		String userName = System.getProperty("user.name");
		
			ArrayList<Category>listCategorie = new ArrayList<Category>();
			ArrayList<Category>listaCategorii = new ArrayList<Category>();
			ArrayList<SubCategory>listSubCategorie = new ArrayList<SubCategory>();
			ArrayList<Banner>listaBannerFirst=new ArrayList<Banner>();
			ArrayList<Banner>listaBanner=new ArrayList<Banner>();
			ArrayList<Product> productList = new ArrayList<Product>();
			ArrayList<PhotoProduct> photoProductList = new ArrayList<PhotoProduct>();
			Banner banFirst = new Banner();
			Category categorie = null;
			CategoryDAO cdao = new CategoryDAO();
			SubCategoryDAO scDao = new SubCategoryDAO();
			BannerDAO  bannerdao = new BannerDAO();
			ProductDAO productDao = new ProductDAO();
			PhotoProductDAO photoDao = new PhotoProductDAO();
			photoProductList = photoDao.getAllPhotoProduct();
		int nr = 1;
		int nrBanner = 0;
		listaCategorii = cdao.getListaCategory();
		listSubCategorie = scDao.getAllSubCategory();
		listaBannerFirst=bannerdao.getListaBanner();
		if(listaBannerFirst.size()>0) {
			if(listaBannerFirst.get(0).getStatut().equals("PUBLICAT")){
		banFirst = listaBannerFirst.get(0);
			}
		}
		for(Category numeCategorie :listaCategorii) {
		categorie=cdao.getCategorieParinte(numeCategorie.getNameCategory());
		if(categorie.getStatut().equals("PUBLICAT")) {
			listCategorie.add(categorie);
		}
		nr++;
		}
		 for(Banner ban:listaBannerFirst) {
			
			 if(ban.getStatut().equals("PUBLICAT")) {
			nrBanner++; 
			listaBanner.add(ban);
			 }
		 }
		 productList = productDao.getProductList();
	ArrayList<Product> newProductList = new ArrayList<Product>();
	ArrayList<Product> prodList = new ArrayList<Product>();
	PhotoProduct newPhoto = new PhotoProduct();
	int countProd = 0;
	for(Product prd : productList) {
		newPhoto = photoDao.getPhotoProduct(prd.getId()); 
		prd.setPhotoProduct(newPhoto);
		if(countProd<5) {
		newProductList.add(prd);
		}
		countProd ++;
		prodList.add(prd);
	}
		model.addAttribute("nr", nr);
		model.addAttribute("userName", userName);
		model.addAttribute("listCategorie", listCategorie);
		//model.addAttribute("listCategorie", listCategorie);
		model.addAttribute("listaBanner", listaBanner);
		model.addAttribute("banFirst", banFirst);
		model.addAttribute("nrBanner", nrBanner);
		model.addAttribute("productList", newProductList);
		model.addAttribute("photoProductList",photoProductList);
		if(listaBannerFirst.size()>0) {
			model.addAttribute("bannerSize", listaBanner.size());
		}
		//model.addAttribute("subcategorii", listaSubCategorii);
		//return new ModelAndView("WEB-INF/Home/home.jsp","model",model);
		return new ModelAndView("index2.jsp","model",model);
		
	}	
	
	//listare  banner/produse
		@RequestMapping(value="home.htm")
		public ModelAndView listHome(@ModelAttribute("myUser") User myUser ,Model model,BindingResult result) throws SQLException, ServletException, IOException {
			
			ArrayList<Category>listCategory = new ArrayList<Category>();
			ArrayList<SubCategory>listSubCategorie = new ArrayList<SubCategory>();   
				ArrayList<Banner>listaBanner=new ArrayList<Banner>();
				ArrayList<Banner>listBanner=new ArrayList<Banner>();
				ArrayList<Product> productList = new ArrayList<Product>();
				ArrayList<PhotoProduct> photoProductList = new ArrayList<PhotoProduct>();
			
				CategoryDAO cdao = new CategoryDAO();
				SubCategoryDAO scDao = new SubCategoryDAO();
				BannerDAO  bannerdao = new BannerDAO();
				ProductDAO productDao = new ProductDAO();
				PhotoProductDAO photoDao = new PhotoProductDAO();
				listCategory = cdao.getPublicListCategory();
				photoProductList = photoDao.getAllPhotoProduct();
				listaBanner = bannerdao.getListaBanner(); 
			    productList = productDao.getTopProductList();
			 for(Banner ban:listaBanner) {
				
				 if(ban.getStatut().equals("PUBLICAT")) {
				
				listBanner.add(ban);
				 }
			 }
		ArrayList<Product> newProductList = new ArrayList<Product>();
		ArrayList<Product> prodList = new ArrayList<Product>();
		PhotoProduct newPhoto = new PhotoProduct();
		int countProd = 0;
		for(Product prd : productList) {
			newPhoto = photoDao.getPhotoProduct(prd.getId()); 
			prd.setPhotoProduct(newPhoto);
			
			newProductList.add(prd);
			countProd++;
		}
		model.addAttribute("listCategory", listCategory);
		    model.addAttribute("countProd", countProd);
			model.addAttribute("listaBanner", listBanner);
			model.addAttribute("productList", newProductList);
			
			return new ModelAndView("index.jsp","model",model);
			
		}	
		//listare  produse&Category
				@RequestMapping(value="homeprd.htm")
				public ModelAndView listCategoryProducts(@ModelAttribute("myUser") User myUser ,Model model,BindingResult result) throws SQLException, ServletException, IOException {
		                   
		                   ArrayList<Category>listaCategorii = new ArrayList<Category>();
		       			ArrayList<SubCategory>listSubCategorie = new ArrayList<SubCategory>();
						ArrayList<Product> productList = new ArrayList<Product>();
						ArrayList<PhotoProduct> photoProductList = new ArrayList<PhotoProduct>();
					
						BannerDAO  bannerdao = new BannerDAO();
						ProductDAO productDao = new ProductDAO();
						PhotoProductDAO photoDao = new PhotoProductDAO();
						photoProductList = photoDao.getAllPhotoProduct();
						
					    productList = productDao.getProductList();
					
					
				ArrayList<Product> newProductList = new ArrayList<Product>();
				ArrayList<Product> prodList = new ArrayList<Product>();
				PhotoProduct newPhoto = new PhotoProduct();
				
				for(Product prd : productList) {
					newPhoto = photoDao.getPhotoProduct(prd.getId()); 
					prd.setPhotoProduct(newPhoto);
					
					newProductList.add(prd);
				
				}
				    
					model.addAttribute("productList", newProductList);
					
					return new ModelAndView("index.jsp","model",model);
					
				}	

	
	//viewSingleProduct
	@RequestMapping(value="viewSingleProduct.htm")
	public ModelAndView viewSingleProduct(@ModelAttribute("idProduct") int id , 
			Model model,BindingResult result) throws SQLException, ServletException, IOException {
			ProductDAO pdao = new ProductDAO();
			PhotoProductDAO phdao = new PhotoProductDAO();
			Product prod = new Product();
			PhotoProduct photoProduct = new PhotoProduct();
	
				prod = pdao.getProducts(id);
				photoProduct = phdao.getPhotoProduct(prod.getId());
				
				 ArrayList<String> photoAlbum = new ArrayList<String>();
		         ArrayList<String> photoAlbum1 = new ArrayList<String>();
		       photoAlbum.add(photoProduct.getBase64Image1());
		       photoAlbum.add(photoProduct.getBase64Image2());
		       photoAlbum.add(photoProduct.getBase64Image3());
		       photoAlbum.add(photoProduct.getBase64Image4());
		       photoAlbum.add(photoProduct.getBase64Image5());
		       for(int i=0;i<photoAlbum.size();i++) {
		    	   if((!photoAlbum.get(i).isEmpty())&&(!photoAlbum.get(i).isBlank())) {
		    		   photoAlbum1.add(photoAlbum.get(i)); 
		    	   }
		       }
				 model.addAttribute("product",prod);
				 model.addAttribute("photoProduct",photoProduct);
				 model.addAttribute("photoAlbum",photoAlbum1);
				return new ModelAndView("WEB-INF/Home/singleproduct.jsp","model",model);
			    }
			
	     }
	


