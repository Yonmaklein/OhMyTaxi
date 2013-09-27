package com.example.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MyLocationListener implements LocationListener{

	
	private double longitude;
	private double latitude;
	
	
	
	public void onCreate(){
//		LocationManager mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	
	
	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		setLatitude(loc.getLatitude());
		setLongitude(loc.getLongitude());
	}


	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}


	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
	
}
