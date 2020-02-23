package com.sanjeev.data;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileUploadDAO {
	
	public int addUser(String firstName,String lastName,InputStream file)throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		int row=0;
		try {
			con = ConnectionFactory.getCon();
			ps = con.prepareStatement("insert user values(?,?,?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			if(file !=null) {
				ps.setBlob(3, file);
			}
			row = ps.executeUpdate();
		}
		finally {
			con.close();
		}
		return row;
	}
	
	public User listUser(String name)throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		User user = null;
		try {
			con = ConnectionFactory.getCon();
			ps = con.prepareStatement("select * from user where first_name=?");
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				Blob photo = rs.getBlob(3);
				byte[] buf = photo.getBytes(1, (int) photo.length());
								
				user = new User(firstName, lastName, buf);
				
				
			}
		}
		finally {
			con.close();
		}
		
		return user;
		
	}
}
