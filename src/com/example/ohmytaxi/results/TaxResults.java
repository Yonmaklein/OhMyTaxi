package com.example.ohmytaxi.results;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class TaxResults {

	private LatLng origin;
	private LatLng destination;
	private LatLng medPoint2;
	private LatLng medPoint3;
	private LatLng medPoint4;
	private double price;
	private double distance;
	private int hour;
	private int minute;
	private int day;
	private int dayOfWeek;
	private int month;	
	private int year;
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
	
	
	
	
	public TaxResults (LatLng origin, LatLng destination, double distance, int year, 
						int month, int day,	int dayOfWeek, int hour, int minute){

		this.distance = distance;
		this.origin = origin;
		this.destination = destination;
	    
		medPoint3 = new LatLng ((origin.latitude + destination.latitude)/2, (origin.longitude + destination.longitude)/2); 
		medPoint2 = new LatLng ((origin.latitude + medPoint3.latitude)/2, (origin.longitude + medPoint3.longitude)/2);		
		medPoint4 = new LatLng ((medPoint3.latitude + destination.latitude)/2, (medPoint3.longitude + destination.longitude)/2);
	    
		this.year = year;
	    this.month = month;
	    this.day = day;
	    this.dayOfWeek = dayOfWeek;
	    this.hour = hour;
	    this.minute = minute;

		
		createPolygons();


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
	    aZone.add(new LatLng(40.368103,-3.718328));
	    aZone.add(new LatLng(40.335358,-3.720688));
	 	aZone.add(new LatLng(40.323832,-3.708112));
	    aZone.add(new LatLng(40.332893,-3.647026));
	    aZone.add(new LatLng(40.408953,-3.51711));
	    aZone.add(new LatLng(40.41324,-3.574613));
	    aZone.add(new LatLng(40.427292,-3.582313));
	    aZone.add(new LatLng(40.438949,-3.571578));
	    aZone.add(new LatLng(40.448265,-3.529888));
	    aZone.add(new LatLng(40.500371,-3.55147));
	    aZone.add(new LatLng(40.511439,-3.654169));
	    aZone.add(new LatLng(40.525828,-3.672761));
	    aZone.add(new LatLng(40.528925,-3.773798));
	    aZone.add(new LatLng(40.475483,-3.832323));
	    aZone.add(new LatLng(40.46246,-3.798535));
	    aZone.add(new LatLng(40.444329,-3.78476));
	    aZone.add(new LatLng(40.44486,-3.767408));
	    aZone.add(new LatLng(40.42911,-3.767772));
	    aZone.add(new LatLng(40.416199,-3.777384));
	    aZone.add(new LatLng(40.402561,-3.771807));
	    aZone.add(new LatLng(40.391242,-3.792273));
	    aZone.add(new LatLng(40.395534,-3.830254));
	    aZone.add(new LatLng(40.365449,-3.806599));
	    aZone.add(new LatLng(40.360622,-3.785414));
	    aZone.add(new LatLng(40.357927,-3.754504));
	    aZone.add(new LatLng(40.368103,-3.718328));	    
	}
	
	
	
	
	
	public boolean isDaily(){
		return ((dayOfWeek != Calendar.SATURDAY) && (dayOfWeek != Calendar.SUNDAY)) &&
				((hour >= 6) && (hour < 21)); 	
	}
	
	
	public double getPrice(){
		// TARIFA 4
		if ((((isPointInPolygon(origin,airport)) && (isPointInPolygon(destination,m30)))) ||
			((isPointInPolygon(destination,airport)) && (isPointInPolygon(origin,m30)))){
			price = (float) 30.0;
		// TARIFA 5	
		}else{
			if ((isPointInPolygon(origin,airport))&&(!isPointInPolygon(destination,m30))){
				if (distance <= 10.0){
					price = 20.0;
				}else{
					price = 20.0 + pricePerKm() * (distance - 10.0);    // atención
					Log.i("PRECIO POR KM", Float.toString(pricePerKm()));
				}						
			}else{					
				price = distance * pricePerKm();   // atención
				Log.i("PRECIO POR KM", Float.toString(pricePerKm()));
			}			
			price = price + getSupplements();
		}
		return price;
	}
	
	
	public float pricePerKm (){        //    O*------1------2------3------*D
		int a = 0;
		int b = 0;
		if (isPointInPolygon(origin, aZone)){
			a++;
		}else{
			b++;
		}
		if (isPointInPolygon(medPoint2, aZone)){
			a++;
		}else{
			b++;
		}
		if (isPointInPolygon(medPoint3, aZone)){
			a++;
		}else{
			b++;
		}
		if (isPointInPolygon(medPoint4, aZone)){
			a++;
		}else{
			b++;
		}
		if (isPointInPolygon(destination, aZone)){
			Log.i("Destino", "dentro");
			a++;
		}else{
			b++;
			Log.i("Destino", "fuera");
		}
		Log.i("A", Integer.toString(a));
		Log.i("B", Integer.toString(b));
		if(isDaily()){				
			return (float) (a * 1.05 + b * 1.20) / 5;		
		}else{
			return (float) (a * 1.20 + b * 1.25) / 5;		
		}
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
		if (isDaily()){
			supplement = (float) 2.40;	
			Log.i("AÑADIDO", "2.40");
		}else{
			supplement = (float) 2.90;
			Log.i("AÑADIDO", "2.90");
		}
		if (isPointInPolygon(destination,airport)){
			supplement = (float) (supplement + 5.50);		
			Log.i("AÑADIDO", "5.50");
		}
		if ((isPointInPolygon(origin,chamartin)) || (isPointInPolygon(origin,avAmerica)) || 
			(isPointInPolygon(origin,atocha)) || (isPointInPolygon(origin,mendezAlvaro))){
			supplement = (float) (supplement + 3.00);
			Log.i("AÑADIDO", "3.00");
		}
		if ((isPointInPolygon(origin,ifema)) || (isPointInPolygon(destination,ifema))){
			supplement = (float) (supplement + 3.00);
			Log.i("AÑADIDO", "3.00");
		}	
		if(month == 12){
			if ((day == 24) || (day == 31)){
				if (((hour == 20) && (minute > 30)) || ((hour >= 21)))  {
					supplement = (float) (supplement + 6.70);
					Log.i("AÑADIDO1", "6.70");
				}
			}
			if (day == 25){
				if ((hour < 6) || (((hour == 5) && (minute < 30)))   ){
					supplement = (float) (supplement + 6.70);
					Log.i("AÑADIDO2", "6.70");
				}
			}
		}
		if (((month == 1) && (day ==1) && ((hour <6)) || ((hour == 5) && (minute < 30)))){
			supplement = (float) (supplement + 6.70);
			Log.i("AÑADIDO3", "6.70");
		}
		return supplement;
	}
	
}
