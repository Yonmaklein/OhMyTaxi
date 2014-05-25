package com.example.ohmytaxi.activities;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.db.RoutesSQLiteHelper;

public class MyRoutesActivity extends ListActivity{
	
	
	ListView list;
	
	
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_myroutes);
 
      list = getListView();

      String dbName = getString(R.string.dbName);
          RoutesSQLiteHelper sqlite = new RoutesSQLiteHelper(this, dbName, null, 1);
          SQLiteDatabase db = sqlite.getReadableDatabase();

          //Debemos incluir el campo "_id" en la consulta obligatoriamente pues es necesÃ¡rio para la lista
          String columns[] = new String[]{"_id", "date", "origin","destination", "km","price"};
          Cursor c = 
                          db.query(
                                          "Routes", columns, null, null, null, null, null, null);
          c.moveToFirst();
          //¿Qué hace?
          String from[] = new String[]{"_id", "date", "origin","destination", "km","price"};
          int to[] = new int[]{R.id.date, R.id.origin, R.id.destination, R.id.km, R.id.price};
         
          SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.route, c,from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

          list.setAdapter(adapter);
          list.setLongClickable(true);

          //Cerramos la base de datos
          db.close();

          //Click al elemento
          
          
          
          
		  list.setOnItemLongClickListener(new OnItemLongClickListener() {

	          public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
	              int pos, long id) {
	                
	        	  Log.i("TOCADO", Long.toString(id) );
	                

	              return true;
	          }
	       }); 

          
          
          
         
   }

   public void onBackPressed(){   
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
	
	
}