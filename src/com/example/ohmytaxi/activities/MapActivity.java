package com.example.ohmytaxi.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
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
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
   

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
	    	
	    Polygon polygon = map.addPolygon(new PolygonOptions().add( 
	    
	    
	    
	    		
	    		
	    		new LatLng(40.368103,-3.718328),
	    		new LatLng(40.335358,-3.720688),
	    		new LatLng(40.323832,-3.708112),
	    		new LatLng(40.332893,-3.647026),
	    		new LatLng(40.408953,-3.51711),
	    		new LatLng(40.41324,-3.574613),
	    		new LatLng(40.427292,-3.582313),
	    		new LatLng(40.438949,-3.571578),
	    		new LatLng(40.448265,-3.529888),
	    		new LatLng(40.500371,-3.55147),
	    		new LatLng(40.511439,-3.654169),
	    		new LatLng(40.525828,-3.672761),
	    		new LatLng(40.528925,-3.773798),
	    		new LatLng(40.475483,-3.832323),
	    		new LatLng(40.46246,-3.798535),
	    		new LatLng(40.444329,-3.78476),
	    		new LatLng(40.44486,-3.767408),
	    		new LatLng(40.42911,-3.767772),
	    		new LatLng(40.416199,-3.777384),
	    		new LatLng(40.402561,-3.771807),
	    		new LatLng(40.391242,-3.792273),
	    		new LatLng(40.395534,-3.830254),
	    		new LatLng(40.365449,-3.806599),
	    		new LatLng(40.360622,-3.785414),
	    		new LatLng(40.357927,-3.754504),
	    		new LatLng(40.368103,-3.718328))
	    		
	    		
	    		
	    		
	 
	
 	   .strokeColor(Color.RED)
 	   .fillColor(Color.BLUE));
 	RectF bounds;
 	
 }

	    
	    
	    
	    /*	   .add(new LatLng(40.482993,-3.674848), new LatLng(40.481067,-3.685362), new LatLng(40.484853,-3.695318), 
	    			   new LatLng(40.479141,-3.716053), new LatLng(40.472547,-3.727898), new LatLng(40.473657,-3.749306),
	    			   new LatLng(40.430452,-3.736302), new LatLng(40.420619,-3.721718), new LatLng(40.401274,-3.720688),
	    			   new LatLng(40.38892,-3.684811), new LatLng(40.395195,-3.673395), new LatLng(40.418266,-3.659662),
	    			   new LatLng(40.443485,-3.660864), new LatLng(40.482993,-3.674848))*/

	    
	    
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
	    

	