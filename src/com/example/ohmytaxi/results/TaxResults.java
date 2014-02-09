package com.example.ohmytaxi.results;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class TaxResults {

	private LatLng origin;
	private LatLng destination;
	private double price;
	private float distance;
	private int hour;
	private int minutes;
	private int day;
	private int dayOfMonth;
	private int month;
	private float supplement;
	private Calendar cal;
	private List <LatLng> m30;
	private List <LatLng> airport;
	private List <LatLng> atocha;
	private List <LatLng> chamartin;
	private List <LatLng> mendezAlvaro;
	private List <LatLng> avAmerica;
	private List <LatLng> ifema;
	private List <LatLng> aZone;
	
	
	
	
	public TaxResults (LatLng origin, LatLng destination, float distance){

		this.distance = distance;
		this.origin = origin;
		this.destination = destination;
	    
	    
		cal = Calendar.getInstance(); 
		cal.setFirstDayOfWeek(Calendar.MONDAY);	
		hour = cal.get(Calendar.HOUR);
		minutes = cal.get(Calendar.MINUTE);
		day = cal.get(Calendar.DAY_OF_WEEK);
		dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		
		createPolygons();


	}
	
	
	public double getPrice(){
		// TARIFA 4
		if ((((isPointInPolygon(origin,airport)) && (isPointInPolygon(destination,m30))))||
			((isPointInPolygon(destination,airport)) && (isPointInPolygon(origin,m30)))){
			price = (float) 30.0;
		// TARIFA 5	
		}else{
			if ((isPointInPolygon(origin,airport))&&(!isPointInPolygon(destination,m30))){
				if (distance <= 10.0){
					price = 20.0;
				}else{
					price = 20.0 + pricePerKm(destination) * (distance - 10.0);
				}						
			}else{
				if (isDaily()){
					
					
					
				}else{
					
					
					
					
				}
				
				
				
			}
			
			price = price + getSupplements();
		}
		return price;
	}
	
	
	public boolean isDaily(){
		return ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) &&
				((hour >= 6) && (hour < 21)); 	
	}
	

	public float pricePerKm (LatLng point){
		if(isDaily()){
			if(isPointInPolygon(point,aZone)){
				return (float) 1.05;
			}else{
				return (float) 1.20;
			}
		}else{
			if(isPointInPolygon(point,aZone)){
				return (float) 1.20;
			}else{
				return (float) 1.25;
			}
		}
	}

	
	
	public void createPolygons(){
		m30 = new ArrayList <LatLng>();
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
				
		airport = new ArrayList <LatLng>();
		airport.add(new LatLng(40.542765,-3.559624));
		airport.add(new LatLng(40.531285,-3.58091));
		airport.add(new LatLng(40.502053,-3.600136));
		airport.add(new LatLng(40.481687,-3.600136));
		airport.add(new LatLng(40.480904,-3.581642));
		airport.add(new LatLng(40.474244,-3.574089));
		airport.add(new LatLng(40.462099,-3.575462));
		airport.add(new LatLng(40.454785,-3.583996));
		airport.add(new LatLng(40.450507,-3.576743));
		airport.add(new LatLng(40.457887,-3.562881));
		airport.add(new LatLng(40.451519,-3.544663));
		airport.add(new LatLng(40.460434,-3.530094));
		airport.add(new LatLng(40.497353,-3.552758));
	    airport.add(new LatLng(40.542765,-3.559624));
		
		atocha = new ArrayList <LatLng>();
	    atocha.add(new LatLng(40.408448,-3.691762));
	    atocha.add(new LatLng(40.407533,-3.693017));
	    atocha.add(new LatLng(40.406307,-3.691773));
	    atocha.add(new LatLng(40.40509,-3.69011));
	    atocha.add(new LatLng(40.406234,-3.687835));
	    atocha.add(new LatLng(40.406887,-3.689219));
	    atocha.add(new LatLng(40.40875,-3.691333));
	    atocha.add(new LatLng(40.408448,-3.691762));
		
	    chamartin = new ArrayList <LatLng>();
	    chamartin.add(new LatLng(40.476995,-3.676817));
	    chamartin.add(new LatLng(40.478374,-3.683801));
	    chamartin.add(new LatLng(40.472432,-3.685981));
	    chamartin.add(new LatLng(40.471012,-3.679887));
	    chamartin.add(new LatLng(40.476995,-3.676817));
	    
	    mendezAlvaro = new ArrayList <LatLng>();
	    mendezAlvaro.add(new LatLng(40.396299,-3.678726));
	    mendezAlvaro.add(new LatLng(40.395915,-3.680765));
	    mendezAlvaro.add(new LatLng(40.392458,-3.677085));
	    mendezAlvaro.add(new LatLng(40.393104,-3.67627));
	    mendezAlvaro.add(new LatLng(40.396331,-3.677911));
	    mendezAlvaro.add(new LatLng(40.396299,-3.678726));
	    
	    avAmerica = new ArrayList <LatLng>();
	    avAmerica.add(new LatLng(40.439023,-3.675475));
	    avAmerica.add(new LatLng(40.437777,-3.677583));
	    avAmerica.add(new LatLng(40.437324,-3.677073));
	    avAmerica.add(new LatLng(40.438586,-3.674981));
	    avAmerica.add(new LatLng(40.439023,-3.675475));
	    
	    ifema = new ArrayList <LatLng>();
	    ifema.add(new LatLng(40.471649,-3.613132));
	    ifema.add(new LatLng(40.470327,-3.624379));
	    ifema.add(new LatLng(40.468303,-3.624336));
	    ifema.add(new LatLng(40.462981,-3.619744));
	    ifema.add(new LatLng(40.464189,-3.61232));
	    ifema.add(new LatLng(40.465821,-3.611462));
	    ifema.add(new LatLng(40.471649,-3.613132));	   
	    
	    aZone = new ArrayList <LatLng>();
	    aZone.add(new LatLng(40.2212285,-3.4386199));	
	    aZone.add(new LatLng(40.2027249,-3.43133794)); 
	    aZone.add(new LatLng(40.19291736,-3.42287848));
	    aZone.add(new LatLng(40.19597215,-3.38419536));
	    aZone.add(new LatLng(40.23355022,-3.32197179));
	    aZone.add(new LatLng(40.24328089,-3.3116857));
	    aZone.add(new LatLng(40.24456554,-3.34270968));
	    aZone.add(new LatLng(40.25286145,-3.34556397));
	    aZone.add(new LatLng(40.26494702,-3.31530789));
	    aZone.add(new LatLng(40.29537941,-3.32590212));
	    aZone.add(new LatLng(40.30440499,-3.39264431));
	    aZone.add(new LatLng(40.3119292,-3.39509684));
	    aZone.add(new LatLng(40.3152441,-3.45521907)); 
	    aZone.add(new LatLng(40.28277377,-3.49515911)); 
	    aZone.add(new LatLng(40.27469647,-3.4823058)); 
	    aZone.add(new LatLng(40.26350403,-3.4791145)); 
	    aZone.add(new LatLng(40.26370375,-3.45586742));
	    aZone.add(new LatLng(40.25431922,-3.4600576)); 
	    aZone.add(new LatLng(40.2544757,-3.46378915)); 
	   	aZone.add(new LatLng(40.2446571,-3.46110295)); 
	    aZone.add(new LatLng(40.23365972,-3.4750453)); 
	    aZone.add(new LatLng(40.23416499,-3.49440414));
	    aZone.add(new LatLng(40.21498895,-3.48262288));
	    aZone.add(new LatLng(40.2222964,-3.48143352)); 
	    aZone.add(new LatLng(40.2133515,-3.4756102)); 
	    aZone.add(new LatLng(40.21281085,-3.45169765)); 
	    aZone.add(new LatLng(40.2212285,-3.4386199));
	}
	
	


	    
	private static boolean isPointInPolygon(LatLng point, List<LatLng> poly){
		int i, j;
		boolean c = false;
		for (i = 0, j = poly.size() - 1; i < poly.size(); j = i++){
			if ((((poly.get(i).latitude <= point.latitude) && (point.latitude < poly.get(j).latitude)) ||
				((poly.get(j).latitude <= point.latitude) && (point.latitude < poly.get(i).latitude))) &&
				(point.longitude < (poly.get(j).longitude - poly.get(i).longitude) * (point.latitude - poly.get(i).latitude) / 
				(poly.get(j).latitude - poly.get(i).latitude) + poly.get(i).longitude))
				c = !c;
		}
		return c;
	}
	
	

	public float getSupplements(){
		if (isPointInPolygon(destination,airport)){
			supplement = (float) (supplement + 5.50);			
		}
		if ((isPointInPolygon(origin,chamartin)) || (isPointInPolygon(origin,avAmerica)) || 
			(isPointInPolygon(origin,atocha)) || (isPointInPolygon(origin,mendezAlvaro))){
			supplement = (float) (supplement + 3.00);
		}
		if ((isPointInPolygon(origin,ifema)) || (isPointInPolygon(destination,ifema))){
			supplement = (float) (supplement + 3.00);			
		}	
		if(month == 12){
			if ((dayOfMonth == 24) || (dayOfMonth == 31)){
				if (((hour == 20) && (minutes > 30)) || ((hour >= 21)))  {
					supplement = (float) (supplement + 6.70);
				}
			}
			if (dayOfMonth == 25){
				if ((hour < 6) || (((hour == 5) && (minutes < 30)))   ){
					supplement = (float) (supplement + 6.70);
				}
			}
		}
		if (((month == 1) && (hour <6)) || ((hour == 5) && (minutes < 30))){
			supplement = (float) (supplement + 6.70);
		}
		return supplement;
	}
	
}
