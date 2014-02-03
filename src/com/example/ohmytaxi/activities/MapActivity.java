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
	    	
	    	Polygon polygon = map.addPolygon(new PolygonOptions() 
	    	   .add(new LatLng(40.482993,-3.674848), new LatLng(40.481067,-3.685362), new LatLng(40.484853,-3.695318), 
	    			   new LatLng(40.479141,-3.716053), new LatLng(40.472547,-3.727898), new LatLng(40.473657,-3.749306),
	    			   new LatLng(40.430452,-3.736302), new LatLng(40.420619,-3.721718), new LatLng(40.401274,-3.720688),
	    			   new LatLng(40.38892,-3.684811), new LatLng(40.395195,-3.673395), new LatLng(40.418266,-3.659662),
	    			   new LatLng(40.443485,-3.660864), new LatLng(40.482993,-3.674848))
	    	   .strokeColor(Color.RED)
	    	   .fillColor(Color.BLUE));
	    	RectF bounds;
	    	
	    	
	    	
	    	List <LatLng> m30 = new ArrayList <LatLng>();
	    	m30.add(new LatLng(40.482993,-3.674848));
	    	m30.add(new LatLng(40.481067,-3.685362));
	    	m30.add(new LatLng(40.484853,-3.695318));
	    	m30.add(new LatLng(40.479141,-3.716053));
	    	m30.add(new LatLng(40.472547,-3.727898));
	    	m30.add(new LatLng(40.473657,-3.749306));
	    	m30.add(new LatLng(40.430452,-3.736302));
	    	m30.add(new LatLng(40.420619,-3.721718));
	    	m30.add(new LatLng(40.401274,-3.720688));
	    	m30.add(new LatLng(40.38892,-3.684811));
	    	m30.add(new LatLng(40.395195,-3.673395));
	    	m30.add(new LatLng(40.418266,-3.659662));
	    	m30.add(new LatLng(40.443485,-3.660864));
	    	m30.add(new LatLng(40.482993,-3.674848));

	    	 
	    	 
	    	 LatLng pointtest = new LatLng(40.482732,-3.670906);
	    	 boolean pruebasino = IsPointInPolygon(m30,pointtest);
	    	 if (pruebasino){
	    		 Log.i("pertenece", "SI");
	    	 }else{
	    		 Log.i("pertenece", "NO");
	    	 }	    	
	    }
	 
	    	    
	    private static boolean IsPointInPolygon(List<LatLng> poly, LatLng point){
	        int i, j;
	        boolean c = false;
	        for (i = 0, j = poly.size() - 1; i < poly.size(); j = i++)
	        {
	            if ((((poly.get(i).latitude <= point.latitude) && (point.latitude < poly.get(j).latitude)) ||
	                ((poly.get(j).latitude <= point.latitude) && (point.latitude < poly.get(i).latitude))) &&
	                (point.longitude < (poly.get(j).longitude - poly.get(i).longitude) * (point.latitude - poly.get(i).latitude) / (poly.get(j).latitude - poly.get(i).latitude) + poly.get(i).longitude))
	                c = !c;
	        }
	        return c;
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
	    

	