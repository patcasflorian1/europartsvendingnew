package org.eurovending.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import org.eurovending.dao.BannerDAO;
import org.eurovending.dao.SubCategoryDAO;
import org.eurovending.pojo.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig(maxFileSize = 16177215)
public class BannerController {

	
	//Login for admin&user
		@RequestMapping(value="listbanner.htm")
		public ModelAndView listBanner(Model model) throws SQLException, IOException {
		ArrayList<Banner> listBanner = new ArrayList<Banner>();
		BannerDAO bdao = new BannerDAO();
		boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
		if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
			listBanner = bdao.getListaBanner();
			model.addAttribute("listBanner", listBanner);
			return new ModelAndView("WEB-INF/admin/adminhome/banner/listbanner.jsp","model",model);
		   }
		else {
			 AdminController.isLoginSuperAdmin = false;
			 return new ModelAndView("redirect:/LoginOut.htm");
		}

		}
	
	//reditect to insertbanner.jsp
	@RequestMapping(value="addbanner.htm")
	public ModelAndView addBanner() throws SQLException {
	
		return new ModelAndView("WEB-INF/admin/adminhome/banner/addBanner.jsp");
	}
	//reditect to listbanner.jsp
	@RequestMapping(value = "insertbanner.htm")
	public ModelAndView saveBanner(@ModelAttribute("banner") Banner banner
			,Model model,BindingResult result) throws IOException, ServletException, SQLException {
		
		int result1 = 0;
		BannerDAO bdao = new BannerDAO();
		result1 = bdao.insertBanner(banner);
		ArrayList<Banner> listBanner = new ArrayList<Banner>();

		boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
		if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
			listBanner = bdao.getListaBanner();
			model.addAttribute("listBanner", listBanner);
			return new ModelAndView("WEB-INF/admin/adminhome/banner/listbanner.jsp","model",model);
		   }
		else {
			 AdminController.isLoginSuperAdmin = false;
			 return new ModelAndView("redirect:/LoginOut.htm");
		}

		}
	
	//reditect to editbanner.jsp
		@RequestMapping(value="editBanner.htm")
		public ModelAndView editBanner(@ModelAttribute("idbanner") int idBanner
				,Model model,BindingResult result) throws IOException, ServletException, SQLException {
		Banner banner = new Banner();
		BannerDAO bndao = new BannerDAO();
		banner = bndao.getBanner(idBanner);
		model.addAttribute("banner", banner);
			return new ModelAndView("WEB-INF/admin/adminhome/banner/editbanner.jsp","model",model);
		}
	
		//update banner and reditect to listbanner.jsp
				@RequestMapping(value="updateBanner.htm")
				public ModelAndView updateBanner(@ModelAttribute("banner") Banner banner
						,Model model,BindingResult result) throws IOException, ServletException, SQLException {
					System.out.println("BannerName "+banner.getBannerName());
					System.out.println("BannerPhoto "+banner.getPhoto().getSize());
					boolean getMyBannerStatutOk = AdminController.isNotNullNotEmptyNotWhiteSpace(banner.getStatut());
					Banner oldBanner = new Banner();
				BannerDAO bndao = new BannerDAO();
				oldBanner = bndao.getBanner(banner.getId());
				if(!getMyBannerStatutOk) {
					banner.setStatut(oldBanner.getStatut());
				}
				if(banner.getPhoto().getSize()>0) {
					bndao.updateBannerPhoto(banner,banner.getPhoto());
				}
				bndao.updateBanner(banner);
				ArrayList<Banner> listBanner = new ArrayList<Banner>();
				boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
				if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
					listBanner = bndao.getListaBanner();
					model.addAttribute("listBanner", listBanner);
					return new ModelAndView("WEB-INF/admin/adminhome/banner/listbanner.jsp","model",model);
				   }
				else {
					 AdminController.isLoginSuperAdmin = false;
					 return new ModelAndView("redirect:/LoginOut.htm");
				}
				}
				//delete  Banner
				@RequestMapping(value="delete-banner.htm")
				public ModelAndView deleteBanner(@ModelAttribute("id") String idSubCategory ,Model model,BindingResult result) 
						throws SQLException, ServletException, IOException {
					
					int id= Integer.parseInt(idSubCategory);
					boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
					if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
						BannerDAO scdao = new BannerDAO();
						scdao.deleteBanner(id);
						
						return new ModelAndView("redirect:/listbanner.htm");
					}
					else {
						 AdminController.isLoginSuperAdmin = false;
						 return new ModelAndView("redirect:/LoginOut.htm");
					}
					}
				
				
				
				
	
}