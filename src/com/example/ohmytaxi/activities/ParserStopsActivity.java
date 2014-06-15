package com.example.ohmytaxi.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.location.Place;
import com.google.android.gms.maps.model.LatLng;


public class ParserStopsActivity extends Activity implements LocationListener{
    
	private List <Place> allStops; 
	private LocationManager myLocManager;
	private EditText etPointStop;
	private LatLng sourceToStop;
	private CheckBox checkMyPositionToStop;
	private Button btSearchStops;
	private String sourceAddress;
	

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser_stops);
	 	checkMyPositionToStop = (CheckBox) findViewById(R.id.checkMyPositionToStop); 	 	
        etPointStop= (EditText) findViewById (R.id.editPointToStop);
        btSearchStops = (Button) findViewById(R.id.buttonShowStops);
        allStops = parse();            
        myLocManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);	 	
	 	checkMyPositionToStop.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 		@Override
	 	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	 			if (isChecked){
	 				etPointStop.setEnabled(false);	 			
	 				LocationManager myLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	 				if (myLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	 					showToastToUser(getResources().getString(R.string.obtaining_location));
	 					getLocation(true);	// Acceso al GPS		 						 						 					
	 				}
	 				else if(myLocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){	 					
	 							if (isNetworkAvailable()){
	 								showToastToUser(getResources().getString(R.string.obtaining_location));
	 								getLocation(false); // Acceso por red	 		
	 							}else{
	 								showToastToUser(getResources().getString(R.string.network_not_available));
	 								checkMyPositionToStop.setChecked(false);
	 								etPointStop.setEnabled(true);
	 							}				
	 					 }else{
	 						 showToastToUser(getResources().getString(R.string.check_conf_location));				
	 					 }
	 			}else{
	 	        	etPointStop.setText(null);
	 				etPointStop.setEnabled(true);	
	 				removeUpdates();
	 	        }
	 	    }
	 	});	 	
	 	btSearchStops.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		if (!isNetworkAvailable()){   
        		 	 showToastToUser(getResources().getString(R.string.network_not_available));
        		}else if (validField()){
        			  	   sourceToStop = getLocationFromAddress(etPointStop.getText()+"");  
        			  	   Log.i("JUSTO ANTES DE PETAR", "PETAAAAAR");
        			  	   //Log.i("SOURCETOSTOP", sourceToStop.toString());
        			  	   if (sourceToStop==null){
        			  		   Log.i("ES NULL", "MENSAJEEE");
        			  		   showToastToUser(getResources().getString(R.string.origin_not_exists));
        			  		   //Log.i("PETA!", "!!¿?");
        			  	   }else{
        			  		   Log.i("NO ES NULL", sourceToStop.toString());
        			  		   sourceAddress = getAddressFromLocation(sourceToStop);
        			  		   if (sourceAddress==null){
            			  		   showToastToUser(getResources().getString(R.string.origin_not_exists));
        			  		   }else{
        			  			   showStopsMap();
        			  		   }
        			  	   }        			  	   
        			  }        			  	  
        	}
	 	});
    }
 
	
 
 
	public String getAddressFromLocation(LatLng source){
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(this, Locale.ENGLISH);
		try {
			addresses = geocoder.getFromLocation(source.latitude, source.longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (addresses.size()==0){
			return null;
		}else{
			String address = addresses.get(0).getAddressLine(0);
			String city = addresses.get(0).getAddressLine(1);
			//sourceCommunity = addresses.get(0).getAddressLine(2);
//			Log.i("COMUNIDAD", addresses.get(0).getAddressLine(2));
			//String country = addresses.get(0).getAddressLine(2);
			return address + " " + city;		
		}	
	}
	
    
	public Place closerStop(LatLng source){
		double minDistance = 100000000.0;
		Place closerStop = new Place();
		Location originLoc = new Location ("origen");
	  	originLoc.setLatitude(sourceToStop.latitude);
	  	originLoc.setLongitude(sourceToStop.longitude);	  	
	  	Location stopLoc = new Location("destino");
        for (int i =0; i<allStops.size(); i++){
        	stopLoc.setLatitude(allStops.get(i).getLat());
        	stopLoc.setLongitude(allStops.get(i).getLon());        	
        	if (originLoc.distanceTo(stopLoc)< minDistance){
        		minDistance = originLoc.distanceTo(stopLoc);
        		Log.i("DIRECCION STOP", allStops.get(i).getAddress());
        		closerStop = new Place(new String(allStops.get(i).getAddress()), allStops.get(i).getLat(),allStops.get(i).getLon());
        	}
        }         
        
		return closerStop;
		
	}

	
    
    private LatLng getLocationFromAddress (String strAddress){	   	 
    	Geocoder coder = new Geocoder(this, Locale.ENGLISH);
    	List<Address> address;
    	LatLng resultLocation = null;
    	Log.i("Locationfromaddress", strAddress);
    	try {
    	    address = coder.getFromLocationName(strAddress,5);
    	    if (address.size()==0) {    	    	
    	    	//showToastToUser("La dirección "+strAddress+" no existe");  
    	    	Log.i("da","NULL");
    			resultLocation = null;
    	    }else{    	 	    	
    	    	Address location = address.get(0);
    	    	//Log.i("getLocationToAddress", String.valueOf(resultLocation.latitude +"  "+  String.valueOf(resultLocation.longitude )));
    	    	resultLocation = new LatLng (location.getLatitude(), location.getLongitude());   
    	    }
    	} catch (IOException e) {    			
    	}
    	//Log.i("RETURN", "NULL");
		return resultLocation;
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
		//showToastToUser(Double.toString(location.getLatitude())+"  "+Double.toString(location.getLongitude()));
		LatLng source = new LatLng(location.getLatitude(), location.getLongitude());
		if(source!=null){
			etPointStop.setText(getAddressFromLocation(source));
			etPointStop.setEnabled(false);
			myLocManager.removeUpdates(this);		
			//sourceLocation = new LatLng(location.getLatitude(),location.getLongitude());
		}
	}


	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	
	
	public boolean validField (){		
        String a = new String (etPointStop.getText()+"");
		if(a.length() == 0){
	  		showToastToUser(getResources().getString(R.string.introduce_origin_address));
			return false;
		}else if((a.contains("@")||(a.contains("#")||(a.contains("*")||(a.contains("/")||(a.contains("%")||
				(a.contains("+")||(a.contains("?")||(a.contains("¿")||(a.contains("{")||(a.contains("}")||
						(a.contains("€")||(a.contains("$")))))))))))))){
				showToastToUser(getResources().getString(R.string.origin_not_exists));
				return false;
				}else{	  	
					return true;
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
	

	
	public void showToastToUser(String message){
		Toast warningMessage = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		warningMessage.show(); 
	}
	
	
	
	public void onBackPressed(){
		removeUpdates();
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
	}

	
	private void removeUpdates() {
		// TODO Auto-generated method stub
		myLocManager.removeUpdates(this);
	}
	
	
	public List<Place> parse(){
	  	XmlResourceParser parser = getResources().getXml(R.xml.stops);   
	  	List<Place> stops = null;
	    try{
	        int evento = parser.getEventType();
	        Place currentStop = null;	 
	        while (evento != XmlPullParser.END_DOCUMENT){
	            String etiqueta = null;	 
	            switch (evento){
	                case XmlPullParser.START_DOCUMENT:
	                //	Log.i("Empieza", " el documento");
	                	stops = new ArrayList<Place>();
	                	break;	 
	                case XmlPullParser.START_TAG:
	                   // Log.i("Empieza", " taaaaag");
	                    etiqueta = parser.getName();
	                    if (etiqueta.equals("stop")){
	                      //  Log.i("encuentra stop", " STOP");
	                        currentStop = new Place();
	                    }else if (currentStop != null){
	                            	if (etiqueta.equals("address")){
	                            		//Log.i("encuentra", " ADDRESS");
	                            		currentStop.setAddress(parser.nextText());
	                            	}else if (etiqueta.equals("lat")){
	                            			//	Log.i("encuentra", " LATITUD");
	                            				currentStop.setLat(Double.parseDouble(parser.nextText()));
	                            		  }else if (etiqueta.equals("lng")){
	                            			  	//	Log.i("encuentra", " LONGITUD");
	                            			  		currentStop.setLon(Double.parseDouble(parser.nextText()));
	                            		  		}
	                          }
	                    break;	
	                case XmlPullParser.END_TAG:	 
	                    etiqueta = parser.getName();	 
	                    if (etiqueta.equals("stop") && currentStop != null){
	                        stops.add(currentStop);
	                 //       Log.i("Añade parada al array", " PARADA AÑADIDA");
	                    }
	                    break;
	                }	 
	                evento = parser.next();
	            }
	        }
	        catch (Exception ex){
	            throw new RuntimeException(ex);
	        }	 
	    return stops;
	}
	
	
	
	private void showStopsMap() {
		 Intent i = new Intent(this, StopsInMapActivity.class);  
		 Log.i("SourceToStop ES:", sourceToStop.toString());
		 Place stop = closerStop(sourceToStop);
		 if (stop==null){
			 Log.i("cercana", "CERCANA");

		 }else{
			 Log.i("stop lat", String.valueOf(stop.getLat()));
			 Log.i("stop lon", String.valueOf(stop.getLon()));
			 Log.i("cercana", stop.getAddress().toString());
		 }
		 Bundle b = new Bundle ();
		 b.putBoolean("write", true);
		 b.putDouble("source lat", sourceToStop.latitude);
		 b.putDouble("source lon", sourceToStop.longitude); 
		 b.putString("source address",  sourceAddress);
		 b.putDouble("stop lat", stop.getLat());
		 b.putDouble("stop lon", stop.getLon()); 
		 b.putString("stop address",  stop.getAddress());
	/*	 Log.i("stop lat let", String.valueOf(stop.getLat()));
		 Log.i("stop lon let", String.valueOf(stop.getLon()));
		 Log.i("stop address let", stop.getDire());
		 Log.i("source lat let", String.valueOf(sourceToStop.latitude));
		 Log.i("source lon let", String.valueOf(sourceToStop.longitude));
		 Log.i("source address let", getAddressFromLocation(sourceToStop));
*/
		/* Log.i("desti! LAT", String.valueOf(destinationLocation.latitude));
		 Log.i("desti! LON", String.valueOf(destinationLocation.longitude));	*/	 
		 i.putExtras(b);
		 
		 startActivity(i);
		 finish();	
		
	}	 		
	
	
	 
}