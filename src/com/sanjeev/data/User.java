package com.sanjeev.data;

import java.io.InputStream;
import java.util.Base64;

public class User {
	private String firstName;
	private String lastName;
	private byte[] photo;
	private String base64Image;
	
	
	
	public String getBase64Image() {
		this.base64Image = Base64.getEncoder().encodeToString(this.photo);
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public User() {
		
	}
	
	public User(String firstName, String lastName, byte[] photo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	

}
