package com.example.ohmytaxi.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



// Clase para gestionar las operaciones de la entidad MyRoutes en la base de datos SQLite.
public class MyRoutesAdapter {
	/**
	    * Definimos constante con el nombre de la tabla
	    */
	   public static final String C_TABLA = "MISRUTAS" ;
	 
	   /**
	    * Definimos constantes con el nombre de las columnas de la tabla
	    */
	   public static final String _ID   = "_id";
	   public static final String COLUMNA_KM = "km";
	   public static final String COLUMNA_PRECIO = "precio";
	   public static final String COLUMNA_FECHA = "fecha";
	   public static final String COLUMNA_ORIGEN = "origen";
	   public static final String COLUMNA_DESTINO = "destino";
	   
	 
	   private Context contexto;
	   private BBDDHelper dbHelper;
	   private SQLiteDatabase db;
	 
	   /**
	    * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
	    */
	   private String[] columnas = new String[]{ _ID, COLUMNA_KM, COLUMNA_PRECIO, COLUMNA_FECHA, COLUMNA_ORIGEN, COLUMNA_DESTINO} ;
	 
	   public MyRoutesAdapter(Context context)
	   {
	      this.contexto = context;
	   }
	 
	   public MyRoutesAdapter abrir() throws SQLException
	   {
	      dbHelper = new BBDDHelper(contexto);
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