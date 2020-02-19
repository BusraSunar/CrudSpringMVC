package com.home.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.home.model.User;

public class jdbcDao {
	
	private String dbName;
	private String tableName;
	
	public jdbcDao(String name, String table) {
		this.dbName = name;
		this.tableName = table;
	}
	
	public void deleteData(String id) {
		try{
			String idStr = id;
			
			//connect to database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/"+dbName+"?autoReconnect=true&useSSL=false", "root", "");
			Statement stat=(Statement) conn.createStatement();
			
			//delete the given id from the database
			String sql= "DELETE FROM "+ tableName+ " WHERE id='" +idStr + "'" ;
			stat.executeUpdate(sql);
			
		}catch(Exception e) {}
		
	}
	public void insertData(String name, String email) {
		Connection conn =null;
		PreparedStatement ps = null;
		try{
			//connect to database 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			if(name!=null && email!=null){
				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/"+dbName+"?autoReconnect=true&useSSL=false", "root", "");
				ps = (PreparedStatement) conn.prepareStatement("INSERT INTO "+tableName+"(name, email) VALUES (?,?)");
				ps.setString(1,name);
				ps.setString(2,email);
				ps.executeUpdate();
			}

		}catch(Exception e){}
			
	}
	public void updateData(String newName, String newEmail, String idStr) {
		try {
			//connect the database
			Class.forName("com.mysql.jdbc.Driver");
	        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/"+dbName+"?autoReconnect=true&useSSL=false", "root", "");

			//get the public editId variable to find the variable thats going to be edited
			PreparedStatement stmt=null;
			
  			String update="UPDATE "+tableName+" SET name=?, email=? WHERE id='" +idStr +"'";
  			stmt = (PreparedStatement) con.prepareStatement(update);
  			stmt.setString(1,newName);
  			stmt.setString(2,newEmail);
  			stmt.executeUpdate();

		}catch(Exception e) {}
		
	} 
	public ArrayList<String> getUser(String idStr){
		ArrayList<String> list= new ArrayList<String>();
		try {
			
			//connect to database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/"+dbName+"?autoReconnect=true&useSSL=false", "root", "");
			
			//get the chosen id's attributes
			Statement stat=(Statement) conn.createStatement();
			String sql= "SELECT * FROM "+tableName+" WHERE id='" +idStr + "'" ;
			ResultSet rs = stat.executeQuery(sql);
			String nameTextBox="";
			String emailTextBox="";
			while(rs.next()) {
				nameTextBox= rs.getString("name");
				emailTextBox= rs.getString("email");
				
			}
			list.add(nameTextBox);
			list.add(emailTextBox);
			

		}catch(Exception e) {}
		return list;
		
	}
	public Boolean signIn(String name, String email) {
		Boolean valid=true;
		 try{

			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/"+dbName+"?autoReconnect=true&useSSL=false", "root", "");
	
			 String stmt = "SELECT * From "+ tableName+" where name = ? and email = ?";
			 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(stmt);
			 pstmt.setString(1, name); 
			 pstmt.setString(2, email);
			 ResultSet rs = pstmt.executeQuery();
			 if(!rs.next()){
				 valid = false;
			 }
			 
		 }catch(Exception e) {}
		 
		 return valid;
	}
	public ArrayList<User> tableComponents(String queryName) {

	    ArrayList<User> list=new ArrayList<User>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		
	        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/"+dbName+"?autoReconnect=true&useSSL=false", "root", "");
	        Statement st=(Statement) con.createStatement();
	
		    String sql="";
			if(queryName!=null){
				sql = "SELECT * FROM "+tableName+" WHERE name LIKE '%"+queryName+"%'";
			}else{
				sql ="SELECT * FROM "+tableName;
			}
	        ResultSet rs=st.executeQuery(sql);     
	        
	        while(rs.next()){
	        	User u= new User();
	        	u.setId(rs.getString("id"));
	        	u.setName(rs.getString("name"));
	        	u.setEmail(rs.getString("email"));
	        	list.add(u);
	        }
		}catch(Exception e) {}
		
		return list;
       
       
	}
	
	

}
