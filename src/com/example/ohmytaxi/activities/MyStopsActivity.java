	package com.example.ohmytaxi.activities;

	import java.util.List;

import com.example.ohmytaxi.R;
import com.example.ohmytaxi.location.Place;
import com.example.ohmytaxi.location.PlaceServices;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

	public class MyStopsActivity extends ListActivity{

		private ListView stops;

		@Override
		protected void onCreate(Bundle arg0) {
				// TODO Auto-generated method stub
				super.onCreate(arg0);
				setContentView(R.layout.activity_mystops);
				stops = (ListView) findViewById(android.R.id.list);
				new GetPlaces(this, getListView()).execute();
		}



//
class GetPlaces extends AsyncTask<Void, Void, Void>{

    private ProgressDialog dialog;
    private Context context;
    private String[] placeName;
    private String[] imageUrl;
    private ListView listView;

    public GetPlaces(Context context, ListView listView) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        dialog.dismiss();

        listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,placeName));
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setCancelable(true);
        dialog.setMessage("Loading..");
        dialog.isIndeterminate();
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // TODO Auto-generated method stub
        PlaceServices service = new PlaceServices("AIzaSyDzxflT_zoz7drQllYL_j_O3N6mWbVfZ3k");
          List<Place> findPlaces = service.findPlaces(40.398006,-3.697868,"Calle Jaime el Conquistador, 1");  // hq for hospital
                                                                                    // atm for ATM

            placeName = new String[findPlaces.size()];
            imageUrl = new String[findPlaces.size()];

          for (int i = 0; i < findPlaces.size(); i++) {

              Place placeDetail = findPlaces.get(i);
              placeDetail.getIcon();

            System.out.println(  placeDetail.getName());
            placeName[i] =placeDetail.getName();

            imageUrl[i] =placeDetail.getIcon();

        }
        return null;
    }

}

}