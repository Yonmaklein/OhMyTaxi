package com.example.ohmytaxi;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



import java.util.Locale;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;





public class MenuActivity extends Activity {

	
	
	private Button buttonCalculate;
	private Button buttonExit;
	private Button buttonHelp;
	private Button buttonSettings;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonCalculate  = (Button) findViewById(R.id.buttonCalculate);
        buttonSettings   = (Button) findViewById(R.id.buttonSettings);
        buttonHelp       = (Button) findViewById(R.id.buttonHelp);
        buttonExit		 = (Button) findViewById(R.id.buttonExit);

        
        
        buttonCalculate.setOnClickListener(new OnClickListener() {
        	public void onClick(View view){
        		showCalculateScreen();
        	}
        });
                         
        buttonSettings.setOnClickListener(new View.OnClickListener() {		
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
	

	public void showSettingsScreen(){
		Intent i = new Intent(this, SettingsActivity.class);  
		startActivity(i);
		finish();
	}
	
	
	public void showHelpScreen() {
		/*Intent intent = new Intent(getBaseContext().getApplicationContext(), HelpActivity.class);  
		startActivity(intent);
		finish();*/
	}
		
		

    protected void exitApp() {
		// TODO Auto-generated method stub
    	android.os.Process.killProcess(android.os.Process.myPid());
		
	}
    
    
}
