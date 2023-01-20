package org.eurovending.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eurovending.helper.DBHelper;
import org.eurovending.pojo.User;


public class UserDAO {

	//Creare Tabela cu Useri
			public void createTableUser() throws SQLException {
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "CREATE TABLE IF NOT EXISTS user"+ 
							" (id INTEGER not NULL auto_increment primary key,full_name VARCHAR(150) NULL,phone_number VARCHAR(15) NULL,"
							+ "email VARCHAR(50) NULL,role VARCHAR(15) NULL,"
		                    + "salt VARCHAR(30) NULL,password VARCHAR(100) NULL,statute VARCHAR(30) NULL)";
							
				      Statement stmt = con.createStatement();
				      
				       stmt.execute(insertUser);
				       helper.closeConnection(con);
				       
				}
			
			//verify if table User is Empty
			public int verifyTableUser() throws SQLException {
				
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String selectUser = "SELECT * FROM user";
				int count = 0;	
			    Statement stmt = (PreparedStatement) con.prepareStatement(selectUser);
			    ResultSet rst = stmt.executeQuery(selectUser);
			        try {
			             while(rst.next()){
			                count++;
			             }
			            
			        } catch (SQLException ex) {
			                
			         }
			  
			     helper.closeConnection(con);
			     return count;
			}

			//afisare continut tabela user

					public ArrayList<User> getUserList() throws SQLException{
						ArrayList<User> listUser=new ArrayList<User>();
						User user = null;
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						String getLocatie = "select*from user";
					      Statement stmt = con.createStatement();
					      ResultSet rst = stmt.executeQuery(getLocatie);
					      while(rst.next()) {
					    	  
					    	     int id = rst.getInt("id");
					    		 String fullName = rst.getString("full_name");
					    		 String phoneNumber = rst.getString("phone_number");
					    		 String email = rst.getString("email");
					    		 String salt = rst.getString("salt");
					    	     String password = rst.getString("password");
					    	     String role = rst.getString("role");
					    	     String statute = rst.getString("statute");
					    	 user = new User(id,fullName,phoneNumber,email,salt,password,role,statute);
					    	  listUser.add(user);
					      }
					      
					     helper.closeConnection(con);
						return listUser;
					}
					
					
					//selectare user dupa fullName din tabela
						public User getOneUser(String fullNam) throws SQLException{
							User user =null;
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
							String getUser = "SELECT*FROM user where full_name ='"+fullNam+"'";
						      Statement stmt = con.createStatement();
						      ResultSet rst = stmt.executeQuery(getUser);
						      while(rst.next()) {
						    	  int id = rst.getInt("id");
						    		 String fullName = rst.getString("full_name");
						    		 String phoneNumber = rst.getString("phone_number");
						    		 String email = rst.getString("email");
						    		 String salt = rst.getString("salt");
						    	     String password = rst.getString("password");
						    	     String role1 = rst.getString("role");
						    	     String statute1 = rst.getString("statute");
						    	 user = new User(id,fullName,phoneNumber,email,salt,password,role1,statute1);
							    	  }
							
							return user;
							
						}
						
						
						
						//Insert  into table user
						public void insertUser(User user) throws SQLException {
							
							
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
							String insertUser = "insert into user"+ 
									"( full_name,phone_number,email,role,salt,password,statute)"
									  +" value(?,?,?,?,?,?,?)";
							
							PreparedStatement ps = con.prepareStatement(insertUser);
							ps.setString(1, user.getFullName());
							ps.setString(2,user.getPhoneNumber());
							ps.setString(3, user.getEmail());
							ps.setString(4, user.getRole());
							ps.setString(5, user.getSalt());
							ps.setString(6, user.getPassword());
							ps.setString(7, user.getStatute());
							ps.executeUpdate();
							
							 helper.closeConnection(con);
						      }

						//Delete User
						public void deleteCategory(int id) throws SQLException {
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
							String deleteRow = "DELETE FROM user WHERE id=?";
							if(id>0) {
							PreparedStatement ps = con.prepareStatement(deleteRow);
							ps.setInt(1, id);
							ps.executeUpdate();
							helper.closeConnection(con);
							}
						}
						

}
