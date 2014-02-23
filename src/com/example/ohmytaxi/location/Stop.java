package com.example.ohmytaxi.location;




public class Stop {
	
	private String dire;
	private double lat;
	private double lon;


	public Stop(String dire, double lat, double lon){
		this.dire= new String (dire);
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


	public String getDire() {
		return dire;
	}

	public void setDire(String d) {
       dire = d;
    }


}