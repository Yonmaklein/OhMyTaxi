package com.example.ohmytaxi.activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.db.DataBaseHelper;


public class MyRoutesListActivity extends ListActivity{
//acciones
public static final int NEW_ROUTE = 1;
public static final int EDIT_ROUTE = 2;
public static final int SHOW_ROUTE = 3;
//elemento seleccionado
private long lastRowSelected = 0;
public static DataBaseHelper mDbHelper = null;
public void onCreate (Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_myroutes);
//abrir la base de datos
mDbHelper = new DataBaseHelper(this);
mDbHelper.open();
fillData();
}
//se encarga de obtener los elementos a mostrar, y crea el adaptador que usa la lista para mostrar los elementos
@SuppressWarnings("deprecation")
private void fillData(){
Cursor itemCursor = null;
itemCursor = mDbHelper.getRoutes();
startManagingCursor(itemCursor);
//array de campos del cursor
String[] from = new String[] {
DataBaseHelper.SL_ID, DataBaseHelper.SL_DATE, DataBaseHelper.SL_ORIGIN, 
DataBaseHelper.SL_DESTINATION, DataBaseHelper.SL_KM, DataBaseHelper.SL_PRICE};
//array para indicar los elementos que mostrarán la información
int[] to = new int[] {R.id.row_date, R.id.row_origin, R.id.row_destination, R.id.row_km, R.id.row_price};
//creación del adaptador para mostrar la información
MyRoutesAdapter routes = new MyRoutesAdapter (this, R.layout.row_list, itemCursor, from, to);
//asignar adaptador a la lista
setListAdapter(routes);	
}
/*
public boolean onCreateOptionsMenu (Menu menu){
MenuInflater inflater = getMenuInflater();
//inflater.inflate(R.menu.route, menu);
return true;
}
public boolean onOptionsItemSelected(MenuItem route){
switch (route.getItemId()){
case R.id.new_route:
Intent intent = new Intent (this, Route.class);
startActivityForResult(intent, NEW_ROUTE);
return true;
default:
return super.onOptionsItemSelected (route);
}
}
*/
private class MyRoutesAdapter extends SimpleCursorAdapter {
private LayoutInflater mInflater;
private Cursor cursor;
public MyRoutesAdapter (Context context, int layout, Cursor c, String[] from, int[] to){
super(context, layout, c, from, to);
cursor = c;
cursor.moveToFirst();
mInflater = LayoutInflater.from(context);
}
public View getView(int position, View convertView, ViewGroup parent){
//comprobamos si hay que iniciar el cursor o no
if (cursor.getPosition()<0){
cursor.moveToFirst();	
} else {
cursor.moveToPosition(position);
}
//obtención de la vista de la linea de la tabla
View row = mInflater.inflate(R.layout.row_list, null);
//rellenamos datos
TextView date = (TextView) row.findViewById(R.id.row_date);
TextView origin = (TextView) row.findViewById(R.id.row_origin);
TextView destination = (TextView) row.findViewById(R.id.row_destination);	
//TextView km = (TextView) row.findViewById(R.id.row_km);	
date.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.SL_DATE)));
origin.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.SL_ORIGIN)));
destination.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.SL_DESTINATION)));
float km = cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseHelper.SL_KM));
float price = cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseHelper.SL_PRICE));
int rowId = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelper.SL_ID));
//ImageView icon = (ImageView) row.findViewById(R.id.row_icon);
//icon.setImageResource(R.drawable.eurocoin);
/*dependiendo del precio, se muestran distintos iconos
* PERO NO SE PUEDE ELEGIR DE VALORES FLOAT
icon.setTag(new Integer(rowId));
switch (price){
case (price < 20.0) :
icon.setImageResource(R.drawable.eurocoin);
break;
default :
icon.setImageResource(R.drawable.eurocoin);
break;
}*/
return row;
}
}
}