package com.example.ohmytaxi.activities;



import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.location.MyLocationListener;

public class PointsActivity extends Activity {
	
	private EditText etPointA;
	private EditText etPointB;
	private TextView tvPointA;
	private TextView tvPointB;
	private CheckBox checkMyPosition;
	private Button   btSearch;
	private double latitude;
	private double longitude;
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_points);
	 	etPointA = (EditText) findViewById(R.id.editPointA);
	 	btSearch = (Button) findViewById(R.id.buttonSearch);
	 	checkMyPosition = (CheckBox) findViewById(R.id.checkMyPosition);
	 	checkMyPosition.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 		@Override
	 	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	 			if (isChecked){
	 				etPointA.setEnabled(false);
	 				LocationManager mylocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 				LocationListener mylocListener = new MyLocationListener();
	 				if (mylocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	 					mylocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mylocListener);  //Acceso por GPS
	 				//	Location myPosition = mylocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		 			/*	double lati = myPosition.getLatitude();
		 				double longi = myPosition.getLongitude();
		 				etPointA.setText(String.valueOf(lati) + "  " + String.valueOf(longi));*/
	 				}
	 				else if(mylocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	 						mylocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, mylocListener); // Acceso por red
	 						Location myPosition = mylocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	 		 				latitude = myPosition.getLatitude();
	 		 				longitude = myPosition.getLongitude();
	 		 				etPointA.setText(String.valueOf(latitude) + "  " + String.valueOf(longitude));
	 					 }
	 				else{
	 					etPointA.setText("No es posible localizar el dispositivo, comprueba la configuración de localización");				
	 				}
	 			}else{
	 	        	etPointA.setText(null);
	 				etPointA.setEnabled(true);
	 	        	
	 	        }
	 	    }
	 	});
	 	
	 	///////////////////////////////////////////
	 	
	 	LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 	String provider = locationManager.getBestProvider(new Criteria(), true);

	 	Location locations = locationManager.getLastKnownLocation(provider);
	 	List<String>  providerList = locationManager.getAllProviders();
	 	if(null!=locations && null!=providerList && providerList.size()>0){                 
	 	double longitude = locations.getLongitude();
	 	double latitude = locations.getLatitude();
	 	Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());                 
	 	try {
	 	    List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
	 	    if(null!=listAddresses&&listAddresses.size()>0){
	 	        String _Location = listAddresses.get(0).getAddressLine(1);
	 	        etPointA.setText(_Location);
	 	    }
	 	} catch (IOException e) {
	 	    e.printStackTrace();
	 	}

	 	}
	 	/////////////////////////////////////////////////
	 	
	 	btSearch.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		showMapScreen();
        	}
        });
	 	
	 	
	 	
	 
	}
	 
	
	public void onBackPressed(){
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
		//
	}
	

	
	 public void showMapScreen() {    // m�todo que llama a la activity que muestra el mapa con nuestra ruta deseada
		 Intent i = new Intent(this, MapActivity.class);  
		 Bundle b = new Bundle ();
		 b.putFloat("lat", (float) latitude);
		 b.putFloat("lon", (float) longitude);
		 b.putString("ubi", etPointA.getText()+"");
		 i.putExtras(b);
		 

		 startActivity(i);
		 finish();	
		 }
	
	


}