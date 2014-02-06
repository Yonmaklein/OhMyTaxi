package com.example.ohmytaxi.results;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class TaxResults {

	private LatLng originPosition;
	private LatLng destinationPosition;
	private float price;
	private double distance;
	private char zoneAB;
	private int hour;
	private String day;
	private float supplement;
	private boolean isInM30;
	private boolean isInAirport;
			
	
	
	
	public TaxResults (LatLng origin, LatLng destination){
		Calendar cal = Calendar.getInstance(); 
		hour = cal.get(Calendar.HOUR);

		this.originPosition = origin;
		this.destinationPosition = destination;
		
		
		
	
		
		
		
	}
	
	
	
	
	public boolean isPointInM30(LatLng pointToTest){				
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
		if (IsPointInPolygon(m30, pointToTest)){
			Log.i("pertenece", "SI");
			return true;
		}else{
			Log.i("pertenece", "NO");
			return false;
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
	
	
	
	public boolean isInM30(LatLng point){
		return isInM30;
		
	}
	
	
	
}
