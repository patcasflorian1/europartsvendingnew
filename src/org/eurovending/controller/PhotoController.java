package org.eurovending.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.eurovending.dao.PhotoProductDAO;
import org.eurovending.pojo.PhotoProduct;
import org.eurovending.pojo.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig(maxFileSize = 16177215) 
public class PhotoController {
   
	public static ArrayList<MultipartFile > multiPhoto = new ArrayList<MultipartFile>();
	
	//Login for admin&user
			@RequestMapping(value="addphoto.htm")
			public ModelAndView addPhoto(@ModelAttribute("idProdus") PhotoProduct newPhotoProduct,String type,Model model,BindingResult result) throws SQLException, IOException {
			PhotoProduct photoString = new PhotoProduct();
			      PhotoProductDAO phdao = new PhotoProductDAO();
			      if((AdminController.isLoginSuperAdmin == true)) {
			            phdao.insertPhotoProduct(newPhotoProduct);
			            newPhotoProduct = phdao.getPhotoProduct(newPhotoProduct.getIdProdus());
						model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
						model.addAttribute("photoProduct", newPhotoProduct);   
				return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);
			      }
					else {
						AdminController.isLoginSuperAdmin = false;
					
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
				}	
	
			//Login for admin&user
			@RequestMapping(value="getPhoto.htm")
			public ModelAndView getPhoto(@ModelAttribute("idProduct") int idProduct,String type,Model model,BindingResult result) throws SQLException, IOException {
		
			       PhotoProduct photoString = new PhotoProduct();
			      PhotoProductDAO phdao = new PhotoProductDAO();
			      if((AdminController.isLoginSuperAdmin == true)) {
			    	  photoString =  phdao.getPhotoProduct(idProduct);
			    	 
						model.addAttribute("idProduct", idProduct);
						model.addAttribute("photoProduct", photoString);   
				return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);
			      }
					else {
						AdminController.isLoginSuperAdmin = false;
					
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
				}	
			
