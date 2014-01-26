package com.example.ohmytaxi.activities;

import android.os.Bundle;

import com.example.ohmytaxi.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;





	public class MyStopsActivity extends MapActivity{


		private GoogleMap map = null;
		private int vista = 0;
		private float origen;
		
		
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_map);
	      
	    	
	    	Bundle bundle = getIntent().getExtras();
	        origen = bundle.getFloat("origen");

	        
	        
	    	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    	map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    
	    	LatLng madrid = new LatLng(40.417164,-3.703542);
	    	CameraPosition camPos = new CameraPosition.Builder()
	    	        .target(madrid)   //Centramos el mapa en Madrid
	    	        .zoom(16)         //Establecemos el zoom en 16
	    	        .bearing(0)      //Establecemos la orientaci�n con el norte arriba
	    	        .tilt(20)         //Bajamos el punto de vista de la c�mara 20 grados
	    	        .build();	    	 
	    	CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);	    	 
	    	map.animateCamera(camUpd3);
	    	
	    	
	    }
	 
	    
	    private void mostrarMarcador(double lat, double lng){
	        map.addMarker(new MarkerOptions()
	            .position(new LatLng(lat, lng))
	            .title("Mi ubicaci�n"));
	    }
	    
	    	
		
		
		
		
}