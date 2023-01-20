package org.eurovending.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.eurovending.controller.AdminController;
import org.eurovending.dao.BannerDAO;
import org.eurovending.dao.CategoryDAO;
import org.eurovending.dao.PhotoProductDAO;
import org.eurovending.dao.ProductDAO;
import org.eurovending.dao.SubCategoryDAO;
import org.eurovending.dao.UserDAO;
import org.eurovending.pojo.PhotoProduct;
import org.eurovending.pojo.User;

public class ServiceLoger {
	
	
	
	String userNameSystem = System.getProperty("user.name");
	public UserDAO udao = new UserDAO();
	public  PasswordUtils uPassword = new PasswordUtils();
	public  CategoryDAO cdao = new CategoryDAO();
	public SubCategoryDAO sCategorii = new SubCategoryDAO();
	public ProductDAO pdao = new ProductDAO();
	public PhotoProductDAO phdao = new PhotoProductDAO();
	public BannerDAO bdao = new BannerDAO();
	public ArrayList<User> userList = new ArrayList<User>();
	
	public String salt = null ;
	public String salt1 = null ;
	public String adminPassword = "eurovending";
	public String admin1Password = "eurovending1";
	public String generatePassword = null;
	public String generatePassword1 = null;
	//generate tabel if not exist
	public void generateTable() {
	 salt = uPassword.getSalt(30);
	 salt1 = uPassword.getSalt(30);
	 generatePassword = uPassword.generateSecurePassword( adminPassword,salt);
	 generatePassword1 = uPassword.generateSecurePassword( admin1Password,salt1);
	 User superAdmin = new User("Patcas Florentina","+40743556569",
             "patcasf12@gmail.com",salt,generatePassword,"SUPERADMIN","ACTIVE");
	 User admin = new User("Patcas Florin","+4074556533",
             "patcasf122@gmail.com",salt,generatePassword1,"USER","ACTIVE");
	
	 try {
			udao.createTableUser();
			cdao.createTableCategorii();
			sCategorii.createTableSubCategory();
			pdao.createTableProduct();
			phdao.createTablePhotoProduct();
			bdao.createTableBanner();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int count=0;
		try {
			 
			 count = udao.verifyTableUser();
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(count==0) {
			   try {
				udao.insertUser(superAdmin);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		 }	

		if(count==1) {
			   try {
				udao.insertUser(admin);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		 }		
	}
	//__end generate table if not exist__
	//test user&password is not empty
	public boolean testLogin(String user,String pwd) throws SQLException {
		
	
		boolean isOkUser  = false;
		boolean myEmailIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(user) ;
		boolean myPasswordIsOk = AdminController.isNotNullNotEmptyNotWhiteSpace(pwd);
		userList = udao.getUserList();
		if((myEmailIsOk==true)&&(myPasswordIsOk==true)) {
			isOkUser  = true;
			return isOkUser ;
		}	
		else {
			return isOkUser;
			}
		}
		//_end testuser&password is not empty
	

}
	