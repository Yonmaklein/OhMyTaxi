package com.example.ohmytaxi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper {
private Context mCtx =null;
private DataBaseHelperInternal mDbHelper = null;
private SQLiteDatabase mDb = null;
private static final String DATABASE_NAME = "ROUTES";
private static final int DATABASE_VERSION = 3;
//tabla y campos
private static final String DATABASE_TABLE_ROUTES ="routes";
public static final String SL_ID="_id";
public static final String SL_DATE="date";
public static final String SL_ORIGIN="origin";
public static final String SL_DESTINATION="destination";
public static final String SL_KM="km";
public static final String SL_PRICE="price";
//SQL de creación dela tabla
private static final String DATABASE_CREATE_ROUTES = "create table "+ DATABASE_TABLE_ROUTES +" ("+SL_ID+
" integer primary key autoincrement, "+SL_DATE+" text not null, "+SL_ORIGIN+" text not null, "+SL_DESTINATION+
" text not null, "+SL_KM+" float not null, "+SL_PRICE+" float not null)";
//constructor
public DataBaseHelper (Context ctx){
this.mCtx= ctx;
}
public DataBaseHelper open() throws SQLException {
mDbHelper = new DataBaseHelperInternal (mCtx);
mDb = mDbHelper.getWritableDatabase();
return this;	
}
public void close(){ 
mDbHelper.close();
}
//obtener todos los elementos ordenados por precio
public Cursor getRoutes(){
return mDb.query(DATABASE_TABLE_ROUTES, new String[] {SL_ID, SL_DATE, SL_ORIGIN, SL_DESTINATION, SL_KM, SL_PRICE}, null, null, null, null, SL_PRICE);
}
//crear elemento
public long insertRoute(String date, String origin, String destination, float km, float price){ 
	Log.i("Se mete en el insert", " se meteeee");
ContentValues initialValues = new ContentValues();
initialValues.put(SL_DATE, date);
initialValues.put(SL_ORIGIN, origin);
initialValues.put(SL_DESTINATION, destination);
initialValues.put(SL_KM, km);
initialValues.put(SL_PRICE, price);

return mDb.insert(DATABASE_TABLE_ROUTES, null, initialValues);
}
//Clase privada para el control de la SQLite
public static class DataBaseHelperInternal extends SQLiteOpenHelper{
public DataBaseHelperInternal(Context context){
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
@Override
public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
createTables(db);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
deleteTables(db);
createTables(db);
}
private void createTables(SQLiteDatabase db){
db.execSQL(DATABASE_CREATE_ROUTES);
}
public void deleteTables(SQLiteDatabase db){
db.execSQL("DELETE TABLE IF EXITS " + DATABASE_TABLE_ROUTES);
}
}

}