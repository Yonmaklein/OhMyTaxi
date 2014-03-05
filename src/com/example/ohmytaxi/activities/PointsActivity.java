package com.example.ohmytaxi.activities;



import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ohmytaxi.R;
import com.google.android.gms.maps.model.LatLng;

public class PointsActivity extends Activity implements LocationListener {
	
	private EditText etPointA;
	private EditText etPointB;
	private CheckBox checkMyPosition;
	private CheckBox checkNow;
	private Button btSearch;
	private ImageButton imageDate;
	private ImageButton imageTime;
	private LatLng sourceLocation;
	private LatLng destinationLocation;
	private String sourceCommunity;
	private String sourceAddress;
	private String destinationAddress;
	private LocationManager myLocManager;
	
	private Calendar cal;
	private int mYear;
	private int mMonth;
	private int mDay; 
	private int mHour;
	private int mMinute;
	private int mDayOfWeek;
	private boolean dateSelected;
	private boolean timeSelected;

	
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 2;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_prueba);
	    cal = Calendar.getInstance();
	    

	    mMonth = cal.get(Calendar.MONTH);
	    mYear = cal.get(Calendar.YEAR);
	    mDay = cal.get(Calendar.DAY_OF_MONTH); 
	    mHour = cal.get(Calendar.HOUR);
	    mMinute = cal.get(Calendar.MINUTE);
	    
	 	etPointA = (EditText) findViewById(R.id.etSource);
	 	etPointB = (EditText) findViewById (R.id.etDestination);
	 	btSearch = (Button) findViewById(R.id.btCalculate);
	 	checkMyPosition = (CheckBox) findViewById(R.id.checkMyPosition); 	 
	 	checkNow = (CheckBox) findViewById(R.id.checkNow); 	 	
	 	imageDate = (ImageButton) findViewById(R.id.imageDate); 
	 	imageTime = (ImageButton) findViewById(R.id.imageTime);
	 	myLocManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);	 	
	 	imageDate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);				
			}	 		
	 	});
	 	imageTime.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			}
	 		
	 	});
	
		checkNow.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 		@Override
	 	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	 			if (isChecked){
	 				imageDate.setEnabled(false);
	 				imageTime.setEnabled(false);
	 				cal = Calendar.getInstance(); 
	 				cal.setFirstDayOfWeek(Calendar.MONDAY);	
	 				mHour = cal.get(Calendar.HOUR);
	 				mMinute = cal.get(Calendar.MINUTE);
	 				mDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	 				mDay = cal.get(Calendar.DAY_OF_MONTH);
	 				mMonth = cal.get(Calendar.MONTH);
	 				dateSelected = true;
	 				timeSelected = true;
	 			}else{
	 				imageDate.setEnabled(true);
	 				imageTime.setEnabled(true);
	 				dateSelected = false;
	 				timeSelected = false;
	 			}
	 		}
	 	});
	
	 	checkMyPosition.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	 		@Override
	 	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	 			if (isChecked){
	 				etPointA.setEnabled(false);	 			
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
	 							checkMyPosition.setChecked(false);
	 							etPointA.setEnabled(true);
	 						}				
	 					 }else{
	 						 showToastToUser(getResources().getString(R.string.check_conf_location));				
	 				}
	 			}else{
	 	        	etPointA.setText(null);
	 				etPointA.setEnabled(true);	
	 				removeUpdates();
	 	        }
	 	    }
	 	});	 	
	 	btSearch.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		if (!isNetworkAvailable()){   
        		 	 showToastToUser(getResources().getString(R.string.network_not_available));
        		}else if (validFields()){
        				  destinationAddress = new String (etPointB.getText()+"");
        			  	  destinationLocation = getLocationFromAddress(String.valueOf(destinationAddress));
        			  	  if (!checkMyPosition.isChecked()){
        			  		  sourceAddress = new String(etPointA.getText()+"");
        			  		  sourceLocation = getLocationFromAddress(sourceAddress);        			
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

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){		
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			dateSelected = true;
		}
	};

	protected Dialog onCreateDialog(int id){
		switch (id){
		case DATE_DIALOG_ID:
			return new DatePickerDialog (this, mDateSetListener, mYear, mMonth, mDay);
		}
		switch (id){
		case TIME_DIALOG_ID:
			return new TimePickerDialog (this, mTimeSetListener, mHour, mMinute, true);
		}
		return null;
	}
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute){
			mHour = hourOfDay;
			mMinute = minute;
			timeSelected =  true;			
		}
	};
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public boolean validFields (){	
		String a = new String(etPointA.getText()+"");
		String b = new String(etPointB.getText()+"");
		if ((!dateSelected)||(!timeSelected)){
			showToastToUser(getResources().getString(R.string.select_date));
			return false;
		}
		if ((a.length() == 0) && (b.length() != 0)){
			showToastToUser(getResources().getString(R.string.introduce_origin_address));
			return false;
		}else if ((b.length() == 0) && (a.length() != 0)){
			  		showToastToUser(getResources().getString(R.string.introduce_destination_address));												        
					return false;
			  }else if((a.length() == 0) && (b.length() == 0)){ 
				  		showToastToUser(getResources().getString(R.string.introduce_origin_destination));
						return false;
					}if((a.contains("@")||(a.contains("#")||(a.contains("*")||(a.contains("/")||(a.contains("%")||
							(a.contains("+")||(a.contains("?")||(a.contains("¿")||(a.contains("{")||(a.contains("}")||
									(a.contains("€")||(a.contains("$")))))))))))))){
				  		showToastToUser(getResources().getString(R.string.origin_not_exists));
						return false;
			  		}else if((b.contains("@")||(b.contains("#")||(b.contains("*")||(b.contains("/")||(b.contains("%")||
							(b.contains("+")||(b.contains("?")||(b.contains("¿")||(b.contains("{")||(b.contains("}")||
									(b.contains("€")||(b.contains("$")))))))))))))){
			  			showToastToUser(getResources().getString(R.string.destination_not_exists));
						return false;
			  		}else {
			  			return true;
			  		}		
	}

	private void removeUpdates() {
		// TODO Auto-generated method stub
		myLocManager.removeUpdates(this);
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

			
	
	private LatLng getLocationFromAddress (String strAddress){	   	 
    	Geocoder coder = new Geocoder(this);
    	List<Address> address;
    	LatLng resultLocation = null;
    	try {
    	    address = coder.getFromLocationName(strAddress,5);
    	    if (address.size()==0) {    	    							// REVISAR !
    	    	showToastToUser("La dirección "+strAddress+" no existe");    	    	
    			resultLocation = null;
    	    }else{    	 	    	
    	    	Address location = address.get(0);
    	    	resultLocation = new LatLng (location.getLatitude(), location.getLongitude());   
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
		if (addresses.size()==0){
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
		 b.putString("source address", sourceAddress);
		 b.putString("destination address", destinationAddress);
		 b.putInt("hour", mHour);
		 b.putInt("minute", mMinute);

		 b.putInt("day", mDay);
		 b.putInt("day of week", mDayOfWeek);
		 b.putInt("month", mMonth);
		 b.putInt("year", mYear);
	
		 
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
			sourceAddress = new String(getAddressFromLocation(source));
			etPointA.setText(sourceAddress);
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