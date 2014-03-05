package com.example.ohmytaxi.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ohmytaxi.R;

public class Route extends Activity {
//referencias a elementos de pantalla
TextView date = null;
TextView origin = null;
TextView destination = null;
TextView km = null;
TextView price = null;
//identificador de entrada
Long rowId = null;
public void onCreate (Bundle savedInstanceState){
super.onCreate(savedInstanceState);
setContentView(R.layout.new_route);
//boton de guardar
Button saveBtn = (Button) findViewById(R.id.add);
saveBtn.setOnClickListener(new View.OnClickListener(){
public void onClick(View view){
setResult(RESULT_OK);
saveData();
finish();
}
});
//obtener referencias
date = (TextView) findViewById(R.id.date);
origin = (TextView) findViewById(R.id.origin);
destination = (TextView) findViewById(R.id.destination);
km = (TextView) findViewById(R.id.km);
price = (TextView) findViewById(R.id.price);
}
protected void saveData(){
//obtener datos
String dateText = new String(date.getText().toString());
String originText = new String(origin.getText().toString());
String destinationText = new String(destination.getText().toString());
String kmText = new String(km.getText().toString());
String priceText = new String(price.getText().toString());
Log.i("fecha", dateText);
Log.i("origen", originText);
Log.i("destino", destinationText);
Log.i("distancia", kmText);
Log.i("precio", priceText);

//insertar
MyRoutesListActivity.mDbHelper.insertRoute(dateText, originText, destinationText, Float.parseFloat(kmText), Float.parseFloat(priceText));
}
}