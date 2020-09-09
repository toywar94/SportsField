package com.example.sportsfield;

public class ImageData {

	String Store_name;
	String Address;
	String Floor;
	double latitude;
	double longitude;



	public ImageData(String store_name, String address, String floor, double latitude, double longitude) {
		this.Store_name = store_name;
		this.Address = address;
		this.Floor = floor;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	public String getFloor() {
		return Floor;
	}

	public void setFloor(String floor) {
		Floor = floor;
	}


	public String getStore_name() {
		return Store_name;
	}

	public void setStore_name(String store_name) {
		Store_name = store_name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
}
