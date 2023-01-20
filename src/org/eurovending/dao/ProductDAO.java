package org.eurovending.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eurovending.helper.DBHelper;
import org.eurovending.pojo.Product;



public class ProductDAO {

	ArrayList<Product> result = new ArrayList<Product>();
	//Create table product
			public void createTableProduct() throws SQLException {  
				DBHelper helper = new DBHelper();
				Connection con =  helper.getConnection();
				String createTable = "CREATE TABLE IF NOT EXISTS products"+ 
						" (id INTEGER not NULL auto_increment primary key,category VARCHAR(150) NOT NULL,sub_category VARCHAR(150) NULL,cod_produs VARCHAR(50) NULL,"
						+ "nume_produs VARCHAR(150) NULL,descriere_produs VARCHAR(1000) NULL,pret_produs DECIMAL(5,2) NULL,pret_produs_promotional DECIMAL(5,2) NULL,"
						+ "cantitate_produs DECIMAL(5,2) NULL,unitate_masura VARCHAR(50),data_modificare VARCHAR(50),"
						+ "statut_produs VARCHAR(30) NULL,nota_produs DECIMAL(5,2) NULL)";
				      Statement stmt = con.createStatement();
				      
				       stmt.execute(createTable);
				       helper.closeConnection(con);
				       
				}
			
			//Insert  into table products
			public void insertProduct(Product product) throws SQLException {
				java.util.Date date=new java.util.Date();
				java.sql.Date sqlDate=new java.sql.Date(date.getTime());
				String numeCat = product.getNumeProdus().substring(0, 1).toUpperCase()
				        + product.getNumeProdus().substring(1);
				product.setNumeProdus(numeCat);
				
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String insertCategorie = "insert into products"+ 
						"(category,sub_category,cod_produs,nume_produs,descriere_produs,pret_produs,pret_produs_promotional,"
						  + "cantitate_produs,unitate_masura,data_modificare,statut_produs,nota_produs)"
						  +" value(?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(insertCategorie);
				ps.setString(1, product.getCategory());
				ps.setString(2, product.getSubCategory());
				ps.setString(3, product.getCodProdus());
				ps.setString(4, product.getNumeProdus());
				ps.setString(5, product.getDescriereProdus());
				ps.setDouble(6, product.getPretProdus());
				ps.setDouble(7, product.getPretProdusPromotional());
				ps.setDouble(8, product.getCantitateProdus());
				ps.setString(9, product.getUnitateMasura());
				ps.setString(10, sqlDate.toString());
				ps.setString(11, product.getStatutProdus());
				ps.setDouble(12, product.getNotaProdus());
				ps.executeUpdate();
				
				 helper.closeConnection(con);
			      }
			
			//Update date Product
			public void updateProduct(Product product) throws SQLException {
				java.util.Date date=new java.util.Date();
				java.sql.Date sqlDate=new java.sql.Date(date.getTime());
				String numeCat = product.getNumeProdus().substring(0, 1).toUpperCase()
				        + product.getNumeProdus().substring(1);
				product.setNumeProdus(numeCat);
				
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String upDateSc = "update products set category =?, sub_category= ?,cod_produs=?,nume_produs=?,descriere_produs=?,pret_produs=?,pret_produs_promotional=?,cantitate_produs=?,unitate_masura=?,data_modificare=?,statut_produs=?,nota_produs=? where id=?";
			
				PreparedStatement ps = con.prepareStatement(upDateSc);
				ps.setString(1, product.getCategory());
				ps.setString(2, product.getSubCategory());
				ps.setString(3, product.getCodProdus());
				ps.setString(4, product.getNumeProdus());
				ps.setString(5, product.getDescriereProdus());
				ps.setDouble(6, product.getPretProdus());
				ps.setDouble(7, product.getPretProdusPromotional());
				ps.setDouble(8, product.getCantitateProdus());
				ps.setString(9, product.getUnitateMasura());
				ps.setString(10, sqlDate.toString());
				ps.setString(11, product.getStatutProdus());
				ps.setDouble(12, product.getNotaProdus());
				ps.setInt(13, product.getId());
				ps.executeUpdate();
				helper.closeConnection(con);
				
			}

			//Update coloumn category in Product
			public void updateCategoryProduct(Product product) throws SQLException {
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String updateCategoryProduct = "UPDATE products SET category =? where id=?";
				PreparedStatement ps = con.prepareStatement(updateCategoryProduct);
				ps.setString(1, product.getCategory());
				ps.setInt(2, product.getId());
				ps.executeUpdate();
				helper.closeConnection(con);
				
			}
			
			//Update coloumn subCategory in Product
			public void updatesubCategoryProduct(Product product) throws SQLException {
				DBHelper helper = new DBHelper();
				Connection con = helper.getConnection();
				String upDateSc = "UPDATE products SET sub_category =? where id=?";
			
				PreparedStatement ps = con.prepareStatement(upDateSc);
				ps.setString(1, product.getSubCategory());
				ps.setInt(2, product.getId());
				ps.executeUpdate();
				helper.closeConnection(con);
				
			}
			
