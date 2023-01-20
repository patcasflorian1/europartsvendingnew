package org.eurovending.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eurovending.dao.BannerDAO;
import org.eurovending.dao.CategoryDAO;
import org.eurovending.dao.PhotoProductDAO;
import org.eurovending.dao.ProductDAO;
import org.eurovending.dao.SubCategoryDAO;
import org.eurovending.dao.UserDAO;
import org.eurovending.pojo.Category;
import org.eurovending.pojo.SubCategory;
import org.eurovending.pojo.User;
import org.eurovending.utils.PasswordUtils;
import org.eurovending.utils.ServiceLoger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@MultipartConfig(maxFileSize = 16177215) 
public class AdminController {

	static boolean isLoginSuperAdmin = false ;
	   static String myUserName=null;
		
	   private static final String UPLOAD_DIRECTORY ="/images";
		private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
		
		
	   
	   
		public static boolean isLoginSuperAdmin() {
			return isLoginSuperAdmin;
		}
		

		public   void setLoginSuperAdmin(boolean isLoginSuperAdmin) {
			this.isLoginSuperAdmin = isLoginSuperAdmin;
		}


		public static String getMyUserName() {
			return myUserName;
		}



		public  void setMyUserName(String myUserName) {
			this.myUserName = myUserName;
		}
		
		//verificare string 
			public static boolean isNotNullNotEmptyNotWhiteSpace( String string)
			{
				boolean stringOk =false;
				if((string!=null)&&(!string.isBlank())&&(!string.trim().isEmpty()))
				{
					stringOk=true;
				}
			   return stringOk;
			}
				
			//Login Out Admin & User(Employ)
			@RequestMapping(value="LoginOut.htm")
			 protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			    	response.setContentType("text/jsp");
			    	Cookie[] cookies = request.getCookies();
			    	if(cookies != null){
			    	for(Cookie cookie : cookies){
			    		if(cookie.getName().equals("JSESSIONID")){
			    			System.out.println("JSESSIONID="+cookie.getValue());
			    		}
			    		cookie.setMaxAge(0);
			    		response.addCookie(cookie);
			    	}
			    	}
			    	//invalidate the session if exists
			    	HttpSession session = request.getSession(false);
			    	
			    	if(session != null){
			    		session.invalidate();
			    	}
			    	//no encoding because we have invalidated the session
			    	response.sendRedirect("index.jsp");
			    }
			
			
			//Login Admin & User(Employ)
			@RequestMapping(value="loginAdmin.htm")
			public ModelAndView doPost(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException, SQLException {
				 ArrayList<User> userList = new ArrayList<User>();
				 UserDAO udao = new UserDAO();
                 ServiceLoger svl = new ServiceLoger();
				// get request parameters for userID and password
				String user = request.getParameter("email");
				String pwd = request.getParameter("password");
			   
				 //Generate table if not exist
				svl.generateTable();
				
				//test user&password is not empty
				boolean isOkUser = svl.testLogin(user, pwd); 
				
				//verifi user&password
				userList = udao.getUserList();
				 boolean adminPasswordMatch=false;
				
					if(isOkUser==true) {
				for(User myUser:userList) {
					 String verifyPassword = myUser.getPassword();
					 String adminSalt = myUser.getSalt();
					
						adminPasswordMatch = PasswordUtils.verifyUserPassword( pwd,myUser.getPassword(), myUser.getSalt());
						
			        if((adminPasswordMatch==true)&&(myUser.getEmail().equalsIgnoreCase(user))) 
			        {	
			        	HttpSession session = request.getSession();
						session.setAttribute("user", user);
						//setting session to expiry in 60 mins
						session.setMaxInactiveInterval(60*60);
						
						Cookie userName = new Cookie("user", user);
						userName.setMaxAge(60*60*60*60);
						response.addCookie(userName);				
			        	setMyUserName(myUser.getFullName());
				
			        	if((myUser.getRole().equals("SUPERADMIN"))&&(myUser.getStatute().equals("ACTIVE"))) {
			        		setLoginSuperAdmin(true);
			        		return new ModelAndView("redirect:/home-admin.htm");
			        	}
			        	
			        	if((myUser.getRole().equals("USER"))&&(myUser.getStatute().equals("ACTIVE"))) {
			        		setLoginSuperAdmin(true);
			        		
			        		return new ModelAndView("redirect:/home-admin.htm");
			            	}
			        }
			        
			        
				}
				
				
					}
			                     
			       
					return new ModelAndView("index.jsp");
			        }
					  

			//listare  homeAdmin
			@RequestMapping(value="home-admin.htm")
			public ModelAndView homeAdmin() 
					 {
				boolean getMyUserNameIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(AdminController.getMyUserName());
				if((AdminController.isLoginSuperAdmin() == true)&&(getMyUserNameIsOk=true)) {
				return new ModelAndView("WEB-INF/admin/adminhome/homeadmin.jsp");
			}
			else {
				 AdminController.isLoginSuperAdmin = false;
				 return new ModelAndView("redirect:/LoginOut.htm");
			}
			}
			
			

			private ServletRequest getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}
				
}

