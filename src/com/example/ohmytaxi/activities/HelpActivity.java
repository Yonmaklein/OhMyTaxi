package com.example.ohmytaxi.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.interfaces.CustomPageAdapter;

public class HelpActivity extends Activity{
	
	private PagerAdapter 	pageAdapter;  
	private ViewPager 		pager;  
	private Context 		_context;  


    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.intro);  
        _context = this;  
        
		findViewById(R.id.ImgLeftArrow).setVisibility(View.INVISIBLE);
        try {  
            pageAdapter = new CustomPageAdapter(_context); 
            pager = (ViewPager) findViewById(R.id.viewPagerId);  
            pager.setAdapter(pageAdapter); 
            pager.setOnPageChangeListener(new OnPageChangeListener(){
				@Override
				public void onPageScrollStateChanged(int arg0) {}
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {}
				@Override
				public void onPageSelected(int arg0) {
					switch(arg0){
					case 0:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.INVISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 1:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);	
						break;
					case 2:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 3:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 4:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 5:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 6:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					/////
					case 7:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 8:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 9:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 10:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_black);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_grey);
						findViewById(R.id.ImgRightArrow).setVisibility(View.VISIBLE);
						break;
					case 11:
						findViewById(R.id.ImgLeftArrow).setVisibility(View.VISIBLE);
						( (ImageView) findViewById(R.id.ImageViewDot1) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot2) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot3) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot4) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot5) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot6) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot7) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot8) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot9) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot10) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot11) ).setImageResource(R.drawable.dot_grey);
						( (ImageView) findViewById(R.id.ImageViewDot12) ).setImageResource(R.drawable.dot_black);
						findViewById(R.id.ImgRightArrow).setVisibility(View.INVISIBLE);
						break;
					}					
				}            	
            });
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  		
          
    }  
    
    
	public void onBackPressed(){
		Intent intent = new Intent(getBaseContext().getApplicationContext(), MenuActivity.class);  
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
  
   
}
