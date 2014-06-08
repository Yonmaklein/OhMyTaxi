package com.example.ohmytaxi.prefs;


import com.example.ohmytaxi.R;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class Preferences extends PreferenceActivity{



	private String language;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setLanguage(preferences.getString("selected_language", "ESP"));
        

      
    }






	public String getLanguage() {
		return language;
	}




	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
	
	
	
	
	
}
