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
		private float sourceLat;
		private float sourceLon;
		private float destinationLat;
		private float destinationLon;
		private String sourceAddress;
		
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_map);
	    
	    	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    	map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	    	
	    	
	    	Bundle bundle = getIntent().getExtras();
	    	
	    	boolean checked = bundle.getBoolean("checked");
	    	if (checked){
	    		sourceLat = bundle.getFloat("origin lat");
		    	sourceLon = bundle.getFloat("origin lon");
		    	Log.i("checkeado", "TRUE");
	    	}
	    	else{
	    		Log.i("checkeado", "FALSE");
	    		sourceAddress = bundle.getString("source address");
	    		Log.i("source", sourceAddress);
	    		getLocationFromAddress(sourceAddress, true);
	    	}
	    	String destinationAddress = bundle.getString("destination address");
	    	getLocationFromAddress(destinationAddress, false);
	    	
	    	showMarker(sourceLat, sourceLon, true);
	    	showMarker(destinationLat, destinationLon, false);

	    	LatLng madrid = new LatLng(sourceLat,sourceLon);
	    	CameraPosition camPos = new CameraPosition.Builder()
	    	        .target(madrid)   //Centramos el mapa en Madrid
	    	        .zoom(16)         //Establecemos el zoom en 16
	    	        .bearing(0)      //Establecemos la orientación con el norte arriba
	    	        .tilt(20)         //Bajamos el punto de vista de la cámara 20 grados
	    	        .build();	    	 
	    	CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);	    	 
	    	map.animateCamera(camUpd3);
	    	map.getUiSettings().setZoomControlsEnabled(false);
	    }
	 
	    
	    
		public void onBackPressed(){
			Intent intent = new Intent(getBaseContext().getApplicationContext(), PointsActivity.class);  
			startActivity(intent);
			finish();
			super.onBackPressed();
		}
	    
	    
		
	    private void showMarker(double lat, double lng, boolean origin){
	    	if (origin){
	    		map.addMarker(new MarkerOptions()
	            	.position(new LatLng(lat, lng))
	            	.title("Origen"));
	    	}
	    	else{
	    		 map.addMarker(new MarkerOptions()
		            .position(new LatLng(lat, lng))
		            .title("Destino"));
	    	}
	    }
	    
	    
	    
	    private void getLocationFromAddress (String strAddress, boolean origin){
	 
	    	Geocoder coder = new Geocoder(this);
	    	List<Address> address;

	    	try {
	    	    address = coder.getFromLocationName(strAddress,5);
	    	    if (address == null) {
	    	       // return null;
	    	    }
	    	    Address location = address.get(0);
	    	    if (origin){
	    	    	sourceLat = (float) location.getLatitude();
	    	        sourceLon = (float) location.getLongitude();
	    	    	Log.i("source", "source");
	    	    }else{
	    	    	destinationLat = (float) location.getLatitude();
	    	    	destinationLon = (float) location.getLongitude();
	    	    	Log.i("destination","destination");
	    	    }
	    	    Log.i("Latitud destino", String.valueOf(location.getLatitude()));
	    	    Log.i("Longitud destino", String.valueOf(location.getLongitude()));
	    	    
	    	} catch (IOException e) {
	    		
	    		
	    }
	    
	    	

	    }

	
	}
	    

	