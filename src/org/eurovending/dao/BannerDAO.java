package org.eurovending.dao;

import java.io.IOException;
import java.io.InputStream;
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
import org.eurovending.pojo.Banner;
import org.springframework.web.multipart.MultipartFile;




	public class BannerDAO {
		
		//Creare Tabela Banner
			public void createTableBanner() throws SQLException {
					DBHelper helper = new DBHelper();
					Connection con = helper.getConnection();
					String insertCategorii = "CREATE TABLE IF NOT EXISTS banner"+ 
							" (id INTEGER not NULL auto_increment primary key,bannerName VARCHAR(250) NULL,link VARCHAR(450) NULL, dataAdaugare DATE NULL,statut VARCHAR(50) NULL,"
							+ "photo MEDIUMBLOB NULL)";			
					Statement stmt = con.createStatement();      
				       stmt.execute(insertCategorii);
				       helper.closeConnection(con);
				       
				}
			
			//Insert  into table Banner
					public int insertBanner(Banner banner) throws SQLException, IOException {
						java.util.Date date=new java.util.Date();
						java.sql.Date sqlDate=new java.sql.Date(date.getTime());
						InputStream photo1 =  banner.getPhoto().getInputStream();
						String statut = "FORBBIDEN";
						Connection conn = null;
					    PreparedStatement stmt = null;
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						conn = helper.getConnection();
						stmt = conn.prepareStatement("set names utf8");
				        stmt.execute();
				        stmt = conn.prepareStatement("set character set utf8");
				        stmt.execute();
						String insertProduct = "insert into banner"+ 
						"(bannerName,link,dataAdaugare,statut,photo)"
						 +" value(?,?,?,?,?)";
						int result = 0;
						PreparedStatement ps = con.prepareStatement(insertProduct);
						ps.setString(1,banner.getBannerName());
						ps.setString(2, banner.getLink());
						ps.setDate(3,sqlDate);
						ps.setString(4,statut);
						ps.setBlob(5,photo1);
						result = ps.executeUpdate();
						//System.out.println("PhotoDao "+banner.getPhoto().getSize());
						 helper.closeConnection(con);
						 return result;
					      }
					

					//selectare listaBanner 
					public ArrayList<Banner>  getListaBanner() throws SQLException, IOException {
						ArrayList<Banner> bannerList = new ArrayList<Banner> ();
						Banner banner= null;
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						String strSql = "select * from banner ";
						Statement stmt;
						stmt= con.createStatement();
						ResultSet rs = stmt.executeQuery(strSql);
						
						while(rs.next()) {
							int id = rs.getInt("id");
							String bannerName = rs.getString("bannerName");
							String link = rs.getNString("link");
							Date dataAdaugare = rs.getDate("dataAdaugare");
							String statut = rs.getString("statut");
						    Blob blob = rs.getBlob("photo"); 
						  byte[] byteArray = blob.getBytes(1, (int)blob.length());
						  String fotoString =( Base64.getEncoder().encodeToString(byteArray));
						     banner = new Banner(id,bannerName,link,dataAdaugare,statut,fotoString);                       
							bannerList.add(banner);
						}
						helper.closeConnection(con);
						return bannerList;
						
						
					}
				
					//selectare Banner dupa id 
					public Banner getBanner(int id) throws SQLException {
						
						Banner banner= null;
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						String strSql = "select*from banner where id =?";
						PreparedStatement stmt = con.prepareStatement(strSql);
						stmt.setInt(1, id);
						ResultSet rs = stmt.executeQuery();
						if(rs.next()) {
							//int id = rs.getInt("id");
							String bannerName = rs.getString("bannerName");
							String link = rs.getNString("link");
							Date dataAdaugare = rs.getDate("dataAdaugare");
							String statut = rs.getString("statut");
						    Blob blob = rs.getBlob("photo");
						    byte[] byteArray = blob.getBytes(1, (int)blob.length());
						    String fotoString =( Base64.getEncoder().encodeToString(byteArray));
						     banner = new Banner(id,bannerName,link,dataAdaugare,statut,fotoString); 
						}
						
						helper.closeConnection(con);
						return banner;	
					}
					
					//update Banner
					public void updateBanner(Banner banner) throws SQLException, IOException {
						
						java.util.Date date=new java.util.Date();
						java.sql.Date sqlDate=new java.sql.Date(date.getTime());
						
						String statut = banner.getStatut();
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						String insertBanner = "UPDATE banner SET bannerName=?,link=?,dataAdaugare=?,statut=? where id ='"+banner.getId()+"'";
						
						int result = 0; 
						
						PreparedStatement ps = con.prepareStatement(insertBanner);
						ps.setString(1,banner.getBannerName());
						ps.setString(2, banner.getLink());
						ps.setDate(3,sqlDate);
						ps.setString(4,statut);
						
						result = ps.executeUpdate();
						 helper.closeConnection(con);
					        }
					
					//update BannerPhoto
					public void updateBannerPhoto(Banner banner,MultipartFile photo) throws SQLException, IOException {
						
						java.util.Date date=new java.util.Date();
						java.sql.Date sqlDate=new java.sql.Date(date.getTime());
						InputStream photo1 =  photo.getInputStream();
						
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						String insertBanner = "UPDATE banner SET dataAdaugare=?,photo=? where id ='"+banner.getId()+"'";
						
						int result = 0; 
						
						PreparedStatement ps = con.prepareStatement(insertBanner);
						ps.setDate(1,sqlDate);
						ps.setBlob(2,photo1);
						result = ps.executeUpdate();
						 helper.closeConnection(con);
					        }
					
					
					//delete banner
					
					public void deleteBanner(int id) throws SQLException {
						DBHelper helper = new DBHelper();
						Connection con = helper.getConnection();
						String deleteRow = "DELETE FROM banner WHERE id=?";
						PreparedStatement ps = con.prepareStatement(deleteRow);
						ps.setInt(1, id);
						ps.executeUpdate();
						helper.closeConnection(con);
					}

	}

