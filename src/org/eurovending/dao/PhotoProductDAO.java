package org.eurovending.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import org.eurovending.helper.DBHelper;
import org.eurovending.pojo.PhotoProduct;
import org.eurovending.pojo.Product;



public class PhotoProductDAO {

	
		
		//Create table photo_product
				public void createTablePhotoProduct() throws SQLException {  
					DBHelper helper = new DBHelper();
					Connection con =  helper.getConnection();
					String createTable = "CREATE TABLE IF NOT EXISTS photo_products"+ 
							" (id INTEGER not NULL auto_increment primary key,"
							+ "id_product INTEGER NULL,video_link VARCHAR(150) NULL,"
							+ "photo1 LONGBLOB NULL,photo2 LONGBLOB NULL,"
							+ "photo3 LONGBLOB NULL,photo4 LONGBLOB NULL,"
							+ "photo5 LONGBLOB NULL)";
					      Statement stmt = con.createStatement();
					      
					       stmt.execute(createTable);
					       helper.closeConnection(con);
					       
					}
				
				
				//Insert  into table Photo_Product
				public void insertPhotoProduct(PhotoProduct product) throws SQLException, IOException {
				
					InputStream photo1;
					InputStream photo2;
					InputStream photo3;
					InputStream photo4;
					InputStream photo5;
					
					if(product.getPhoto1().getInputStream()!=null) {
					 photo1 =  product.getPhoto1().getInputStream();
					}
					else {
						photo1=null;
					}
					if(product.getPhoto2().getInputStream()!=null) {
						 photo2 =  product.getPhoto2().getInputStream();
						}
						else {
							photo2=null;
						}
					if(product.getPhoto3().getInputStream()!=null) {
						 photo3 =  product.getPhoto3().getInputStream();
						}
						else {
							photo3=null;
						}
					if(product.getPhoto4().getInputStream()!=null) {
						 photo4 =  product.getPhoto4().getInputStream();
						}
						else {
							photo4=null;
						}
					if(product.getPhoto5().getInputStream()!=null) {
						 photo5 =  product.getPhoto5().getInputStream();
						}
						else {
							photo5=null;
						}
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertProduct = "insert into photo_products"+ 
					"(id_product,video_link,photo1,photo2,photo3,photo4,photo5)"
					 +" value(?,?,?,?,?,?,?)";
					
					PreparedStatement ps = con.prepareStatement(insertProduct);
					ps.setInt(1, product.getIdProdus());
					ps.setString(2, product.getVideoLink());
					ps.setBlob(3, photo1);
					ps.setBlob(4, photo2);
					ps.setBlob(5, photo3);
					ps.setBlob(6, photo4);
					ps.setBlob(7, photo5);
					ps.executeUpdate();
					 helper.closeConnection(con);
				      }
				