			//add and Update photo1
			@RequestMapping(value="addphotoproduct1.htm")
			public ModelAndView addPhotoProduct1(@ModelAttribute("idProduct") PhotoProduct newPhotoProduct,
					 Model model,BindingResult result ) throws SQLException, ServletException, IOException {
				PhotoProduct photoProduct = new PhotoProduct();
				PhotoProductDAO phdao = new PhotoProductDAO();
				ArrayList<PhotoProduct> allPhotoProduct = phdao.getAllPhotoProduct();
				if((AdminController.isLoginSuperAdmin == true)) {
					for(PhotoProduct p:allPhotoProduct) {
						if(p.getIdProdus()==(newPhotoProduct.getIdProdus())) {
							
								phdao.updatePhotoProduct1(newPhotoProduct);
						   } 
						}
								photoProduct = phdao.getPhotoProduct(newPhotoProduct.getId());
								if(photoProduct.getBase64Image1().isEmpty()) {
									photoProduct.setBase64Image1(null);
								}
								
							model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
							model.addAttribute("photoProduct", photoProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);	
						}
					
				else {
					AdminController.isLoginSuperAdmin = false;
				
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
			}	
			
			//add and Update photo2
			@RequestMapping(value="addphotoproduct2.htm")
			public ModelAndView addPhotoProduct2(@ModelAttribute("idProduct") PhotoProduct newPhotoProduct,
					 Model model,BindingResult result ) throws SQLException, ServletException, IOException {
				PhotoProduct photoProduct = new PhotoProduct();
				PhotoProductDAO phdao = new PhotoProductDAO();
				ArrayList<PhotoProduct> allPhotoProduct = phdao.getAllPhotoProduct();
				if((AdminController.isLoginSuperAdmin == true)) {
					for(PhotoProduct p:allPhotoProduct) {
						if(p.getIdProdus()==newPhotoProduct.getIdProdus()) {
							
								phdao.updatePhotoProduct2(newPhotoProduct);
						   } 
						}
								photoProduct = phdao.getPhotoProduct(newPhotoProduct.getId());
								if(photoProduct.getBase64Image2().isEmpty()) {
									photoProduct.setBase64Image2(null);
								}
								
							model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
							model.addAttribute("photoProduct", photoProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);	
						}
					
				else {
					AdminController.isLoginSuperAdmin = false;
				
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
			}	
			//add and Update photo3
			@RequestMapping(value="addphotoproduct3.htm")
			public ModelAndView addPhotoProduct3(@ModelAttribute("idProduct") PhotoProduct newPhotoProduct,
					 Model model,BindingResult result ) throws SQLException, ServletException, IOException {
			
				PhotoProduct photoProduct = new PhotoProduct();
				PhotoProductDAO phdao = new PhotoProductDAO();
				ArrayList<PhotoProduct> allPhotoProduct = phdao.getAllPhotoProduct();
				if((AdminController.isLoginSuperAdmin == true)) {
					for(PhotoProduct p:allPhotoProduct) {
						if(p.getIdProdus()==newPhotoProduct.getIdProdus()) {
							
								phdao.updatePhotoProduct3(newPhotoProduct);
						   } 
						}
								photoProduct = phdao.getPhotoProduct(newPhotoProduct.getId());
								if(photoProduct.getBase64Image3().isEmpty()) {
									photoProduct.setBase64Image3(null);
								}
								
							model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
							model.addAttribute("photoProduct", photoProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);	
						}
					
				else {
					AdminController.isLoginSuperAdmin = false;
				
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
			}	
			
			//add and Update photo4
			@RequestMapping(value="addphotoproduct4.htm")
			public ModelAndView addPhotoProduct4(@ModelAttribute("idProduct") PhotoProduct newPhotoProduct,
					 Model model,BindingResult result ) throws SQLException, ServletException, IOException {
		
				PhotoProduct photoProduct = new PhotoProduct();
				PhotoProductDAO phdao = new PhotoProductDAO();
				ArrayList<PhotoProduct> allPhotoProduct = phdao.getAllPhotoProduct();
				if((AdminController.isLoginSuperAdmin == true)) {
					for(PhotoProduct p:allPhotoProduct) {
						if(p.getIdProdus()==newPhotoProduct.getIdProdus()) {
							
								phdao.updatePhotoProduct4(newPhotoProduct);
						   } 
						}
								photoProduct = phdao.getPhotoProduct(newPhotoProduct.getId());
								if(photoProduct.getBase64Image4().isEmpty()) {
									photoProduct.setBase64Image4(null);
								}
								
							model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
							model.addAttribute("photoProduct", photoProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);	
						}
					
				else {
					AdminController.isLoginSuperAdmin = false;
				
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
			}	
			//add and Update photo5
			@RequestMapping(value="addphotoproduct5.htm")
			public ModelAndView addPhotoProduct5(@ModelAttribute("idProduct") PhotoProduct newPhotoProduct,
					 Model model,BindingResult result ) throws SQLException, ServletException, IOException {
		
				PhotoProduct photoProduct = new PhotoProduct();
				PhotoProductDAO phdao = new PhotoProductDAO();
				ArrayList<PhotoProduct> allPhotoProduct = phdao.getAllPhotoProduct();
				if((AdminController.isLoginSuperAdmin == true)) {
					for(PhotoProduct p:allPhotoProduct) {
						if(p.getIdProdus()==newPhotoProduct.getIdProdus()) {
							
								phdao.updatePhotoProduct5(newPhotoProduct);
						   } 
						}
								photoProduct = phdao.getPhotoProduct(newPhotoProduct.getId());
								if(photoProduct.getBase64Image5().isEmpty()) {
									photoProduct.setBase64Image5(null);
								}
								
							model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
							model.addAttribute("photoProduct", photoProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);	
						}
					
				else {
					AdminController.isLoginSuperAdmin = false;
				
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
			}	
			
			//add and Update videoLink
			@RequestMapping(value="addvideolink.htm")
			public ModelAndView addVideoLink(@ModelAttribute("idProduct") PhotoProduct newPhotoProduct,
					 Model model,BindingResult result ) throws SQLException, ServletException, IOException {
		
				PhotoProduct photoProduct = new PhotoProduct();
				PhotoProductDAO phdao = new PhotoProductDAO();
				ArrayList<PhotoProduct> allPhotoProduct = phdao.getAllPhotoProduct();
				if((AdminController.isLoginSuperAdmin == true)) {
					for(PhotoProduct p:allPhotoProduct) {
						if(p.getIdProdus()==newPhotoProduct.getIdProdus()) {
							
								phdao.updatePhotoVideoLink(newPhotoProduct);
						   } 
						}
								photoProduct = phdao.getPhotoProduct(newPhotoProduct.getId());
								if(photoProduct.getBase64Image5().isEmpty()) {
									photoProduct.setBase64Image5(null);
								}
								
							model.addAttribute("idProduct", newPhotoProduct.getIdProdus());
							model.addAttribute("photoProduct", photoProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);	
						}
					
				else {
					AdminController.isLoginSuperAdmin = false;
				
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
			}	
		
			
}
