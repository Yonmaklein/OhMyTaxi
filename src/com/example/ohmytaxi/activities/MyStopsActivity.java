package com.example.ohmytaxi.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.db.MyStopsAdapter;
import com.example.ohmytaxi.db.MyStopsCursorAdapter;

public class MyStopsActivity extends Activity{
	private MyStopsAdapter dbAdapter;
    private Cursor cursor;
    private MyStopsCursorAdapter stopsAdapter ;
    private ListView lista;
 
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_mystops);
 
      lista = (ListView) findViewById(android.R.id.list);
 
      dbAdapter = new MyStopsAdapter(this);
      dbAdapter.abrir();
 
      consultar();
   }
 
   @SuppressWarnings("deprecation")
private void consultar()
   {
      cursor = dbAdapter.getCursor();
      startManagingCursor(cursor);
      stopsAdapter = new MyStopsCursorAdapter(this, cursor);
      lista.setAdapter(stopsAdapter);
   }
 /*
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
     // getMenuInflater().inflate(R.menu.hipoteca, menu);
      return true;
   }
   */
   public void onBackPressed(){   // al pulsar la tecla back del teléfono vuelve al menú principal
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
}