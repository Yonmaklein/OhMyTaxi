package com.example.ohmytaxi.activities;



import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
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
import android.widget.Toast;

import com.example.ohmytaxi.R;
import com.google.android.gms.maps.model.LatLng;

public class PointsActivity extends Activity implements LocationListener {
	
	private EditText etPointA;
	private EditText etPointB;
	private CheckBox checkMyPosition;
	private Button btSearch;
	private LatLng sourceLocation;
	private LatLng destinationLocation;
	private String sourceCommunity;
	private LocationManager myLocManager;
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_points);
	 	etPointA = (EditText) findViewById(R.id.editPointA);
	 	etPointB = (EditText) findViewById (R.id.editPointB);
	 	btSearch = (Button) findViewById(R.id.buttonSearch);
	 	checkMyPosition = (CheckBox) findViewById(R.id.checkMyPosition); 
	 	
	 	myLocManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);
	 	
	 	/*if (myLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
				getLocation(true);
				//Location myPosition = mylocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				//etPointA.setText(String.valueOf(myPosition.getLatitude()) + "  " + String.valueOf(myPosition.getLongitude()));
				
				

	 	}*/
	 	checkMyPosition.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 		@Override
	 	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	 			if (isChecked){
	 				etPointA.setEnabled(false);	 			
	 				LocationManager myLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 				if (myLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	 					showToastToUser("Obteniendo ubicación");
	 					getLocation(true);	// Acceso al GPS		 						 						 					
	 				}
	 				else if(myLocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	 						showToastToUser("Obteniendo ubicación");
	 						getLocation(false); // Acceso por red
	 						//Location myPosition = myLocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);	 							 		 		
	 		 				/*if (myPosition == null){
	 		 					showToastToUser(getResources().getString(R.string.not_able_to_locate));
	 		 					showToastToUser(getResources().getString(R.string.introduce_address_manually));
	 		 					checkMyPosition.setChecked(false);
	 		 					etPointA.setEnabled(true);
	 		 				}else{ 
	 		 					sourceLocation = new LatLng (myPosition.getLatitude(), myPosition.getLongitude());
	 		 					String source = getAddressFromLocation(sourceLocation.latitude,sourceLocation.longitude);
	 		 					if (!sourceCommunity.contains("Madrid")){
	 		 					  	  showToastToUser(getResources().getString(R.string.origin_must_madrid));
	 		 						  etPointA.setText("");
	 		 						  etPointA.setEnabled(true);
	 		 						  checkMyPosition.setChecked(false);	 		 						
	 		 					}else{
	 		 						  etPointA.setText(source);
	 		 					}	 	
	 		 				}*/
	 					 }else{
	 						 showToastToUser(getResources().getString(R.string.check_conf_location));				
	 				}
	 			}else{
	 	        	//etPointA.setText(null);
	 				etPointA.setEnabled(true);	 	        	
	 	        }
	 	    }
	 	});	 	
	 	btSearch.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		if (validFields()){
        			destinationLocation = getLocationFromAddress(String.valueOf(etPointB.getText()));
        			if (!checkMyPosition.isChecked()){
        				sourceLocation = getLocationFromAddress(etPointA.getText()+"");        			
        			}
        			if (destinationLocation == null){
        				showToastToUser(getResources().getString(R.string.destination_not_exists));
        			}else if(sourceLocation == null){
        						showToastToUser(getResources().getString(R.string.origin_not_exists));
        				  }else{
        					  Log.i("sourcelocationLAT", String.valueOf(sourceLocation.latitude));
        					  Log.i("sourcelocationLONG", String.valueOf(sourceLocation.longitude));
        					  Log.i("destinationlocationLAT", String.valueOf(destinationLocation.latitude));
        					  Log.i("destinationlocationLONG", String.valueOf(destinationLocation.longitude));
        					  showMapScreen(sourceLocation, destinationLocation);
        				  }
        		}

        	}	 		
	 	});
	 	
	}
	 



	public boolean validFields (){		
		if ((etPointA.getText().length() == 0) && (etPointB.getText().length() != 0)){
			showToastToUser(getResources().getString(R.string.introduce_origin_address));
			return false;
		}else if ((etPointB.getText().length() == 0) && (etPointA.getText().length() != 0)){
			  		showToastToUser(getResources().getString(R.string.introduce_destination_address));												        
					return false;
			  }else if((etPointA.getText().length() == 0) && (etPointB.getText().length() == 0)){ 
				  		showToastToUser(getResources().getString(R.string.introduce_origin_destination));
						return false;
			  		}else{
			  			return true;
			  		}		
	}

	
	
	
	public void showToastToUser(String message){
		Toast warningMessage = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		warningMessage.show(); 
	}
	

	
	public void onBackPressed(){
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
	}

			
	
	private LatLng getLocationFromAddress (String strAddress){	   	 
    	Geocoder coder = new Geocoder(this);
    	List<Address> address;
    	LatLng resultLocation = null;
    	try {
    	    address = coder.getFromLocationName(strAddress,5);
			Log.i("DEVUELVE NO nulo address", address.toString());
    	    if (address.size()==0) {    	    	
    	    	showToastToUser("La dirección "+strAddress+" no existe");    	    	
    			resultLocation = null;
    	    }else{    	 	    	
    	    	Address location = address.get(0);
    			Log.i("DEVUELVE NO nulo location", location.toString());
    	    	resultLocation = new LatLng (location.getLatitude(), location.getLongitude());   
    			Log.i("DEVUELVE NO nulo", resultLocation.toString());

    	    }
    	} catch (IOException e) {    			
    	}
		return resultLocation;
    }

	
	
	public String getAddressFromLocation(LatLng source){
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(this, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(source.latitude, source.longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (addresses==null){
			return null;
		}else{
			String address = addresses.get(0).getAddressLine(0);
			String city = addresses.get(0).getAddressLine(1);
			sourceCommunity = addresses.get(0).getAddressLine(2);
			Log.i("COMUNIDAD", addresses.get(0).getAddressLine(2));
			//String country = addresses.get(0).getAddressLine(2);
			return address + " " + city;		
		}	
	}
	
	
	
	
	public void showMapScreen(LatLng sourceLocation, LatLng destinationLocation) {    // método que llama a la activity que muestra el mapa con nuestra ruta deseada
		 Intent i = new Intent(this, RouteActivity.class);  
		 Bundle b = new Bundle ();
		 b.putDouble("source lat", sourceLocation.latitude);
		 b.putDouble("source lon", sourceLocation.longitude); 
		 b.putDouble("destination lat", destinationLocation.latitude);
		 b.putDouble("destination lon", destinationLocation.longitude); 
		 Log.i("desti! LAT", String.valueOf(destinationLocation.latitude));
		 Log.i("desti! LON", String.valueOf(destinationLocation.longitude));
		 i.putExtras(b);
		 startActivity(i);
		 finish();	
	}


	
	public void getLocation(boolean gps){
		if (gps){
			myLocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 100, 0, this);  //Acceso por GPS
		}else{
			myLocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 100, 0, this);  //Acceso por GPS
		}		
	}
	


	@Override
	public void onLocationChanged(Location location) {
		showToastToUser(Double.toString(location.getLatitude())+"  "+Double.toString(location.getLongitude()));
		LatLng source = new LatLng(location.getLatitude(), location.getLongitude());
		if(source!=null){
			etPointA.setText(getAddressFromLocation(source));
			etPointA.setEnabled(false);
			myLocManager.removeUpdates(this);		
			sourceLocation = new LatLng(location.getLatitude(),location.getLongitude());
		}
	}




	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
		
	}




	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	 

	   
	
	 	
	


}