package com.example.ohmytaxi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BBDDHelper extends SQLiteOpenHelper{
//http://www.nosinmiubuntu.com/2011/11/como-guardar-datos-en-android-bases-de.html
	
	private static int version = 1;
	private static String name = "bdRutas";
	private static CursorFactory cursorFactory = null;
	private int routesNumber = 0;
	
	public BBDDHelper(Context context) {
		super(context, name, cursorFactory, version);
		// TODO Auto-generated constructor stub
	}

   //definir el nombre de nuestra base de datos, la versi�n y un CursorFactory necesario para 
	//el constructor del que heredamos. Con estas variables centralizadas aqu�, controlaremos 
	//desde un �nico punto la versi�n de nuestra base de datos o su nombre. Estas variables 
	//las utilizamos para llamar al constructor de nuestra clase padre.
	 
	//creamos la tabla
	
	   public void onCreate(SQLiteDatabase db)
	   {
	      Log.i(this.getClass().toString(), "Creando base de datos");
	    
	      db.execSQL( "CREATE TABLE MISRUTAS(" +
	             " _id INTEGER PRIMARY KEY," +
	             " fecha TEXT NOT NULL, " +
	             " km TEXT, " +
	             " precio TEXT," +
	             " origen TEXT," +
	             " destino TEXT)" );
	    
	      db.execSQL( "CREATE UNIQUE INDEX fecha ON MISRUTAS(fecha ASC)" );
	    
	      Log.i(this.getClass().toString(), "Tabla MISRUTAS creada");
	    
	      /*
	       * Insertamos datos iniciales
	      
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(1,'Santander')");
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(2,'BBVA')");
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(3,'La Caixa')");
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(4,'Cajamar')");
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(5,'Bankia')");
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(6,'Banco Sabadell')");
	      db.execSQL("INSERT INTO MISRUTAS(_id, fecha) VALUES(7,'Banco Popular')");
	    */
	      Log.i(this.getClass().toString(), "Datos iniciales MIS RUTAS insertados");
	    
	      Log.i(this.getClass().toString(), "Base de datos creada");
	   }
	   //_id se lo tengo que pasar?? lo deber�a calcular el m�todo
	   public void insertOnTable (SQLiteDatabase db, int _id, String fecha, String km, String precio, String origen, String destino){
		   _id = routesNumber + 1;
		   db.execSQL("INSERT INTO MISRUTAS(_id, fecha, km, precio, origen, destino) VALUES(_id, fecha, km, precio, origen, destino)");   
	   }
	   
	   public void DeleteFromTable(int _id){
		   
	   }
	   
	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	   {
	 
	   }
}