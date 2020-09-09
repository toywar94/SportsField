package com.example.sportsfield;

public class DocumentData {

	String userID;
	String d_Store_name;
	String d_Address;
	String d_Date;
	String d_Time;

	public DocumentData(String userID, String d_Store_name, String d_Address, String d_Date, String d_Time) {
		this.userID = userID;
		this.d_Store_name = d_Store_name;
		this.d_Address = d_Address;
		this.d_Date = d_Date;
		this.d_Time = d_Time;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getD_Store_name() {
		return d_Store_name;
	}

	public void setD_Store_name(String d_Store_name) {
		this.d_Store_name = d_Store_name;
	}

	public String getD_Address() {
		return d_Address;
	}

	public void setD_Address(String d_Address) {
		this.d_Address = d_Address;
	}

	public String getD_Date() {
		return d_Date;
	}

	public void setD_Date(String d_Date) {
		this.d_Date = d_Date;
	}

	public String getD_Time() {
		return d_Time;
	}

	public void setD_Time(String d_Time) {
		this.d_Time = d_Time;
	}
}
