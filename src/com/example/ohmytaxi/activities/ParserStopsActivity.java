package com.example.ohmytaxi.activities;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.location.Stop;


public class ParserStopsActivity extends Activity{
    
private List <Stop> allStops; 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser_stops);
        Log.i("Empieza", " la ejecución");
        allStops = parse();
        
        for (int i =0; i<allStops.size(); i++){
        Log.i("Address", allStops.get(i).getDire());
        Log.i("Latitud", String.valueOf(allStops.get(i).getLat()));
        Log.i("Longitud", String.valueOf(allStops.get(i).getLon()));
        }
       
       
    }
 
    public List<Stop> parse()
    {
       
       // XmlPullParser parser = Xml.newPullParser();
    XmlResourceParser parser = getResources().getXml(R.xml.stops);
   
    List<Stop> stops = null;

        try
        {
           // parser.setInput(this.getInputStream(), null);
 
            int evento = parser.getEventType();
 
            Stop currentStop = null;
 
            while (evento != XmlPullParser.END_DOCUMENT)
            {
                String etiqueta = null;
 
                switch (evento)
                {
                    case XmlPullParser.START_DOCUMENT:
                    Log.i("Empieza", " el documento");
                        stops = new ArrayList<Stop>();
                        break;
 
                    case XmlPullParser.START_TAG:
                    Log.i("Empieza", " taaaaag");

                        etiqueta = parser.getName();
 
                        if (etiqueta.equals("stop"))
                        {
                        Log.i("encuentra stop", " STOP");
                            currentStop = new Stop(null, 0, 0);
                        }
                        else if (currentStop != null)
                        {
                            if (etiqueta.equals("address"))
                            {
                            Log.i("encuentra", " ADDRESS");
                                currentStop.setDire(parser.nextText());
                            }
                            else if (etiqueta.equals("lat"))
                            {
                            Log.i("encuentra", " LATITUD");
                                currentStop.setLat(Double.parseDouble(parser.nextText()));
                            }
                            else if (etiqueta.equals("lng"))
                            {
                            Log.i("encuentra", " LONGITUD");
                            currentStop.setLon(Double.parseDouble(parser.nextText()));
                            }
                            
                        }
                        break;
 
                    case XmlPullParser.END_TAG:
 
                        etiqueta = parser.getName();
 
                        if (etiqueta.equals("stop") && currentStop != null)
                        {
                            stops.add(currentStop);
                            Log.i("Añade parada al array", " PARADA AÑADIDA");
                        }
                        break;
                }
 
                evento = parser.next();
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
 
        return stops;
    }
 
 
    
   /* 
    private LatLng getLocationFromAddress (String strAddress){	   
    Geocoder coder = new Geocoder(this);
    List<Address> address;
    LatLng resultLocation = null;
    try {
       address = coder.getFromLocationName(strAddress,5);
       if (address.size()==0) {
   	Log.i("nullsahdshsdaads", "2656256252");

    resultLocation = null;
       }
       else{    	   	
       	Address location = address.get(0);
       
       	resultLocation = new LatLng (location.getLatitude(), location.getLongitude());      	
       	Log.i("yyyyyasghshg", String.valueOf(resultLocation.latitude));  	   	
       }
    } catch (IOException e) {  
    }
return resultLocation;
    }
*/
    

}