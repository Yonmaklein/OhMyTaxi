package com.example.ohmytaxi.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.ohmytaxi.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
   

	public class StopsInMapActivity extends android.support.v4.app.FragmentActivity {

		private GoogleMap map = null;
		private LatLng source;		
		private String sourceAddress;
		private LatLng stopLatLng;
		private String stopAddress;
		private Button btBack;
		private Button btGuideme;
	
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_map);
	    
	    	map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    	map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

	    	btGuideme  = (Button) findViewById(R.id.buttonGuide);
	    	btBack  = (Button) findViewById(R.id.buttonBacktoStops);
	    	
	    	btBack.setOnClickListener(new OnClickListener() {
	         	public void onClick(View view){
	         		previousScreen();
	         	}
	         });
	    	
	    	btGuideme.setOnClickListener(new OnClickListener() {
	         	public void onClick(View view){
	         		guidemeToStop();
	         	}
	         });
	    	
	    	Bundle bundle = getIntent().getExtras();	    	
	    	source = new LatLng (bundle.getDouble("source lat"), bundle.getDouble("source lon"));		    
		    sourceAddress = new String(bundle.getString("source address"));		    
		    stopLatLng = new LatLng (bundle.getDouble("stop lat"), bundle.getDouble("stop lon"));		   
		    stopAddress = new String (bundle.getString("stop address"));

	    	showMarker(false);

	    	CameraPosition camPos = new CameraPosition.Builder()
	    	        .target(stopLatLng)   //Centramos el mapa en la parada
	    	        .zoom(16)         //Establecemos el zoom en 16
	    	        .bearing(0)      //Establecemos la orientación con el norte arriba
	    	        .tilt(20)         //Bajamos el punto de vista de la cámara 20 grados
	    	        .build();	    	 
	    	CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);	    	 
	    	map.animateCamera(camUpd3);
	    	map.getUiSettings().setZoomControlsEnabled(false);
 }

	    
	    
	    
	    public void guidemeToStop(){
	    	/*String uri = "http://maps.google.com/maps?saddr="+String.valueOf(source.latitude)+","
	    					+String.valueOf(source.longitude)+"&daddr="
	    					+String.valueOf(stopLatLng.latitude)
	    					+","+String.valueOf(stopLatLng.longitude);
			Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)); 
			i.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"); 
			startActivity(i);*/
	    	Intent intent = new Intent( Intent.ACTION_VIEW, 
	                Uri.parse("http://maps.google.com/maps?saddr="+ String.valueOf(source.latitude)+","
					+String.valueOf(source.longitude)+"&daddr="
					+String.valueOf(stopLatLng.latitude)+","+String.valueOf(stopLatLng.longitude)+"&hl=zh&t=m&dirflg=w")); 
	        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
	        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	        startActivity(intent);
	    }
	    

		public void onBackPressed(){
			previousScreen();
		}
	    	    
		public void previousScreen(){
			Intent intent = new Intent(getBaseContext().getApplicationContext(), ParserStopsActivity.class);  
			startActivity(intent);
			finish();
			super.onBackPressed();
		}
		
		
	    private void showMarker(boolean origin){
	    	if (origin){
	    		map.addMarker(new MarkerOptions()
	            	.position(source)
	            	.title(sourceAddress));
	    	}
	    	else{
	    		 map.addMarker(new MarkerOptions()
		            .position(stopLatLng)
		            .title(stopAddress));
	    	}
	    }
	    

	}
	    

	