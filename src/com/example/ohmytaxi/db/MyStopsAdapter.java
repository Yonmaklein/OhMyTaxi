package com.example.ohmytaxi.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyStopsAdapter {
	/**
	    * Definimos constante con el nombre de la tabla
	    */
	   public static final String C_TABLA = "STOPS" ;
	 
	   /**
	    * Definimos constantes con el nombre de las columnas de la tabla
	    */
	   public static final String _ID   = "_id";
	   public static final String COLUMNA_LAT = "lat";
	   public static final String COLUMNA_LON = "lon";
	   public static final String COLUMNA_DIRE = "dire";

	   
	 
	   private Context contexto;
	   private StopsHelper dbHelper;
	   private SQLiteDatabase db;
	 
	   /**
	    * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
	    */
	   private String[] columnas = new String[]{ _ID, COLUMNA_LAT, COLUMNA_LON, COLUMNA_DIRE} ;
	 
	   public MyStopsAdapter(Context context)
	   {
	      this.contexto = context;
	   }
	 
	   public MyStopsAdapter abrir() throws SQLException
	   {
	      dbHelper = new StopsHelper(contexto);
	      db = dbHelper.getWritableDatabase();
	      return this;
	   }
	 
	   public void cerrar()
	   {
	      dbHelper.close();
	   }
	 
	   /**
	    * Devuelve cursor con todos las columnas de la tabla
	    */
	   public Cursor getCursor() throws SQLException
	   {
	      Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);
	 
	      return c;
	   }
}
