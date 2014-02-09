package com.example.ohmytaxi.location;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;


public class MyLocationListener implements LocationListener{

	
	
	public void onLocationChanged(Location loc){
		/*loc.getLatitude();
		loc.getLongitude();*/
	/*	setLat(loc.getLatitude());
		setLon(loc.getLongitude());*/
		//String coordenadas = "Mis coordenadas son: Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude();
		//Log.i("GPS", coordenadas);
	/*	Log.i("LATITUD", String.valueOf(getLat()));
		Log.i("LONGITUD", String.valueOf(getLon()));*/
		//Toast.makeText( getApplicationContext(),coordenadas,Toast.LENGTH_LONG).show();
	}

	public void onProviderDisabled(String provider){
		//Toast.makeText( getApplicationContext(),â€œGps Desactivadoâ€?,Toast.LENGTH_SHORT ).show();
	}

	
	public void onProviderEnabled(String provider){
		//Toast.makeText( getApplicationContext(),â€œGps Activoâ€?,Toast.LENGTH_SHORT ).show();
	}

	
	public void onStatusChanged(String provider, int status, Bundle extras){
		
		
		
		
	}


}