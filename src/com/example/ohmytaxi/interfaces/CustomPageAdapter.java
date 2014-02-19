package com.example.ohmytaxi.interfaces;

import com.example.ohmytaxi.R;

import android.content.Context;  
import android.os.Parcelable;  
import android.support.v4.view.PagerAdapter;  
import android.support.v4.view.ViewPager;  
import android.view.LayoutInflater;
import android.view.View;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;
import android.widget.TextView;  
  
public class CustomPageAdapter extends PagerAdapter{        
    private final Context 				context;  
      
    public CustomPageAdapter(Context context) {  
        super();  
        this.context = context;  
    }  
  
    @Override  
    public void destroyItem(View collection, int position, Object view) {  
        ((ViewPager) collection).removeView((View) view);  
    }  
  
    @Override  
    public void finishUpdate(View arg0) {  
        // TODO Auto-generated method stub  
          
    }  
  
    @Override  
    public int getCount() {  
        return 12;  
    }  
    

    
  
    @Override  
    public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
        View view = inflater.inflate(R.layout.introview, null);
        RelativeLayout rl1 	= (RelativeLayout) view.findViewById(R.id.rl01);
        RelativeLayout rl2 	= (RelativeLayout) view.findViewById(R.id.rl02);
        TextView tvTitle1 	= (TextView) view.findViewById(R.id.textView1);
        TextView tvText1 	= (TextView) view.findViewById(R.id.textView2);
        TextView tvTitle2 	= (TextView) view.findViewById(R.id.TextView01);
        TextView tvText2 	= (TextView) view.findViewById(R.id.TextView02);
        TextView tvText3    = (TextView) view.findViewById(R.id.TextView03); 
        ImageView img	 	= (ImageView) view.findViewById(R.id.imageView1);
        switch (position) {
        case 0: //pantalla Introducción
        	rl1.setVisibility(View.GONE); 	
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	rl2.setVisibility(View.VISIBLE);
        	break;
        case 1: //pantalla Introducción
        	rl1.setVisibility(View.GONE);
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	tvText3.setText(R.string.stops);
        	rl2.setVisibility(View.VISIBLE);
            break;
        case 2: //pantalla Introducción
        	rl1.setVisibility(View.GONE);
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	tvText3.setText(R.string.stops);
        	rl2.setVisibility(View.VISIBLE);
            break;
        case 3: //pantalla Introducción
        	rl1.setVisibility(View.GONE);
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);        	
        	img.setImageResource(R.drawable.settings);
        	tvText3.setText(R.string.stops);
        	rl2.setVisibility(View.VISIBLE);
            break;
        case 4: //pantalla Introducción
        	rl1.setVisibility(View.GONE);
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	tvText3.setText(R.string.stops);
        	rl2.setVisibility(View.VISIBLE);
            break;
        case 5: //pantalla App
        	rl2.setVisibility(View.GONE);
        	tvTitle1.setText(R.string.stops);
        	tvText1.setText(R.string.stops);
        	rl1.setVisibility(View.VISIBLE);
            break;
        case 6: //pantalla App
        	rl1.setVisibility(View.GONE);
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	rl2.setVisibility(View.VISIBLE);
            break;
        case 7: //pantalla App
        	rl1.setVisibility(View.GONE);  
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
         	img.setImageResource(R.drawable.settings);
         	tvText3.setText(R.string.stops);
        	rl2.setVisibility(View.VISIBLE);
        	break;
        case 8: //pantalla App
        	rl1.setVisibility(View.GONE);  
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	tvText3.setText(R.string.stops);
        	rl2.setVisibility(View.VISIBLE);
        	break;
        case 9: // pantalla Caracterización de superficies foliares (Ejemplo)
        	rl1.setVisibility(View.GONE);
        	tvTitle2.setText(R.string.stops);
        	tvText2.setText(R.string.stops);
        	img.setImageResource(R.drawable.settings);
        	rl2.setVisibility(View.VISIBLE);
        	break;       		        	
        case 10: // pantalla Caracterización de superficies foliares (Ejemplo)
        	rl2.setVisibility(View.GONE);
        	tvTitle1.setText(R.string.stops);
        	tvText1.setText(R.string.stops);
        	rl1.setVisibility(View.VISIBLE);
        	break;  	
        case 11:  // pantalla Comentarios adicionales
        	rl2.setVisibility(View.GONE);
        	tvTitle1.setText(R.string.stops);
        	tvText1.setText(R.string.stops); 
        	rl1.setVisibility(View.VISIBLE);
            break;
        }
        ((ViewPager) collection).addView(view, 0);
        
        return view;  
    }  
  
    private Object getString(int appText2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override  
    public boolean isViewFromObject(View view, Object object) {  
         return view==((View)object);  
    }  
  
    @Override  
    public void restoreState(Parcelable arg0, ClassLoader arg1) {  
        // TODO Auto-generated method stub  
          
    }  
  
    @Override  
    public Parcelable saveState() {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    @Override  
    public void startUpdate(View arg0) {  
        // TODO Auto-generated method stub  
          
    }  
  
}  
