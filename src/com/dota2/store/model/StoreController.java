package com.dota2.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dota2.store.beans.Hero;
import com.dota2.store.beans.Items;
import com.dota2.store.beans.SetDetails;

public class StoreController {

	List<Hero>					hero	= new ArrayList<Hero>();

	private static Connection	conn;

	public List<Hero> getHerosList() {

		try {
			conn = DBConnection.connect();

			String sql = "select * from herodetails";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				hero.add(new Hero(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			ps.close();
			return hero;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

		
	}

	public static List<SetDetails> getSetDetailsList(int id) throws SQLException {

		List<SetDetails> lists = new ArrayList<SetDetails>();

		conn = DBConnection.connect();

		String sql = "select SetID,SetName,SetPrice,SetQuality, setImg from setdetails  where heroID = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
	

		while (rs.next()) {
			
			SetDetails sd = new SetDetails(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4),rs.getString(5));
			//System.out.println(rs.getString(5));
			lists.add(sd);
		}

		ps.close();
		return lists;

	}

	public static List<Items> getItemDetailsList(int setID) throws SQLException {

		List<Items> itemsList = new ArrayList<Items>();

		conn = DBConnection.connect();

		String sql = "select id.ItemID,id.ItemName,id.ItemPrice,id.ItemQuality from itemdetails as id, setdetails rt where rt.setID = id.referenceID and rt.setID= ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, setID);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			itemsList.add(new Items(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
		}

		ps.close();
		return itemsList;
	}

	public static Items getItemsByID(String itemID) throws SQLException {

		Items l = new Items();
		conn = DBConnection.connect();

		String sql = "select ItemName,ItemPrice,ItemQuality from itemdetails where ItemID=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, itemID);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			l.setItemName(rs.getString(1));
			l.setItemPrice(rs.getDouble(2));
			l.setItemQuality(rs.getString(3));
		}
		ps.close();
		return l;
	}


	public static SetDetails getSetDetailsByID(String setID) throws SQLException {
		
		SetDetails s = new SetDetails();
		conn = DBConnection.connect();
		
		String sql = "select SetName,SetPrice,SetQuality from setdetails where SetID=?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, setID);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			s.setSetName(rs.getString(1));
			s.setSetPrice(rs.getDouble(2));
			s.setSetQuality(rs.getString(3));
			s.setSetID(setID);
		}
		ps.close();
		  //System.out.println(s.getSetName());
		return s;
	}
	 public static void setWishByID(int userID, String setID) throws SQLException {
		 
		 conn = DBConnection.connect();
		 String sql = "insert into wishList (userID, setID) values(?,?)";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, userID);
		 ps.setString(2, setID);
		 ps.execute();
			
	 }
	 
	 public static List<SetDetails> getWishList(int userID) throws SQLException {
		    List<SetDetails> l = new ArrayList<SetDetails>();

			conn = DBConnection.connect();

			String sql = "select * from wishList where userID=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String setID = rs.getString("setID");
				//System.out.println("setID" + setID);
				
				SetDetails s = helper(setID);
				
				l.add(s);
			}
			ps.close();
			return l;

	 }
	 public static void deleteWishlist(int userID, String setID) throws SQLException {
		 conn = DBConnection.connect();
		 String sql = "delete from wishList where userID=? and setId=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, userID);
		 ps.setString(2, setID);	
		 ps.execute();
		 ps.close();
			
	 }
	 
	 private static SetDetails helper(String setID) {
		 SetDetails s = new SetDetails();
		 String sql1 = "select SetName,SetPrice,SetQuality from setdetails where SetID=?";
		 try {
			 conn = DBConnection.connect();
			 PreparedStatement ls = conn.prepareStatement(sql1);
			 ls.setString(1, setID);
			 ResultSet as = ls.executeQuery();
			while(as.next()){
				s.setSetName(as.getString(1));
				s.setSetPrice(as.getDouble(2));
				s.setSetQuality(as.getString(3));
				s.setSetID(setID);
			}
			ls.close();
			as.close();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return s;
	 }
}
