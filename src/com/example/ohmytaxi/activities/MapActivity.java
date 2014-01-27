package com.example.ohmytaxi.activities;

import java.io.IOException;
import java.util.List;

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
		
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_map);
	    
	    	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    	map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	    	
	    	
	    	Bundle bundle = getIntent().getExtras();
	    	
	    	sourceLat = bundle.getFloat("source lat");
		    sourceLon = bundle.getFloat("source lon");
		    destinationLat = bundle.getFloat("destination lat");
		    destinationLon = bundle.getFloat("destination lon");
		    
		    Log.i("desti mapact! LAT", String.valueOf(destinationLat));
			Log.i("destino mapact! LON", String.valueOf(destinationLon));
		    
		    
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
	    
	    
	    
	    
	    
	    
	   
	
	}
	    

	