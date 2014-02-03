package com.example.ohmytaxi.activities;



import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import com.example.ohmytaxi.R;





	public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
		
		
		
		public static final String KEY_PREF_SYNC_CONN = "pref_syncConnectionType";




		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    addPreferencesFromResource(R.xml.preferences);
		}


		@Override
		public void onBackPressed(){
			Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
			startActivity(intent);
			finish();
			super.onBackPressed();
		}



		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			if (key.equals(KEY_PREF_SYNC_CONN)) {
				Preference connectionPref = findPreference(key);
				connectionPref.setSummary(sharedPreferences.getString(key, ""));
			}

			if (key.equals("selected_language")) {
					Intent intent = new Intent(getBaseContext().getApplicationContext(), SettingsActivity.class);
					startActivity(intent);
					finish();
			}
		}



		@Override
		protected void onResume() {
		    super.onResume();
		    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		}

		@Override
		protected void onPause() {
		    super.onPause();
		    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		}



}