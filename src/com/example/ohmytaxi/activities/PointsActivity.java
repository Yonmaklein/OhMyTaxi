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
import com.example.ohmytaxi.location.MyLocationListener;
import com.google.android.gms.maps.model.LatLng;

public class PointsActivity extends Activity {
	
	private EditText etPointA;
	private EditText etPointB;
	private CheckBox checkMyPosition;
	private Button btSearch;
	private LatLng sourceLocation;
	private LatLng destinationLocation;

	
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_points);
	 	etPointA = (EditText) findViewById(R.id.editPointA);
	 	etPointB = (EditText) findViewById (R.id.editPointB);
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
	 					Location myPosition = mylocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		 				etPointA.setText(String.valueOf(myPosition.getLatitude()) + "  " + String.valueOf(myPosition.getLongitude()));
	 				}
	 				else if(mylocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	 						mylocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, mylocListener); // Acceso por red
	 						Location myPosition = mylocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	 						sourceLocation = new LatLng (myPosition.getLatitude(), myPosition.getLongitude());	 		 		
	 		 				String source = getAddressFromLocation(sourceLocation.latitude,sourceLocation.longitude);
	 		 				if (source == null){
	 		 					showToastToUser("No es posible acceder a tu ubicación en este momento");
	 		 					showToastToUser("Introduce tu dirección de origen manualmente");
	 		 					checkMyPosition.setEnabled(true);
	 		 				}else{
	 		 					etPointA.setText(source);
	 		 				}
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
	 	btSearch.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		if (validFields()){
        			destinationLocation = getLocationFromAddress(String.valueOf(etPointB.getText()),false);
        			if (!checkMyPosition.isChecked()){
        				sourceLocation = getLocationFromAddress(etPointA.getText()+"",true);
        			}
        			if (destinationLocation == null){
        				showToastToUser("La ubicación de destino no existe");
        			}else if(sourceLocation == null){
        				      showToastToUser("La ubicación de origen no existe");	
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
			showToastToUser("Introduce una dirección de origen");
			return false;
		}else if ((etPointB.getText().length() == 0) && (etPointA.getText().length() != 0)){
			  		showToastToUser("Introduce una dirección de destino");												        
					return false;
			  }else if((etPointA.getText().length() == 0) && (etPointB.getText().length() == 0)){ 
				  		showToastToUser("Introduce el origen y el destino");
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

	
	
	
	
	private LatLng getLocationFromAddress (String strAddress, boolean origin){	   	 
    	Geocoder coder = new Geocoder(this);
    	List<Address> address;
    	LatLng resultLocation = null;
    	try {
    	    address = coder.getFromLocationName(strAddress,5);
    	    if (address.size()==0) {
    	    	Toast warningMessage = Toast.makeText(getApplicationContext(),
	                    "La dirección "+strAddress+" no existe", Toast.LENGTH_LONG);
    			warningMessage.show();  
    			resultLocation = null;
    	    }
    	    else{    	 	    	
    	    	Address location = address.get(0);
    	    	if (origin){
    	    		resultLocation = new LatLng(location.getLatitude(),location.getLongitude());
    	    		     	    		
    	    	}else{
    	    		resultLocation = new LatLng (location.getLatitude(), location.getLongitude());   	    		
    	    	}
    	    	
    	    }
    	} catch (IOException e) {
    			
    	}
		return resultLocation;
    }

	
	
	public String getAddressFromLocation(double sourceLatitude2, double sourceLongitude2){
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(this, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(sourceLatitude2, sourceLongitude2, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (addresses==null){
			return null;
		}else{
			String address = addresses.get(0).getAddressLine(0);
			String city = addresses.get(0).getAddressLine(1);
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
	
	 
	 
	 	
	


}