package com.example.ohmytaxi.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyStopsCursorAdapter extends CursorAdapter {
	private MyStopsAdapter dbAdapter = null ;
	 
	   @SuppressWarnings("deprecation")
	public MyStopsCursorAdapter(Context context, Cursor c)
	   {
	      super(context, c);
	      dbAdapter = new MyStopsAdapter(context);
	      dbAdapter.abrir();
	   }
	 
	   @Override
	   public void bindView(View view, Context context, Cursor cursor)
	   {
	      TextView tv = (TextView) view ;
	 
	      tv.setText(cursor.getString(cursor.getColumnIndex(MyStopsAdapter.COLUMNA_DIRE)));
	   }
	 
	   @Override
	   public View newView(Context context, Cursor cursor, ViewGroup parent)
	   {
	      final LayoutInflater inflater = LayoutInflater.from(context);
	      final View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
	 
	      return view;
	   }
}
