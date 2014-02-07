package com.example.ohmytaxi.activities;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.db.MyRoutesAdapter;
import com.example.ohmytaxi.db.MyRoutesCursorAdapter;

public class MyRoutesActivity extends ListActivity{
	
	private MyRoutesAdapter dbAdapter;
    private Cursor cursor;
    private MyRoutesCursorAdapter routesAdapter ;
    private ListView lista;
 
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_myroutes);
 
      lista = (ListView) findViewById(android.R.id.list);
      
 
      dbAdapter = new MyRoutesAdapter(this);
      dbAdapter.abrir();
 
      consultar();
   }
 
   @SuppressWarnings("deprecation")
private void consultar()
   {
      cursor = dbAdapter.getCursor();
      startManagingCursor(cursor);
      routesAdapter = new MyRoutesCursorAdapter(this, cursor);
      lista.setAdapter(routesAdapter);
   }
 /*
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
     // getMenuInflater().inflate(R.menu.hipoteca, menu);
      return true;
   }
   */
   public void onBackPressed(){   // al pulsar la tecla back del telÃ©fono vuelve al menÃº principal
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
	
	
	// old version
	
/*
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		   setContentView(R.layout.acivity_misrutas);
		 
		   /*
		    * Declaramos el controlador de la BBDD y accedemos en modo escritura
		   
		   BBDDHelper dbHelper = new BBDDHelper(getBaseContext());
		 
		   SQLiteDatabase db = dbHelper.getWritableDatabase();
		 
		   Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
	    }
	
	 
	 // old version
	 
	 
	
	 private void leerRutasBBDD(){
		 
	     TextView lbl = (TextView) findViewById(R.id.lbl);
	     BBDDHelper bd = new BBDDHelper(this);
	     Cursor cursor = bd.leerRutas();
	     
	     if(cursor.moveToFirst()){
	      do{
	     lbl.append(String.valueOf(cursor.getInt(0)) + " - " + cursor.getString(1) + " - " + cursor.getString(2) + "\n");  
	      }while(cursor.moveToNext());
	     }
	     
	    }
	 //http://www.nosinmiubuntu.com/2013/01/rellenar-un-listview-con-sqlite.html
	 // lo último no sé en qué activity hacerlo
	 /*
	 ListView listView = (ListView) findViewById(R.id.listView);

	 bbdd bbdd = new bbdd(this);

	 Cursor cursor = bbdd.leerLibros();
	 startManagingCursor(cursor);

	 String[] from = new String[]{"name"};
	 int[] to = new int[]{R.id.text};

	 SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);

	 listView.setAdapter(cursorAdapter);
*/
}