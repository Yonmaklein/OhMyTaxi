package com.example.ohmytaxi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class RoutesSQLiteHelper extends SQLiteOpenHelper {
	
	private String dbName = "rutas";//Nombre de la base de datos
	public static int DBVERSION = 1;

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Routes (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, origin TEXT, destination TEXT, km TEXT, price TEXT)";
    
   
    public RoutesSQLiteHelper(Context context, String name,
                               CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DBVERSION = version;

    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
        
        


    }
 
    public void saveRoute(String date, String origin, String destination, float km, float price){
    	
    	SQLiteDatabase database = this.getWritableDatabase();
    	
    	String sqlInsert = "INSERT INTO Routes (date, origin, destination, km, price) VALUES('"
    						+date+"', '"+origin+"', '"+destination+"', '"+Float.toString(km)+
    						"km', '"+Float.toString(price)+"€')";
    	
    	database.execSQL(sqlInsert);
    	database.close();
    }
    
    
    public void deleteRoute(int id){
    	
    	SQLiteDatabase database = this.getWritableDatabase();
    	database.execSQL("DELETE FROM Routes WHERE _id="+Integer.toString(id));
    	database.close();
    }
 
    
    
    
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                          int newVersion) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente 
        //      la opción de eliminar la tabla anterior y crearla de nuevo 
        //      vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la 
        //      tabla antigua a la nueva, por lo que este método debería 
        //      ser más elaborado.
 
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Routes");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
}

}