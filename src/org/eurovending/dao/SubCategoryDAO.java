package org.eurovending.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import org.eurovending.helper.DBHelper;
import org.eurovending.pojo.SubCategory;



public class SubCategoryDAO {
	
	ArrayList<SubCategory> category = new ArrayList<SubCategory> ();
	
		//Creare Tabela cu Subcategory
		public void createTableSubCategory() throws SQLException {
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String insertCategorii = "CREATE TABLE IF NOT EXISTS list_subCategory"+ 
						" (id INTEGER not NULL auto_increment primary key,name_Category VARCHAR(250) NULL,"
						+"name_subCategory VARCHAR(250) NULL,photoSubCategory MEDIUMBLOB NULL,dateOfPublish DATE NULL,"
						+"statut_SubCategorie VARCHAR(15) NULL)";			
				Statement stmt = con.createStatement();      
			       stmt.execute(insertCategorii);
			       helper.closeConnection(con);
			       
			}
		
		//selectare listaSubCategory 
		public ArrayList<SubCategory>  getAllSubCategory() throws SQLException {
			
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String strSql = "select * from list_subCategory ";
			Statement stmt;
			stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(strSql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nameOfCategory = rs.getString("name_Category");
				String nameOfSubCategory = rs.getString("name_subCategory");
				 Blob blob = rs.getBlob("photoSubCategory"); 
				  byte[] byteArray = blob.getBytes(1, (int)blob.length());
				 String fotoString =( Base64.getEncoder().encodeToString(byteArray));
				 Date dateOfPublish = rs.getDate("dateOfPublish");
				String statut = rs.getString("statut_SubCategorie");
				SubCategory subCategorie = new SubCategory(id,nameOfCategory,nameOfSubCategory, fotoString,dateOfPublish,statut);
				category.add(subCategorie);
			}
			helper.closeConnection(con);
			return category;
			
			
		}
		
		//selectare listaSubCategory dupa numeCategorie din tabela
				public ArrayList<SubCategory>  getListaSubCategorii(String nameCategory) throws SQLException {
					
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String strSql = "select * from list_subCategory where name_Category = '"+nameCategory+"'";
					Statement stmt;
					stmt= con.createStatement();
					ResultSet rs = stmt.executeQuery(strSql);
					while(rs.next()) {
						int id = rs.getInt("id");
						String nameOfCategory = rs.getString("name_Category");
						String nameOfSubCategory = rs.getString("name_subCategory");
						 Blob blob = rs.getBlob("photoSubCategory"); 
						  byte[] byteArray = blob.getBytes(1, (int)blob.length());
						 String fotoString =( Base64.getEncoder().encodeToString(byteArray));
						 Date dateOfPublish = rs.getDate("dateOfPublish");
						String statut = rs.getString("statut_SubCategorie");
						SubCategory subCategorie = new SubCategory(id,nameOfCategory,nameOfSubCategory, fotoString,dateOfPublish,statut);
						category.add(subCategorie);
					}
					helper.closeConnection(con);
					return category;
					
					
				}
		
				//selectare subCategorie dupa id din tabela
				public SubCategory getSubCategorie(int getId) throws SQLException {
					
					SubCategory subCategorie = null;
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String strSql = "select*from list_subCategory where id =?";
					PreparedStatement stmt = con.prepareStatement(strSql);
					stmt.setInt(1, getId);
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) {
						int id = rs.getInt("id");
						String nameOfCategory = rs.getString("name_Category");
						String nameOfSubCategory = rs.getString("name_subCategory");
						 Blob blob = rs.getBlob("photoSubCategory"); 
						  byte[] byteArray = blob.getBytes(1, (int)blob.length());
						 String fotoString =( Base64.getEncoder().encodeToString(byteArray));
						 Date dateOfPublish = rs.getDate("dateOfPublish");
						String statut = rs.getString("statut_SubCategorie");
						 subCategorie = new SubCategory(id,nameOfCategory,nameOfSubCategory, fotoString,dateOfPublish,statut);
					}
					
					helper.closeConnection(con);
					return subCategorie;	
				}
		

				//selectare subCategorie dupa denumireCategorieCopil din tabela
				public SubCategory getSubCategorie(String numeSubCategorie ) throws SQLException {
					
					SubCategory subCategorie = null;
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String strSql = "select*from list_subCategory where name_subCategory = ?";
					PreparedStatement stmt = con.prepareStatement(strSql);
					stmt.setString(1, numeSubCategorie);
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) {
						int id = rs.getInt("id");
						String nameOfCategory = rs.getString("name_Category");
						String nameOfSubCategory = rs.getString("name_subCategory");
						 Blob blob = rs.getBlob("photoSubCategory"); 
						  byte[] byteArray = blob.getBytes(1, (int)blob.length());
						 String fotoString =( Base64.getEncoder().encodeToString(byteArray));
						 Date dateOfPublish = rs.getDate("dateOfPublish");
						String statut = rs.getString("statut_SubCategorie");
						 subCategorie = new SubCategory(id,nameOfCategory,nameOfSubCategory, fotoString,dateOfPublish,statut);
					}
					
					helper.closeConnection(con);
					return subCategorie;	
				}
		
		//Insert  into table SubCategorii
		public void insertSubCategorii(SubCategory subCategorie) throws SQLException, IOException {
			
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			subCategorie.setDateOfPublish(sqlDate);
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String insertCategorie = "insert into list_subCategory"+ 
					"(name_Category,name_subCategory,photoSubCategory,dateOfPublish,statut_SubCategorie)"
					  +" value(?,?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(insertCategorie);
			ps.setString(1, subCategorie.getNameCategory());
			ps.setString(2, subCategorie.getNameSubCategory());
			ps.setBlob(3,subCategorie.getPhotoSubCategory().getInputStream());
			ps.setDate(4,(Date) subCategorie.getDateOfPublish());
			ps.setString(5, subCategorie.getStatut());
			ps.executeUpdate();
			
			 helper.closeConnection(con);
		      }
		
		//Update categorie SubCategorie
		public void updateSubCategory(SubCategory subCategorie) throws SQLException, IOException {
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			subCategorie.setDateOfPublish(sqlDate);
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String upDateCategorii = "UPDATE list_subCategory SET name_Category=?, name_subCategory=?,photoSubCategory=?,dateOfPublish=?,statut_SubCategorie=? where id=?";
			PreparedStatement ps = con.prepareStatement(upDateCategorii);
			ps.setString(1, subCategorie.getNameCategory());
			ps.setString(2, subCategorie.getNameSubCategory());
			ps.setBlob(3,subCategorie.getPhotoSubCategory().getInputStream());
			ps.setDate(4,(Date) subCategorie.getDateOfPublish());
			ps.setString(5, subCategorie.getStatut());
			ps.setInt(6, subCategorie.getId());
			ps.executeUpdate();
			helper.closeConnection(con);
			
		}
		
		
		//verify if table SubCategorii is Empty
		public int verifyTableSubCategorii() throws SQLException {
			
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String selectUser = "SELECT * FROM list_subCategory";
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

		
		//Delete subCategorie 
				public void deleteCategory(int id) throws SQLException {
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String deleteRow = "DELETE FROM list_subCategory WHERE id=?";
					PreparedStatement ps = con.prepareStatement(deleteRow);
					ps.setInt(1, id);
					ps.executeUpdate();
					helper.closeConnection(con);
				}

}
