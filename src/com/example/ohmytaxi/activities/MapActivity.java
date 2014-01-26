package com.example.ohmytaxi.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.example.ohmytaxi.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

	public class MapActivity extends android.support.v4.app.FragmentActivity {

		//
		private GoogleMap map = null;
		private int vista = 0;
		private float originLat;
		private float originLon;
		private float destinationLat;
		private float destinationLon;
		
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_map);
	      	    	
	    	Bundle bundle = getIntent().getExtras();
	    	originLat = bundle.getFloat("origin lat");
	    	originLon = bundle.getFloat("origin lon");
	    	String destinationAddress = bundle.getString("destination");
	    	//Log.i("leido de etB", destinationAddress);
	    	
	    	  
	    	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    	map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    
	    	
	    	getLocationFromAddress(destinationAddress);
	    	
	    	showMarker(originLat, originLon);
	    	
	    	LatLng madrid = new LatLng(originLat,originLon);
	    	CameraPosition camPos = new CameraPosition.Builder()
	    	        .target(madrid)   //Centramos el mapa en Madrid
	    	        .zoom(16)         //Establecemos el zoom en 16
	    	        .bearing(0)      //Establecemos la orientación con el norte arriba
	    	        .tilt(20)         //Bajamos el punto de vista de la cámara 20 grados
	    	        .build();	    	 
	    	CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);	    	 
	    	map.animateCamera(camUpd3);
	    	
	    	
	    }
	 
	    
	    
		public void onBackPressed(){
			Intent intent = new Intent(getBaseContext().getApplicationContext(), PointsActivity.class);  
			startActivity(intent);
			finish();
			super.onBackPressed();
		}
	    
	    
		
	    private void showMarker(double lat, double lng){
	        map.addMarker(new MarkerOptions()
	            .position(new LatLng(lat, lng))
	            .title("Origen"));
	    }
	    
	    
	    
	    private void getLocationFromAddress (String strAddress){
	 
	    	Geocoder coder = new Geocoder(this);
	    	List<Address> address;

	    	try {
	    	    address = coder.getFromLocationName(strAddress,5);
	    	    if (address == null) {
	    	       // return null;
	    	    }
	    	    Address location = address.get(0);
	    	    showMarker(location.getLatitude(),location.getLongitude());
	    	    Log.i("latitud destino", String.valueOf(location.getLatitude()));
	    	    Log.i("Longitud destino", String.valueOf(location.getLongitude()));
	    	    
	    	} catch (IOException e) {
	    		
	    		
	        }
	    
	    	

	    }

	
	}
	    

	