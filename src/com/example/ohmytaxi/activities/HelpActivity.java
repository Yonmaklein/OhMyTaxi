package com.example.ohmytaxi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.ohmytaxi.R;

public class HelpActivity extends Activity{
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		TextView tvHelp = (TextView)findViewById(R.id.tvHelp);
		Linkify.addLinks(tvHelp, Linkify.ALL);
	}
	
	   public void onBackPressed(){   // al pulsar la tecla back del teléfono vuelve al menú principal
			Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
			startActivity(intent);
			finish();
			super.onBackPressed();
	   }
	

}
