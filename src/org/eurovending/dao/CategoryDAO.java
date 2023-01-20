package org.eurovending.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import org.eurovending.helper.DBHelper;
import org.eurovending.pojo.Category;





public class CategoryDAO {

ArrayList<Category> category = new ArrayList<Category> ();

	//Creare Tabela cu CategoriiProduse
	public void createTableCategorii() throws SQLException {
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String insertCategorii = "CREATE TABLE IF NOT EXISTS list_of_category"+ 
	   " (id INTEGER not NULL auto_increment primary key,name_category VARCHAR(250) NULL,"
		+"dateOfPublish DATE NULL,statut_categorie VARCHAR(15) NULL)";			
			Statement stmt = con.createStatement();      
		       stmt.execute(insertCategorii);
		       helper.closeConnection(con);
		       
		}
	
	//afisare continut tabela Category
	public ArrayList<Category>  getListaCategory() throws SQLException {
		DBHelper helper = new DBHelper();
		Connection con = helper.getConnection();
		String strSql = "select * from list_of_category";
		Statement stmt;
		stmt= con.createStatement();
		ResultSet rs = stmt.executeQuery(strSql);
		while(rs.next()) {
			int id = rs.getInt("id");
			String nameCategory = rs.getString("name_category");
			Date dateOfPublish = rs.getDate("dateOfPublish");
			String statut = rs.getString("statut_categorie");
		   
			Category categorie = new Category(id,nameCategory,dateOfPublish,statut);
			category.add(categorie);
		}
		helper.closeConnection(con);
		return category;
		
		
	}
	
	//afisare continut tabela CategoryPublicate
		public ArrayList<Category>  getPublicListCategory() throws SQLException {
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String strSql = "select * from list_of_category";
			Statement stmt;
			stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(strSql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nameCategory = rs.getString("name_category");
				Date dateOfPublish = rs.getDate("dateOfPublish");
				String statut = rs.getString("statut_categorie");
			   
				Category categorie = new Category(id,nameCategory,dateOfPublish,statut);
				if(categorie.getStatut().equals("PUBLICAT")) {
				category.add(categorie);
				}
			}
			helper.closeConnection(con);
			return category;
			
			
		}

			//selectare  categorie dupa denumireCategorie din tabela
		public Category getCategorieParinte(String numeCategorie ) throws SQLException {
				
			Category categorie = new Category();
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String strSql = "select*from list_of_category where name_category = ?";
				PreparedStatement stmt = con.prepareStatement(strSql);
				stmt.setString(1, numeCategorie);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					int id = rs.getInt("id");
					String nameCategory = rs.getString("name_category");
					Date dateOfPublish = rs.getDate("dateOfPublish");
					String statut = rs.getString("statut_categorie");
				   
					 categorie = new Category(id,nameCategory,dateOfPublish,statut);
					
				}
				
				helper.closeConnection(con);
				return categorie;	
			}
		
		//selectare categorieParinte dupa id din tabela
		public Category getIdCategorieParinte(int id ) throws SQLException {

		Category categorie = null;
		DBHelper helper = new DBHelper();
		Connection con = helper.getConnection();
		String strSql = "select*from list_of_category where id =?";
		PreparedStatement stmt = con.prepareStatement(strSql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			int id1 = rs.getInt("id");
			String nameCategory = rs.getString("name_category");
			Date dateOfPublish = rs.getDate("dateOfPublish");
			String statut = rs.getString("statut_categorie");
			categorie = new Category(id1,nameCategory,dateOfPublish,statut);
		}

		helper.closeConnection(con);
		return categorie;	
		}

		
		
		//Insert  into table Category
		public void insertCategorie(Category categorie) throws SQLException, IOException {
			java.util.Date date = new java.util.Date();
			java.sql.Date dateOfPublish = new java.sql.Date(date.getTime());
			
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String insertCategorie = "insert into list_of_category"+ 
					"(name_category,dateOfPublish,statut_categorie)"
					  +" value(?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(insertCategorie);
			ps.setString(1, categorie.getNameCategory());
			ps.setDate(2,dateOfPublish);
			ps.setString(3, categorie.getStatut());
			ps.executeUpdate();
			
			 helper.closeConnection(con);
		      }
		
		//Update Category
		public void updateCategory(int id,Category newCategorie) throws SQLException {
			java.util.Date date=new java.util.Date();
			java.sql.Date dateOfPublish=new java.sql.Date(date.getTime());
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String upDateCategory = "UPDATE list_of_category SET name_category = '"+newCategorie.getNameCategory()+"',dateOfPublish = '"+dateOfPublish+"',statut_categorie = '"+newCategorie.getStatut()+"' where id = '"+id+"'";
			PreparedStatement ps = con.prepareStatement(upDateCategory);
			ps.executeUpdate();
			helper.closeConnection(con);
			
		}
		
		//Delete categorie parinte
		public void deleteCategory(int id) throws SQLException {
			DBHelper helper = new DBHelper();
			Connection con = helper.getConnection();
			String deleteRow = "DELETE FROM list_of_category WHERE id=?";
			PreparedStatement ps = con.prepareStatement(deleteRow);
			ps.setInt(1, id);
			ps.executeUpdate();
			helper.closeConnection(con);
		}
		

}
