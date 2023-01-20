package org.eurovending.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.eurovending.dao.CategoryDAO;
import org.eurovending.dao.SubCategoryDAO;
import org.eurovending.pojo.Category;
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
public class SubCategoryAdmin {

	//listare  SubCategorie
		@RequestMapping(value="list-subCategory.htm")
		public ModelAndView admin(@ModelAttribute("myUser") User myUser ,Model model,BindingResult result) 
				throws SQLException, ServletException, IOException {
			
			boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
			if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
				ArrayList<Category> listCategory = new ArrayList<Category>();
				ArrayList<SubCategory> listSubCategory = new ArrayList<SubCategory>();
				ArrayList<SubCategory> newListSubCategory = new ArrayList<SubCategory>();
				Category category = new Category();
				CategoryDAO cdao = new CategoryDAO();
				SubCategoryDAO scdao =new SubCategoryDAO();
				//listCategory = cdao.getListaCategory();
				listSubCategory = scdao.getAllSubCategory();
				
		        	for(SubCategory scat:listSubCategory) {
		        		category = cdao.getCategorieParinte(scat.getNameCategory());
		        		boolean getMyCategoryNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(category.getNameCategory());
		        	if(getMyCategoryNameIsOk = true) {
		        		//category = cdao.getCategorieParinte(scat.getNameCategory());
		        		scat.setNameCategory(category.getNameCategory());
		        		}
		        	else {
		        		scat.setNameCategory(null);
		        	}
		        	
	        		newListSubCategory.add(scat);
		        	}
		        	
			model.addAttribute("listSubCategory", newListSubCategory);
			return new ModelAndView("WEB-INF/admin/adminhome/SubCategory/subCategoryTable.jsp","model",model);
			}
			else {
				 AdminController.isLoginSuperAdmin = false;
				 return new ModelAndView("redirect:/LoginOut.htm");
			}
			}
		
		
		
		//Select Category for add  SubCategorie
				@RequestMapping(value="addsubcategory.htm")
				public ModelAndView addSubcategory(@ModelAttribute("nameCategory") Category category ,
						@ModelAttribute("subCategory") SubCategory newSubCategory,Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					CategoryDAO cdao = new CategoryDAO();
					SubCategoryDAO scdao =new SubCategoryDAO();
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						ArrayList<Category> listCategory = new ArrayList<Category>();
						ArrayList<SubCategory> listSubCategory = new ArrayList<SubCategory>();
						listCategory = cdao.getListaCategory();
				    model.addAttribute("nameCategory",category.getNameCategory());     	  	
					model.addAttribute("listCategory", listCategory);
					return new ModelAndView("WEB-INF/admin/adminhome/SubCategory/addSubCategory.jsp","model",model);
					
					}
					
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}
				
			
				//insertSubcategory
				@RequestMapping(value="insertsubcategory.htm")
				public ModelAndView insertSubcategory(@ModelAttribute("nameCategory") Category category ,
						@ModelAttribute("subCategory") SubCategory newSubCategory,Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					CategoryDAO cdao = new CategoryDAO();
					
		
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					boolean nameSubCategory = AdminController.isNotNullNotEmptyNotWhiteSpace(newSubCategory.getNameSubCategory());
					boolean statutSubCategory = AdminController.isNotNullNotEmptyNotWhiteSpace(newSubCategory.getStatut());
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						SubCategoryDAO scdao =new SubCategoryDAO();
						ArrayList<Category> listCategory = new ArrayList<Category>();
						ArrayList<SubCategory> listSubCategory = new ArrayList<SubCategory>();
						listCategory = cdao.getListaCategory();
						listSubCategory = scdao.getAllSubCategory();
						if(nameSubCategory==true) {
							category = cdao.getCategorieParinte(newSubCategory.getNameCategory());
						for(SubCategory subCat:listSubCategory) {
							if(newSubCategory.getNameSubCategory().equalsIgnoreCase(subCat.getNameSubCategory())) {
								String mesaj = "Nume SubCategorie Existent,Alegeti alt Nume !";
								 model.addAttribute("mesaj",mesaj);   
								    model.addAttribute("nameCategory",category.getNameCategory()); 
								    model.addAttribute("newSubCategory",newSubCategory);    	
									model.addAttribute("listCategory", listCategory);
									
									return new ModelAndView("WEB-INF/admin/adminhome/SubCategory/addSubCategory.jsp","model",model);
							}
							
							String name = newSubCategory.getNameSubCategory().substring(0, 1).toUpperCase()
							        + newSubCategory.getNameSubCategory().substring(1);
							newSubCategory.setNameSubCategory(name);
						}
						if(!statutSubCategory) {
							newSubCategory.setStatut("FORBIDDEN");
						}
						
						scdao.insertSubCategorii(newSubCategory);
						
						}
						else {
							String mesaj = "Nume SubCategorie nu poate fi gol !";
							 model.addAttribute("mesaj",mesaj); 
							 if(!nameSubCategory) {
								
								 category = cdao.getCategorieParinte(newSubCategory.getNameCategory());
							 }
							 
							    model.addAttribute("nameCategory",category.getNameCategory());
							    model.addAttribute("newSubCategory",newSubCategory);    	
								model.addAttribute("listCategory", listCategory);
								
								return new ModelAndView("WEB-INF/admin/adminhome/SubCategory/addSubCategory.jsp","model",model);
						}
						
						//listSubCategory = scdao.getAllSubCategory();
						
					//model.addAttribute("listSubCategory", listSubCategory);
					return new ModelAndView("redirect:/list-subCategory.htm");
					
					}
					
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}
				
				//edit SubCategorie
				@RequestMapping(value="listCategory.htm")
				public ModelAndView listCategory(@ModelAttribute("idsubCategorii") int idsubCategorii ,
						@ModelAttribute("nameCategory") String nameCategory ,Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					boolean getNameCategoryIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(nameCategory);
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						ArrayList<Category> listCategory = new ArrayList<Category>();
						SubCategory subCategory = new SubCategory();
						CategoryDAO cdao = new CategoryDAO();
						SubCategoryDAO scdao = new SubCategoryDAO();
						listCategory = cdao.getListaCategory();
						subCategory = scdao.getSubCategorie(idsubCategorii);
                       if(getNameCategoryIsOk==false) {
                    	  nameCategory =null;
                       }
                       
					    model.addAttribute("nameCategory",nameCategory);
						model.addAttribute("listCategory", listCategory);
						model.addAttribute("subCategory", subCategory);
						return new ModelAndView("WEB-INF/admin/adminhome/SubCategory/editSubCategory.jsp","model",model);
					}
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}

				//update SubCategorie
				@RequestMapping(value="updateSubCategory.htm")
				public ModelAndView updateSubCategory(@ModelAttribute("subCategory") SubCategory newSubCategory,
						                              Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					boolean getNameSubCategoryIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(newSubCategory.getNameSubCategory());
					boolean getStatutSubCategoryIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(newSubCategory.getStatut());
					
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						
						CategoryDAO cdao = new CategoryDAO();
						SubCategoryDAO scdao = new SubCategoryDAO();
						SubCategory subCategory = new SubCategory();
						subCategory = scdao.getSubCategorie(newSubCategory.getId());
						subCategory.setNameCategory(newSubCategory.getNameCategory());
						if(getNameSubCategoryIsOk==true) {
							subCategory.setNameSubCategory(newSubCategory.getNameSubCategory());
						}
						if(getStatutSubCategoryIsOk == true) {
							subCategory.setStatut(newSubCategory.getStatut());
						}
						subCategory.setPhotoSubCategory(newSubCategory.getPhotoSubCategory());
						scdao.updateSubCategory(subCategory);
						return new ModelAndView("redirect:/list-subCategory.htm");
					}
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}
				
				
				//delete  SubCategorie
				@RequestMapping(value="delete-subcategory.htm")
				public ModelAndView deleteSubCategory(@ModelAttribute("id") String idSubCategory ,Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					
					int id= Integer.parseInt(idSubCategory);
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						SubCategoryDAO scdao = new SubCategoryDAO();
						scdao.deleteCategory(id);
						
						return new ModelAndView("redirect:/list-subCategory.htm");
					}
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}
				
				
}
