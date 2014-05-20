package com.example.ohmytaxi.activities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.location.GMapV2GetRouteDirection;
import com.example.ohmytaxi.results.TaxResults;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;




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
      Location location;
      private double distance;
      private Button buttonBack;
      private Button buttonSave;
      private TextView textData;
      private String fromAddress;
      private String toAddress;
      private double price;
      
      private int hour;
      private int minute;
      private int day;
      private int dayOfWeek;
      private int month;	
      private int year;
  	
      
      
      @Override
      protected void onCreate(Bundle savedInstanceState) {
    	  
    	  	StrictMode.ThreadPolicy policy = new StrictMode.
    	  	ThreadPolicy.Builder().permitAll().build();
       	    StrictMode.setThreadPolicy(policy);    	  
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_route);
            v2GetRouteDirection = new GMapV2GetRouteDirection();
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
            mGoogleMap = supportMapFragment.getMap();            
            buttonSave = (Button) findViewById(R.id.buttonSave);
            buttonBack = (Button) findViewById(R.id.buttonBack);
            buttonBack.setOnClickListener(new OnClickListener() {
            	public void onClick(View view){
            		showPointsScreen();
            	}
            });
            buttonSave.setOnClickListener(new OnClickListener() {
            	public void onClick(View view){
            		showSaveScreen();
            	}
            });
            textData = (TextView) findViewById(R.id.tvTextData);

            
	    	Bundle bundle = getIntent().getExtras();
            fromAddress = new String(bundle.getString("source address"));
            toAddress = new String(bundle.getString("destination address"));
	
	    	fromPosition = new LatLng (bundle.getDouble("source lat"), bundle.getDouble("source lon"));
		    toPosition = new LatLng (bundle.getDouble("destination lat"), bundle.getDouble("destination lon"));
		
            hour = bundle.getInt("hour");
            minute = bundle.getInt("minute");
            day = bundle.getInt("day");
            dayOfWeek = bundle.getInt("day of week");
            month = bundle.getInt("month");
            year = bundle.getInt("year");
            
            
            // Enabling MyLocation in Google Map
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
            mGoogleMap.getUiSettings().setCompassEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
            mGoogleMap.setTrafficEnabled(false);
            
            
            
            Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();

            LatLngBounds bounds = new LatLngBounds(            			 
            			new LatLng(Math.min(fromPosition.latitude, toPosition.latitude),
            					   Math.min(fromPosition.longitude, toPosition.longitude)),
            			new LatLng(Math.max(fromPosition.latitude, toPosition.latitude),
                    			   Math.max(fromPosition.longitude, toPosition.longitude)));
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width - 120, height - 120, 0));
   


            GetRouteTask getRoute = new GetRouteTask();
            getRoute.execute();
            String dst = getDistance(fromPosition.latitude,fromPosition.longitude,toPosition.latitude,toPosition.longitude);
            Log.i("DST", dst);
            
            
            String newdst = new String(dst.replaceAll(",",".").replaceAll(" km", "").trim());
            Log.i("DISTANCIA",newdst);
            
            try{
            	distance = Double.valueOf(newdst);	
            }catch(RuntimeException e){
            	Log.i("Error","No ha sido posible calcular la distancia");
            	distance = 0.0;
            	Toast warningMessage = Toast.makeText(getApplicationContext(), getResources().getString(R.string.distance_not_available), Toast.LENGTH_LONG);
        		warningMessage.show(); 
            }
            
            
            
            
            TaxResults tax = new TaxResults(fromPosition, toPosition, distance, year, month, day, dayOfWeek, hour, minute);
        
            Log.i("PRECIO",String.valueOf(tax.getPrice()));
            textData.setText("PRECIO:  "+ String.valueOf( Math.rint(tax.getPrice() * 100) / 100)+" €"+"  "+"DISTANCIA:  "+newdst+" Km");            
      }

      
      
      
      public String getDistance(double lat1, double lon1, double lat2, double lon2) {
    	    String result_in_kms = "";
    	    String url = "http://maps.google.com/maps/api/directions/xml?origin="+lat1+","+lon1+"&destination="+lat2+","+lon2+"&sensor=false&units=metric";
    	    String tag[] = {"text"};
    	    HttpResponse response = null;
    	    try {
    	        HttpClient httpClient = new DefaultHttpClient();
    	        HttpContext localContext = new BasicHttpContext();
    	        HttpPost httpPost = new HttpPost(url);
    	        response = httpClient.execute(httpPost, localContext);
    	        InputStream is = response.getEntity().getContent();
    	        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    	        Document doc = builder.parse(is);
    	        if (doc != null) {
    	            NodeList nl;
    	            ArrayList args = new ArrayList();
    	            for (String s : tag) {
    	                nl = doc.getElementsByTagName(s);
    	                if (nl.getLength() > 0) {
    	                    Node node = nl.item(nl.getLength() - 1);
    	                    args.add(node.getTextContent());
    	                } else {
    	                    args.add(" - ");
    	                }
    	            }
    	            result_in_kms = String.format("%s", args.get(0));
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    //Log.i("DISTANCIA", result_in_kms);
    	    return result_in_kms;
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
      
      
		public void showPointsScreen(){
			Intent intent = new Intent(getBaseContext().getApplicationContext(), PointsActivity.class);  
			startActivity(intent);
			finish();
		}
	  
		public void showSaveScreen(){						
	/*		 Intent i = new Intent(this, CacafutiActivity.class);  
			 Bundle b = new Bundle();	

			 b.putString("source address",  fromAddress);		
			 b.putString("destination address",  toAddress);
			 
			 b.putDouble("distance", distance);
			 b.putDouble("price", price);
			 i.putExtras(b);	
			 
			 startActivity(i);
			 finish();	
			 */
			
			
			
			
			 /*
			  * para sacar los datos en el oncreate  de la activity a la que llamas pegas esto
				
			Bundle bundle = getIntent().getExtras();
			
			// estos 2 son 2 atributos String
            fromAddress = new String(bundle.getString("source address"));            
            toAddress = new String(bundle.getString("destination address"));
	
	        // estos de abajo son 2 atributos double;
	    	price = bundle.getDouble("price");
		    distance = bundle.getDouble("distance");
			
			
			*/
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
                	PolylineOptions rectLine = new PolylineOptions().width(8).color(Color.RED);
                	for (int i = 0; i < directionPoint.size(); i++) {
                		rectLine.add(directionPoint.get(i));
                	}
                	// Adding route on the map
                	mGoogleMap.addPolyline(rectLine);
                	/*   markerOptions.position(toPosition);
                  	markerOptions.draggable(true);
                    mGoogleMap.addMarker(markerOptions);*/
                	showMarker(fromPosition, true);
                	showMarker(toPosition, false);
                }              
                Dialog.dismiss();
            }
            
            
      	  	private void showMarker(LatLng position, boolean origin){
      	  		if (origin){
      	  			mGoogleMap.addMarker(new MarkerOptions()
      	  			.position(position)
      	  			.title("Origen"));
      	  		}else{
      	  			mGoogleMap.addMarker(new MarkerOptions()
      	  			.position(position)
      	  			.title("Destino")).showInfoWindow();
      	  		}
      	  	}
      }
      
      
      


      
      
}