				//update table Photo_Product
				public void updatePhotoProduct(PhotoProduct product) throws SQLException, IOException {
					

					InputStream photo1 =  product.getPhoto1().getInputStream();
					InputStream photo2 =  product.getPhoto2().getInputStream();
					InputStream photo3 =  product.getPhoto3().getInputStream();
					InputStream photo4 =  product.getPhoto4().getInputStream();
					InputStream photo5 =  product.getPhoto5().getInputStream();
					
					
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "UPDATE photo_products SET"+ 
							" id_product=?,video_link=?,photo1=?,photo2=?,photo3=?,photo4=?,photo5=? "
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
				
					ps.setInt(1, product.getIdProdus());
					ps.setString(2, product.getVideoLink());
					ps.setBlob(3, photo1);
					ps.setBlob(4, photo2);
					ps.setBlob(5, photo3);
					ps.setBlob(6, photo4);
					ps.setBlob(7, photo5);
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				
				//update in table Photo_Product column id_product
				public void updatePhotoCodProdus(Product product) throws SQLException, IOException {
					
					
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "UPDATE photo_products SET"+ 
							" id_product=?"
							+ " where id_product = '"+product.getId()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setInt(1,product.getId());
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				

				//update in table Photo_Product column video_link
				public void updatePhotoVideoLink(PhotoProduct product) throws SQLException, IOException {
					
					
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "UPDATE photo_products SET"+ 
							" video_link=?"
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setString(1,product.getVideoLink());
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				
				
				//update in table Photo_Product column photo1
				public void updatePhotoProduct1(PhotoProduct product) throws SQLException, IOException {
					
					InputStream photo1 =  product.getPhoto1().getInputStream();
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "UPDATE photo_products SET"+ 
							" photo1=?"
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setBlob(1,photo1);
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				
				//update in table Photo_Product column photo2
				public void updatePhotoProduct2(PhotoProduct product) throws SQLException, IOException {
				
					InputStream photo2 =  product.getPhoto2().getInputStream();
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "UPDATE photo_products SET"+ 
							" photo2=?"
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setBlob(1,photo2);
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				//update in table Photo_Product column photo3
				public void updatePhotoProduct3(PhotoProduct product) throws SQLException, IOException {
					
					InputStream photo3 =  product.getPhoto3().getInputStream();
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					//int id = anunt.getId();
					String insertUser = "UPDATE photo_products SET"+ 
							" photo3=?"
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setBlob(1,photo3);
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				
				//update in table Photo_Product column photo4
				public void updatePhotoProduct4(PhotoProduct product) throws SQLException, IOException {
					
					InputStream photo4 =  product.getPhoto4().getInputStream();
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertUser = "UPDATE photo_products SET"+ 
							" photo4=?"
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setBlob(1,photo4);
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				//update in table Photo_Product column photo5
				public void updatePhotoProduct5(PhotoProduct product) throws SQLException, IOException {
					
					InputStream photo5 =  product.getPhoto5().getInputStream();
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					
					String insertUser = "UPDATE photo_products SET"+ 
							" photo5=?"
							+ " where id_product = '"+product.getIdProdus()+"'";
					PreparedStatement ps = con.prepareStatement(insertUser);
					ps.setBlob(1,photo5);
					 ps.executeUpdate();
					 helper.closeConnection(con);
				        }
				
				
				// get photoProduct by idProduct
				public PhotoProduct getPhotoProduct(int idProd) throws SQLException, IOException
				{
					PhotoProduct productPhoto =null;
					
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String getUser = "SELECT*FROM photo_products where id_product ='"+idProd+"'";
				      Statement stmt = con.createStatement();
				      ResultSet rst = stmt.executeQuery(getUser);
			
				      while(rst.next()) {
				    	     int id = rst.getInt("id");
				    	     int idProduct = rst.getInt("id_product");
				    	     String videoLink = rst.getString("video_link");
				    	     byte[] buffer1 = rst.getBytes("photo1");
				    	     byte[] buffer2 = rst.getBytes("photo2");
				    	     byte[] buffer3 = rst.getBytes("photo3");
				    	     byte[] buffer4 = rst.getBytes("photo4");
				    	     byte[] buffer5 = rst.getBytes("photo5");
				    	     
				             
				                String base64Image1 = Base64.getEncoder().encodeToString(buffer1);			               
				                String base64Image2 = Base64.getEncoder().encodeToString(buffer2); 			                
				                String base64Image3 = Base64.getEncoder().encodeToString(buffer3);
				                String base64Image4 = Base64.getEncoder().encodeToString(buffer4);
				                String base64Image5 = Base64.getEncoder().encodeToString(buffer5);
				                 
				               productPhoto = new PhotoProduct(id,idProduct,videoLink,base64Image1,base64Image2,base64Image3,base64Image4,base64Image5);
				    		 
					    	  }
					
					return productPhoto;
					
				}
				
				// get All photoProduct 
							public ArrayList<PhotoProduct> getAllPhotoProduct() throws SQLException, IOException
							{
								PhotoProduct productPhoto =null;
								ArrayList<PhotoProduct> allPhotoProduct = new ArrayList<PhotoProduct>();	
								DBHelper helper = new DBHelper();
								Connection con = helper.getConnection();
								String getUser = "SELECT*FROM photo_products ";
							      Statement stmt = con.createStatement();
							      ResultSet rst = stmt.executeQuery(getUser);
						
							      while(rst.next()) {
							    	     int id = rst.getInt("id");
							    	     int idProduct = rst.getInt("id_product");
							    	     String videoLink = rst.getString("video_link");
							    	     byte[] buffer1 = rst.getBytes("photo1");
							    	     byte[] buffer2 = rst.getBytes("photo2");
							    	     byte[] buffer3 = rst.getBytes("photo3");
							    	     byte[] buffer4 = rst.getBytes("photo4");
							    	     byte[] buffer5 = rst.getBytes("photo5");
							    	     
							             
							                String base64Image1 = Base64.getEncoder().encodeToString(buffer1);			               
							                String base64Image2 = Base64.getEncoder().encodeToString(buffer2); 			                
							                String base64Image3 = Base64.getEncoder().encodeToString(buffer3);
							                String base64Image4 = Base64.getEncoder().encodeToString(buffer4);
							                String base64Image5 = Base64.getEncoder().encodeToString(buffer5);
							                 
							               productPhoto = new PhotoProduct(id,idProduct,videoLink,base64Image1,base64Image2,base64Image3,base64Image4,base64Image5);
							               allPhotoProduct.add(productPhoto);							    	  }
								
								return allPhotoProduct;
								
							}
				
				
				
				//delete photoProduct
				
				public void deletePhoto(int idProduct) throws SQLException {
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String deleteRow = "DELETE FROM photo_products WHERE id_product ='"+idProduct+"'";
					PreparedStatement ps = con.prepareStatement(deleteRow);
					//ps.setInt(1, id);
					ps.executeUpdate();
					helper.closeConnection(con);
				}
				
				
	}



