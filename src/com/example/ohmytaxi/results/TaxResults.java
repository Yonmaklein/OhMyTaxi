package com.example.ohmytaxi.results;

import java.util.Calendar;

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
	
	
	
	public TaxResults (LatLng origin, LatLng destination){
		Calendar cal = Calendar.getInstance(); 
		hour = cal.get(Calendar.HOUR);

		this.originPosition = origin;
		this.destinationPosition = destination;
		
	}
	
	
	
	public boolean isInM30(LatLng point){
		return isInM30;
		
	}
	
	
	
}
