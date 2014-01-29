package com.example.ohmytaxi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StopsHelper extends SQLiteOpenHelper {
	private static int version = 1;
	private static String name = "bdStops";
	private static CursorFactory cursorFactory = null;

	
	public StopsHelper(Context context) {
		super(context, name, cursorFactory, version);
		// TODO Auto-generated constructor stub
	}

   //definir el nombre de nuestra base de datos, la versión y un CursorFactory necesario para 
	//el constructor del que heredamos. Con estas variables centralizadas aquí, controlaremos 
	//desde un único punto la versión de nuestra base de datos o su nombre. Estas variables 
	//las utilizamos para llamar al constructor de nuestra clase padre.
	 
	//creamos la tabla
	
	   public void onCreate(SQLiteDatabase db)
	   {
	      Log.i(this.getClass().toString(), "Creando base de datos");
	    
	      db.execSQL( "CREATE TABLE STOPS(" +
	             " _id INTEGER PRIMARY KEY," +
	             " lat REAL, " +
	             " lon REAL, " +
	             " dire TEXT)" );
	    
	      db.execSQL( "CREATE UNIQUE INDEX _id ON STOPS(_id ASC)" );
	    
	      Log.i(this.getClass().toString(), "Tabla STOPS creada");
	    
	      /*
	       * Insertamos datos iniciales
	      */
	      db.execSQL("INSERT INTO STOPS(_id, lat, lon, dire) VALUES(1,40.398006,-3.697868,'Calle Jaime el Conquistador, 1')");
	      db.execSQL("INSERT INTO STOPS(_id, lat, lon, dire) VALUES(2,40.406667,-3.711988,'Glorieta Puerta de Toledo, 4')");
	      db.execSQL("INSERT INTO STOPS(_id, lat, lon, dire) VALUES(3,40.407091,-3.713254,'Ronda de Segovia, 50')");
	      db.execSQL("INSERT INTO STOPS(_id, lat, lon, dire) VALUES(4,40.404804,-3.702053,'Embajadores, 55')");
	      db.execSQL("INSERT INTO STOPS(_id, lat, lon, dire) VALUES(5,40.408072,-3.69156,'Atocha')");
	      
	    
	      Log.i(this.getClass().toString(), "Datos iniciales STOPS insertados");
	    
	      Log.i(this.getClass().toString(), "Base de datos creada");
	   }
	   /*_id se lo tengo que pasar?? lo debería calcular el método
	   public void insertOnTable (SQLiteDatabase db, int _id, String fecha, String km, String precio, String origen, String destino){
		   _id = routesNumber + 1;
		   db.execSQL("INSERT INTO MISRUTAS(_id, fecha, km, precio, origen, destino) VALUES(_id, fecha, km, precio, origen, destino)");   
	   }
	   
	   public void DeleteFromTable(int _id){
		   
	   }
	   */
	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	   {
	 
	   }}
