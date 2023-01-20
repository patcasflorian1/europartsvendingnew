package org.eurovending.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.eurovending.dao.CategoryDAO;
import org.eurovending.dao.PhotoProductDAO;
import org.eurovending.dao.ProductDAO;
import org.eurovending.dao.SubCategoryDAO;
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
public class ProductController {

	
	//listare  produse
		@RequestMapping(value="productlist.htm")
		public ModelAndView productList(@ModelAttribute("myUser") User myUser ,Model model,BindingResult result) throws SQLException, ServletException, IOException {
			
			Category category = new Category();
			SubCategory subCategory = new SubCategory();
			ArrayList<Product> productList = new ArrayList<Product>();
			ArrayList<Product> newproductList = new ArrayList<Product>();
			ProductDAO pdao = new ProductDAO();
			CategoryDAO cdao = new CategoryDAO();
			SubCategoryDAO sCdao = new SubCategoryDAO();
			productList = pdao.getProductList();
			
			boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
			if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
				for( int i = 0;i<productList.size();i++) {
	        		category = cdao.getCategorieParinte(productList.get(i).getCategory());
	        		subCategory = sCdao.getSubCategorie(productList.get(i).getSubCategory());
	        		if(subCategory!=null) {
	        		productList.get(i).setSubCategory(subCategory.getNameSubCategory());
	        		}
	        		else {
	        			productList.get(i).setSubCategory("Lipsa SubCategorie");
	        		}
	        	if(category.getNameCategory()!=null) {
	        		productList.get(i).setCategory(category.getNameCategory());
	        		}
	        	else {
	        		productList.get(i).setCategory("Lipsa Categorie");
	        	}
        		newproductList.add(productList.get(i));
	        	}
				
				model.addAttribute("productList", newproductList);
				
				return new ModelAndView("WEB-INF/admin/adminhome/Products/productsTabel.jsp","model",model);	
			
			}
			else {
				 AdminController.isLoginSuperAdmin = false;
				 return new ModelAndView("redirect:/LoginOut.htm");
			}
		}
		
		
		//select Category  for Product
		@RequestMapping(value="selectCategory.htm")
		public ModelAndView selectCategory(@ModelAttribute("nameCategory") Category category , Model model,BindingResult result) throws SQLException, ServletException, IOException {
			
				ArrayList<Category>listCategory = new ArrayList<Category>();
				ArrayList<Category>listaCategorii = new ArrayList<Category>();
				CategoryDAO cdao = new CategoryDAO();
				boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
				if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
					listaCategorii = cdao.getListaCategory();
					for(Category categorie :listaCategorii) {
					
					if(categorie.getStatut().equals("PUBLICAT")) {
						listCategory.add(categorie);
					       }
					    }
					String subCat = null;
					model.addAttribute("subCategory", subCat);
					 model.addAttribute("nameCategory",category.getNameCategory()); 
					 model.addAttribute("listCategory", listCategory);
						return new ModelAndView("WEB-INF/admin/adminhome/Products/addProduct.jsp","model",model);
				    }
				else {
					 AdminController.isLoginSuperAdmin = false;
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
		     }
	
		
		//select SubCategory  for Product
				@RequestMapping(value="selectSubCategory.htm")
				public ModelAndView selectSubCategory(@ModelAttribute("nameCategory") Category category,@ModelAttribute("subCategory") SubCategory newSubCategory,
						             Model model,BindingResult result) throws SQLException, ServletException, IOException {
					
						ArrayList<Category>listCategory = new ArrayList<Category>();
						ArrayList<Category>listaCategorii = new ArrayList<Category>();
						ArrayList<SubCategory> listSubCat = new ArrayList<SubCategory>();
						CategoryDAO cdao = new CategoryDAO();
						SubCategoryDAO scdao = new SubCategoryDAO();
						
						boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
						if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
							listaCategorii = cdao.getListaCategory();
							for(Category categorie :listaCategorii) {
							
							if(categorie.getStatut().equals("PUBLICAT")) {
								listCategory.add(categorie);
							       }
							    }
							if(category.getNameCategory()!=null) {
								listSubCat = scdao.getListaSubCategorii(category.getNameCategory());
								model.addAttribute("nameCategory", category.getNameCategory());
							}
							
							String subCat = null;
							model.addAttribute("subCategory", subCat);
							
							if(listSubCat.isEmpty()) {
							String codMesage ="Inca Nu ati creat SubCategorie. Adaugati o SubCategorie in sectiunea SubCategorii !";
							model.addAttribute("subCatMesage", codMesage);
							}
							model.addAttribute("subCategory", subCat);
							model.addAttribute("category",category);							
							model.addAttribute("listCategory", listCategory);
							model.addAttribute("listSubCat", listSubCat);
							 
								return new ModelAndView("WEB-INF/admin/adminhome/Products/addProduct.jsp","model",model);
						    }
						else {
							 AdminController.isLoginSuperAdmin = false;
							 return new ModelAndView("redirect:/LoginOut.htm");
						}
				     }
				
		
	
	//add&edit  produs
	@RequestMapping(value="addproduct.htm")
	public ModelAndView editCategoryHome(@ModelAttribute("productName") Product newProduct,@ModelAttribute("subCategory") String subCat,
		Model model,BindingResult result) throws SQLException, ServletException, IOException {
		
			ArrayList<Product> productList = new ArrayList<Product>();
			SubCategory subCategory = new SubCategory();
			SubCategoryDAO scdao = new SubCategoryDAO();
			ProductDAO pdao = new ProductDAO();
			subCategory = scdao.getSubCategorie(subCat);
			int countProducts = pdao.verifyTableProducts();
			newProduct.setCategory(subCategory.getNameCategory());
			newProduct.setSubCategory(subCategory.getNameSubCategory());
	
		
		boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
		
		boolean getMyProductNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(newProduct.getNumeProdus());
		boolean getMyProductCodeIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(newProduct.getCodProdus());
		boolean getMyProductStatutIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(newProduct.getStatutProdus());
		String numeCatParinte = subCat;
		if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
			
			
			
			if((getMyProductNameIsOk)&&(getMyProductCodeIsOk)) {
				productList = pdao.getProductList();
				if(countProducts>0) {
				for(Product prd:productList) {
					if(prd.getCodProdus().equalsIgnoreCase(newProduct.getCodProdus())) {
						
						String codMesage ="Cod Produs Existent,alegeti alt cod";
						
						model.addAttribute("subCategory", subCat);
						model.addAttribute("codMesage", codMesage);
						model.addAttribute("productName", newProduct);
						
						return new ModelAndView("WEB-INF/admin/adminhome/Products/addProduct.jsp","model",model);	
					}
					if(prd.getNumeProdus().equalsIgnoreCase(newProduct.getNumeProdus())) {
					
						String numeMesage ="Nume Produs Existent,alegeti alt nume";
						
						
						model.addAttribute("subCategory", subCat);
						model.addAttribute("numeMesage", numeMesage);
						model.addAttribute("productName", newProduct);
						return new ModelAndView("WEB-INF/admin/adminhome/Products/addProduct.jsp","model",model);
					}
					
				}
				}
				String numeProd =newProduct.getNumeProdus().substring(0, 1).toUpperCase() 
	                    + newProduct.getNumeProdus().substring(1);
				newProduct.setNumeProdus(numeProd);
			    if(getMyProductStatutIsOk==false) {
			    	newProduct.setStatutProdus("FORBIDDEN");
			    }
				
				pdao.insertProduct(newProduct);
				newProduct = pdao.getLastProducts();
				
				model.addAttribute("idProduct", newProduct.getId());
				model.addAttribute("productName", newProduct);
				return new ModelAndView("WEB-INF/admin/adminhome/PhotoProduct/addphotoproduct.jsp","model",model);
			}
		}
		Product lastProduct = new Product();
		String codInitialProd;
		if(countProducts>0) {
		lastProduct = pdao.getLastProducts();
		 codInitialProd = lastProduct.getCodProdus();
		}
		else {
			codInitialProd = "01";
			
		}
		model.addAttribute("subCategory", subCat);
		model.addAttribute("codInitialProd", codInitialProd);
		model.addAttribute("nameCategory", numeCatParinte);
		model.addAttribute("productName", newProduct);
		return new ModelAndView("WEB-INF/admin/adminhome/Products/addProduct.jsp","model",model);
		
	}
	
	//listProductForEdit
	@RequestMapping(value="listProductForEdit.htm")
	public ModelAndView listProductForEdit(@ModelAttribute("idProduct") int idProduct , Model model,BindingResult result) throws SQLException, ServletException, IOException {
		   
		    Product product = new Product();
			ArrayList<Category>listCategory = new ArrayList<Category>();
			ArrayList<SubCategory>listSubCategory = new ArrayList<SubCategory>();
			ProductDAO pdao = new ProductDAO();
			CategoryDAO cdao = new CategoryDAO();
			SubCategoryDAO scdao = new SubCategoryDAO();
			
			boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
			if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
				product = pdao.getProducts(idProduct);
				listCategory = cdao.getListaCategory();
				listSubCategory = null;
			 model.addAttribute("product" , product);
			 model.addAttribute("listSubCategory",listSubCategory); 
			 model.addAttribute("listCategory", listCategory);
			// model.addAttribute("subCatMesage",subCatMesage);
			
			return new ModelAndView("WEB-INF/admin/adminhome/Products/editproduct.jsp","model",model);
		    }
		else {
			 AdminController.isLoginSuperAdmin = false;
			 return new ModelAndView("redirect:/LoginOut.htm");
		}
     }
	
	//setCategoryProduct
		@RequestMapping(value="setCategoryProduct.htm")
		public ModelAndView setCategoryProduct(@ModelAttribute("idProduct") int idProduct ,@ModelAttribute("nameCategory")  Category category,
				                 Model model,BindingResult result) throws SQLException, ServletException, IOException {
			   
			    Product product = new Product();
				ArrayList<Category>listCategory = new ArrayList<Category>();
				ArrayList<SubCategory>listSubCategory = new ArrayList<SubCategory>();
				ProductDAO pdao = new ProductDAO();
				CategoryDAO cdao = new CategoryDAO();
				SubCategoryDAO scdao = new SubCategoryDAO();
				
				boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
				if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
					product = pdao.getProducts(idProduct);
					product.setCategory(category.getNameCategory());
					pdao.updateCategoryProduct(product);
					listCategory = cdao.getListaCategory();
					listSubCategory = scdao.getListaSubCategorii(category.getNameCategory());
					String subCatMesage = null;
					if(listSubCategory.size()==0) {
						subCatMesage = "Nu Exista Subcategorii in Aceasta Categorie ! Creati O Subcategorie in Sectiunea SubCategorii";
				      listSubCategory=null;
					}
					String editProduct = null;
				 product = pdao.getProducts(idProduct);
				 model.addAttribute("product" , product);
				 model.addAttribute("listSubCategory",listSubCategory); 
				 model.addAttribute("listCategory", listCategory);
				 model.addAttribute("subCatMesage",subCatMesage);
				 model.addAttribute("editProduct",editProduct);
				return new ModelAndView("WEB-INF/admin/adminhome/Products/editproduct.jsp","model",model);
			    }
			else {
				 AdminController.isLoginSuperAdmin = false;
				 return new ModelAndView("redirect:/LoginOut.htm");
			}
	     }
	
		//setCategoryProduct
				@RequestMapping(value="setSubCategoryProduct.htm")
				public ModelAndView setSubCategoryProduct(@ModelAttribute("idProduct") int idProduct ,@ModelAttribute("nameCategory")  Category category,
						@ModelAttribute("nameSubCategory") SubCategory subCategory,Model model,BindingResult result) throws SQLException, ServletException, IOException {
					   
					    Product product = new Product();
						ArrayList<Category>listCategory = new ArrayList<Category>();
						ArrayList<SubCategory>listSubCategory = new ArrayList<SubCategory>();
						ProductDAO pdao = new ProductDAO();
						CategoryDAO cdao = new CategoryDAO();
						SubCategoryDAO scdao = new SubCategoryDAO();
						
						boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
						if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
							product = pdao.getProducts(idProduct);
							product.setSubCategory(subCategory.getNameSubCategory());
							pdao.updatesubCategoryProduct(product);
							listCategory = cdao.getListaCategory();
							listSubCategory = scdao.getListaSubCategorii(product.getCategory());
							String subCatMesage = null;
							if(listSubCategory.size()==0) {
								subCatMesage = "Nu Exista Subcategorii in Aceasta Categorie ! Creati O Subcategorie in Sectiunea SubCategorii";
						      listSubCategory=null;
							}
						 product = pdao.getProducts(idProduct);
						 
							String editSubCat = "Set New Product";
						 model.addAttribute("product" , product);
						 model.addAttribute("listSubCategory",listSubCategory); 
						 model.addAttribute("listCategory", listCategory);
						 model.addAttribute("subCatMesage",subCatMesage);
						 model.addAttribute("editSubCat",editSubCat);
						return new ModelAndView("WEB-INF/admin/adminhome/Products/editproduct.jsp","model",model);
					    }
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
			     }
			
	
	 //edit product
			@RequestMapping(value="updateProduct.htm")
			public ModelAndView editProduct(@ModelAttribute("idProduct") Product product , 
					Model model,BindingResult result) throws SQLException, ServletException, IOException {
				   
					ProductDAO pdao = new ProductDAO();
					PhotoProductDAO photoProduct = new PhotoProductDAO();
					boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
						pdao.updateProduct(product);
						photoProduct.updatePhotoCodProdus(product);
						return new ModelAndView("redirect:/productlist.htm","model",model);
					    }
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
			     }
       
			//edit product
			@RequestMapping(value="viewProduct.htm")
			public ModelAndView viewProduct(@ModelAttribute("idProduct") int id , 
					Model model,BindingResult result) throws SQLException, ServletException, IOException {
				   
					ProductDAO pdao = new ProductDAO();
					PhotoProductDAO phdao = new PhotoProductDAO();
					Product prod = new Product();
					PhotoProduct photoProduct = new PhotoProduct();
					boolean getMyUserNameIsOk =AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					if((AdminController.isLoginSuperAdmin == true)&&(getMyUserNameIsOk=true)) {
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
						return new ModelAndView("WEB-INF/admin/adminhome/Products/single-product.jsp","model",model);
					    }
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
			     }
			
			
	//delete  SubCategorie
	@RequestMapping(value="delete-product.htm")
	public ModelAndView deleteSubCategory(@ModelAttribute("id") String idProduct ,Model model,BindingResult result) 
			throws SQLException, ServletException, IOException {
		
		int id= Integer.parseInt(idProduct);
		boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
		if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
			Product product = new Product();
			ProductDAO pdao = new ProductDAO();
			PhotoProductDAO phdao = new PhotoProductDAO();
			product = pdao.getProducts(id);
			pdao.deleteProduct(id);
			phdao.deletePhoto(product.getId());
			return new ModelAndView("redirect:/productlist.htm");
		}
		else {
			 AdminController.isLoginSuperAdmin = false;
			 return new ModelAndView("redirect:/LoginOut.htm");
		}
		}
	
	
}