			// get top 5produse
			public ArrayList<Product> getTopProductList() throws SQLException{
				
				DBHelper helper = new DBHelper();
				Connection conn = helper.getConnection();
				String strSql = "SELECT * FROM products ORDER BY id DESC LIMIT 5 ";
				Statement stmt;
				stmt= conn.createStatement();
				ResultSet rs = stmt.executeQuery(strSql);
				while(rs.next()) {
					 int id = rs.getInt("id");
					 String category = rs.getString("category");
					 String subCategory = rs.getString("sub_category");
					 String codProdus = rs.getString("cod_produs");
					 String numeProdus = rs.getString("nume_produs");
					 String descriereProdus = rs.getString("descriere_produs");
					 double pretProdus = rs.getDouble("pret_produs");
					 double pretProdusPromotional = rs.getDouble("pret_produs_promotional");
					 double cantitateProdus = rs.getDouble("cantitate_produs");
					 String  unitateMasura = rs.getString("unitate_masura");
					 String  dataModificare = rs.getString("data_modificare");
					 String statutProdus = rs.getString("statut_produs");
				     double notaProdus = rs.getDouble("nota_produs");
					Product product = new Product(id,category,subCategory,codProdus,numeProdus,
							descriereProdus,pretProdus,pretProdusPromotional,cantitateProdus,
							unitateMasura,dataModificare,statutProdus,notaProdus);
					
					result.add(product);
				}
				helper.closeConnection(conn);
				return result;
			}

			
			// get lista produse
						public ArrayList<Product> getProductList() throws SQLException{
							
							DBHelper helper = new DBHelper();
							Connection conn = helper.getConnection();
							String strSql = "select * from products";
							Statement stmt;
							stmt= conn.createStatement();
							ResultSet rs = stmt.executeQuery(strSql);
							while(rs.next()) {
								 int id = rs.getInt("id");
								 String category = rs.getString("category");
								 String subCategory = rs.getString("sub_category");
								 String codProdus = rs.getString("cod_produs");
								 String numeProdus = rs.getString("nume_produs");
								 String descriereProdus = rs.getString("descriere_produs");
								 double pretProdus = rs.getDouble("pret_produs");
								 double pretProdusPromotional = rs.getDouble("pret_produs_promotional");
								 double cantitateProdus = rs.getDouble("cantitate_produs");
								 String  unitateMasura = rs.getString("unitate_masura");
								 String  dataModificare = rs.getString("data_modificare");
								 String statutProdus = rs.getString("statut_produs");
							     double notaProdus = rs.getDouble("nota_produs");
								Product product = new Product(id,category,subCategory,codProdus,numeProdus,
										descriereProdus,pretProdus,pretProdusPromotional,cantitateProdus,
										unitateMasura,dataModificare,statutProdus,notaProdus);
								
								result.add(product);
							}
							helper.closeConnection(conn);
							return result;
						}
						
						//selectare Product dupa id
						public Product getProducts(int id1 ) throws SQLException{
							
							String strSql = "SELECT*FROM products where id ='"+id1+"'";
						    
						    
							
							Product newProduct = null;
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
						      Statement stmt = con.createStatement();
						      ResultSet rs = stmt.executeQuery(strSql);
						      while(rs.next()) {
						    	  
						    	     int id=rs.getInt("id");
						    	     String category = rs.getString("category");
									 String subCategory = rs.getString("sub_category");
									 String codProdus = rs.getString("cod_produs");
									 String numeProdus = rs.getString("nume_produs");
									 String descriereProdus = rs.getString("descriere_produs");
									 double pretProdus = rs.getDouble("pret_produs");
									 double pretProdusPromotional = rs.getDouble("pret_produs_promotional");
									 double cantitateProdus = rs.getDouble("cantitate_produs");
									 String  unitateMasura = rs.getString("unitate_masura");
									 String  dataModificare = rs.getString("data_modificare");
									 String statutProdus = rs.getString("statut_produs");
									 double notaProdus = rs.getDouble("nota_produs");
						    		 
						    		 newProduct = new Product(id,category,subCategory,codProdus,numeProdus,descriereProdus,pretProdus,
						    				 pretProdusPromotional,cantitateProdus,unitateMasura,dataModificare,statutProdus,notaProdus);
						               }
						      
						     helper.closeConnection(con);
							return newProduct;
							
						}
				
						//selectare LastProduct 
						public Product getLastProducts() throws SQLException{
							
							//String strSql = "SELECT*FROM products where id ='"+id1+"'";
							String strSql = ("SELECT * FROM products ORDER BY id DESC LIMIT 1");
						    
							
							Product newProduct = null;
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
						      Statement stmt = con.createStatement();
						      ResultSet rs = stmt.executeQuery(strSql);
						      while(rs.next()) {
						    	  
						    	     int id=rs.getInt("id");
						    	     String category = rs.getString("category");
									 String subCategory = rs.getString("sub_category");
									 String codProdus = rs.getString("cod_produs");
									 String numeProdus = rs.getString("nume_produs");
									 String descriereProdus = rs.getString("descriere_produs");
									 double pretProdus = rs.getDouble("pret_produs");
									 double pretProdusPromotional = rs.getDouble("pret_produs_promotional");
									 double cantitateProdus = rs.getDouble("cantitate_produs");
									 String  unitateMasura = rs.getString("unitate_masura");
									 String  dataModificare = rs.getString("data_modificare");
									 String statutProdus = rs.getString("statut_produs");
									 double notaProdus = rs.getDouble("nota_produs");
						    		 newProduct = new Product(id,category,subCategory,codProdus,numeProdus,descriereProdus,pretProdus,
						    				 pretProdusPromotional,cantitateProdus,unitateMasura,dataModificare,statutProdus,notaProdus);
						               }
						      
						     helper.closeConnection(con);
							return newProduct;
							
						}
						
						
						//verify if table Products is Empty
						public int verifyTableProducts() throws SQLException {
							
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
							String selectUser = "SELECT * FROM products";
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

						//Delete Product
						public void deleteProduct(int id) throws SQLException {
							DBHelper helper = new DBHelper();
							Connection con = helper.getConnection();
							String deleteRow = "DELETE FROM products WHERE id=?";
							PreparedStatement ps = con.prepareStatement(deleteRow);
							ps.setInt(1, id);
							ps.executeUpdate();
							helper.closeConnection(con);
						}	
								
   }
