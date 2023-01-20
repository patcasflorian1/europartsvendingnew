package org.eurovending.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

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
public class CategoryAdmin {

	//listare  categorie
	@RequestMapping(value="list-category.htm")
	public ModelAndView admin(@ModelAttribute("myUser") User myUser ,Model model,BindingResult result) 
			throws SQLException, ServletException, IOException {
		
		boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
		if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
			ArrayList<Category> listCategory = new ArrayList<Category>();
			ArrayList<SubCategory> listSubCategory = new ArrayList<SubCategory>();
			CategoryDAO cdao = new CategoryDAO();
			SubCategoryDAO scdao =new SubCategoryDAO();
			listCategory = cdao.getListaCategory();
			listSubCategory = scdao.getAllSubCategory();
	        	
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listSubCategory", listSubCategory);
		return new ModelAndView("WEB-INF/admin/adminhome/category/admincategorytable.jsp","model",model);
		}
		else {
			 AdminController.isLoginSuperAdmin = false;
			 return new ModelAndView("redirect:/LoginOut.htm");
		}
		}
	
	
	//collect and validate data from addcategorii.jsp
		@RequestMapping(value="add-category.htm")
		public ModelAndView insertCatParinte(@ModelAttribute("newCategory") Category catParinte,
				Model model,BindingResult result) throws SQLException, IOException, ServletException {
			 //System.out.println("Category "+catParinte.getNameCategory());
			String myUserFullName= AdminController.getMyUserName();
			boolean isOk = false;
			boolean isOk1 = false;
			isOk = AdminController.isNotNullNotEmptyNotWhiteSpace(catParinte.getNameCategory());
			isOk1 = AdminController.isNotNullNotEmptyNotWhiteSpace(catParinte.getStatut());
			if(isOk1 ==false) {
				catParinte.setStatut("FORBIDDEN");
			}
			CategoryDAO cdao = new CategoryDAO();
			ArrayList<Category>listCategory = new ArrayList<Category>();	
			listCategory = cdao.getListaCategory();
			if((isOk ==true)&&(catParinte.getNameCategory().length()<40)) {
			if((AdminController.isLoginSuperAdmin() == true)&&(AdminController.getMyUserName()!=null)) {
			String statut =catParinte.getStatut();
			Category categorie =new Category();
			String numeCat = catParinte.getNameCategory().substring(0, 1).toUpperCase()
					        + catParinte.getNameCategory().substring(1);
			categorie.setNameCategory(numeCat);
				categorie.setStatut(catParinte.getStatut());
				for(Category cat:listCategory) {
					if(cat.getNameCategory().equalsIgnoreCase(categorie.getNameCategory())) {
						String mesaj = "Denumirea categorie exista. Alegeti o alta denumire";
						model.addAttribute("listCategory", listCategory);
						model.addAttribute("mesaj", mesaj);
						return new ModelAndView("WEB-INF/admin/adminhome/category/admincategorytable.jsp","model",model);
					}
					
				}
			
				cdao.insertCategorie(categorie);
			}
			return new ModelAndView("redirect:/list-category.htm");
			}
			else {
				String mesaj = "Denumirea de categorie este prea lunga (>35 Caractere). Alegeti o denumire mai scurta si sugestiva";
				model.addAttribute("listCategory", listCategory);
				model.addAttribute("mesaj", mesaj);
				return new ModelAndView("WEB-INF/admin/adminhome/category/admincategorytable.jsp","model",model);
			}
		}	
		
		
		//listare  categorie de editat
		@RequestMapping(value="editcategory.htm")
		public ModelAndView editCategory(@ModelAttribute("idCategorie") String idcategory ,Model model,BindingResult result) 
				throws SQLException, ServletException, IOException {
			int idCategory = Integer.parseInt(idcategory);
			
			boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
			if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
				Category newCategory = new Category();
				CategoryDAO cdao = new CategoryDAO();
				newCategory = cdao.getIdCategorieParinte(idCategory);
			model.addAttribute("newCategory", newCategory);
			return new ModelAndView("WEB-INF/admin/adminhome/category/admincategorytable.jsp","model",model);
			}
			else {
				 AdminController.isLoginSuperAdmin = false;
				 return new ModelAndView("redirect:/LoginOut.htm");
			}
			}
		
		//listare  categorie de editat
				@RequestMapping(value="update-category.htm")
				public ModelAndView updateCategory(@ModelAttribute("idcategory") Category category ,Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					boolean getMyCategoryNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(category.getNameCategory());
					boolean getMyCategoryStatutIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(category.getStatut());
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						ArrayList<Category>listCategory = new ArrayList<Category>();
						Category oldCategory = new Category();
						Category newCategory = new Category();
						CategoryDAO cdao = new CategoryDAO();
						oldCategory = cdao.getIdCategorieParinte(category.getId());
						if(getMyCategoryNameIsOk==true) {
							String numeCat = category.getNameCategory().substring(0, 1).toUpperCase()
							        + category.getNameCategory().substring(1);
							newCategory.setNameCategory(numeCat);
					
						}
						else {
							newCategory.setNameCategory(oldCategory.getNameCategory());
						}
						if(getMyCategoryStatutIsOk ==true) {
							newCategory.setStatut(category.getStatut());
						}
						else {
							newCategory.setStatut(oldCategory.getStatut());
						}
						cdao.updateCategory(category.getId(), newCategory);
						listCategory = cdao.getListaCategory();
					    model.addAttribute("listCategory", listCategory);
					return new ModelAndView("WEB-INF/admin/adminhome/category/admincategorytable.jsp","model",model);
					}
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}
		
		
		//delete  categorie
		@RequestMapping(value="delete-category.htm")
		public ModelAndView deleteCategory(@ModelAttribute("idCategorie") String idcategory ,Model model,BindingResult result) 
				throws SQLException, ServletException, IOException {
			int idCategory = Integer.parseInt(idcategory);
			boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
			if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
				ArrayList<Category>listCategory = new ArrayList<Category>();
				CategoryDAO cdao = new CategoryDAO();
				cdao.deleteCategory(idCategory);
				listCategory = cdao.getListaCategory();
			    model.addAttribute("listCategory", listCategory);
				
				return new ModelAndView("WEB-INF/admin/adminhome/category/admincategorytable.jsp","model",model);
			}
			else {
				 AdminController.isLoginSuperAdmin = false;
				 return new ModelAndView("redirect:/LoginOut.htm");
			}
			}


		
}
