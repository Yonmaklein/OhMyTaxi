package com.example.ohmytaxi.activities;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.location.GMapV2GetRouteDirection;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;




public class RouteActivity extends FragmentActivity  {

     
      List<Overlay> mapOverlays;
      GeoPoint point1, point2;
      LocationManager locManager;
      Drawable drawable;
      private Document document;
      private GMapV2GetRouteDirection v2GetRouteDirection;
      private LatLng fromPosition;
      private LatLng toPosition;
      private GoogleMap mGoogleMap;
      private MarkerOptions markerOptions;
      Location location;
      
      
      
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_route);
            v2GetRouteDirection = new GMapV2GetRouteDirection();
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
            mGoogleMap = supportMapFragment.getMap();
            
            
	    	Bundle bundle = getIntent().getExtras();	
	    	double sourceLat = bundle.getDouble("source lat");
		    double sourceLon = bundle.getDouble("source lon");
		    double destinationLat = bundle.getDouble("destination lat");
		    double destinationLon = bundle.getDouble("destination lon");
            fromPosition = new LatLng(sourceLat,sourceLon);
            toPosition = new LatLng(destinationLat,destinationLon);
            

            // Enabling MyLocation in Google Map
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            mGoogleMap.getUiSettings().setCompassEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
            mGoogleMap.setTrafficEnabled(true);
            
            

            CameraPosition camPos = new CameraPosition.Builder()
	        .target(new LatLng(sourceLat,sourceLon))   //Centramos el mapa en Madrid
	        .zoom(16)         //Establecemos el zoom en 16
	        .bearing(0)      //Establecemos la orientación con el norte arriba
	        .tilt(20)         //Bajamos el punto de vista de la cámara 20 grados
	        .build();	    	 
            CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);	    	 
            mGoogleMap.animateCamera(camUpd3);
            
                   
           // mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            //markerOptions = new MarkerOptions();
            GetRouteTask getRoute = new GetRouteTask();
            getRoute.execute();
      }

      
      
      
      private class GetRouteTask extends AsyncTask<String, Void, String> {
           
            private ProgressDialog Dialog;
            String response = "";
            @Override
            protected void onPreExecute() {
                  Dialog = new ProgressDialog(RouteActivity.this);
                  Dialog.setMessage(getString(R.string.loading_route));
                  Dialog.show();
            }

            @Override
            protected String doInBackground(String... urls) {
                  //Get All Route values
                        document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);
                        response = "Success";
                  return response;

            }

            @Override
            protected void onPostExecute(String result) {
                  mGoogleMap.clear();
                  if(response.equalsIgnoreCase("Success")){
                  ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                  PolylineOptions rectLine = new PolylineOptions().width(6).color(
                              Color.RED);

                  for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                  }
                  // Adding route on the map
                  mGoogleMap.addPolyline(rectLine);
               /*   markerOptions.position(toPosition);
                  markerOptions.draggable(true);
                  mGoogleMap.addMarker(markerOptions);*/
                  showMarker(fromPosition.latitude, fromPosition.longitude, true);
                  showMarker(toPosition.latitude, toPosition.longitude, false);

                  }
                 
                  Dialog.dismiss();
            }
            
            
      	  private void showMarker(double lat, double lng, boolean origin){
  	    	if (origin){
  	    		mGoogleMap.addMarker(new MarkerOptions()
  	            	.position(new LatLng(lat, lng))
  	            	.title("Origen"));
  	    	}
  	    	else{
  	    		mGoogleMap.addMarker(new MarkerOptions()
  		            .position(new LatLng(lat, lng))
  		            .title("Destino"));
  	    	}
  	  }
      }
      
      
      
      @Override
      protected void onStop() {
            super.onStop();
            finish();
      }
      
      
      
	  public void onBackPressed(){
			Intent intent = new Intent(getBaseContext().getApplicationContext(), PointsActivity.class);  
			startActivity(intent);
			finish();
			super.onBackPressed();
	  }
      

      
      
}