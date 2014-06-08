package com.example.ohmytaxi.location;




public class Place {
	
	private String address;
	private double lat;
	private double lon;


	
	
	public Place(){
		
	}
	
	
	public Place(String address, double lat, double lon){
		this.address= new String (address);
		this.lat = lat;
		this.lon=lon;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLon() {
		return lon;
	}


	public void setLon(double lon) {
		this.lon = lon;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String d) {
       address = d;
    }


}