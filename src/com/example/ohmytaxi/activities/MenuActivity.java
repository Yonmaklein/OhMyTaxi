package com.example.ohmytaxi.activities;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ohmytaxi.R;

public class MenuActivity extends Activity {
	
	private ImageButton buttonCalculate;
	private ImageButton buttonMyRoutes;
	private ImageButton buttonMyStops;
	private ImageButton buttonExit;
	private ImageButton buttonHelp;
	private ImageButton buttonSettings;
	

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menugrid);

        buttonCalculate  = (ImageButton) findViewById(R.id.buttonCalculate);
        buttonMyRoutes 	 = (ImageButton) findViewById(R.id.buttonMyRoutes);
        buttonMyStops  	 = (ImageButton) findViewById(R.id.buttonMyStops);
        buttonSettings   = (ImageButton) findViewById(R.id.buttonSettings);
        buttonHelp       = (ImageButton) findViewById(R.id.buttonHelp);
        buttonExit		 = (ImageButton) findViewById(R.id.buttonExit);
               
        
        buttonCalculate.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		showCalculateScreen();
        	}
        });
        
        buttonMyRoutes.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		showMyRoutesScreen();
        	}
        });
        
        buttonMyStops.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		showMyStopsScreen();
        	}
        });                         
        buttonSettings.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				showSettingsScreen();				
			}
		});
        
        buttonHelp.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		showHelpScreen();
        	}
        });
                
        buttonExit.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		exitApp();
        	}
        });            
        
    }

    
    @Override
    public void onStart(){
    	super.onStart(); 
    }
    
    @Override
    public void onBackPressed() {  
    	finish();
		this.onStop();
    }
    



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    

	public void showCalculateScreen() {
		Intent i = new Intent(this, PointsActivity.class);  
		startActivity(i);
		finish();		
	}
    public void showMyRoutesScreen(){
		Intent i = new Intent(this, MyRoutesActivity.class);  
		startActivity(i);
		finish();
	}
    public void showMyStopsScreen(){
		Intent i = new Intent(this, MyStopsActivity.class);  
		startActivity(i);
		finish();
	}

	public void showSettingsScreen(){
		Intent i = new Intent(this, SettingsActivity.class);  
		startActivity(i);
		finish();
	}
	
	public void showHelpScreen() {
		Intent i = new Intent(this, HelpActivity.class);  
		startActivity(i);
		finish();
	}
		
    protected void exitApp() {
		// TODO Auto-generated method stub
    	android.os.Process.killProcess(android.os.Process.myPid());
		
	}

    
}
